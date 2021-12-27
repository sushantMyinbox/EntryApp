package com.mim.entryapp.models;

public class  SocietyListModel {
    private String owner_name;
    private String mobile_number;
    private String email_id;
    private String aadhar_num;
    private String voter_id;
    private String flat_address;

    public SocietyListModel(String owner_name, String mobile_number, String email_id,
                            String aadhar_num, String voter_id, String flat_address) {
        this.owner_name = owner_name;
        this.mobile_number = mobile_number;
        this.email_id = email_id;
        this.aadhar_num = aadhar_num;
        this.voter_id = voter_id;
        this.flat_address = flat_address;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getAadhar_num() {
        return aadhar_num;
    }

    public void setAadhar_num(String aadhar_num) {
        this.aadhar_num = aadhar_num;
    }

    public String getVoter_id() {
        return voter_id;
    }

    public void setVoter_id(String voter_id) {
        this.voter_id = voter_id;
    }

    public String getFlat_address() {
        return flat_address;
    }

    public void setFlat_address(String flat_address) {
        this.flat_address = flat_address;
    }
}

