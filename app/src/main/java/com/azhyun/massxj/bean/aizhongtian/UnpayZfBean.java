package com.azhyun.massxj.bean.aizhongtian;

/**
 * Created by wkk on 2020/8/7.
 */

public class UnpayZfBean {

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
        private String tn;

        public String getTn() {
            return tn;
        }

        public void setTn(String tn) {
            this.tn = tn;
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
