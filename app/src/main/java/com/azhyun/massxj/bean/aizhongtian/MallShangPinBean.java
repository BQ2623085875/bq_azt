package com.azhyun.massxj.bean.aizhongtian;

import java.util.List;

/**
 * Created by dell on 2020/7/6.
 */

public class MallShangPinBean {

    /**
     * result : {"code":"200","message":"操作成功！","data":{}}
     * data : {"totalrows":1,"JSESSIONID":"96B7D60F0B4CBB35E0A501DF1F10DE7E","rows":[{"price":250,"id":165,"defaultImg":"store/1000/1/item/20200702/img15936548252714.jpg","name":"保你无恙"}]}
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
         * JSESSIONID : 96B7D60F0B4CBB35E0A501DF1F10DE7E
         * rows : [{"price":250,"id":165,"defaultImg":"store/1000/1/item/20200702/img15936548252714.jpg","name":"保你无恙"}]
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
             * price : 250
             * id : 165
             * defaultImg : store/1000/1/item/20200702/img15936548252714.jpg
             * name : 保你无恙
             */

            private double price;
            private int id;
            private String defaultImg;
            private String name;
            private String onlineTitle;
            private String brand;

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getOnlineTitle() {
                return onlineTitle;
            }

            public void setOnlineTitle(String onlineTitle) {
                this.onlineTitle = onlineTitle;
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
        }
    }
}
