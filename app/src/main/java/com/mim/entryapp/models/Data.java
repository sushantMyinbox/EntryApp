
package com.mim.entryapp.models;

import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("result")
    private String result;

    @SerializedName("description")
    private String description;

    @SerializedName("apiKey")
    private String apiKey;

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

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

}
