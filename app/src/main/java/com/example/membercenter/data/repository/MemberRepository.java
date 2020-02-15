package com.example.membercenter.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.membercenter.Utils.StaticVariables;
import com.example.membercenter.data.db.dao.MemberDao;
import com.example.membercenter.data.db.db.MemberDatabase;
import com.example.membercenter.data.db.entity.Member;
import com.example.membercenter.data.network.model.MemberResponse;
import com.example.membercenter.data.network.model.PutMemberParam;
import com.example.membercenter.data.network.services.MemberService;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MemberRepository {

    private MemberDao mMemberDao;
    private LiveData<Member> mMember;
    private MemberService mMemberService;

    public MemberRepository(Application application){
        MemberDatabase memberDatabase = MemberDatabase.getInstance(application);
        mMemberDao = memberDatabase.memberDao();
        mMember = mMemberDao.select(StaticVariables.memberId);
        mMemberService = MemberService.getInstance();
    }

    public void insert(Member member){
        new InsertMemberAsyncTask(mMemberDao).execute(member);
    }

    public void requestMember(Subscriber<MemberResponse> subscriber){

        mMemberService.getMemberApi().getMember()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void uploadAvatar(Subscriber<MemberResponse> subscriber, File file){

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);

        mMemberService.getMemberApi().uploadAvatar(requestBody)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public void putMember(Subscriber<MemberResponse> subscriber,String changeType,String originalContent){
        PutMemberParam param = new PutMemberParam(changeType,originalContent);

        mMemberService.getMemberApi().putMember(param)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public LiveData<Member> getMember() {
        return mMember;
    }

    private static class InsertMemberAsyncTask extends AsyncTask<Member,Void,Void>{
        private MemberDao mMemberDao;

        InsertMemberAsyncTask(MemberDao memberDao) {
            mMemberDao = memberDao;
        }

        @Override
        protected Void doInBackground(Member... members) {
            mMemberDao.insert(members[0]);
            return null;
        }
    }


}
