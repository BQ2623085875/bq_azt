package com.azhyun.massxj.bean;

/**
 * Created by tl on 2018/7/2.
 */

public class JPushMessageResult {
    //"workStatus":"2","id":"211","type":"1","userId":"84","workId":"77"
    private int type;
    private int workId;
    private int workStatus;
    private int id;
    private String userId;
    private int isImg;

    public int getIsImg() {
        return isImg;
    }

    public void setIsImg(int isImg) {
        this.isImg = isImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public int getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(int workStatus) {
        this.workStatus = workStatus;
    }

    @Override
    public String toString() {
        return "JPushMessageResult{" +
                "type=" + type +
                ", workId=" + workId +
                ", workStatus=" + workStatus +
                ", id=" + id +
                ", userId='" + userId + '\'' +
                ", isImg=" + isImg +
                '}';
    }
}
