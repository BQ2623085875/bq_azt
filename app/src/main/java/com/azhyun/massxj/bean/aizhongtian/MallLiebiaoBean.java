package com.azhyun.massxj.bean.aizhongtian;

import java.util.List;

/**
 * Created by dell on 2020/7/6.
 */

public class MallLiebiaoBean {

    /**
     * result : {"code":"200","message":"操作成功！","data":{}}
     * data : [{"parentId":0,"name":"保险","id":233},{"parentId":0,"name":"测试一级","id":239}]
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
         * message : 操作成功！
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
         * parentId : 0
         * name : 保险
         * id : 233
         */

        private int parentId;
        private String name;
        private int id;

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
