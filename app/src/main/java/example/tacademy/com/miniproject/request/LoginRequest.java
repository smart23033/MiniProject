package example.tacademy.com.miniproject.request;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import example.tacademy.com.miniproject.data.NetworkResult;
import example.tacademy.com.miniproject.data.NetworkResultTemp;
import example.tacademy.com.miniproject.data.User;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Tacademy on 2016-08-09.
 */
public class LoginRequest extends AbstractRequest<NetworkResult<User>>{
    Request request;

    public LoginRequest(Context context, String email, String password, String regId) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("signin")
                .build();
        RequestBody body = new FormBody.Builder()
                .add("email",email)
                .add("password",password)
                .add("registrationId",regId)
                .build();

        request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
    }

    @Override
    public Request getRequest() {
        return request;
    }

//    @Override
//    protected NetworkResult<User> parse(ResponseBody body) throws IOException {
//        String text = body.string();
//        Gson gson = new Gson();
//        NetworkResultTemp temp = gson.fromJson(text,NetworkResultTemp.class);
//        if(temp.getCode() == 1 ){
//            Type type = new TypeToken<NetworkResult<User>>(){}.getType();
//            NetworkResult<User> result = gson.fromJson(text,type);
//            return result;
//        }else{
//            Type type = new TypeToken<NetworkResult<User>>(){}.getType();
//            NetworkResult<String> result = gson.fromJson(text,type);
//            throw new IOException(result.getResult());
//        }
//    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<User>>(){}.getType();
    }
}
