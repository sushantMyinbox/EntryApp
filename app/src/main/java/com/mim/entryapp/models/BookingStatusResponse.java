package com.mim.entryapp.models;

import java.util.List;

public class BookingStatusResponse {


    /**
     * statusCode : 200
     * data : {"result":"success","description":"success","BookingList":[{"bookingNo":"BK0001","bookedFrom":"2021-10-05","bookedTo":"2021-10-09","bookedFor":"Rachit","remark":"Booked"},{"bookingNo":"BK0013","bookedFrom":"2021-10-05","bookedTo":"2021-10-09","bookedFor":"Rachit","remark":"Booked"},{"bookingNo":"BK0014","bookedFrom":"2021-10-05","bookedTo":"2021-10-09","bookedFor":"Rachit","remark":"Booked"},{"bookingNo":"BK0002","bookedFrom":"2021-10-05","bookedTo":"2021-10-09","bookedFor":"Rachit","remark":"Booked"},{"bookingNo":"BK0004","bookedFrom":"2021-10-05","bookedTo":"2021-10-09","bookedFor":"Rachit","remark":"Booked"}]}
     */

    private String statusCode;
    /**
     * result : success
     * description : success
     * BookingList : [{"bookingNo":"BK0001","bookedFrom":"2021-10-05","bookedTo":"2021-10-09","bookedFor":"Rachit","remark":"Booked"},{"bookingNo":"BK0013","bookedFrom":"2021-10-05","bookedTo":"2021-10-09","bookedFor":"Rachit","remark":"Booked"},{"bookingNo":"BK0014","bookedFrom":"2021-10-05","bookedTo":"2021-10-09","bookedFor":"Rachit","remark":"Booked"},{"bookingNo":"BK0002","bookedFrom":"2021-10-05","bookedTo":"2021-10-09","bookedFor":"Rachit","remark":"Booked"},{"bookingNo":"BK0004","bookedFrom":"2021-10-05","bookedTo":"2021-10-09","bookedFor":"Rachit","remark":"Booked"}]
     */

    private DataBean data;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String result;
        private String description;
        /**
         * bookingNo : BK0001
         * bookedFrom : 2021-10-05
         * bookedTo : 2021-10-09
         * bookedFor : Rachit
         * remark : Booked
         */

        private List<BookingListBean> BookingList;

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

        public List<BookingListBean> getBookingList() {
            return BookingList;
        }

        public void setBookingList(List<BookingListBean> BookingList) {
            this.BookingList = BookingList;
        }

        public static class BookingListBean {
            private String bookingNo;
            private String bookedFrom;
            private String bookedTo;
            private String bookedFor;
            private String remark;

            public String getBookingNo() {
                return bookingNo;
            }

            public void setBookingNo(String bookingNo) {
                this.bookingNo = bookingNo;
            }

            public String getBookedFrom() {
                return bookedFrom;
            }

            public void setBookedFrom(String bookedFrom) {
                this.bookedFrom = bookedFrom;
            }

            public String getBookedTo() {
                return bookedTo;
            }

            public void setBookedTo(String bookedTo) {
                this.bookedTo = bookedTo;
            }

            public String getBookedFor() {
                return bookedFor;
            }

            public void setBookedFor(String bookedFor) {
                this.bookedFor = bookedFor;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
