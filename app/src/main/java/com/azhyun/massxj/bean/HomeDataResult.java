package com.azhyun.massxj.bean;

import java.util.List;

/**
 * Created by tl on 2018/8/27.
 */

public class HomeDataResult {


    /**
     * result : {"code":"200","message":"操作成功！","data":{}}
     * data : {"item":[{"id":179,"brand":"爱种网","categoryId":null,"companyCategoryId":null,"companyCategorytwoId":null,"companyId":0,"defaultImg":"store/1000/1/item/20200710/img15994371209375.jpg","detail":null,"isOnline":0,"name":"测试水稻0710005","onlineApprover":null,"onlineStatus":0,"onlineTime":null,"onlineTitle":"销售标题5","status":0,"onlineOutlet":0,"feature":null,"categoryName":null,"ccName":null,"remark":null,"company":null,"imgs":[],"skus":[],"prepaidFlag":0,"deliveryTime":null,"prepaidRatio":0,"tempCategoryId":null,"lookOnline":null,"adapRegion":null,"listSheng":null,"listShi":null,"region":null,"price":0,"isExtend":0,"homeExtend":0,"units":null,"supplierId":0,"supplier":null,"updateTime":null,"addedTime":null,"approveTime":null,"approver":null,"seq":5,"itemSkuImgs":null,"moreimg":null,"names":null,"ids":null,"fileId":null,"viewUrl":null,"type":null,"labelId":null,"oldLabelId":null,"labelIdName":null,"labelList":null,"skuMemberPrice":null,"regionIds":null,"regionNames":null,"category":null},{"id":178,"brand":"爱种网","categoryId":null,"companyCategoryId":null,"companyCategorytwoId":null,"companyId":0,"defaultImg":"store/1000/1/item/20200710/img15943502169830.jpg","detail":null,"isOnline":0,"name":"测试水稻0710004","onlineApprover":null,"onlineStatus":0,"onlineTime":null,"onlineTitle":"销售标题4","status":0,"onlineOutlet":0,"feature":null,"categoryName":null,"ccName":null,"remark":null,"company":null,"imgs":[],"skus":[],"prepaidFlag":0,"deliveryTime":null,"prepaidRatio":0,"tempCategoryId":null,"lookOnline":null,"adapRegion":null,"listSheng":null,"listShi":null,"region":null,"price":36.9,"isExtend":0,"homeExtend":0,"units":null,"supplierId":0,"supplier":null,"updateTime":null,"addedTime":null,"approveTime":null,"approver":null,"seq":5,"itemSkuImgs":null,"moreimg":null,"names":null,"ids":null,"fileId":null,"viewUrl":null,"type":null,"labelId":null,"oldLabelId":null,"labelIdName":null,"labelList":null,"skuMemberPrice":null,"regionIds":null,"regionNames":null,"category":null},{"id":171,"brand":"爱种网","categoryId":null,"companyCategoryId":null,"companyCategorytwoId":null,"companyId":0,"defaultImg":"store/1000/1/item/20200709/img15924274682430.jpg","detail":null,"isOnline":0,"name":"测试水稻0709004","onlineApprover":null,"onlineStatus":0,"onlineTime":null,"onlineTitle":"销售标题","status":0,"onlineOutlet":0,"feature":null,"categoryName":null,"ccName":null,"remark":null,"company":null,"imgs":[],"skus":[],"prepaidFlag":0,"deliveryTime":null,"prepaidRatio":0,"tempCategoryId":null,"lookOnline":null,"adapRegion":null,"listSheng":null,"listShi":null,"region":null,"price":3.2,"isExtend":0,"homeExtend":0,"units":null,"supplierId":0,"supplier":null,"updateTime":null,"addedTime":null,"approveTime":null,"approver":null,"seq":5,"itemSkuImgs":null,"moreimg":null,"names":null,"ids":null,"fileId":null,"viewUrl":null,"type":null,"labelId":null,"oldLabelId":null,"labelIdName":null,"labelList":null,"skuMemberPrice":null,"regionIds":null,"regionNames":null,"category":null}],"articleNum":0,"JSESSIONID":"DEB258A5DD223D41E17B27B6DA5179C6","articles":[],"banners":[{"id":40,"endTime":null,"startTime":null,"status":0,"title":"0709004","type":4,"visitUrl":"","url":"land/1000/1/land/land15942828017090.jpg"},{"id":39,"endTime":null,"startTime":null,"status":0,"title":"0709002","type":3,"visitUrl":"","url":"land/1000/1/land/land15942818931292.jpg"},{"id":38,"endTime":null,"startTime":null,"status":0,"title":"0709001","type":2,"visitUrl":"http://www.baidu.com","url":"land/1000/1/land/land15942811886509.jpg"},{"id":37,"endTime":null,"startTime":null,"status":0,"title":"试验田","type":1,"visitUrl":"","url":"land/1000/1/land/land15942811734126.jpg"}]}
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
         * item : [{"id":179,"brand":"爱种网","categoryId":null,"companyCategoryId":null,"companyCategorytwoId":null,"companyId":0,"defaultImg":"store/1000/1/item/20200710/img15994371209375.jpg","detail":null,"isOnline":0,"name":"测试水稻0710005","onlineApprover":null,"onlineStatus":0,"onlineTime":null,"onlineTitle":"销售标题5","status":0,"onlineOutlet":0,"feature":null,"categoryName":null,"ccName":null,"remark":null,"company":null,"imgs":[],"skus":[],"prepaidFlag":0,"deliveryTime":null,"prepaidRatio":0,"tempCategoryId":null,"lookOnline":null,"adapRegion":null,"listSheng":null,"listShi":null,"region":null,"price":0,"isExtend":0,"homeExtend":0,"units":null,"supplierId":0,"supplier":null,"updateTime":null,"addedTime":null,"approveTime":null,"approver":null,"seq":5,"itemSkuImgs":null,"moreimg":null,"names":null,"ids":null,"fileId":null,"viewUrl":null,"type":null,"labelId":null,"oldLabelId":null,"labelIdName":null,"labelList":null,"skuMemberPrice":null,"regionIds":null,"regionNames":null,"category":null},{"id":178,"brand":"爱种网","categoryId":null,"companyCategoryId":null,"companyCategorytwoId":null,"companyId":0,"defaultImg":"store/1000/1/item/20200710/img15943502169830.jpg","detail":null,"isOnline":0,"name":"测试水稻0710004","onlineApprover":null,"onlineStatus":0,"onlineTime":null,"onlineTitle":"销售标题4","status":0,"onlineOutlet":0,"feature":null,"categoryName":null,"ccName":null,"remark":null,"company":null,"imgs":[],"skus":[],"prepaidFlag":0,"deliveryTime":null,"prepaidRatio":0,"tempCategoryId":null,"lookOnline":null,"adapRegion":null,"listSheng":null,"listShi":null,"region":null,"price":36.9,"isExtend":0,"homeExtend":0,"units":null,"supplierId":0,"supplier":null,"updateTime":null,"addedTime":null,"approveTime":null,"approver":null,"seq":5,"itemSkuImgs":null,"moreimg":null,"names":null,"ids":null,"fileId":null,"viewUrl":null,"type":null,"labelId":null,"oldLabelId":null,"labelIdName":null,"labelList":null,"skuMemberPrice":null,"regionIds":null,"regionNames":null,"category":null},{"id":171,"brand":"爱种网","categoryId":null,"companyCategoryId":null,"companyCategorytwoId":null,"companyId":0,"defaultImg":"store/1000/1/item/20200709/img15924274682430.jpg","detail":null,"isOnline":0,"name":"测试水稻0709004","onlineApprover":null,"onlineStatus":0,"onlineTime":null,"onlineTitle":"销售标题","status":0,"onlineOutlet":0,"feature":null,"categoryName":null,"ccName":null,"remark":null,"company":null,"imgs":[],"skus":[],"prepaidFlag":0,"deliveryTime":null,"prepaidRatio":0,"tempCategoryId":null,"lookOnline":null,"adapRegion":null,"listSheng":null,"listShi":null,"region":null,"price":3.2,"isExtend":0,"homeExtend":0,"units":null,"supplierId":0,"supplier":null,"updateTime":null,"addedTime":null,"approveTime":null,"approver":null,"seq":5,"itemSkuImgs":null,"moreimg":null,"names":null,"ids":null,"fileId":null,"viewUrl":null,"type":null,"labelId":null,"oldLabelId":null,"labelIdName":null,"labelList":null,"skuMemberPrice":null,"regionIds":null,"regionNames":null,"category":null}]
         * articleNum : 0
         * JSESSIONID : DEB258A5DD223D41E17B27B6DA5179C6
         * articles : []
         * banners : [{"id":40,"endTime":null,"startTime":null,"status":0,"title":"0709004","type":4,"visitUrl":"","url":"land/1000/1/land/land15942828017090.jpg"},{"id":39,"endTime":null,"startTime":null,"status":0,"title":"0709002","type":3,"visitUrl":"","url":"land/1000/1/land/land15942818931292.jpg"},{"id":38,"endTime":null,"startTime":null,"status":0,"title":"0709001","type":2,"visitUrl":"http://www.baidu.com","url":"land/1000/1/land/land15942811886509.jpg"},{"id":37,"endTime":null,"startTime":null,"status":0,"title":"试验田","type":1,"visitUrl":"","url":"land/1000/1/land/land15942811734126.jpg"}]
         */

        private int articleNum;
        private String JSESSIONID;
        private List<ItemBean> item;
        private List<ArticlesBean> articles;
        private List<BannersBean> banners;

        public int getArticleNum() {
            return articleNum;
        }

        public void setArticleNum(int articleNum) {
            this.articleNum = articleNum;
        }

        public String getJSESSIONID() {
            return JSESSIONID;
        }

        public void setJSESSIONID(String JSESSIONID) {
            this.JSESSIONID = JSESSIONID;
        }

        public List<ItemBean> getItem() {
            return item;
        }

        public void setItem(List<ItemBean> item) {
            this.item = item;
        }

        public List<ArticlesBean> getArticles() {
            return articles;
        }

        public void setArticles(List<ArticlesBean> articles) {
            this.articles = articles;
        }

        public List<BannersBean> getBanners() {
            return banners;
        }

        public void setBanners(List<BannersBean> banners) {
            this.banners = banners;
        }

        public static class ArticlesBean {
            /**
             * addPerson : 一号员工
             * addTime : 1593679994000
             * content : 20200702/arts/art15936799192143.html
             * id : 114
             * introduce : 爱种田摘要
             * isTop : 0
             * source : 爱种田文案
             * status : 2
             * title : 爱种田新品
             */

            private String addPerson;
            private long addTime;
            private String content;
            private int id;
            private String introduce;
            private int isTop;
            private String source;
            private int status;
            private String title;

            public String getAddPerson() {
                return addPerson;
            }

            public void setAddPerson(String addPerson) {
                this.addPerson = addPerson;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public int getIsTop() {
                return isTop;
            }

            public void setIsTop(int isTop) {
                this.isTop = isTop;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class ItemBean {
            /**
             * id : 179
             * brand : 爱种网
             * categoryId : null
             * companyCategoryId : null
             * companyCategorytwoId : null
             * companyId : 0
             * defaultImg : store/1000/1/item/20200710/img15994371209375.jpg
             * detail : null
             * isOnline : 0
             * name : 测试水稻0710005
             * onlineApprover : null
             * onlineStatus : 0
             * onlineTime : null
             * onlineTitle : 销售标题5
             * status : 0
             * onlineOutlet : 0
             * feature : null
             * categoryName : null
             * ccName : null
             * remark : null
             * company : null
             * imgs : []
             * skus : []
             * prepaidFlag : 0
             * deliveryTime : null
             * prepaidRatio : 0
             * tempCategoryId : null
             * lookOnline : null
             * adapRegion : null
             * listSheng : null
             * listShi : null
             * region : null
             * price : 0
             * isExtend : 0
             * homeExtend : 0
             * units : null
             * supplierId : 0
             * supplier : null
             * updateTime : null
             * addedTime : null
             * approveTime : null
             * approver : null
             * seq : 5
             * itemSkuImgs : null
             * moreimg : null
             * names : null
             * ids : null
             * fileId : null
             * viewUrl : null
             * type : null
             * labelId : null
             * oldLabelId : null
             * labelIdName : null
             * labelList : null
             * skuMemberPrice : null
             * regionIds : null
             * regionNames : null
             * category : null
             */

            private int id;
            private String brand;
            private Object categoryId;
            private Object companyCategoryId;
            private Object companyCategorytwoId;
            private int companyId;
            private String defaultImg;
            private Object detail;
            private int isOnline;
            private String name;
            private Object onlineApprover;
            private int onlineStatus;
            private Object onlineTime;
            private String onlineTitle;
            private int status;
            private int onlineOutlet;
            private Object feature;
            private Object categoryName;
            private Object ccName;
            private Object remark;
            private Object company;
            private int prepaidFlag;
            private Object deliveryTime;
            private int prepaidRatio;
            private Object tempCategoryId;
            private Object lookOnline;
            private Object adapRegion;
            private Object listSheng;
            private Object listShi;
            private Object region;
            private double price;
            private int isExtend;
            private int homeExtend;
            private Object units;
            private int supplierId;
            private Object supplier;
            private Object updateTime;
            private Object addedTime;
            private Object approveTime;
            private Object approver;
            private int seq;
            private Object itemSkuImgs;
            private Object moreimg;
            private Object names;
            private Object ids;
            private Object fileId;
            private Object viewUrl;
            private Object type;
            private Object labelId;
            private Object oldLabelId;
            private Object labelIdName;
            private Object labelList;
            private Object skuMemberPrice;
            private Object regionIds;
            private Object regionNames;
            private Object category;
            private List<?> imgs;
            private List<?> skus;

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

            public Object getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(Object categoryId) {
                this.categoryId = categoryId;
            }

            public Object getCompanyCategoryId() {
                return companyCategoryId;
            }

            public void setCompanyCategoryId(Object companyCategoryId) {
                this.companyCategoryId = companyCategoryId;
            }

            public Object getCompanyCategorytwoId() {
                return companyCategorytwoId;
            }

            public void setCompanyCategorytwoId(Object companyCategorytwoId) {
                this.companyCategorytwoId = companyCategorytwoId;
            }

            public int getCompanyId() {
                return companyId;
            }

            public void setCompanyId(int companyId) {
                this.companyId = companyId;
            }

            public String getDefaultImg() {
                return defaultImg;
            }

            public void setDefaultImg(String defaultImg) {
                this.defaultImg = defaultImg;
            }

            public Object getDetail() {
                return detail;
            }

            public void setDetail(Object detail) {
                this.detail = detail;
            }

            public int getIsOnline() {
                return isOnline;
            }

            public void setIsOnline(int isOnline) {
                this.isOnline = isOnline;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getOnlineApprover() {
                return onlineApprover;
            }

            public void setOnlineApprover(Object onlineApprover) {
                this.onlineApprover = onlineApprover;
            }

            public int getOnlineStatus() {
                return onlineStatus;
            }

            public void setOnlineStatus(int onlineStatus) {
                this.onlineStatus = onlineStatus;
            }

            public Object getOnlineTime() {
                return onlineTime;
            }

            public void setOnlineTime(Object onlineTime) {
                this.onlineTime = onlineTime;
            }

            public String getOnlineTitle() {
                return onlineTitle;
            }

            public void setOnlineTitle(String onlineTitle) {
                this.onlineTitle = onlineTitle;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getOnlineOutlet() {
                return onlineOutlet;
            }

            public void setOnlineOutlet(int onlineOutlet) {
                this.onlineOutlet = onlineOutlet;
            }

            public Object getFeature() {
                return feature;
            }

            public void setFeature(Object feature) {
                this.feature = feature;
            }

            public Object getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(Object categoryName) {
                this.categoryName = categoryName;
            }

            public Object getCcName() {
                return ccName;
            }

            public void setCcName(Object ccName) {
                this.ccName = ccName;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public Object getCompany() {
                return company;
            }

            public void setCompany(Object company) {
                this.company = company;
            }

            public int getPrepaidFlag() {
                return prepaidFlag;
            }

            public void setPrepaidFlag(int prepaidFlag) {
                this.prepaidFlag = prepaidFlag;
            }

            public Object getDeliveryTime() {
                return deliveryTime;
            }

            public void setDeliveryTime(Object deliveryTime) {
                this.deliveryTime = deliveryTime;
            }

            public int getPrepaidRatio() {
                return prepaidRatio;
            }

            public void setPrepaidRatio(int prepaidRatio) {
                this.prepaidRatio = prepaidRatio;
            }

            public Object getTempCategoryId() {
                return tempCategoryId;
            }

            public void setTempCategoryId(Object tempCategoryId) {
                this.tempCategoryId = tempCategoryId;
            }

            public Object getLookOnline() {
                return lookOnline;
            }

            public void setLookOnline(Object lookOnline) {
                this.lookOnline = lookOnline;
            }

            public Object getAdapRegion() {
                return adapRegion;
            }

            public void setAdapRegion(Object adapRegion) {
                this.adapRegion = adapRegion;
            }

            public Object getListSheng() {
                return listSheng;
            }

            public void setListSheng(Object listSheng) {
                this.listSheng = listSheng;
            }

            public Object getListShi() {
                return listShi;
            }

            public void setListShi(Object listShi) {
                this.listShi = listShi;
            }

            public Object getRegion() {
                return region;
            }

            public void setRegion(Object region) {
                this.region = region;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getIsExtend() {
                return isExtend;
            }

            public void setIsExtend(int isExtend) {
                this.isExtend = isExtend;
            }

            public int getHomeExtend() {
                return homeExtend;
            }

            public void setHomeExtend(int homeExtend) {
                this.homeExtend = homeExtend;
            }

            public Object getUnits() {
                return units;
            }

            public void setUnits(Object units) {
                this.units = units;
            }

            public int getSupplierId() {
                return supplierId;
            }

            public void setSupplierId(int supplierId) {
                this.supplierId = supplierId;
            }

            public Object getSupplier() {
                return supplier;
            }

            public void setSupplier(Object supplier) {
                this.supplier = supplier;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getAddedTime() {
                return addedTime;
            }

            public void setAddedTime(Object addedTime) {
                this.addedTime = addedTime;
            }

            public Object getApproveTime() {
                return approveTime;
            }

            public void setApproveTime(Object approveTime) {
                this.approveTime = approveTime;
            }

            public Object getApprover() {
                return approver;
            }

            public void setApprover(Object approver) {
                this.approver = approver;
            }

            public int getSeq() {
                return seq;
            }

            public void setSeq(int seq) {
                this.seq = seq;
            }

            public Object getItemSkuImgs() {
                return itemSkuImgs;
            }

            public void setItemSkuImgs(Object itemSkuImgs) {
                this.itemSkuImgs = itemSkuImgs;
            }

            public Object getMoreimg() {
                return moreimg;
            }

            public void setMoreimg(Object moreimg) {
                this.moreimg = moreimg;
            }

            public Object getNames() {
                return names;
            }

            public void setNames(Object names) {
                this.names = names;
            }

            public Object getIds() {
                return ids;
            }

            public void setIds(Object ids) {
                this.ids = ids;
            }

            public Object getFileId() {
                return fileId;
            }

            public void setFileId(Object fileId) {
                this.fileId = fileId;
            }

            public Object getViewUrl() {
                return viewUrl;
            }

            public void setViewUrl(Object viewUrl) {
                this.viewUrl = viewUrl;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public Object getLabelId() {
                return labelId;
            }

            public void setLabelId(Object labelId) {
                this.labelId = labelId;
            }

            public Object getOldLabelId() {
                return oldLabelId;
            }

            public void setOldLabelId(Object oldLabelId) {
                this.oldLabelId = oldLabelId;
            }

            public Object getLabelIdName() {
                return labelIdName;
            }

            public void setLabelIdName(Object labelIdName) {
                this.labelIdName = labelIdName;
            }

            public Object getLabelList() {
                return labelList;
            }

            public void setLabelList(Object labelList) {
                this.labelList = labelList;
            }

            public Object getSkuMemberPrice() {
                return skuMemberPrice;
            }

            public void setSkuMemberPrice(Object skuMemberPrice) {
                this.skuMemberPrice = skuMemberPrice;
            }

            public Object getRegionIds() {
                return regionIds;
            }

            public void setRegionIds(Object regionIds) {
                this.regionIds = regionIds;
            }

            public Object getRegionNames() {
                return regionNames;
            }

            public void setRegionNames(Object regionNames) {
                this.regionNames = regionNames;
            }

            public Object getCategory() {
                return category;
            }

            public void setCategory(Object category) {
                this.category = category;
            }

            public List<?> getImgs() {
                return imgs;
            }

            public void setImgs(List<?> imgs) {
                this.imgs = imgs;
            }

            public List<?> getSkus() {
                return skus;
            }

            public void setSkus(List<?> skus) {
                this.skus = skus;
            }
        }

        public static class BannersBean {
            /**
             * id : 40
             * endTime : null
             * startTime : null
             * status : 0
             * title : 0709004
             * type : 4
             * visitUrl :
             * url : land/1000/1/land/land15942828017090.jpg
             */

            private int id;
            private Object endTime;
            private Object startTime;
            private int status;
            private String title;
            private int type;
            private String visitUrl;
            private String url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }

            public Object getStartTime() {
                return startTime;
            }

            public void setStartTime(Object startTime) {
                this.startTime = startTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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

            public String getVisitUrl() {
                return visitUrl;
            }

            public void setVisitUrl(String visitUrl) {
                this.visitUrl = visitUrl;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
