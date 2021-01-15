package com.azhyun.massxj.bean;

import java.util.List;

/**
 * Auto-generated: 2018-09-06 19:13:9
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class LandListResult {

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
     * Auto-generated: 2018-09-06 19:13:9
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public class Data {

        private String JSESSIONID;
        private List<Rows> rows;
        private int totalrows;
        public void setJSESSIONID(String JSESSIONID) {
            this.JSESSIONID = JSESSIONID;
        }
        public String getJSESSIONID() {
            return JSESSIONID;
        }

        public void setRows(List<Rows> rows) {
            this.rows = rows;
        }
        public List<Rows> getRows() {
            return rows;
        }

        public void setTotalrows(int totalrows) {
            this.totalrows = totalrows;
        }
        public int getTotalrows() {
            return totalrows;
        }

        /**
         * Auto-generated: 2018-09-06 19:13:9
         *
         * @author bejson.com (i@bejson.com)
         * @website http://www.bejson.com/java2pojo/
         */
        public class Rows {

            private double area;
            private String defaultImg;
            private String fullName;
            private int id;
            private String introduce;
            private int isImg;
            private String name;
            private String regionName;
            private String title;
            private String phone;
            public void setArea(double area) {
                this.area = area;
            }
            public double getArea() {
                return area;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public void setDefaultImg(String defaultImg) {
                this.defaultImg = defaultImg;
            }
            public String getDefaultImg() {
                return defaultImg;
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

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }
            public String getIntroduce() {
                return introduce;
            }

            public void setIsImg(int isImg) {
                this.isImg = isImg;
            }
            public int getIsImg() {
                return isImg;
            }

            public void setName(String name) {
                this.name = name;
            }
            public String getName() {
                return name;
            }

            public void setRegionName(String regionName) {
                this.regionName = regionName;
            }
            public String getRegionName() {
                return regionName;
            }

            public void setTitle(String title) {
                this.title = title;
            }
            public String getTitle() {
                return title;
            }

        }

    }

    /**
     * Auto-generated: 2018-09-06 19:13:9
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