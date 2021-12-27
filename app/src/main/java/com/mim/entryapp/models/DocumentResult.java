
package com.mim.entryapp.models;


import com.google.gson.annotations.SerializedName;


public class DocumentResult {

    @SerializedName("statusCode")
    private String statusCode;

    @SerializedName("data")
    private Data data;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
