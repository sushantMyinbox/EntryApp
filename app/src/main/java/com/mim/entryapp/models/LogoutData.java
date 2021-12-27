
package com.mim.entryapp.models;

import com.google.gson.annotations.SerializedName;


public class LogoutData {

    @SerializedName("result")
    private String result;

    @SerializedName("description")
    private String description;

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

}
