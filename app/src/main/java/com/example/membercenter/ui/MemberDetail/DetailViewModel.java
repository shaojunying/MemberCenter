package com.example.membercenter.ui.MemberDetail;

import androidx.lifecycle.MutableLiveData;

import com.example.membercenter.data.network.model.Member;
import com.example.membercenter.ui.base.BaseViewModel;

public class DetailViewModel extends BaseViewModel {

    private Member mMember;

    private MutableLiveData<Member> mMemberMutableLiveData;

    public DetailViewModel(Member member){
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
