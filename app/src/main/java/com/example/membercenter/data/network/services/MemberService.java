package com.example.membercenter.data.network.services;

import com.example.membercenter.Utils.StaticVariables;
import com.example.membercenter.data.network.model.MemberResponse;
import com.example.membercenter.data.network.model.PutMemberParam;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import rx.Observable;

public class MemberService {

    private MemberApi mMemberApi;

    private static MemberService instance;

    public static MemberService getInstance(){
        if (instance == null){
            instance = new MemberService();
        }
        return instance;
    }

    private MemberService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StaticVariables.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mMemberApi = retrofit.create(MemberApi.class);
    }

    public MemberApi getMemberApi() {
        return mMemberApi;
    }

    public interface MemberApi {

        @GET("user")
        Observable<MemberResponse> getMember();

        @PUT("user")
        Observable<MemberResponse> putMember(@Body PutMemberParam param);

        @PUT("avatar")
        Observable<MemberResponse> uploadAvatar(@Body RequestBody requestBody);
    }
}