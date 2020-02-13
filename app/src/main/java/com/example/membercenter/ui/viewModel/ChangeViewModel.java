package com.example.membercenter.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.membercenter.data.db.entity.Member;
import com.example.membercenter.data.network.model.MemberResponse;
import com.example.membercenter.data.network.model.PutMemberParam;
import com.example.membercenter.data.network.services.MemberService;
import com.example.membercenter.data.repository.MemberRepository;

import retrofit2.Call;
import retrofit2.Response;

public class ChangeViewModel extends AndroidViewModel {

    private MemberService mService;

    private MemberRepository mRepository;

    private ChangeInterface mChangeInterface;

    public ChangeViewModel(@NonNull Application application) {
        super(application);
        mService = MemberService.getInstance();
        mRepository = new MemberRepository(application);
    }

    public void setChangeInterface(ChangeInterface changeInterface) {
        mChangeInterface = changeInterface;
    }

    public void putMember(String changeType, String originalContent){
        PutMemberParam param = new PutMemberParam(changeType,originalContent);
        mService.getMemberApi().putMember(param).enqueue(new PutMemberCallBack());
    }

    private class PutMemberCallBack implements retrofit2.Callback<com.example.membercenter.data.network.model.MemberResponse> {
        @Override
        public void onResponse(Call<MemberResponse> call, Response<MemberResponse> response) {
            MemberResponse memberResponse = response.body();

            if (memberResponse == null || !memberResponse.isSuccessful()){
                mChangeInterface.onFailure();
                return;
            }
            // 获取用户信息成功
            Member member = memberResponse.getMember();
            mRepository.insert(member);
        }

        @Override
        public void onFailure(Call<MemberResponse> call, Throwable t) {
            // 获取用户信息失败
            t.printStackTrace();
            mChangeInterface.onFailure();
        }
    }

    public static interface ChangeInterface{
        void onSuccessful();
        void onFailure();
    }

}
