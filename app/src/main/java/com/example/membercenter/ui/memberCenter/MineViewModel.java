package com.example.membercenter.ui.memberCenter;

import androidx.lifecycle.MutableLiveData;

import com.example.membercenter.data.network.model.Member;
import com.example.membercenter.ui.base.BaseViewModel;

public class MineViewModel extends BaseViewModel {

    private Member mMember;
    private MutableLiveData<Member> mMemberMutableLiveData;

    public MineViewModel(Member member){
        mMemberMutableLiveData = new MutableLiveData<>();
        mMember = member;
    }

    public void loadMember(){
        mMemberMutableLiveData.postValue(mMember);
    }

    public MutableLiveData<Member> getMemberMutableLiveData(){
        return mMemberMutableLiveData;
    }

}