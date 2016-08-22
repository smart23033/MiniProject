package example.tacademy.com.miniproject.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import example.tacademy.com.miniproject.data.FacebookUser;
import example.tacademy.com.miniproject.data.NetworkResult;
import example.tacademy.com.miniproject.data.User;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-08-22.
 */
public class FacebookLoginRequest extends AbstractRequest<NetworkResult<Object>> {

    Request mRequest;
    public FacebookLoginRequest(Context context, String token, String regId) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("facebooksignin")
                .build();
        RequestBody body = new FormBody.Builder()
                .add("access_token", token)
                .add("registrationId", regId)
                .build();

        mRequest = new Request.Builder()
                .url(url)
                .post(body)
                .tag(context)
                .build();
    }
    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<User>>(){}.getType();
    }

    @Override
    protected Type getType(int code) {
        if (code == 3) {
            return new TypeToken<NetworkResult<FacebookUser>>(){}.getType();
        }
        return null;
    }

    @Override
    public Request getRequest() {
        return mRequest;
    }
}
