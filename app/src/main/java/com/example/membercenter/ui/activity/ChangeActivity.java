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
import com.example.membercenter.data.db.entity.Member;
import com.example.membercenter.data.network.model.MemberResponse;
import com.example.membercenter.ui.viewModel.ChangeViewModel;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import retrofit2.Response;
import rx.Subscriber;

public class ChangeActivity extends AppCompatActivity{

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

        String changeType = getIntent().getStringExtra(StaticVariables.CHANGE_TYPE);
        String originalContent = getIntent().getStringExtra(StaticVariables.ORIGINAL_CONTENT);

        editContent.setCenterEditString(originalContent);

        submitButton.setOnClickListener(v -> mViewModel.putMember(mSubscriber,changeType,originalContent));
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
            if (memberResponse == null || !memberResponse.isSuccessful()){
                Toast.makeText(ChangeActivity.this,"网络连接失败，请重试",Toast.LENGTH_SHORT).show();
                return;
            }
            // 获取用户信息成功
            Member member = memberResponse.getMember();
            mViewModel.insert(member);
        }
    };

    static void start(Context context,String changeType,String oriContent){
        Intent intent = new Intent(context,ChangeActivity.class);
        intent.putExtra(StaticVariables.CHANGE_TYPE,changeType);
        intent.putExtra(StaticVariables.ORIGINAL_CONTENT,oriContent);
        context.startActivity(intent);
    }
}
