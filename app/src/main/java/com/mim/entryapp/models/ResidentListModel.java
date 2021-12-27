package com.mim.entryapp.models;

import com.google.gson.annotations.SerializedName;

public class ResidentListModel {

    @SerializedName("FlatNo")
    private String flatNo;

    @SerializedName("TowerName")
    private String towerName;

    @SerializedName("MobileNo")
    private String mobileNo;

    @SerializedName("Name")
    private String name;

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public String getTowerName() {
        return towerName;
    }

    public void setTowerName(String towerName) {
        this.towerName = towerName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
