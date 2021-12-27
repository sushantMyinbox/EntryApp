package com.mim.entryapp.models;

public class BookingStatusModel {

    private String booked_from;
    private String booked_to;
    private String booked_for;
    private String remarks;
    private String booking_num;

    public BookingStatusModel(String booked_from, String booked_to, String booked_for, String remarks, String booking_num) {
        this.booked_from = booked_from;
        this.booked_to = booked_to;
        this.booked_for = booked_for;
        this.remarks = remarks;
        this.booking_num = booking_num;
    }

    public String getBooked_from() {
        return booked_from;
    }

    public void setBooked_from(String booked_from) {
        this.booked_from = booked_from;
    }

    public String getBooked_to() {
        return booked_to;
    }

    public void setBooked_to(String booked_to) {
        this.booked_to = booked_to;
    }

    public String getBooked_for() {
        return booked_for;
    }

    public void setBooked_for(String booked_for) {
        this.booked_for = booked_for;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getBooking_num() {
        return booking_num;
    }

    public void setBooking_num(String booking_num) {
        this.booking_num = booking_num;
    }
}
