package com.example.membercenter.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.membercenter.data.network.model.Member;
import com.example.membercenter.data.network.model.MemberResponse;
import com.example.membercenter.data.network.services.MemberService;
import com.example.membercenter.ui.base.BaseViewModel;

import retrofit2.Call;
import retrofit2.Response;

public class MainViewModel extends BaseViewModel {

    private MutableLiveData<Member> mMemberMutableLiveData;

    private MemberService mMemberService;

    MainViewModel(MemberService memberService){
        mMemberMutableLiveData = new MutableLiveData<>();
        mMemberService = memberService;
    }

    void requestMember(){
        mMemberService.getMemberApi().getMember().enqueue(new MemberCallBack());
    }

    public MutableLiveData<Member> getMemberMutableLiveData() {
        return mMemberMutableLiveData;
    }

    private class MemberCallBack implements retrofit2.Callback<com.example.membercenter.data.network.model.MemberResponse> {
        @Override
        public void onResponse(Call<MemberResponse> call, Response<MemberResponse> response) {
            MemberResponse memberResponse = response.body();

            if (memberResponse == null || !memberResponse.isSuccessful()){
                mMemberMutableLiveData.setValue(null);
                return;
            }
            // 获取用户信息成功
            Member member = memberResponse.getMember();
            mMemberMutableLiveData.postValue(member);
        }

        @Override
        public void onFailure(Call<MemberResponse> call, Throwable t) {
            // 获取用户信息失败
            t.printStackTrace();
            mMemberMutableLiveData.postValue(null);
        }
    }

}
