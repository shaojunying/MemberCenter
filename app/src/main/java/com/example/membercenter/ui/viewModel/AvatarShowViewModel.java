package com.example.membercenter.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.membercenter.data.db.entity.Member;
import com.example.membercenter.data.network.model.MemberResponse;
import com.example.membercenter.data.repository.MemberRepository;

import java.io.File;

import rx.Subscriber;

public class AvatarShowViewModel extends AndroidViewModel {

    private MemberRepository mRepository;



    public AvatarShowViewModel(@NonNull Application application) {
        super(application);
        mRepository = new MemberRepository(application);
    }

    public void uploadAvatar(Subscriber<MemberResponse> subscriber, File file){
        mRepository.uploadAvatar(subscriber,file);
    }

    public LiveData<Member> getMember(){
        return mRepository.getMember();
    }

    public void insertMember(Member member){
        mRepository.insert(member);
    }
}