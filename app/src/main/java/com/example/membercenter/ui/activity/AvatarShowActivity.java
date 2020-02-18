package com.example.membercenter.ui.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.membercenter.R;
import com.example.membercenter.Utils.StaticMethods;
import com.example.membercenter.data.network.model.MemberResponse;
import com.example.membercenter.ui.viewModel.AvatarShowViewModel;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;

import java.io.File;
import java.io.IOException;

import rx.Subscriber;

public class AvatarShowActivity extends AppCompatActivity {

    AvatarShowViewModel mViewModel;


    private SuperButton startCameraButton;
    private SuperButton choiceFromAlbumButton;
    private ImageView pictureImageView;

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 0; // 相机权限处理返回码
    private static final int WRITE_SDCARD_PERMISSION_REQUEST_CODE = 1; // 读写存储卡权限处理返回码

    private static final int TAKE_PHOTO_REQUEST_CODE = 2; // 拍照返回的request code
    private static final int CHOICE_FROM_ALBUM_REQUEST_CODE = 3; //从相册选择照片的request code

    private Uri photoUri = null;
    private File file = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_show);
        mViewModel = ViewModelProviders.of(this).get(AvatarShowViewModel.class);

        mViewModel.getMember().observe(this, member -> StaticMethods.showMessage(AvatarShowActivity.this, member.getAvatarLink(), pictureImageView, R.drawable.avatar));

        startCameraButton = findViewById(R.id.startCameraButton);
        choiceFromAlbumButton = findViewById(R.id.choiceFromAlbumButton);
        pictureImageView = findViewById(R.id.pictureImage);


        startCameraButton.setOnClickListener(clickListener);
        choiceFromAlbumButton.setOnClickListener(clickListener);

        /*
         * 读取存储卡内容权限
         * */
        if (ContextCompat.checkSelfPermission(AvatarShowActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AvatarShowActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_SDCARD_PERMISSION_REQUEST_CODE);
        }

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == startCameraButton) {
                // 调用相机拍照
                if (ContextCompat.checkSelfPermission(AvatarShowActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AvatarShowActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
                } else {
                    startCamera();
                }

            } else if (view == choiceFromAlbumButton) {
                // 从相册获取
                choiceFromAlbum();
            }
        }
    };

    /*
     * 启动相机
     * */
    private void startCamera() {
        file = new File(getExternalCacheDir(), "image.jpg");
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT >= 24) {
            photoUri = FileProvider.getUriForFile(this, "com.hongzhi.provider", file);
        } else {
            photoUri = Uri.fromFile(file);
        }
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 设置拍出照片的输出目录
        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(takePhotoIntent, TAKE_PHOTO_REQUEST_CODE);
    }

    /*
     * 从相机选择
     * */
    private void choiceFromAlbum() {
        Intent choiceFromAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
        // 设置数据类型为图片类型
        choiceFromAlbumIntent.setType("image/*");
        startActivityForResult(choiceFromAlbumIntent, CHOICE_FROM_ALBUM_REQUEST_CODE);
    }

    /*
     * 用户权限授予结果处理
     * */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSION_REQUEST_CODE:
                // 调用相机拍照
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startCamera();
                } else {
                    Toast.makeText(this, "拍照权限被拒绝", Toast.LENGTH_SHORT).show();
                }
                break;
            case WRITE_SDCARD_PERMISSION_REQUEST_CODE:
                // 读写存储卡权限
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, "读写存储卡内容权限被拒绝", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_PHOTO_REQUEST_CODE:
                    mViewModel.uploadAvatar(mSubscriber, file);
                    break;
                case CHOICE_FROM_ALBUM_REQUEST_CODE:
                    if (data != null && data.getData() != null && data.getData().getPath() != null) {
                        Log.d("shao", "获取成功");
                        // Todo 将相册中的文件上传
//                        mViewModel.uploadAvatar(mSubscriber, imageFile);
                    } else {
                        Log.d("shao", "获取失败");
                    }
                    break;
            }
        }
    }

    private Subscriber<MemberResponse> mSubscriber = new Subscriber<MemberResponse>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onNext(MemberResponse memberResponse) {
            Log.d("shao", "成功了");
            Toast.makeText(AvatarShowActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
            mViewModel.insertMember(memberResponse.getMember());
        }
    };
}
