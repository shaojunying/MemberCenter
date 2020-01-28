package com.example.membercenter.ui.memberCenter;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.membercenter.data.network.model.Member;

public class MineViewModelFactory implements ViewModelProvider.Factory {

    private final Member mMember;

    public MineViewModelFactory(Member member){
        this.mMember = member;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MineViewModel.class)) {
            return (T) new MineViewModel(mMember);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
