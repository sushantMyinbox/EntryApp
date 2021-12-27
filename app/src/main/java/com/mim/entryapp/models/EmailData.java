package com.mim.entryapp.models;

import com.google.gson.annotations.SerializedName;

public class EmailData {

    @SerializedName("result")
    private String result;

    @SerializedName("description")
    private String description;

    @SerializedName("mobile")
    private String mobile;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
