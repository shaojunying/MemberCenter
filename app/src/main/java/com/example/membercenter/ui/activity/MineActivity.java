package com.example.membercenter.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.membercenter.R;
import com.example.membercenter.Utils.StaticMethods;
import com.example.membercenter.data.db.entity.Member;
import com.example.membercenter.ui.viewModel.MineViewModel;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

public class MineActivity extends AppCompatActivity {

    SuperTextView mSuperTextView;

    MineViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        mSuperTextView = findViewById(R.id.memberProfile);

        mViewModel = ViewModelProviders.of(this).get(MineViewModel.class);

        mViewModel.getMember().observe(this, new Observer<Member>() {
            @Override
            public void onChanged(Member member) {
                updateMemberProfile(member);
            }
        });

        Context context = this;

        mSuperTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
                startActivity(intent);
            }
        });
    }


    private void updateMemberProfile(Member member) {

        mSuperTextView.setLeftTopString(member.getName());
        mSuperTextView.setLeftBottomString(member.getPhone());

        // 根据URL加载头像
        StaticMethods.showMessage(this,member.getAvatarLink(),mSuperTextView.getLeftIconIV(),R.drawable.avatar);
    }
}