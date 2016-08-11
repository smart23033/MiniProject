package example.tacademy.com.miniproject.request;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import example.tacademy.com.miniproject.data.NetworkResult;
import example.tacademy.com.miniproject.data.NetworkResultTemp;
import example.tacademy.com.miniproject.data.User;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Created by Tacademy on 2016-08-09.
 */
public class FriendListRequest extends AbstractRequest<NetworkResult<List<User>>> {
   Request request;

    public FriendListRequest(Context context) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("friendlist")
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    public Request getRequest() {
        return request;
    }


    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<User>>>(){}.getType();
    }
}
