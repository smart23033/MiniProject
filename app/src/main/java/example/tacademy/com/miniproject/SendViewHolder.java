package example.tacademy.com.miniproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-11.
 */
public class SendViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.text_message)
    TextView messageView;
    public SendViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setMessage(String message) {
        messageView.setText(message);
    }

}
