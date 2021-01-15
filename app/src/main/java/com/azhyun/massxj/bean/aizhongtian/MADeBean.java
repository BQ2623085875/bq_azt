package com.azhyun.massxj.bean.aizhongtian;

/**
 * Created by dell on 2020/7/7.
 */

public class MADeBean {


    /**
     * data : {"companyId":"","id":0}
     * payNumber :
     * result : {"code":"200","data":{},"message":"获取信息成功！"}
     */

//    private DataBean data;
    private String payNumber;
    private ResultBean result;

//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

//    public static class DataBean {
//        /**
//         * companyId :
//         * id : 0
//         */
//
//        private String companyId;
//        private String id;
//
//        public String getCompanyId() {
//            return companyId;
//        }
//
//        public void setCompanyId(String companyId) {
//            this.companyId = companyId;
//        }
//
//        public String getId() {
//            return id;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//    }

    public static class ResultBean {
        /**
         * code : 200
         * data : {}
         * message : 获取信息成功！
         */

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

        public static class DataBeanX {
        }
    }
}
