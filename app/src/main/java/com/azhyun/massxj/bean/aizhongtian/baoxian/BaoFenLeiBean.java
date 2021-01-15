package com.azhyun.massxj.bean.aizhongtian.baoxian;

import java.util.List;

/**
 * Created by wkk on 2020/9/15.
 */

public class BaoFenLeiBean {

    /**
     * result : {"code":"200","message":"操作成功","data":{}}
     * data : [{"id":46,"addTime":1600132473000,"status":1,"name":"玉米保险","seq":0}]
     */

    private ResultBean result;
    private List<DataBeanX> data;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public List<DataBeanX> getData() {
        return data;
    }

    public void setData(List<DataBeanX> data) {
        this.data = data;
    }

    public static class ResultBean {
        /**
         * code : 200
         * message : 操作成功
         * data : {}
         */

        private String code;
        private String message;
        private DataBean data;

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

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
        }
    }

    public static class DataBeanX {
        /**
         * id : 46
         * addTime : 1600132473000
         * status : 1
         * name : 玉米保险
         * seq : 0
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
