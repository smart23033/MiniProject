package example.tacademy.com.miniproject.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import example.tacademy.com.miniproject.Utils;
import example.tacademy.com.miniproject.data.ChatMessage;
import example.tacademy.com.miniproject.data.NetworkResult;
import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Administrator on 2016-08-23.
 */
public class MessageListRequest extends AbstractRequest<NetworkResult<List<ChatMessage>>> {
    Request mRequest;
    public MessageListRequest(Context context, Date date) {
        String dateString = Utils.convertTimeToString(date);
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("messagelist")
                .addQueryParameter("lastDate",dateString)
                .build();
        mRequest = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }
    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<ChatMessage>>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return mRequest;
    }

}
