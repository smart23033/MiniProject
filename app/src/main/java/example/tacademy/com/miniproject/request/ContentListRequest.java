package example.tacademy.com.miniproject.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import example.tacademy.com.miniproject.data.ContentData;
import example.tacademy.com.miniproject.data.NetworkResult;
import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-12.
 */
public class ContentListRequest extends AbstractRequest<NetworkResult<List<ContentData>>> {
    Request request;
    public ContentListRequest(Context context){
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("contents")
                .build();
        request = new Request.Builder()
                .url(url)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<ContentData>>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
