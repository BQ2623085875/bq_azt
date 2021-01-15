package com.azhyun.massxj.bean;

import java.io.Serializable;

/**
 * Created by tl on 2018/9/4.
 */

public class PersonInfoResult implements Serializable {
    private Data data;
    private Result result;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Data implements Serializable {
        private String JSESSIONID;
        private String accountNo;
        private String fullName;
        private String headPortrait;
        private int managerRole;
        private int isManager;
        private String mobilePhone;
        private String name;
        private String nickname;
        private String sex;
        private String address;
        private String region;
        private String regionName;
        private String status1;
        private String status2;
        private String status3;
        private String status4;
        private String credit;
        private String currency;

        public String getCredit() {
            return credit;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getStatus4() {
            return status4;
        }

        public String getStatus3() {
            return status3;
        }

        public String getStatus2() {
            return status2;
        }

        public String getStatus1() {
            return status1;
        }

        public void setStatus4(String status4) {
            this.status4 = status4;
        }

        public void setStatus3(String status3) {
            this.status3 = status3;
        }

        public void setStatus2(String status2) {
            this.status2 = status2;
        }

        public void setStatus1(String status1) {
            this.status1 = status1;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }


        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }


        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getIsManager() {
            return isManager;
        }

        public void setIsManager(int isManager) {
            this.isManager = isManager;
        }

        public String getJSESSIONID() {
            return JSESSIONID;
        }

        public void setJSESSIONID(String JSESSIONID) {
            this.JSESSIONID = JSESSIONID;
        }

        public String getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(String accountNo) {
            this.accountNo = accountNo;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public int getManagerRole() {
            return managerRole;
        }

        public void setManagerRole(int managerRole) {
            this.managerRole = managerRole;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }

    public class Result implements Serializable {
        private String code;
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
