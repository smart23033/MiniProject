package example.tacademy.com.miniproject.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import example.tacademy.com.miniproject.data.NetworkResult;
import example.tacademy.com.miniproject.data.User;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class MessageSendRequest extends AbstractRequest<NetworkResult<String>> {
    Request request;
    public MessageSendRequest(Context context, User user, String message) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("sendmessage")
                .build();
        RequestBody body = new FormBody.Builder()
                .add("receiver","" + user.getId())
                .add("message", message)
                .build();

        request = new Request.Builder()
                .url(url)
                .post(body)
                .tag(context)
                .build();
    }
    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<String>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
