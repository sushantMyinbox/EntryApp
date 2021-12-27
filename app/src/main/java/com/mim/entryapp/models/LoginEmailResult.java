package com.mim.entryapp.models;

import com.google.gson.annotations.SerializedName;

public class LoginEmailResult {

    @SerializedName("statusCode")
    private String statusCode;

    @SerializedName("data")
    private EmailData emailData;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public EmailData getEmailData() {
        return emailData;
    }

    public void setEmailData(EmailData emailData) {
        this.emailData = emailData;
    }
}
