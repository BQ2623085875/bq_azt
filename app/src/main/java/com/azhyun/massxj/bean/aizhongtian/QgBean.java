package com.azhyun.massxj.bean.aizhongtian;

import java.util.List;

/**
 * Created by wkk on 2020/8/11.
 */

public class QgBean {

    /**
     * result : {"code":"200","message":"操作成功！","data":{}}
     * demandInfo : {"num":1,"title":"1","categoryName":"玉米种子","id":9,"imgs":[{"id":36,"url":"azt/1000/1/azt/azt15971265372222.png"}],"descriptionContent":"1","headImg":"","fullName":"新疆 乌鲁木齐市 天山区 燕儿窝街道 燕儿窝南社区","phone":"14711111111","addtimes":"1597126532000","contacts":"孙先生","status":1}
     */

    private ResultBean result;
    private DemandInfoBean demandInfo;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public DemandInfoBean getDemandInfo() {
        return demandInfo;
    }

    public void setDemandInfo(DemandInfoBean demandInfo) {
        this.demandInfo = demandInfo;
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

    public static class DemandInfoBean {
        /**
         * num : 1
         * title : 1
         * categoryName : 玉米种子
         * id : 9
         * imgs : [{"id":36,"url":"azt/1000/1/azt/azt15971265372222.png"}]
         * descriptionContent : 1
         * headImg :
         * fullName : 新疆 乌鲁木齐市 天山区 燕儿窝街道 燕儿窝南社区
         * phone : 14711111111
         * addtimes : 1597126532000
         * contacts : 孙先生
         * status : 1
         */

        private int num;
        private String title;
        private String categoryName;
        private int id;
        private String descriptionContent;
        private String headImg;
        private String fullName;
        private String phone;
        private String addtimes;
        private String contacts;
        private int status;

        public String getDownlineTime() {
            return downlineTime;
        }

        public void setDownlineTime(String downlineTime) {
            this.downlineTime = downlineTime;
        }

        private String downlineTime;

        private List<ImgsBean> imgs;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
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

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddtimes() {
            return addtimes;
        }

        public void setAddtimes(String addtimes) {
            this.addtimes = addtimes;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<ImgsBean> getImgs() {
            return imgs;
        }

        public void setImgs(List<ImgsBean> imgs) {
            this.imgs = imgs;
        }

        public static class ImgsBean {
            /**
             * id : 36
             * url : azt/1000/1/azt/azt15971265372222.png
             */

            private int id;
            private String url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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
