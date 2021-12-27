package com.mim.entryapp.models;

import com.google.gson.annotations.SerializedName;

public class SaveAddressModel {

    @SerializedName("apiKey")
    private String apiKey;

    @SerializedName("mobileNo")
    private String mobileNo;

    @SerializedName("societyCode")
    private String societyCode;

    @SerializedName("towerName")
    private String towerName;

    @SerializedName("flatNo")
    private String flatNo;

    @SerializedName("ownershipStatus")
    private String ownershipStatus;

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

    public String getSocietyCode() {
        return societyCode;
    }

    public void setSocietyCode(String societyCode) {
        this.societyCode = societyCode;
    }

    public String getTowerName() {
        return towerName;
    }

    public void setTowerName(String towerName) {
        this.towerName = towerName;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public String getOwnershipStatus() {
        return ownershipStatus;
    }

    public void setOwnershipStatus(String ownershipStatus) {
        this.ownershipStatus = ownershipStatus;
    }
}
