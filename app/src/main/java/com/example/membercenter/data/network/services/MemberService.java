package com.example.membercenter.data.network.services;

import com.example.membercenter.Utils.StaticVariables;
import com.example.membercenter.data.network.model.MemberResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

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
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(StaticVariables.URL).build();
        mMemberApi = retrofit.create(MemberApi.class);
    }

    public MemberApi getMemberApi() {
        return mMemberApi;
    }

    public interface MemberApi {

        @GET("user")
        Call<MemberResponse> getMember();
    }
}