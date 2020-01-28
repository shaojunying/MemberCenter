package com.example.membercenter.ui.memberCenter;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.membercenter.R;
import com.example.membercenter.Utils.StaticVariables;
import com.example.membercenter.data.DataManager;
import com.example.membercenter.data.network.model.Member;
import com.example.membercenter.ui.base.BaseActivity;
import com.example.membercenter.ui.main.MainViewModel;
import com.example.membercenter.ui.main.MainViewModelFactory;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;

public class MineActivity extends BaseActivity<MineViewModel> {

    SuperTextView mSuperTextView;

    @NonNull
    @Override
    protected MineViewModel createViewModel() {
        Member member = getIntent().getParcelableExtra(StaticVariables.MEMBER);
        MineViewModelFactory factory = new MineViewModelFactory(member);
        return ViewModelProviders.of(this,factory).get(MineViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        mSuperTextView = findViewById(R.id.memberProfile);

        viewModel.loadMember();

        viewModel.getMemberMutableLiveData().observe(this, new Observer<Member>() {
            @Override
            public void onChanged(Member member) {
                updateMemberProfile(member);
            }
        });
    }

    public static void start(Context context,Member member){
        Intent intent = new Intent(context,MineActivity.class);
        intent.putExtra(StaticVariables.MEMBER,member);
        context.startActivity(intent);
    }

    private void updateMemberProfile(Member member) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        mSuperTextView.setLeftTopString(member.getName());
        mSuperTextView.setLeftBottomString(member.getPhone());

        Glide.with(this)
                .load(member.getAvatarLink())
                .apply(options)
                .into(mSuperTextView.getLeftIconIV());
    }
}