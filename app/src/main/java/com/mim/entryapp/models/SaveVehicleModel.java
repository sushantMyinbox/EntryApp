package com.mim.entryapp.models;

import com.google.gson.annotations.SerializedName;

public class SaveVehicleModel {

    @SerializedName("apiKey")
    private String apiKey;

    @SerializedName("mobileNo")
    private String mobileNo;

    @SerializedName("registrationNumber")
    private String registrationNumber;

    @SerializedName("model")
    private String model;

    @SerializedName("parkingLevel")
    private String parkingLevel;

    @SerializedName("parkingNumber")
    private String parkingNumber;

    @SerializedName("document")
    private String document;

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

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getParkingLevel() {
        return parkingLevel;
    }

    public void setParkingLevel(String parkingLevel) {
        this.parkingLevel = parkingLevel;
    }

    public String getParkingNumber() {
        return parkingNumber;
    }

    public void setParkingNumber(String parkingNumber) {
        this.parkingNumber = parkingNumber;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

}
