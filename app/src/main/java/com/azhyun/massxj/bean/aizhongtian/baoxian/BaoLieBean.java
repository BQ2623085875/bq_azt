package com.azhyun.massxj.bean.aizhongtian.baoxian;

import java.util.List;

/**
 * Created by wkk on 2020/9/15.
 */

public class BaoLieBean {


    /**
     * result : {"code":"200","message":"操作成功","data":{}}
     * data : {"totalrows":3,"JSESSIONID":"93214FD85D589E59AEDF09EB9597289E","rows":[{"img":"azt/1000/1/item/20200915/img16001396450613.jpg","units":"亩","responsibility":"全险","price":30,"id":16,"amount":"160/亩","insuranceName":"玉米保险"},{"img":"azt/1000/1/item/20200915/img16700134096227.jpg","units":"亩","responsibility":"全险","price":50,"id":15,"amount":"150/亩","insuranceName":"玉米保险"},{"img":"azt/1000/1/item/20200915/img16050132942801.jpg","units":"亩","responsibility":"全险","price":30,"id":14,"amount":"150/亩","insuranceName":"玉米保险"}]}
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
         * message : 操作成功
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
         * JSESSIONID : 93214FD85D589E59AEDF09EB9597289E
         * rows : [{"img":"azt/1000/1/item/20200915/img16001396450613.jpg","units":"亩","responsibility":"全险","price":30,"id":16,"amount":"160/亩","insuranceName":"玉米保险"},{"img":"azt/1000/1/item/20200915/img16700134096227.jpg","units":"亩","responsibility":"全险","price":50,"id":15,"amount":"150/亩","insuranceName":"玉米保险"},{"img":"azt/1000/1/item/20200915/img16050132942801.jpg","units":"亩","responsibility":"全险","price":30,"id":14,"amount":"150/亩","insuranceName":"玉米保险"}]
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
             * img : azt/1000/1/item/20200915/img16001396450613.jpg
             * units : 亩
             * responsibility : 全险
             * price : 30
             * id : 16
             * amount : 160/亩
             * insuranceName : 玉米保险
             */

            private String img;
            private String units;
            private String responsibility;
            private double price;
            private int id;
            private String amount;
            private String insuranceName;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUnits() {
                return units;
            }

            public void setUnits(String units) {
                this.units = units;
            }

            public String getResponsibility() {
                return responsibility;
            }

            public void setResponsibility(String responsibility) {
                this.responsibility = responsibility;
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

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getInsuranceName() {
                return insuranceName;
            }

            public void setInsuranceName(String insuranceName) {
                this.insuranceName = insuranceName;
            }
        }
    }
}
