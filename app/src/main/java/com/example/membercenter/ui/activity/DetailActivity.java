package com.example.membercenter.ui.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.membercenter.R;
import com.example.membercenter.Utils.StaticMethods;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);

        initComponents();

        mViewModel.getMember().observe(this, new Observer<Member>() {
            @Override
            public void onChanged(Member member) {
                updateMemberDetail(member);
            }
        });

    }

    private void initComponents(){
        avatar = findViewById(R.id.avatar);
        name = findViewById(R.id.name);
        companyName = findViewById(R.id.companyName);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);

//        Context context = this;

//        name.setOnClickListener(v -> ChangeActivity.start(context, StaticVariables.AttributeType.name));
//        companyName.setOnClickListener(v -> ChangeActivity.start(context, StaticVariables.AttributeType.companyName));
//        address.setOnClickListener(v -> ChangeActivity.start(context, StaticVariables.AttributeType.address));
//        email.setOnClickListener(v -> ChangeActivity.start(context, StaticVariables.AttributeType.email));
//        phone.setOnClickListener(v -> ChangeActivity.start(context, StaticVariables.AttributeType.phone));
    }

    private void updateMemberDetail(Member member) {
        StaticMethods.showMessage(this,member.getAvatarLink(),avatar,R.drawable.avatar);
        name.setRightString(member.getName());
        companyName.setRightString(member.getCompanyName());
        address.setRightString(member.getAddress());
        email.setRightString(member.getEmail());
        phone.setRightString(member.getPhone());
    }


}
