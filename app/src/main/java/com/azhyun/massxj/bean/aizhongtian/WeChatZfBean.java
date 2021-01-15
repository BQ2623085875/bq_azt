package com.azhyun.massxj.bean.aizhongtian;

/**
 * Created by wkk on 2020/8/7.
 */

public class WeChatZfBean {

    private ResultData data;
    private ResultBeanX result;

    public void setData(ResultData data) {
        this.data = data;
    }

    public void setResult(ResultBeanX result) {
        this.result = result;
    }

    public ResultData getData() {
        return data;
    }

    public ResultBeanX getResult() {
        return result;
    }

    public static class ResultData {
        private String appid;
        private String nonceStr;
        private String prepayId;
        private String partnerId;
        private String timeStamp;
        private String wxSign;

        public void setPartnerId(String partnerId) {
            this.partnerId = partnerId;
        }

        public String getPartnerId() {
            return partnerId;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }

        public void setPrepayId(String prepayId) {
            this.prepayId = prepayId;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public void setWxSign(String wxSign) {
            this.wxSign = wxSign;
        }

        public String getAppid() {
            return appid;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public String getPrepayId() {
            return prepayId;
        }

        public String getTimeStamp() {
            return timeStamp;
        }

        public String getWxSign() {
            return wxSign;
        }
    }

    public static class ResultBeanX {
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
