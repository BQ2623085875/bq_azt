package com.azhyun.massxj.bean;

import java.util.List;

/**
 * Created by tl on 2018/9/4.
 */

public class MyServiceListResult {
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

    public class Data {
        private String JSESSIONID;
        private List<Rows> rows;
        private int totalrows;

        public int getTotalrows() {
            return totalrows;
        }

        public void setTotalrows(int totalrows) {
            this.totalrows = totalrows;
        }

        public String getJSESSIONID() {
            return JSESSIONID;
        }

        public void setJSESSIONID(String JSESSIONID) {
            this.JSESSIONID = JSESSIONID;
        }

        public List<Rows> getRows() {
            return rows;
        }

        public void setRows(List<Rows> rows) {
            this.rows = rows;
        }

        public class Rows {
            private String code;
            private String defaultImg;
            private String description;
            private int id;
            private String introduce;
            private int num;
            private String serviceCategory;
            private String serviceName;
            private double servicePrice;
            private String serviceTime;


            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public String getDefaultImg() {
                return defaultImg;
            }

            public void setDefaultImg(String defaultImg) {
                this.defaultImg = defaultImg;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getServiceCategory() {
                return serviceCategory;
            }

            public void setServiceCategory(String serviceCategory) {
                this.serviceCategory = serviceCategory;
            }

            public String getServiceName() {
                return serviceName;
            }

            public void setServiceName(String serviceName) {
                this.serviceName = serviceName;
            }

            public double getServicePrice() {
                return servicePrice;
            }

            public void setServicePrice(double servicePrice) {
                this.servicePrice = servicePrice;
            }

            public String getServiceTime() {
                return serviceTime;
            }

            public void setServiceTime(String serviceTime) {
                this.serviceTime = serviceTime;
            }


        }
    }

    public class Result {
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
