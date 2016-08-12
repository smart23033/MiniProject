package example.tacademy.com.miniproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.tacademy.com.miniproject.data.ContentData;
import example.tacademy.com.miniproject.data.NetworkResult;
import example.tacademy.com.miniproject.manager.NetworkManager;
import example.tacademy.com.miniproject.manager.NetworkRequest;
import example.tacademy.com.miniproject.request.UploadRequest;

public class ContentAddActivity extends AppCompatActivity {

    @BindView(R.id.edit_message)
    EditText messageView;
    @BindView(R.id.image_picture)
    ImageView pictureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_add);
        ButterKnife.bind(this);
    }

    private static final int RC_GET_IMAGE = 1;

    @OnClick(R.id.btn_get_image)
    public void onGetImageClick(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent,RC_GET_IMAGE);
    }

    File uploadFile = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_GET_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                Uri uri = data.getData();
                Cursor c = getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA},null,null,null);
                if(c.moveToNext()){
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    uploadFile = new File(path);
                    Glide.with(this)
                            .load(uploadFile)
                            .into(pictureView);
                }
            }
        }
    }

    @OnClick(R.id.btn_upload)
    public void onUpload(){
        String content = messageView.getText().toString();
        if(!TextUtils.isEmpty(content) && uploadFile != null){
            UploadRequest request = new UploadRequest(this,content,uploadFile);
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<ContentData>>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResult<ContentData>> request, NetworkResult<ContentData> result) {
                    Toast.makeText(ContentAddActivity.this,"success",Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFail(NetworkRequest<NetworkResult<ContentData>> request, int errorCode, String errorMessage, Throwable e) {
                    Toast.makeText(ContentAddActivity.this,"fail",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
