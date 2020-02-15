package com.example.membercenter.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.membercenter.R;
import com.example.membercenter.Utils.StaticMethods;
import com.example.membercenter.Utils.StaticVariables;
import com.example.membercenter.data.db.entity.Member;
import com.example.membercenter.ui.viewModel.DetailViewModel;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

public class DetailActivity extends AppCompatActivity {

    private RadiusImageView avatar;
    private SuperTextView name;
    private SuperTextView companyName;
    private SuperTextView address;
    private SuperTextView email;
    private SuperTextView phone;

    DetailViewModel mViewModel;

    LiveData<Member> mMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        mMember = mViewModel.getMember();

        initComponents();

        mMember.observe(this, new Observer<Member>() {
            @Override
            public void onChanged(Member member) {
                updateMemberDetail(member);
            }
        });

    }

    private void initComponents() {
        avatar = findViewById(R.id.avatar);
        name = findViewById(R.id.name);
        companyName = findViewById(R.id.companyName);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);

        Context context = this;
        // TODO 头像的点击事件

        avatar.setOnClickListener(v->{
            Intent intent = new Intent(this,AvatarShowActivity.class);
            startActivity(intent);
        });

        name.setOnClickListener(v -> ChangeActivity.start(context, StaticVariables.NAME, name.getRightString()));
        companyName.setOnClickListener(v -> ChangeActivity.start(context, StaticVariables.COMPANY_NAME, companyName.getRightString()));
        address.setOnClickListener(v -> ChangeActivity.start(context, StaticVariables.ADDRESS, address.getRightString()));
        email.setOnClickListener(v -> ChangeActivity.start(context, StaticVariables.EMAIL, email.getRightString()));
        phone.setOnClickListener(v -> ChangeActivity.start(context, StaticVariables.PHONE, phone.getRightString()));
    }

    private void updateMemberDetail(Member member) {
        StaticMethods.showMessage(this, member.getAvatarLink(), avatar, R.drawable.avatar);
        name.setRightString(member.getName());
        companyName.setRightString(member.getCompanyName());
        address.setRightString(member.getAddress());
        email.setRightString(member.getEmail());
        phone.setRightString(member.getPhone());
    }


}
