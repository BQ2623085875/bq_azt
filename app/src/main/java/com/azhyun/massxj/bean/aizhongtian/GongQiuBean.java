package com.azhyun.massxj.bean.aizhongtian;

import java.util.List;

/**
 * Created by wkk on 2020/8/7.
 */

public class GongQiuBean {


    /**
     * result : {"code":"200","message":"操作成功！","data":{}}
     * data : {"totalrows":3,"JSESSIONID":"DA7EF8037D6422FAA6740DCD948D666E","rows":[{"title":"买玉米种子","type":2,"price":2,"id":66,"descriptionContent":"急需大量玉米种，请联系我！","defaultImg":"azt/1000/1/azt/azt19596778880069.jpg","norms":"元/粒","addtimes":"1596778880000","status":1},{"title":"卖仙丹！","type":1,"price":5000,"id":65,"descriptionContent":"此仙丹法力无边。服用可保寿元大增！","defaultImg":"azt/1000/1/azt/azt15967789565448.jpg","norms":"元/颗","addtimes":"1596778565000","status":1},{"title":"我要买种子","type":2,"price":500,"id":64,"descriptionContent":"20200807/arts/art15967970724093.html","defaultImg":"azt/1000/1/azt/azt18596770724060.jpg","norms":"元/个","addtimes":"1596770724000","status":1}]}
     */

    private ResultBean result;
    private DataBeanX data;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
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
         * totalrows : 3
         * JSESSIONID : DA7EF8037D6422FAA6740DCD948D666E
         * rows : [{"title":"买玉米种子","type":2,"price":2,"id":66,"descriptionContent":"急需大量玉米种，请联系我！","defaultImg":"azt/1000/1/azt/azt19596778880069.jpg","norms":"元/粒","addtimes":"1596778880000","status":1},{"title":"卖仙丹！","type":1,"price":5000,"id":65,"descriptionContent":"此仙丹法力无边。服用可保寿元大增！","defaultImg":"azt/1000/1/azt/azt15967789565448.jpg","norms":"元/颗","addtimes":"1596778565000","status":1},{"title":"我要买种子","type":2,"price":500,"id":64,"descriptionContent":"20200807/arts/art15967970724093.html","defaultImg":"azt/1000/1/azt/azt18596770724060.jpg","norms":"元/个","addtimes":"1596770724000","status":1}]
         */

        private int totalrows;
        private String JSESSIONID;
        private List<RowsBean> rows;

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

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * title : 买玉米种子
             * type : 2
             * price : 2
             * id : 66
             * descriptionContent : 急需大量玉米种，请联系我！
             * defaultImg : azt/1000/1/azt/azt19596778880069.jpg
             * norms : 元/粒
             * addtimes : 1596778880000
             * status : 1
             */

            private String title;
            private int type;
            private double price;
            private int id;
            private String descriptionContent;
            private String defaultImg;
            private String norms;
            private String addtimes;
            private int status;
            private int userId;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDescriptionContent() {
                return descriptionContent;
            }

            public void setDescriptionContent(String descriptionContent) {
                this.descriptionContent = descriptionContent;
            }

            public String getDefaultImg() {
                return defaultImg;
            }

            public void setDefaultImg(String defaultImg) {
                this.defaultImg = defaultImg;
            }

            public String getNorms() {
                return norms;
            }

            public void setNorms(String norms) {
                this.norms = norms;
            }

            public String getAddtimes() {
                return addtimes;
            }

            public void setAddtimes(String addtimes) {
                this.addtimes = addtimes;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
