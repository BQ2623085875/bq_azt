package com.azhyun.massxj.bean.aizhongtian;

import java.util.List;

/**
 * Created by dell on 2020/7/8.
 */

public class OrdserDetailsBean {

    /**
     * data : {"mob":"","logisticsId":0,"discount":0,"retailPayAmount":5251.23,"payAmount":0,"payType":0,"tel":"","id":573,"consignee":"","logisticsName":"","couponDiscount":0,"addedTime":"2020-07-07 16:39","logisticsNo":"","items":[{"img":"store/1000/1/item/20200702/img15936548252714.jpg","discountPrice":0,"units":"kg","promotionId":0,"norm":"100kg/保险","itemName":"保你无恙","promotionName":"","price":250,"realityPrice":0,"brand":"中国人寿","skuId":150,"memberPrice":0,"itemId":165,"qty":21,"ispId":0,"evaluateId":0}],"status":3,"salemanCode":"","code":"","payNumber":"","regionName":"","remark":"","codeId":0,"tradeStauts":"","company":{"address":"123123123","banner2":"","banner3":"","banners":[],"content":"","banner1":"","serviceTel":"","name":"常山造纸公司","notice":""},"amount":5250,"address":"","clientId":0,"payMode":4,"fullName":"","feeAmount":1.23}
     * result : {"code":"200","data":{},"message":"获取订单详情成功！"}
     */

    private DataBean data;
    private ResultBean result;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class DataBean {
        /**
         * mob :
         * logisticsId : 0
         * discount : 0
         * retailPayAmount : 5251.23
         * payAmount : 0
         * payType : 0
         * tel :
         * id : 573
         * consignee :
         * logisticsName :
         * couponDiscount : 0
         * addedTime : 2020-07-07 16:39
         * logisticsNo :
         * items : [{"img":"store/1000/1/item/20200702/img15936548252714.jpg","discountPrice":0,"units":"kg","promotionId":0,"norm":"100kg/保险","itemName":"保你无恙","promotionName":"","price":250,"realityPrice":0,"brand":"中国人寿","skuId":150,"memberPrice":0,"itemId":165,"qty":21,"ispId":0,"evaluateId":0}]
         * status : 3
         * salemanCode :
         * code :
         * payNumber :
         * regionName :
         * remark :
         * codeId : 0
         * tradeStauts :
         * company : {"address":"123123123","banner2":"","banner3":"","banners":[],"content":"","banner1":"","serviceTel":"","name":"常山造纸公司","notice":""}
         * amount : 5250
         * address :
         * clientId : 0
         * payMode : 4
         * fullName :
         * feeAmount : 1.23
         */

        private String mob;
        private int logisticsId;
        private int discount;
        private double retailPayAmount;
        private int payAmount;
        private int payType;
        private String tel;
        private int id;
        private String consignee;
        private String logisticsName;
        private int couponDiscount;
        private String addedTime;
        private String signTime;
        private String logisticsNo;
        private int status;
        private String salemanCode;
        private String code;
        private String payNumber;
        private String regionName;
        private String remark;
        private int codeId;
        private String tradeStauts;
        private CompanyBean company;
        private double amount;
        private String address;
        private int clientId;
        private int payMode;
        private String fullName;
        private String payOrderId;
        private double feeAmount;
        private List<ItemsBean> items;

        public String getPayOrderId() {
            return payOrderId;
        }

        public void setPayOrderId(String payOrderId) {
            this.payOrderId = payOrderId;
        }

        public String getSignTime() {
            return signTime;
        }

        public void setSignTime(String signTime) {
            this.signTime = signTime;
        }

        public String getMob() {
            return mob;
        }

        public void setMob(String mob) {
            this.mob = mob;
        }

        public int getLogisticsId() {
            return logisticsId;
        }

        public void setLogisticsId(int logisticsId) {
            this.logisticsId = logisticsId;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public double getRetailPayAmount() {
            return retailPayAmount;
        }

        public void setRetailPayAmount(double retailPayAmount) {
            this.retailPayAmount = retailPayAmount;
        }

        public int getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(int payAmount) {
            this.payAmount = payAmount;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getLogisticsName() {
            return logisticsName;
        }

        public void setLogisticsName(String logisticsName) {
            this.logisticsName = logisticsName;
        }

        public int getCouponDiscount() {
            return couponDiscount;
        }

        public void setCouponDiscount(int couponDiscount) {
            this.couponDiscount = couponDiscount;
        }

        public String getAddedTime() {
            return addedTime;
        }

        public void setAddedTime(String addedTime) {
            this.addedTime = addedTime;
        }

        public String getLogisticsNo() {
            return logisticsNo;
        }

        public void setLogisticsNo(String logisticsNo) {
            this.logisticsNo = logisticsNo;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getSalemanCode() {
            return salemanCode;
        }

        public void setSalemanCode(String salemanCode) {
            this.salemanCode = salemanCode;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPayNumber() {
            return payNumber;
        }

        public void setPayNumber(String payNumber) {
            this.payNumber = payNumber;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getCodeId() {
            return codeId;
        }

        public void setCodeId(int codeId) {
            this.codeId = codeId;
        }

        public String getTradeStauts() {
            return tradeStauts;
        }

        public void setTradeStauts(String tradeStauts) {
            this.tradeStauts = tradeStauts;
        }

        public CompanyBean getCompany() {
            return company;
        }

        public void setCompany(CompanyBean company) {
            this.company = company;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getClientId() {
            return clientId;
        }

        public void setClientId(int clientId) {
            this.clientId = clientId;
        }

        public int getPayMode() {
            return payMode;
        }

        public void setPayMode(int payMode) {
            this.payMode = payMode;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public double getFeeAmount() {
            return feeAmount;
        }

        public void setFeeAmount(double feeAmount) {
            this.feeAmount = feeAmount;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class CompanyBean {
            /**
             * address : 123123123
             * banner2 :
             * banner3 :
             * banners : []
             * content :
             * banner1 :
             * serviceTel :
             * name : 常山造纸公司
             * notice :
             */

            private String address;
            private String banner2;
            private String banner3;
            private String content;
            private String banner1;
            private String serviceTel;
            private String name;
            private String notice;
            private List<?> banners;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getBanner2() {
                return banner2;
            }

            public void setBanner2(String banner2) {
                this.banner2 = banner2;
            }

            public String getBanner3() {
                return banner3;
            }

            public void setBanner3(String banner3) {
                this.banner3 = banner3;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getBanner1() {
                return banner1;
            }

            public void setBanner1(String banner1) {
                this.banner1 = banner1;
            }

            public String getServiceTel() {
                return serviceTel;
            }

            public void setServiceTel(String serviceTel) {
                this.serviceTel = serviceTel;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public List<?> getBanners() {
                return banners;
            }

            public void setBanners(List<?> banners) {
                this.banners = banners;
            }
        }

        public static class ItemsBean {
            /**
             * img : store/1000/1/item/20200702/img15936548252714.jpg
             * discountPrice : 0
             * units : kg
             * promotionId : 0
             * norm : 100kg/保险
             * itemName : 保你无恙
             * promotionName :
             * price : 250
             * realityPrice : 0
             * brand : 中国人寿
             * skuId : 150
             * memberPrice : 0
             * itemId : 165
             * qty : 21
             * ispId : 0
             * evaluateId : 0
             */

            private String img;
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
            private int memberPrice;
            private int itemId;
            private int qty;
            private int ispId;
            private int evaluateId;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public double getDiscountPrice() {
                return discountPrice;
            }

            public void setDiscountPrice(double discountPrice) {
                this.discountPrice = discountPrice;
            }

            public String getUnits() {
                return units;
            }

            public void setUnits(String units) {
                this.units = units;
            }

            public int getPromotionId() {
                return promotionId;
            }

            public void setPromotionId(int promotionId) {
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

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getRealityPrice() {
                return realityPrice;
            }

            public void setRealityPrice(double realityPrice) {
                this.realityPrice = realityPrice;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public int getSkuId() {
                return skuId;
            }

            public void setSkuId(int skuId) {
                this.skuId = skuId;
            }

            public int getMemberPrice() {
                return memberPrice;
            }

            public void setMemberPrice(int memberPrice) {
                this.memberPrice = memberPrice;
            }

            public int getItemId() {
                return itemId;
            }

            public void setItemId(int itemId) {
                this.itemId = itemId;
            }

            public int getQty() {
                return qty;
            }

            public void setQty(int qty) {
                this.qty = qty;
            }

            public int getIspId() {
                return ispId;
            }

            public void setIspId(int ispId) {
                this.ispId = ispId;
            }

            public int getEvaluateId() {
                return evaluateId;
            }

            public void setEvaluateId(int evaluateId) {
                this.evaluateId = evaluateId;
            }
        }
    }

    public static class ResultBean {
        /**
         * code : 200
         * data : {}
         * message : 获取订单详情成功！
         */

        private String code;
        private DataBeanX data;
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public DataBeanX getData() {
            return data;
        }

        public void setData(DataBeanX data) {
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public static class DataBeanX {
        }
    }
}
