package example.tacademy.com.miniproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.tacademy.com.miniproject.data.ContentData;

/**
 * Created by Tacademy on 2016-08-12.
 */
public class ContentViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text_content)
    TextView contentView;
    @BindView(R.id.image_picture)
    ImageView pictureView;

    public ContentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setContent(ContentData content){
        contentView.setText(content.getContent());
        Glide.with(pictureView.getContext())
                .load(content.getImageUrl())
                .into(pictureView);
    }
}
