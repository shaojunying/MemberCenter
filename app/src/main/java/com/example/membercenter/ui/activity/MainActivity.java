package com.example.membercenter.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.membercenter.R;
import com.example.membercenter.ui.viewModel.MainViewModel;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        ButterKnife.bind(this);


        Context context = this;
        viewModel.getMember().observe(this,member -> {
            Toast.makeText(context,"获取用户信息成功",Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.requestMember();
    }

    @OnClick(R.id.jump_button)
    void jump(){
        Intent intent = new Intent(this,MineActivity.class);
        startActivity(intent);
    }
}