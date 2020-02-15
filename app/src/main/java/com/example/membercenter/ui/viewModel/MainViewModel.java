package com.example.membercenter.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.membercenter.data.db.entity.Member;
import com.example.membercenter.data.network.model.MemberResponse;
import com.example.membercenter.data.repository.MemberRepository;

import rx.Subscriber;

public class MainViewModel extends AndroidViewModel {

    private MemberRepository repository;
    private LiveData<Member> member;


    public MainViewModel(@NonNull Application application) {
        super(application);

        repository = new MemberRepository(application);
        member = repository.getMember();
    }

    public void requestMember(){
        repository.requestMember(sSubscriber);
    }


    private Subscriber<MemberResponse> sSubscriber = new Subscriber<MemberResponse>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onNext(MemberResponse memberResponse) {
            if (memberResponse ==null || !memberResponse.isSuccessful()){
                return;
            }
            repository.insert(memberResponse.getMember());
        }
    };


    public LiveData<Member> getMember(){
        return member;
    }

}
