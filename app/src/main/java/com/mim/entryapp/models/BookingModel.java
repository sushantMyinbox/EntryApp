package com.mim.entryapp.models;

import com.google.gson.annotations.SerializedName;

public class BookingModel {

    @SerializedName("BookingNo")
    private String bookingNo;

    @SerializedName("BookingStatus")
    private String bookingStatus;

    @SerializedName("BookingDate")
    private String bookingDate;

    @SerializedName("BookingFor")
    private String bookingFor;

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingFor() {
        return bookingFor;
    }

    public void setBookingFor(String bookingFor) {
        this.bookingFor = bookingFor;
    }

}
