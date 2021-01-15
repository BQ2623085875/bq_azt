package com.azhyun.massxj.bean;

/**
 * Created by tl on 2018/9/6.
 */

import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2018-09-06 12:53:24
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class MyServiceInfoResult implements Serializable{

    private Data data;
    private Result result;
    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }

    public void setResult(Result result) {
        this.result = result;
    }
    public Result getResult() {
        return result;
    }


    /**
     * Auto-generated: 2018-09-06 12:53:24
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public class Data implements Serializable{

        private String JSESSIONID;
        private Info info;
        public void setJSESSIONID(String JSESSIONID) {
            this.JSESSIONID = JSESSIONID;
        }
        public String getJSESSIONID() {
            return JSESSIONID;
        }

        public void setInfo(Info info) {
            this.info = info;
        }
        public Info getInfo() {
            return info;
        }

        /**
         * Auto-generated: 2018-09-06 12:53:24
         *
         * @author bejson.com (i@bejson.com)
         * @website http://www.bejson.com/java2pojo/
         */
        public class Info implements Serializable{

            private String address;
            private String defaultImg;
            private String description;
            private String fullName;
            private int id;
            private int num;
            private String code;
            private List<ServImgs> servImgs;
            private String serviceCategory;
            private String descriptionContent;
            private String serviceName;
            private double servicePrice;
            private String serviceTime;
            private String introduce;
            private String userName;
            private String userMob;
            private int status;
            private String remark;

            private long addTime;
            private long affirmPayTime;
            private long affirmServiceTime;
            private long affirmTime;

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public long getAffirmPayTime() {
                return affirmPayTime;
            }

            public void setAffirmPayTime(long affirmPayTime) {
                this.affirmPayTime = affirmPayTime;
            }

            public long getAffirmServiceTime() {
                return affirmServiceTime;
            }

            public void setAffirmServiceTime(long affirmServiceTime) {
                this.affirmServiceTime = affirmServiceTime;
            }

            public long getAffirmTime() {
                return affirmTime;
            }

            public void setAffirmTime(long affirmTime) {
                this.affirmTime = affirmTime;
            }

            public String getDescriptionContent() {
                return descriptionContent;
            }

            public void setDescriptionContent(String descriptionContent) {
                this.descriptionContent = descriptionContent;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserMob() {
                return userMob;
            }

            public void setUserMob(String userMob) {
                this.userMob = userMob;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public void setAddress(String address) {
                this.address = address;
            }
            public String getAddress() {
                return address;
            }

            public void setDefaultImg(String defaultImg) {
                this.defaultImg = defaultImg;
            }
            public String getDefaultImg() {
                return defaultImg;
            }

            public void setDescription(String description) {
                this.description = description;
            }
            public String getDescription() {
                return description;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }
            public String getFullName() {
                return fullName;
            }

            public void setId(int id) {
                this.id = id;
            }
            public int getId() {
                return id;
            }

            public void setNum(int num) {
                this.num = num;
            }
            public int getNum() {
                return num;
            }

            public void setServImgs(List<ServImgs> servImgs) {
                this.servImgs = servImgs;
            }
            public List<ServImgs> getServImgs() {
                return servImgs;
            }

            public void setServiceCategory(String serviceCategory) {
                this.serviceCategory = serviceCategory;
            }
            public String getServiceCategory() {
                return serviceCategory;
            }

            public void setServiceName(String serviceName) {
                this.serviceName = serviceName;
            }
            public String getServiceName() {
                return serviceName;
            }

            public void setServicePrice(double servicePrice) {
                this.servicePrice = servicePrice;
            }
            public double getServicePrice() {
                return servicePrice;
            }

            public void setServiceTime(String serviceTime) {
                this.serviceTime = serviceTime;
            }
            public String getServiceTime() {
                return serviceTime;
            }

            public void setStatus(int status) {
                this.status = status;
            }
            public int getStatus() {
                return status;
            }


            /**
             * Auto-generated: 2018-09-06 12:53:24
             *
             * @author bejson.com (i@bejson.com)
             * @website http://www.bejson.com/java2pojo/
             */
            public class ServImgs implements Serializable{

                private int id;
                private String url;
                public void setId(int id) {
                    this.id = id;
                }
                public int getId() {
                    return id;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
                public String getUrl() {
                    return url;
                }

            }
        }
    }

    /**
     * Auto-generated: 2018-09-06 12:53:24
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public class Result implements Serializable{

        private String code;
        private String message;
        public void setCode(String code) {
            this.code = code;
        }
        public String getCode() {
            return code;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }

    }
}