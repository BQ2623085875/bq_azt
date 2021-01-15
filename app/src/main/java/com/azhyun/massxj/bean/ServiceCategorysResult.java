package com.azhyun.massxj.bean;

import java.util.List;

/**
 * Created by tl on 2018/9/3.
 */

public class ServiceCategorysResult {
    private ResultBean result;
    private DataBean data;

    public DataBean getDataBean() {
        return data;
    }

    public void setDataBean(DataBean dataBean) {
        this.data = dataBean;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private String message;
        private String code;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public class DataBean {
        private String JSESSIONID;
        private List<Categorys> categorys;

        public String getJSESSIONID() {
            return JSESSIONID;
        }

        public void setJSESSIONID(String JSESSIONID) {
            this.JSESSIONID = JSESSIONID;
        }

        public List<Categorys> getCategorys() {
            return categorys;
        }

        public void setCategorys(List<Categorys> categorys) {
            this.categorys = categorys;
        }

        public class Categorys {

            private String  fullName;
            private int id;
            private String name;

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

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
}
