package com.example.membercenter.ui.MemberDetail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.membercenter.data.network.model.Member;

public class DetailViewModelFactory implements ViewModelProvider.Factory {

    private final Member mMember;

    public DetailViewModelFactory(Member member){
        this.mMember = member;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailViewModel.class)) {
            return (T) new DetailViewModel(mMember);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}