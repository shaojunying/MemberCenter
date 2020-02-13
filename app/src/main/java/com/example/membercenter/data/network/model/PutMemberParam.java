package com.example.membercenter.data.network.model;

public class PutMemberParam {

    private String change_type;

    private String original_content;

    public PutMemberParam(String change_type, String original_content) {
        this.change_type = change_type;
        this.original_content = original_content;
    }

    public String getChange_type() {
        return change_type;
    }

    public String getOriginal_content() {
        return original_content;
    }
}
