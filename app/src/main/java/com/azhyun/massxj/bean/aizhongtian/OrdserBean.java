package com.azhyun.massxj.bean.aizhongtian;

import java.util.List;

/**
 * Created by dell on 2020/7/7.
 */

public class OrdserBean {


    /**
     * data : [{"itemQty":1,"orderType":0,"payOrderId":"","promotionId":0,"retailPayAmount":0,"points":0,"saleName":"测试采收","payAmount":0,"payType":0,"serviceTel":"","contact":"","id":669,"invoiceTitle":"","signTime":null,"confirmTime":null,"commentStatus":0,"logisticsName":"","couponDiscount":0,"finaBillId":0,"warehouseId":0,"logisticsNo":"","items":[{"img":"azt/1000/1/item/20200907/img15994686284064.jpg","supplierId":0,"discountPrice":0,"units":"袋","promotionId":0,"norm":"21","itemName":"33","promotionName":"","price":0,"realityPrice":0,"onLineTitle":"33","brand":"33","seq":0,"skuId":181,"memberPrice":0,"itemId":199,"qty":1,"ispId":0,"categoryId":"","evaluateId":0}],"party":0,"status":1,"finaSettleStatus":0,"salemanCode":"","code":"","payNumber":"","deliveryTime":null,"clientName":"","schemeId":0,"codeId":0,"tradeStauts":"","approveTime":null,"amount":0,"clientId":0,"isPolicy":0,"payMode":1,"pointsAmount":0,"fullName":"","prepaid":0},{"itemQty":1,"orderType":0,"payOrderId":"","promotionId":0,"retailPayAmount":0,"points":0,"saleName":"测试采收","payAmount":0,"payType":0,"serviceTel":"","contact":"","id":668,"invoiceTitle":"","signTime":null,"confirmTime":null,"commentStatus":0,"logisticsName":"","couponDiscount":0,"finaBillId":0,"warehouseId":0,"logisticsNo":"","items":[{"img":"azt/1000/1/item/20200907/img15994677797891.jpg","supplierId":0,"discountPrice":0,"units":"袋","promotionId":0,"norm":"21","itemName":"采收5","promotionName":"","price":0,"realityPrice":0,"onLineTitle":"好","brand":"采收5","seq":0,"skuId":180,"memberPrice":0,"itemId":197,"qty":1,"ispId":0,"categoryId":"","evaluateId":0}],"party":0,"status":1,"finaSettleStatus":0,"salemanCode":"","code":"","payNumber":"","deliveryTime":null,"clientName":"","schemeId":0,"codeId":0,"tradeStauts":"","approveTime":null,"amount":0,"clientId":0,"isPolicy":0,"payMode":1,"pointsAmount":0,"fullName":"","prepaid":0},{"itemQty":1,"orderType":0,"payOrderId":"","promotionId":0,"retailPayAmount":0,"points":0,"saleName":"测试采收","payAmount":0,"payType":0,"serviceTel":"","contact":"","id":667,"invoiceTitle":"","signTime":null,"confirmTime":null,"commentStatus":0,"logisticsName":"","couponDiscount":0,"finaBillId":0,"warehouseId":0,"logisticsNo":"","items":[{"img":"azt/1000/1/item/20200907/img15939460924960.jpg","supplierId":0,"discountPrice":0,"units":"袋","promotionId":0,"norm":"21","itemName":"采收1","promotionName":"","price":0,"realityPrice":0,"onLineTitle":"好","brand":"采收1","seq":0,"skuId":179,"memberPrice":0,"itemId":196,"qty":1,"ispId":0,"categoryId":"","evaluateId":0}],"party":0,"status":1,"finaSettleStatus":0,"salemanCode":"","code":"","payNumber":"","deliveryTime":null,"clientName":"","schemeId":0,"codeId":0,"tradeStauts":"","approveTime":null,"amount":0,"clientId":0,"isPolicy":0,"payMode":1,"pointsAmount":0,"fullName":"","prepaid":0},{"itemQty":1,"orderType":0,"payOrderId":"","promotionId":0,"retailPayAmount":0,"points":0,"saleName":"测试采收","payAmount":0,"payType":0,"serviceTel":"","contact":"","id":666,"invoiceTitle":"","signTime":null,"confirmTime":null,"commentStatus":0,"logisticsName":"","couponDiscount":0,"finaBillId":0,"warehouseId":0,"logisticsNo":"","items":[{"img":"azt/1000/1/item/20200907/img15939460924960.jpg","supplierId":0,"discountPrice":0,"units":"袋","promotionId":0,"norm":"21","itemName":"采收1","promotionName":"","price":0,"realityPrice":0,"onLineTitle":"好","brand":"采收1","seq":0,"skuId":179,"memberPrice":0,"itemId":196,"qty":1,"ispId":0,"categoryId":"","evaluateId":0}],"party":0,"status":1,"finaSettleStatus":0,"salemanCode":"","code":"","payNumber":"","deliveryTime":null,"clientName":"","schemeId":0,"codeId":0,"tradeStauts":"","approveTime":null,"amount":0,"clientId":0,"isPolicy":0,"payMode":1,"pointsAmount":0,"fullName":"","prepaid":0},{"itemQty":8,"orderType":0,"payOrderId":"","promotionId":0,"retailPayAmount":0,"points":0,"saleName":"测试采收","payAmount":0,"payType":0,"serviceTel":"","contact":"","id":665,"invoiceTitle":"","signTime":null,"confirmTime":null,"commentStatus":0,"logisticsName":"","couponDiscount":0,"finaBillId":0,"warehouseId":0,"logisticsNo":"","items":[{"img":"azt/1000/1/item/20200907/img15939460924960.jpg","supplierId":0,"discountPrice":0,"units":"袋","promotionId":0,"norm":"21","itemName":"采收1","promotionName":"","price":0,"realityPrice":0,"onLineTitle":"好","brand":"采收1","seq":0,"skuId":179,"memberPrice":0,"itemId":196,"qty":8,"ispId":0,"categoryId":"","evaluateId":0}],"party":0,"status":1,"finaSettleStatus":0,"salemanCode":"","code":"","payNumber":"","deliveryTime":null,"clientName":"","schemeId":0,"codeId":0,"tradeStauts":"","approveTime":null,"amount":0,"clientId":0,"isPolicy":0,"payMode":1,"pointsAmount":0,"fullName":"","prepaid":0}]
     * result : {"code":"200","data":{},"message":"获取订单列表成功！"}
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
         * message : 获取订单列表成功！
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
         * itemQty : 1
         * orderType : 0
         * payOrderId :
         * promotionId : 0
         * retailPayAmount : 0
         * points : 0
         * saleName : 测试采收
         * payAmount : 0
         * payType : 0
         * serviceTel :
         * contact :
         * id : 669
         * invoiceTitle :
         * signTime : null
         * confirmTime : null
         * commentStatus : 0
         * logisticsName :
         * couponDiscount : 0
         * finaBillId : 0
         * warehouseId : 0
         * logisticsNo :
         * items : [{"img":"azt/1000/1/item/20200907/img15994686284064.jpg","supplierId":0,"discountPrice":0,"units":"袋","promotionId":0,"norm":"21","itemName":"33","promotionName":"","price":0,"realityPrice":0,"onLineTitle":"33","brand":"33","seq":0,"skuId":181,"memberPrice":0,"itemId":199,"qty":1,"ispId":0,"categoryId":"","evaluateId":0}]
         * party : 0
         * status : 1
         * finaSettleStatus : 0
         * salemanCode :
         * code :
         * payNumber :
         * deliveryTime : null
         * clientName :
         * schemeId : 0
         * codeId : 0
         * tradeStauts :
         * approveTime : null
         * amount : 0
         * clientId : 0
         * isPolicy : 0
         * payMode : 1
         * pointsAmount : 0
         * fullName :
         * prepaid : 0
         */

        private int itemQty;
        private int orderType;
        private String payOrderId;
        private int promotionId;
        private double retailPayAmount;
        private int points;
        private String saleName;
        private double payAmount;
        private int payType;
        private String serviceTel;
        private String contact;
        private int id;
        private String invoiceTitle;
        private Object signTime;
        private Object confirmTime;
        private int commentStatus;
        private String logisticsName;
        private int couponDiscount;
        private int finaBillId;
        private int warehouseId;
        private String logisticsNo;
        private int party;
        private int status;
        private int finaSettleStatus;
        private String salemanCode;
        private String code;
        private String payNumber;
        private Object deliveryTime;
        private String clientName;
        private int schemeId;
        private int codeId;
        private String tradeStauts;
        private Object approveTime;
        private double amount;
        private int clientId;
        private int isPolicy;
        private int payMode;
        private double pointsAmount;
        private String fullName;
        private int prepaid;
        private List<ItemsBean> items;

        public int getItemQty() {
            return itemQty;
        }

        public void setItemQty(int itemQty) {
            this.itemQty = itemQty;
        }

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public String getPayOrderId() {
            return payOrderId;
        }

        public void setPayOrderId(String payOrderId) {
            this.payOrderId = payOrderId;
        }

        public int getPromotionId() {
            return promotionId;
        }

        public void setPromotionId(int promotionId) {
            this.promotionId = promotionId;
        }

        public double getRetailPayAmount() {
            return retailPayAmount;
        }

        public void setRetailPayAmount(double retailPayAmount) {
            this.retailPayAmount = retailPayAmount;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public String getSaleName() {
            return saleName;
        }

        public void setSaleName(String saleName) {
            this.saleName = saleName;
        }

        public double getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(double payAmount) {
            this.payAmount = payAmount;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getServiceTel() {
            return serviceTel;
        }

        public void setServiceTel(String serviceTel) {
            this.serviceTel = serviceTel;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInvoiceTitle() {
            return invoiceTitle;
        }

        public void setInvoiceTitle(String invoiceTitle) {
            this.invoiceTitle = invoiceTitle;
        }

        public Object getSignTime() {
            return signTime;
        }

        public void setSignTime(Object signTime) {
            this.signTime = signTime;
        }

        public Object getConfirmTime() {
            return confirmTime;
        }

        public void setConfirmTime(Object confirmTime) {
            this.confirmTime = confirmTime;
        }

        public int getCommentStatus() {
            return commentStatus;
        }

        public void setCommentStatus(int commentStatus) {
            this.commentStatus = commentStatus;
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

        public int getFinaBillId() {
            return finaBillId;
        }

        public void setFinaBillId(int finaBillId) {
            this.finaBillId = finaBillId;
        }

        public int getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(int warehouseId) {
            this.warehouseId = warehouseId;
        }

        public String getLogisticsNo() {
            return logisticsNo;
        }

        public void setLogisticsNo(String logisticsNo) {
            this.logisticsNo = logisticsNo;
        }

        public int getParty() {
            return party;
        }

        public void setParty(int party) {
            this.party = party;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getFinaSettleStatus() {
            return finaSettleStatus;
        }

        public void setFinaSettleStatus(int finaSettleStatus) {
            this.finaSettleStatus = finaSettleStatus;
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

        public Object getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(Object deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }

        public int getSchemeId() {
            return schemeId;
        }

        public void setSchemeId(int schemeId) {
            this.schemeId = schemeId;
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

        public Object getApproveTime() {
            return approveTime;
        }

        public void setApproveTime(Object approveTime) {
            this.approveTime = approveTime;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public int getClientId() {
            return clientId;
        }

        public void setClientId(int clientId) {
            this.clientId = clientId;
        }

        public int getIsPolicy() {
            return isPolicy;
        }

        public void setIsPolicy(int isPolicy) {
            this.isPolicy = isPolicy;
        }

        public int getPayMode() {
            return payMode;
        }

        public void setPayMode(int payMode) {
            this.payMode = payMode;
        }

        public double getPointsAmount() {
            return pointsAmount;
        }

        public void setPointsAmount(double pointsAmount) {
            this.pointsAmount = pointsAmount;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public int getPrepaid() {
            return prepaid;
        }

        public void setPrepaid(int prepaid) {
            this.prepaid = prepaid;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * img : azt/1000/1/item/20200907/img15994686284064.jpg
             * supplierId : 0
             * discountPrice : 0
             * units : 袋
             * promotionId : 0
             * norm : 21
             * itemName : 33
             * promotionName :
             * price : 0
             * realityPrice : 0
             * onLineTitle : 33
             * brand : 33
             * seq : 0
             * skuId : 181
             * memberPrice : 0
             * itemId : 199
             * qty : 1
             * ispId : 0
             * categoryId :
             * evaluateId : 0
             */

            private String img;
            private int supplierId;
            private double discountPrice;
            private String units;
            private int promotionId;
            private String norm;
            private String itemName;
            private String promotionName;
            private double price;
            private double realityPrice;
            private String onLineTitle;
            private String brand;
            private int seq;
            private int skuId;
            private double memberPrice;
            private int itemId;
            private int qty;
            private int ispId;
            private String categoryId;
            private int evaluateId;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getSupplierId() {
                return supplierId;
            }

            public void setSupplierId(int supplierId) {
                this.supplierId = supplierId;
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

            public int getSeq() {
                return seq;
            }

            public void setSeq(int seq) {
                this.seq = seq;
            }

            public int getSkuId() {
                return skuId;
            }

            public void setSkuId(int skuId) {
                this.skuId = skuId;
            }

            public double getMemberPrice() {
                return memberPrice;
            }

            public void setMemberPrice(double memberPrice) {
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

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
            }

            public int getEvaluateId() {
                return evaluateId;
            }

            public void setEvaluateId(int evaluateId) {
                this.evaluateId = evaluateId;
            }
        }
    }
}
