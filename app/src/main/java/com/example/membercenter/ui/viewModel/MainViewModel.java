package com.example.membercenter.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.membercenter.data.db.entity.Member;
import com.example.membercenter.data.repository.MemberRepository;

public class MainViewModel extends AndroidViewModel {

    private MemberRepository repository;
    private LiveData<Member> member;


    public MainViewModel(@NonNull Application application) {
        super(application);

        repository = new MemberRepository(application);
        member = repository.getMember();
    }

    public void requestMember(){
        repository.requestMember();
    }


    public LiveData<Member> getMember(){
        return member;
    }

}
