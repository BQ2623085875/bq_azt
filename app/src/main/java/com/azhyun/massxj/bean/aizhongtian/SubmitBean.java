package com.azhyun.massxj.bean.aizhongtian;

import java.util.List;

/**
 * Created by dell on 2020/7/6.
 */

public class SubmitBean {

    private ResultBean result;
    private List<DataBeanX> data;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public List<DataBeanX> getData() {
        return data;
    }

    public void setData(List<DataBeanX> data) {
        this.data = data;
    }

    public static class DataBeanX {
        private String salemanCode;
        private double freight;
        private double orderAmt;
        private double itemAllAmt;
        private double memberPrice;
        private int codeStatus;
        private List<ItemListBean> itemList;

        public double getMemberPrice() {
            return memberPrice;
        }

        public double getFreight() {
            return freight;
        }

        public int getCodeStatus() {
            return codeStatus;
        }

        public double getItemAllAmt() {
            return itemAllAmt;
        }

        public double getOrderAmt() {
            return orderAmt;
        }

        public String getSalemanCode() {
            return salemanCode;
        }

        public void setMemberPrice(double memberPrice) {
            this.memberPrice = memberPrice;
        }

        public void setFreight(double freight) {
            this.freight = freight;
        }

        public void setCodeStatus(int codeStatus) {
            this.codeStatus = codeStatus;
        }

        public void setItemAllAmt(double itemAllAmt) {
            this.itemAllAmt = itemAllAmt;
        }

        public void setOrderAmt(double orderAmt) {
            this.orderAmt = orderAmt;
        }

        public void setSalemanCode(String salemanCode) {
            this.salemanCode = salemanCode;
        }

        public List<ItemListBean> getItemList() {
            return itemList;
        }

        public void setItemList(List<ItemListBean> itemList) {
            this.itemList = itemList;
        }

        public static class ItemListBean {
            private String img;
            private String companyName;
            private double discountPrice;
            private String units;
            private int promotionId;
            private String norm;
            private String itemName;
            private String promotionName;
            private double price;
            private double realityPrice;
            private String brand;
            private int skuId;
            private double memberPrice;
            private int qty;
            private int ispId;
            private int evaluateId;


            public int getQty() {
                return qty;
            }

            public double getDiscountPrice() {
                return discountPrice;
            }

            public int getEvaluateId() {
                return evaluateId;
            }

            public int getIspId() {
                return ispId;
            }

            public double getMemberPrice() {
                return memberPrice;
            }

            public double getPrice() {
                return price;
            }

            public int getPromotionId() {
                return promotionId;
            }

            public double getRealityPrice() {
                return realityPrice;
            }

            public int getSkuId() {
                return skuId;
            }

            public String getBrand() {
                return brand;
            }

            public String getCompanyName() {
                return companyName;
            }

            public String getImg() {
                return img;
            }

            public String getItemName() {
                return itemName;
            }

            public String getNorm() {
                return norm;
            }

            public String getPromotionName() {
                return promotionName;
            }

            public String getUnits() {
                return units;
            }

            public void setQty(int qty) {
                this.qty = qty;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public void setDiscountPrice(double discountPrice) {
                this.discountPrice = discountPrice;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public void setEvaluateId(int evaluateId) {
                this.evaluateId = evaluateId;
            }

            public void setIspId(int ispId) {
                this.ispId = ispId;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public void setMemberPrice(double memberPrice) {
                this.memberPrice = memberPrice;
            }

            public void setNorm(String norm) {
                this.norm = norm;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public void setPromotionId(int promotionId) {
                this.promotionId = promotionId;
            }

            public void setPromotionName(String promotionName) {
                this.promotionName = promotionName;
            }

            public void setRealityPrice(double realityPrice) {
                this.realityPrice = realityPrice;
            }

            public void setSkuId(int skuId) {
                this.skuId = skuId;
            }

            public void setUnits(String units) {
                this.units = units;
            }
        }
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
}
