package com.example.membercenter.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.membercenter.data.db.entity.Member;
import com.example.membercenter.data.repository.MemberRepository;

public class DetailViewModel extends AndroidViewModel {

    private MemberRepository mRepository;

    private LiveData<Member> mMember;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        mRepository = new MemberRepository(application);
        mMember = mRepository.getMember();
    }

    public LiveData<Member> getMember(){
        return mMember;
    }
}
