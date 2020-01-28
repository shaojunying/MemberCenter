package com.example.membercenter.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.membercenter.data.network.services.MemberService;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    private final MemberService mMemberService;

    public MainViewModelFactory(MemberService memberService){
        this.mMemberService = memberService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(mMemberService);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
