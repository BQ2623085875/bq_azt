package com.azhyun.massxj.bean.aizhongtian;

import java.util.List;

/**
 * Created by wkk on 2020/9/28.
 */

public class NbOrderXQBean {

    /**
     * data : {"mob":"17800000000","payType":0,"id":710,"signTime":null,"confirmTime":"2020-09-27 13:46","insuranceItem":{"img":"azt/1000/1/item/20200915/img16100145463002.jpg","addTime":"2020-09-15 12:52","subject":"雨涝","companyName":"农保险服务商","regionName":"","units":"","insuranceBookingVo":null,"responsibility":"全险","price":12,"insuranceId":46,"onlineTime":"2020-09-15 00:00","id":17,"supplierName":"","imgs":[],"amount":"160/亩","period":"5月1日-6月1日","process":"","moreimg":[],"companyId":46,"name":"水稻防倒伏","insuranceName":"","detail":"","category":null,"region":"","status":1},"addedTime":"2020-09-08 13:46","status":4,"salemanCode":"","payNumber":"","deliveryTime":"2020-09-27 14:31","regionName":"新疆 乌鲁木齐市 天山区 延安路街道 晨光社区","remark":"","insuranceBookingVo":{"insuranceItemId":17,"address":"新疆乌鲁木齐市天山区燕儿窝街道燕儿窝南社区 ","clientId":138,"addTime":"2020-09-27 13:46","orderId":710,"idcard":"612725199507072519","mu":20,"name":"大圣","currency":100,"id":164,"region":"650102002002"},"codeId":0,"tradeStauts":"","company":{"address":"北京","serviceTel":"","name":"农保险服务商"},"approveTime":null,"amount":240,"address":"","clientId":0,"payMode":1,"fullName":"","feeAmount":0}
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

    public static class ResultBean {
        private String code;
        private String message;

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
    }

    public static class DataBean {
        /**
         * mob : 17800000000
         * payType : 0
         * id : 710
         * signTime : null
         * confirmTime : 2020-09-27 13:46
         * insuranceItem : {"img":"azt/1000/1/item/20200915/img16100145463002.jpg","addTime":"2020-09-15 12:52","subject":"雨涝","companyName":"农保险服务商","regionName":"","units":"","insuranceBookingVo":null,"responsibility":"全险","price":12,"insuranceId":46,"onlineTime":"2020-09-15 00:00","id":17,"supplierName":"","imgs":[],"amount":"160/亩","period":"5月1日-6月1日","process":"","moreimg":[],"companyId":46,"name":"水稻防倒伏","insuranceName":"","detail":"","category":null,"region":"","status":1}
         * addedTime : 2020-09-08 13:46
         * status : 4
         * salemanCode :
         * payNumber :
         * deliveryTime : 2020-09-27 14:31
         * regionName : 新疆 乌鲁木齐市 天山区 延安路街道 晨光社区
         * remark :
         * insuranceBookingVo : {"insuranceItemId":17,"address":"新疆乌鲁木齐市天山区燕儿窝街道燕儿窝南社区 ","clientId":138,"addTime":"2020-09-27 13:46","orderId":710,"idcard":"612725199507072519","mu":20,"name":"大圣","currency":100,"id":164,"region":"650102002002"}
         * codeId : 0
         * tradeStauts :
         * company : {"address":"北京","serviceTel":"","name":"农保险服务商"}
         * approveTime : null
         * amount : 240
         * address :
         * clientId : 0
         * payMode : 1
         * fullName :
         * feeAmount : 0
         */

        private String mob;
        private String payType;
        private String id;
        private String signTime;
        private String confirmTime;
        private InsuranceItemBean insuranceItem;
        private String addedTime;
        private String status;
        private String salemanCode;
        private String payNumber;
        private String deliveryTime;
        private String regionName;
        private String remark;
        private InsuranceBookingVoBean insuranceBookingVo;
        private String codeId;
        private String tradeStauts;
        private CompanyBean company;
        private double amount;
        private String address;
        private String clientId;
        private String payMode;
        private String fullName;
        private String feeAmount;
        private String approveTime;


        public String getMob() {
            return mob;
        }

        public void setMob(String mob) {
            this.mob = mob;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSignTime() {
            return signTime;
        }

        public void setSignTime(String signTime) {
            this.signTime = signTime;
        }

        public String getConfirmTime() {
            return confirmTime;
        }

        public void setConfirmTime(String confirmTime) {
            this.confirmTime = confirmTime;
        }

        public InsuranceItemBean getInsuranceItem() {
            return insuranceItem;
        }

        public void setInsuranceItem(InsuranceItemBean insuranceItem) {
            this.insuranceItem = insuranceItem;
        }

        public String getAddedTime() {
            return addedTime;
        }

        public void setAddedTime(String addedTime) {
            this.addedTime = addedTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSalemanCode() {
            return salemanCode;
        }

        public void setSalemanCode(String salemanCode) {
            this.salemanCode = salemanCode;
        }

        public String getPayNumber() {
            return payNumber;
        }

        public void setPayNumber(String payNumber) {
            this.payNumber = payNumber;
        }

        public String getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(String deliveryTime) {
            this.deliveryTime = deliveryTime;
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

        public InsuranceBookingVoBean getInsuranceBookingVo() {
            return insuranceBookingVo;
        }

        public void setInsuranceBookingVo(InsuranceBookingVoBean insuranceBookingVo) {
            this.insuranceBookingVo = insuranceBookingVo;
        }

        public String getCodeId() {
            return codeId;
        }

        public void setCodeId(String codeId) {
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

        public String getApproveTime() {
            return approveTime;
        }

        public void setApproveTime(String approveTime) {
            this.approveTime = approveTime;
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

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getPayMode() {
            return payMode;
        }

        public void setPayMode(String payMode) {
            this.payMode = payMode;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getFeeAmount() {
            return feeAmount;
        }

        public void setFeeAmount(String feeAmount) {
            this.feeAmount = feeAmount;
        }

        public static class InsuranceItemBean {
            /**
             * img : azt/1000/1/item/20200915/img16100145463002.jpg
             * addTime : 2020-09-15 12:52
             * subject : 雨涝
             * companyName : 农保险服务商
             * regionName :
             * units :
             * insuranceBookingVo : null
             * responsibility : 全险
             * price : 12
             * insuranceId : 46
             * onlineTime : 2020-09-15 00:00
             * id : 17
             * supplierName :
             * imgs : []
             * amount : 160/亩
             * period : 5月1日-6月1日
             * process :
             * moreimg : []
             * companyId : 46
             * name : 水稻防倒伏
             * insuranceName :
             * detail :
             * category : null
             * region :
             * status : 1
             */

            private String img;
            private String addTime;
            private String subject;
            private String companyName;
            private String regionName;
            private String units;
            private String insuranceBookingVo;
            private String responsibility;
            private double price;
            private String insuranceId;
            private String onlineTime;
            private String id;
            private String supplierName;
            private String amount;
            private String period;
            private String process;
            private String companyId;
            private String name;
            private String insuranceName;
            private String detail;
            private String category;
            private String region;
            private String status;
            private List<?> imgs;
            private List<?> moreimg;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getRegionName() {
                return regionName;
            }

            public void setRegionName(String regionName) {
                this.regionName = regionName;
            }

            public String getUnits() {
                return units;
            }

            public void setUnits(String units) {
                this.units = units;
            }

            public String getInsuranceBookingVo() {
                return insuranceBookingVo;
            }

            public void setInsuranceBookingVo(String insuranceBookingVo) {
                this.insuranceBookingVo = insuranceBookingVo;
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

            public String getInsuranceId() {
                return insuranceId;
            }

            public void setInsuranceId(String insuranceId) {
                this.insuranceId = insuranceId;
            }

            public String getOnlineTime() {
                return onlineTime;
            }

            public void setOnlineTime(String onlineTime) {
                this.onlineTime = onlineTime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSupplierName() {
                return supplierName;
            }

            public void setSupplierName(String supplierName) {
                this.supplierName = supplierName;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getPeriod() {
                return period;
            }

            public void setPeriod(String period) {
                this.period = period;
            }

            public String getProcess() {
                return process;
            }

            public void setProcess(String process) {
                this.process = process;
            }

            public String getCompanyId() {
                return companyId;
            }

            public void setCompanyId(String companyId) {
                this.companyId = companyId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getInsuranceName() {
                return insuranceName;
            }

            public void setInsuranceName(String insuranceName) {
                this.insuranceName = insuranceName;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public List<?> getImgs() {
                return imgs;
            }

            public void setImgs(List<?> imgs) {
                this.imgs = imgs;
            }

            public List<?> getMoreimg() {
                return moreimg;
            }

            public void setMoreimg(List<?> moreimg) {
                this.moreimg = moreimg;
            }
        }

        public static class InsuranceBookingVoBean {
            /**
             * insuranceItemId : 17
             * address : 新疆乌鲁木齐市天山区燕儿窝街道燕儿窝南社区
             * clientId : 138
             * addTime : 2020-09-27 13:46
             * orderId : 710
             * idcard : 612725199507072519
             * mu : 20
             * name : 大圣
             * currency : 100
             * id : 164
             * region : 650102002002
             */

            private String insuranceItemId;
            private String address;
            private String clientId;
            private String addTime;
            private String orderId;
            private String idcard;
            private String mu;
            private String name;
            private int currency;
            private String id;
            private String region;

            public String getInsuranceItemId() {
                return insuranceItemId;
            }

            public void setInsuranceItemId(String insuranceItemId) {
                this.insuranceItemId = insuranceItemId;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getClientId() {
                return clientId;
            }

            public void setClientId(String clientId) {
                this.clientId = clientId;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getIdcard() {
                return idcard;
            }

            public void setIdcard(String idcard) {
                this.idcard = idcard;
            }

            public String getMu() {
                return mu;
            }

            public void setMu(String mu) {
                this.mu = mu;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCurrency() {
                return currency;
            }

            public void setCurrency(int currency) {
                this.currency = currency;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }
        }

        public static class CompanyBean {
            /**
             * address : 北京
             * serviceTel :
             * name : 农保险服务商
             */

            private String address;
            private String serviceTel;
            private String name;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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
        }
    }
}
