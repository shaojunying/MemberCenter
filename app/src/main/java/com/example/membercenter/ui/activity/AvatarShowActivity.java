package com.example.membercenter.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.membercenter.R;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;

public class AvatarShowActivity extends AppCompatActivity {

    private final int TAKE_AVATAR_GALLERY_REQUEST = 1;


    ImageView mImageView;
    SuperButton mSuperButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_show);

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
            Uri selectedImage = data.getData();
            mImageView.setImageURI(selectedImage);
        }
    }


}
