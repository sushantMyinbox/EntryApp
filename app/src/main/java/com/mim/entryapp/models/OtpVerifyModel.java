package com.mim.entryapp.models;

import com.google.gson.annotations.SerializedName;

public class OtpVerifyModel {

    @SerializedName("apiKey")
    private String apiKey;

    @SerializedName("secret")
    private String secret;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("otp")
    private String otp;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
