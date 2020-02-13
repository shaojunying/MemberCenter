package com.example.membercenter.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.membercenter.Utils.StaticVariables;
import com.example.membercenter.data.db.dao.MemberDao;
import com.example.membercenter.data.db.db.MemberDatabase;
import com.example.membercenter.data.db.entity.Member;
import com.example.membercenter.data.network.model.MemberResponse;
import com.example.membercenter.data.network.services.MemberService;

import retrofit2.Call;
import retrofit2.Response;

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

    public void requestMember(){
        mMemberService.getMemberApi().getMember().enqueue(new MemberCallBack());
    }


//    public void putMember(String changeType,String originalContent){
//        PutMemberParam param = new PutMemberParam(changeType,originalContent);
//        mMemberService.getMemberApi().putMember(param).enqueue(new PutMemberCallBack());
//    }

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

    public class MemberCallBack implements retrofit2.Callback<MemberResponse> {
        @Override
        public void onResponse(Call<MemberResponse> call, Response<MemberResponse> response) {
            MemberResponse memberResponse = response.body();

            if (memberResponse == null || !memberResponse.isSuccessful()){
                // TODO 获取信息失败的处理
                return;
            }
            // 获取用户信息成功
            Member member = memberResponse.getMember();

            insert(member);
        }

        @Override
        public void onFailure(Call<MemberResponse> call, Throwable t) {
            // 获取用户信息失败
            t.printStackTrace();
            // TODO 联网失败的处理
        }
    }
}
