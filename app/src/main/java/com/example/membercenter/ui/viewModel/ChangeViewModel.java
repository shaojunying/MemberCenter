package com.example.membercenter.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.membercenter.data.db.entity.Member;
import com.example.membercenter.data.network.model.MemberResponse;
import com.example.membercenter.data.repository.MemberRepository;

import rx.Subscriber;

public class ChangeViewModel extends AndroidViewModel {

    private MemberRepository mRepository;

    public ChangeViewModel(@NonNull Application application) {
        super(application);
        mRepository = new MemberRepository(application);
    }

    public void putMember(Subscriber<MemberResponse> subscriber,String changeType, String originalContent){
        mRepository.putMember(subscriber,changeType,originalContent);
    }

    public void insert(Member member){
        mRepository.insert(member);
    }

}
