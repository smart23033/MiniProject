package example.tacademy.com.miniproject.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.tacademy.com.miniproject.R;
import example.tacademy.com.miniproject.data.NetworkResult;
import example.tacademy.com.miniproject.data.User;
import example.tacademy.com.miniproject.manager.NetworkManager;
import example.tacademy.com.miniproject.manager.NetworkRequest;
import example.tacademy.com.miniproject.manager.PropertyManager;
import example.tacademy.com.miniproject.request.LoginRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }
    @BindView(R.id.edit_email)
    EditText emailView;

    @BindView(R.id.edit_password)
    EditText passwordView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.btn_login)
    public void onLogin(View view){
        final String email = emailView.getText().toString();
        final String password = passwordView.getText().toString();
        LoginRequest request = new LoginRequest(getContext(),email,password,"1234");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<User>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {
                User user = result.getResult();
                Toast.makeText(getContext(),"user id : " + user.getId(), Toast.LENGTH_SHORT).show();
                PropertyManager.getInstance().setEmail(email);
                PropertyManager.getInstance().setPassword(password);
                PropertyManager.getInstance().setRegistrationId("1234");
                ((SimpleLoginActivity)getActivity()).moveMainActivity();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(),"error : " + errorMessage,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btn_signup)
    public void onSignUp(){
        ((SimpleLoginActivity)getActivity()).changeSignUp();
    }

}
