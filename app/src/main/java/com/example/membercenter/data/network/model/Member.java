package com.example.membercenter.data.network.model;

import com.google.gson.annotations.Expose;

public class Member {

    /*
     * 头像地址链接
     * */
    @Expose
    private String avatarLink;


    /*
     * 昵称
     * */
    @Expose
    private String name;

    /*
     * 公司名称
     * */
    @Expose
    private String companyName;

    /*
     * 公司地址
     * */
    @Expose
    private String address;

    /*
     * 公司邮箱
     * */
    @Expose
    private String email;

    /*
     * 公司电话
     * */
    @Expose
    private String phone;


}
