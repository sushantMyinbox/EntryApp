package com.mim.entryapp.models;

import java.util.List;

public class ResidentResponse {


    /**
     * statusCode : 200
     * data : {"result":"success","description":"success","ResidentList":[{"FlatNo":"10","TowerName":"Landmark","MobileNo":"9773937533","Name":"Rachit"},{"FlatNo":"10","TowerName":"Landmark","MobileNo":"9773937533","Name":"Rachit"},{"FlatNo":"","TowerName":"","MobileNo":"9773937533","Name":"Rachit"},{"FlatNo":"10","TowerName":"Landmark","MobileNo":"9773937533","Name":"Rachit"},{"FlatNo":"10","TowerName":"Landmark","MobileNo":"9773937533","Name":"Rachit"},{"FlatNo":"10","TowerName":"Landmark","MobileNo":"9773937533","Name":"Rachit"},{"FlatNo":"10","TowerName":"","MobileNo":"9773937533","Name":"Rachit"},{"FlatNo":"10","TowerName":"Landmark","MobileNo":"9773937533","Name":"Rachit"},{"FlatNo":"10","TowerName":"Landmark","MobileNo":"9773937533","Name":"Rachit"}]}
     */

    private String statusCode;
    /**
     * result : success
     * description : success
     * ResidentList : [{"FlatNo":"10","TowerName":"Landmark","MobileNo":"9773937533","Name":"Rachit"},{"FlatNo":"10","TowerName":"Landmark","MobileNo":"9773937533","Name":"Rachit"},{"FlatNo":"","TowerName":"","MobileNo":"9773937533","Name":"Rachit"},{"FlatNo":"10","TowerName":"Landmark","MobileNo":"9773937533","Name":"Rachit"},{"FlatNo":"10","TowerName":"Landmark","MobileNo":"9773937533","Name":"Rachit"},{"FlatNo":"10","TowerName":"Landmark","MobileNo":"9773937533","Name":"Rachit"},{"FlatNo":"10","TowerName":"","MobileNo":"9773937533","Name":"Rachit"},{"FlatNo":"10","TowerName":"Landmark","MobileNo":"9773937533","Name":"Rachit"},{"FlatNo":"10","TowerName":"Landmark","MobileNo":"9773937533","Name":"Rachit"}]
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
         * FlatNo : 10
         * TowerName : Landmark
         * MobileNo : 9773937533
         * Name : Rachit
         */

        private List<ResidentListBean> ResidentList;

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

        public List<ResidentListBean> getResidentList() {
            return ResidentList;
        }

        public void setResidentList(List<ResidentListBean> ResidentList) {
            this.ResidentList = ResidentList;
        }

        public static class ResidentListBean {
            private String FlatNo;
            private String TowerName;
            private String MobileNo;
            private String Name;

            public String getFlatNo() {
                return FlatNo;
            }

            public void setFlatNo(String FlatNo) {
                this.FlatNo = FlatNo;
            }

            public String getTowerName() {
                return TowerName;
            }

            public void setTowerName(String TowerName) {
                this.TowerName = TowerName;
            }

            public String getMobileNo() {
                return MobileNo;
            }

            public void setMobileNo(String MobileNo) {
                this.MobileNo = MobileNo;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }
        }
    }
}
