package com.azhyun.massxj.utils;

public class LoginInfo {
    private static volatile LoginInfo sLoginInfo = null;

    private String JSESSIONID;
    private String status;
    private String storeId;


    public static LoginInfo getInstance() {
        if (sLoginInfo == null) {
            synchronized (LoginInfo.class) {
                if (sLoginInfo == null) {
                    sLoginInfo = new LoginInfo();
                }
            }
        }

        return sLoginInfo;
    }

    public static LoginInfo getsLoginInfo() {
        return sLoginInfo;
    }

    public static void setsLoginInfo(LoginInfo sLoginInfo) {
        LoginInfo.sLoginInfo = sLoginInfo;
    }

    public String getJSESSIONID() {
        return JSESSIONID;
    }

    public void setJSESSIONID(String JSESSIONID) {
        this.JSESSIONID = JSESSIONID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}