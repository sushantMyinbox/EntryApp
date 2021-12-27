package com.mim.entryapp.models;

public class CarStatusModel {

    private String full_name;
    private int mobile_number;
    private String accompanied_by;
    private String coming_from;
    private String vehicle_number;


    public CarStatusModel(String full_name, String accompanied_by, String coming_from, int mobile_number, String vehicle_number) {
        this.full_name = full_name;
        this.accompanied_by = accompanied_by;
        this.coming_from = coming_from;
        this.mobile_number = mobile_number;
        this.vehicle_number = vehicle_number;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(int mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getAccompanied_by() {
        return accompanied_by;
    }

    public void setAccompanied_by(String accompanied_by) {
        this.accompanied_by = accompanied_by;
    }

    public String getComing_from() {
        return coming_from;
    }

    public void setComing_from(String coming_from) {
        this.coming_from = coming_from;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }
}
