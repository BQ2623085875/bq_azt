package com.azhyun.massxj.bean.aizhongtian;

import java.util.List;

/**
 * Created by wkk on 2020/9/7.
 */

public class CaiBean {


    /**
     * result : {"code":"200","message":"操作成功！","data":{}}
     * data : {"totalrows":1,"JSESSIONID":"859C37953C18E52C95FD9829195D5D4D.jvm5c2","rows":[{"price":23.6,"id":202,"brand":"爱种牌","defaultImg":"azt/1000/1/item/20200907/img15994771440022.jpg","name":"采收测试","onlineTitle":"好"}]}
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
         * totalrows : 1
         * JSESSIONID : 859C37953C18E52C95FD9829195D5D4D.jvm5c2
         * rows : [{"price":23.6,"id":202,"brand":"爱种牌","defaultImg":"azt/1000/1/item/20200907/img15994771440022.jpg","name":"采收测试","onlineTitle":"好"}]
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
             * price : 23.6
             * id : 202
             * brand : 爱种牌
             * defaultImg : azt/1000/1/item/20200907/img15994771440022.jpg
             * name : 采收测试
             * onlineTitle : 好
             */

            private double price;
            private int id;
            private String brand;
            private String defaultImg;
            private String name;
            private String onlineTitle;

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

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getDefaultImg() {
                return defaultImg;
            }

            public void setDefaultImg(String defaultImg) {
                this.defaultImg = defaultImg;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOnlineTitle() {
                return onlineTitle;
            }

            public void setOnlineTitle(String onlineTitle) {
                this.onlineTitle = onlineTitle;
            }
        }
    }
}
