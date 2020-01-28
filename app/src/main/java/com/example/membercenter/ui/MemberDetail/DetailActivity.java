package com.example.membercenter.ui.MemberDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.membercenter.R;
import com.example.membercenter.Utils.StaticMethods;
import com.example.membercenter.Utils.StaticVariables;
import com.example.membercenter.data.network.model.Member;
import com.example.membercenter.ui.base.BaseActivity;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

public class DetailActivity extends BaseActivity<DetailViewModel> {

    private RadiusImageView avatar;
    private SuperTextView name;
    private SuperTextView companyName;
    private SuperTextView address;
    private SuperTextView email;
    private SuperTextView phone;

    @NonNull
    @Override
    protected DetailViewModel createViewModel() {
        Member member = getIntent().getParcelableExtra(StaticVariables.MEMBER);
        DetailViewModelFactory factory = new DetailViewModelFactory(member);
        return ViewModelProviders.of(this,factory).get(DetailViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        viewModel.loadMember();

        initComponents();

        viewModel.getMemberMutableLiveData().observe(this, new Observer<Member>() {
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
    }

    private void updateMemberDetail(Member member) {
        StaticMethods.showMessage(this,member.getAvatarLink(),avatar,R.drawable.avatar);
        name.setRightString(member.getName());
        companyName.setRightString(member.getCompanyName());
        address.setRightString(member.getAddress());
        email.setRightString(member.getEmail());
        phone.setRightString(member.getPhone());
    }

    public static void start(Context context, Member member){
        Intent intent = new Intent(context,DetailActivity.class);
        intent.putExtra(StaticVariables.MEMBER,member);
        context.startActivity(intent);
    }

}
