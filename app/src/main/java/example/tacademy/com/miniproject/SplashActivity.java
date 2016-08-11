package example.tacademy.com.miniproject;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import example.tacademy.com.miniproject.data.NetworkResult;
import example.tacademy.com.miniproject.data.User;
import example.tacademy.com.miniproject.login.SimpleLoginActivity;
import example.tacademy.com.miniproject.manager.NetworkManager;
import example.tacademy.com.miniproject.manager.NetworkRequest;
import example.tacademy.com.miniproject.manager.PropertyManager;
import example.tacademy.com.miniproject.request.LoginRequest;
import example.tacademy.com.miniproject.request.ProfileRequest;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("SplashActivity","onCreate");
        setContentView(R.layout.activity_splash);

        ProfileRequest request = new ProfileRequest(this);

        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<User>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {
                Log.i("SplashActivity","onSuccess");
                moveMainActivity();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                Log.i("SplashActivity","onFail");
                if(errorCode == -1){
                    if(errorMessage.equals("not login")){
                        loginSharedPreference();
                        return;
                    }
                }
                moveLoginActivity();
            }
        });

    }

    private void loginSharedPreference(){
        if(isAutoLogin()){
            processAutoLogin();
        }else{
            moveLoginActivity();
        }
    }

    private boolean isAutoLogin(){
        String email = PropertyManager.getInstance().getEmail();
        return !TextUtils.isEmpty(email);
    }

    private void processAutoLogin(){
        String email = PropertyManager.getInstance().getEmail();
        if(!TextUtils.isEmpty(email)){
            String password = PropertyManager.getInstance().getPassword();
            String regid = PropertyManager.getInstance().getRegistrationId();
            LoginRequest request = new LoginRequest(this, email, password, regid);
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<User>>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {
                    moveMainActivity();
                }

                @Override
                public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                    moveLoginActivity();
                }
            });
        }
    }

    private void moveMainActivity(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void moveLoginActivity(){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, SimpleLoginActivity.class));
                finish();
            }
        },1000);
    }

    Handler mHandler = new Handler(Looper.getMainLooper());
}
