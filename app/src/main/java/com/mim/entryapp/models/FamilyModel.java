package com.mim.entryapp.models;

import com.google.gson.annotations.SerializedName;

public class FamilyModel {

    @SerializedName("apiKey")
    private String apiKey;

    @SerializedName("mobileNo")
    private String mobileNo;

    @SerializedName("memberName")
    private String memberName;

    @SerializedName("memberPhone")
    private String memberPhone;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }
}
