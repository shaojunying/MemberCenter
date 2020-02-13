package com.example.membercenter.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.membercenter.R;
import com.example.membercenter.Utils.StaticVariables;
import com.example.membercenter.ui.viewModel.ChangeViewModel;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

public class ChangeActivity extends AppCompatActivity implements ChangeViewModel.ChangeInterface{

    ChangeViewModel mViewModel;

    SuperTextView editContent;
    SuperButton submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        editContent = findViewById(R.id.edit_content);
        submitButton = findViewById(R.id.submit_button);

        mViewModel = ViewModelProviders.of(this).get(ChangeViewModel.class);
        mViewModel.setChangeInterface(this);

        String changeType = getIntent().getStringExtra(StaticVariables.CHANGE_TYPE);
        String originalContent = getIntent().getStringExtra(StaticVariables.ORIGINAL_CONTENT);

        Log.d("shao",originalContent);
        editContent.setCenterEditString(originalContent);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.putMember(changeType,originalContent);
            }
        });
    }

    static void start(Context context,String changeType,String oriContent){
        Intent intent = new Intent(context,ChangeActivity.class);
        intent.putExtra(StaticVariables.CHANGE_TYPE,changeType);
        intent.putExtra(StaticVariables.ORIGINAL_CONTENT,oriContent);
        context.startActivity(intent);
    }

    @Override
    public void onSuccessful() {

    }

    @Override
    public void onFailure() {
        Toast.makeText(this,"网络连接失败，请重试",Toast.LENGTH_SHORT).show();
    }
}
