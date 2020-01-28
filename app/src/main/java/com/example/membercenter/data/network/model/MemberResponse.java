package com.example.membercenter.data.network.model;

import com.google.gson.annotations.Expose;

public class MemberResponse {

    @Expose
    private boolean isSuccessful;

    @Expose
    private Member member;

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public Member getMember() {
        return member;
    }
}
