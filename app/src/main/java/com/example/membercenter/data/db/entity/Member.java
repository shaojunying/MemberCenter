package com.example.membercenter.data.db.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.membercenter.Utils.StaticVariables;
import com.google.gson.annotations.Expose;

@Entity
public class Member implements Parcelable {

    @PrimaryKey
    private int _id;

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
    public Member(){
        _id = StaticVariables.memberId;
    }

    public Member(int _id, String avatarLink, String name, String companyName, String address, String email, String phone) {
        this._id = _id;
        this.avatarLink = avatarLink;
        this.name = name;
        this.companyName = companyName;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    protected Member(Parcel in){
        avatarLink = in.readString();
        name = in.readString();
        companyName = in.readString();
        address = in.readString();
        email = in.readString();
        phone = in.readString();
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
