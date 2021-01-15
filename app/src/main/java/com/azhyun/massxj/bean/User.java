package com.azhyun.massxj.bean;


import android.text.TextUtils;

/**
 * Created by tl on 2018/7/20.
 */

public enum User {
    INSTANCE;
    private  boolean isLogin ;
    private  String JSESSIONID;
    private  String token;
    private  int managerRole;
    private  String region ;
    private  String regionName ;
    private  String storeId;

    public boolean isLogin() {
        if (!isLogin){
            isLogin = (boolean)SpUtils.get("isLogin",false);
        }
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getJSESSIONID() {
        if (TextUtils.isEmpty(JSESSIONID)){
            JSESSIONID = (String)SpUtils.get("JSESSIONID","");
        }
        return JSESSIONID;
    }

    public void setJSESSIONID(String JSESSIONID) {
        this.JSESSIONID = JSESSIONID;
    }

    public String getToken() {
        if (TextUtils.isEmpty(token)){
            token = (String)SpUtils.get("token","");
        }
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getManagerRole() {
        if (managerRole == 0){
            managerRole = (int)SpUtils.get("managerRole",0);
        }
        return managerRole;
    }

    public void setManagerRole(int managerRole) {
        this.managerRole = managerRole;
    }

    public String getRegion() {
        if (TextUtils.isEmpty(region)){
            region = (String)SpUtils.get("region","");
        }
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionName() {
        if (TextUtils.isEmpty(regionName)){
            regionName = (String)SpUtils.get("regionName","");
        }
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getStoreId() {
        if (TextUtils.isEmpty(storeId)){
            storeId = (String)SpUtils.get("storeId","");
        }
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
