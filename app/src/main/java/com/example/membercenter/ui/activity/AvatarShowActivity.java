package com.example.membercenter.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.membercenter.R;
import com.example.membercenter.data.network.model.MemberResponse;
import com.example.membercenter.ui.viewModel.AvatarShowViewModel;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;

import java.io.File;

import rx.Subscriber;

public class AvatarShowActivity extends AppCompatActivity {

    private final int TAKE_AVATAR_GALLERY_REQUEST = 1;
    AvatarShowViewModel mViewModel;


    ImageView mImageView;
    SuperButton mSuperButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_show);
        mViewModel = ViewModelProviders.of(this).get(AvatarShowViewModel.class);

        // TODO 需要初始显示原照片

        mImageView = findViewById(R.id.avatar_show);
        mSuperButton = findViewById(R.id.select_from_gallery);

        mSuperButton.setOnClickListener(v -> {
            String strAvatarPrompt = "选择一个照片作为头像";
            Intent pickPhoto = new Intent(Intent.ACTION_PICK);
            pickPhoto.setType("image/*");
            startActivityForResult(Intent.createChooser(pickPhoto, strAvatarPrompt), TAKE_AVATAR_GALLERY_REQUEST);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_AVATAR_GALLERY_REQUEST && resultCode == RESULT_OK && null != data) {
            // TODO 此处有异常
            //  W/System.err: java.io.FileNotFoundException:
            //  /-1/1/content:/media/external/images/media/31/ORIGINAL/NONE/2138087137:
            //  open failed: ENOENT (No such file or directory)
            Uri selectedImage = data.getData();
            if (selectedImage.getPath() == null){
                return;
            }
            mImageView.setImageURI(selectedImage);
            File file = new File(selectedImage.getPath());
            mViewModel.uploadAvatar(mSubscriber,file);
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
            Toast.makeText(AvatarShowActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
        }
    };


}
