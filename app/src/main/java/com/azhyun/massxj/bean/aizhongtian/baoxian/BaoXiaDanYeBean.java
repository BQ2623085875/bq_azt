package com.azhyun.massxj.bean.aizhongtian.baoxian;

import java.util.List;

/**
 * Created by wkk on 2020/9/15.
 */

public class BaoXiaDanYeBean {

    /**
     * data : [{"salemanCode":"","freight":0,"orderAmt":250,"result":null,"itemAllAmt":0,"memberPrice":0,"codeStatus":0,"itemList":[{"img":"azt/1000/1/item/20200915/img16700134096227.jpg","supplierId":0,"companyName":"农保险服务商","discountPrice":0,"units":"","promotionId":0,"norm":"","itemName":"棉花锈病","promotionName":"","responsibility":"全险","price":50,"realityPrice":0,"onLineTitle":"","brand":"","skuId":0,"amount":"150/亩","memberPrice":0,"mu":5,"idcard":"152323199312521025","qty":5,"ispId":0,"evaluateId":0}]}]
     * result : {"code":"200","data":{},"message":"获取信息成功！"}
     */

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

    public static class ResultBean {
        /**
         * code : 200
         * data : {}
         * message : 获取信息成功！
         */

        private String code;
        private DataBean data;
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public static class DataBean {
        }
    }

    public static class DataBeanX {
        /**
         * salemanCode :
         * freight : 0
         * orderAmt : 250
         * result : null
         * itemAllAmt : 0
         * memberPrice : 0
         * codeStatus : 0
         * itemList : [{"img":"azt/1000/1/item/20200915/img16700134096227.jpg","supplierId":0,"companyName":"农保险服务商","discountPrice":0,"units":"","promotionId":0,"norm":"","itemName":"棉花锈病","promotionName":"","responsibility":"全险","price":50,"realityPrice":0,"onLineTitle":"","brand":"","skuId":0,"amount":"150/亩","memberPrice":0,"mu":5,"idcard":"152323199312521025","qty":5,"ispId":0,"evaluateId":0}]
         */

        private String salemanCode;
        private String freight;
        private String orderAmt;
        private String result;
        private String itemAllAmt;
        private String memberPrice;
        private String codeStatus;
        private List<ItemListBean> itemList;

        public String getSalemanCode() {
            return salemanCode;
        }

        public void setSalemanCode(String salemanCode) {
            this.salemanCode = salemanCode;
        }

        public String getFreight() {
            return freight;
        }

        public void setFreight(String freight) {
            this.freight = freight;
        }

        public String getOrderAmt() {
            return orderAmt;
        }

        public void setOrderAmt(String orderAmt) {
            this.orderAmt = orderAmt;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getItemAllAmt() {
            return itemAllAmt;
        }

        public void setItemAllAmt(String itemAllAmt) {
            this.itemAllAmt = itemAllAmt;
        }

        public String getMemberPrice() {
            return memberPrice;
        }

        public void setMemberPrice(String memberPrice) {
            this.memberPrice = memberPrice;
        }

        public String getCodeStatus() {
            return codeStatus;
        }

        public void setCodeStatus(String codeStatus) {
            this.codeStatus = codeStatus;
        }

        public List<ItemListBean> getItemList() {
            return itemList;
        }

        public void setItemList(List<ItemListBean> itemList) {
            this.itemList = itemList;
        }

        public static class ItemListBean {
            /**
             * img : azt/1000/1/item/20200915/img16700134096227.jpg
             * supplierId : 0
             * companyName : 农保险服务商
             * discountPrice : 0
             * units :
             * promotionId : 0
             * norm :
             * itemName : 棉花锈病
             * promotionName :
             * responsibility : 全险
             * price : 50
             * realityPrice : 0
             * onLineTitle :
             * brand :
             * skuId : 0
             * amount : 150/亩
             * memberPrice : 0
             * mu : 5
             * idcard : 152323199312521025
             * qty : 5
             * ispId : 0
             * evaluateId : 0
             */

            private String img;
            private String supplierId;
            private String companyName;
            private String discountPrice;
            private String units;
            private String promotionId;
            private String norm;
            private String itemName;
            private String promotionName;
            private String responsibility;
            private String price;
            private String realityPrice;
            private String onLineTitle;
            private String brand;
            private String skuId;
            private String amount;
            private String memberPrice;
            private String mu;
            private String idcard;
            private String qty;
            private String ispId;
            private String evaluateId;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getSupplierId() {
                return supplierId;
            }

            public void setSupplierId(String supplierId) {
                this.supplierId = supplierId;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getDiscountPrice() {
                return discountPrice;
            }

            public void setDiscountPrice(String discountPrice) {
                this.discountPrice = discountPrice;
            }

            public String getUnits() {
                return units;
            }

            public void setUnits(String units) {
                this.units = units;
            }

            public String getPromotionId() {
                return promotionId;
            }

            public void setPromotionId(String promotionId) {
                this.promotionId = promotionId;
            }

            public String getNorm() {
                return norm;
            }

            public void setNorm(String norm) {
                this.norm = norm;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public String getPromotionName() {
                return promotionName;
            }

            public void setPromotionName(String promotionName) {
                this.promotionName = promotionName;
            }

            public String getResponsibility() {
                return responsibility;
            }

            public void setResponsibility(String responsibility) {
                this.responsibility = responsibility;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getRealityPrice() {
                return realityPrice;
            }

            public void setRealityPrice(String realityPrice) {
                this.realityPrice = realityPrice;
            }

            public String getOnLineTitle() {
                return onLineTitle;
            }

            public void setOnLineTitle(String onLineTitle) {
                this.onLineTitle = onLineTitle;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getSkuId() {
                return skuId;
            }

            public void setSkuId(String skuId) {
                this.skuId = skuId;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getMemberPrice() {
                return memberPrice;
            }

            public void setMemberPrice(String memberPrice) {
                this.memberPrice = memberPrice;
            }

            public String getMu() {
                return mu;
            }

            public void setMu(String mu) {
                this.mu = mu;
            }

            public String getIdcard() {
                return idcard;
            }

            public void setIdcard(String idcard) {
                this.idcard = idcard;
            }

            public String getQty() {
                return qty;
            }

            public void setQty(String qty) {
                this.qty = qty;
            }

            public String getIspId() {
                return ispId;
            }

            public void setIspId(String ispId) {
                this.ispId = ispId;
            }

            public String getEvaluateId() {
                return evaluateId;
            }

            public void setEvaluateId(String evaluateId) {
                this.evaluateId = evaluateId;
            }
        }
    }
}
