package com.azhyun.massxj.bean;

/**
 * Created by tl on 2018/9/8.
 */
/**
 * Auto-generated: 2018-09-08 17:1:22
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class MyManageInfoResult {

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
     * Auto-generated: 2018-09-08 17:1:22
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public class Data {

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
         * Auto-generated: 2018-09-08 17:1:22
         *
         * @author bejson.com (i@bejson.com)
         * @website http://www.bejson.com/java2pojo/
         */
        public class Info {

            private long applyRole;
            private int area;
            private String fullName;
            private String mobilePhone;
            private String name;
            private String sex;
            private int userId;
            public void setApplyRole(long applyRole) {
                this.applyRole = applyRole;
            }
            public long getApplyRole() {
                return applyRole;
            }

            public void setArea(int area) {
                this.area = area;
            }
            public int getArea() {
                return area;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }
            public String getFullName() {
                return fullName;
            }

            public void setMobilePhone(String mobilePhone) {
                this.mobilePhone = mobilePhone;
            }
            public String getMobilePhone() {
                return mobilePhone;
            }

            public void setName(String name) {
                this.name = name;
            }
            public String getName() {
                return name;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }
            public String getSex() {
                return sex;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
            public int getUserId() {
                return userId;
            }

        }

    }

    /**
     * Auto-generated: 2018-09-08 17:1:22
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public class Result {

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