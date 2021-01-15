package com.azhyun.massxj.bean;

import java.util.List;

/**
 * Auto-generated: 2018-09-03 13:42:11
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ServiceListResult {

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
     * Auto-generated: 2018-09-03 13:42:11
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public class Data {

        private String JSESSIONID;
        private List<Rows> rows;
        private long totalrows;
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

        public void setTotalrows(long totalrows) {
            this.totalrows = totalrows;
        }
        public long getTotalrows() {
            return totalrows;
        }


        /**
         * Auto-generated: 2018-09-03 13:42:11
         *
         * @author bejson.com (i@bejson.com)
         * @website http://www.bejson.com/java2pojo/
         */
        public class Rows {

            private String defaultImg;
            private String description;
            private String introduce;
            private int id;
            private String name;
            private double price;

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
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

            public void setId(int id) {
                this.id = id;
            }
            public int getId() {
                return id;
            }

            public void setName(String name) {
                this.name = name;
            }
            public String getName() {
                return name;
            }

            public void setPrice(double price) {
                this.price = price;
            }
            public double getPrice() {
                return price;
            }

        }

    }


    /**
     * Auto-generated: 2018-09-03 13:42:11
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