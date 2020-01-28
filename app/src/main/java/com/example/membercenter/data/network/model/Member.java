package com.example.membercenter.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class Member  implements Parcelable {

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

    protected Member(Parcel in){
        avatarLink = in.readString();
        name = in.readString();
        companyName = in.readString();
        address = in.readString();
        email = in.readString();
        phone = in.readString();
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public String getName() {
        return name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public static final Creator<Member> CREATOR = new Creator<Member>() {
        @Override
        public Member createFromParcel(Parcel in) {
            return new Member(in);
        }

        @Override
        public Member[] newArray(int size) {
            return new Member[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(avatarLink);
        dest.writeString(name);
        dest.writeString(companyName);
        dest.writeString(address);
        dest.writeString(email);
        dest.writeString(phone);
    }
}
