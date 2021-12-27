package com.mim.entryapp.models;

import com.google.gson.annotations.SerializedName;

public class SaveVisitorModel {

    @SerializedName("APIKEY")
    private String apikey;

    @SerializedName("mobileNo")
    private String mobileNo;

    @SerializedName("comingFrom")
    private String comingFrom;

    @SerializedName("vistorMobile")
    private String vistorMobile;

    @SerializedName("adult")
    private String adult;

    @SerializedName("child")
    private String child;

    @SerializedName("vehicle")
    private String vehicle;

    @SerializedName("visitorName")
    private String visitorName;

    @SerializedName("visitDate")
    private String visitDate;

    @SerializedName("visitTime")
    private String visitTime;

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getComingFrom() {
        return comingFrom;
    }

    public void setComingFrom(String comingFrom) {
        this.comingFrom = comingFrom;
    }

    public String getVistorMobile() {
        return vistorMobile;
    }

    public void setVistorMobile(String vistorMobile) {
        this.vistorMobile = vistorMobile;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }
}