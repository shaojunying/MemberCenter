package com.example.membercenter.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.example.membercenter.R;
import com.example.membercenter.data.DataManager;
import com.example.membercenter.ui.base.BaseActivity;
import com.example.membercenter.ui.memberCenter.MineActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainViewModel> {


    @NonNull
    @Override
    protected MainViewModel createViewModel() {
        MainViewModelFactory factory = new MainViewModelFactory(DataManager.getInstance().getMemberService());
        return ViewModelProviders.of(this,factory).get(MainViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Context context = this;

        viewModel.requestMember();

        viewModel.getMemberMutableLiveData().observe(this, member -> {
            String message = "获取用户信息成功";
            if (member == null){
                message = "获取用户信息失败";
            }
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
        });

    }

    @OnClick(R.id.jump_button)
    void jump(){
        Intent intent = new Intent(this, MineActivity.class);
        startActivity(intent);
    }
}
