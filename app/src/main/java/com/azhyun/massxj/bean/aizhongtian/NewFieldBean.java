package com.azhyun.massxj.bean.aizhongtian;

import java.util.List;

/**
 * Created by dell on 2020/7/8.
 */

public class NewFieldBean {

    /**
     * result : {"code":"200","message":"操作成功！","data":{}}
     * data : {"totalrows":1,"JSESSIONID":"3695F6C764B46D8CFD66CC799D163919","rows":[{"id":114,"addPerson":null,"addTime":null,"content":null,"defaultImg":"land/1000/1/land/land15936799579754.jpg","introduce":"爱种田摘要","isTop":0,"source":"爱种田文案","status":0,"title":"爱种田新品","publishTime":1593680203000,"author":"爱种田杨二狗","publishUserId":null,"downlinePerson":null,"downlineTime":null,"isRead":null,"categoryName":"试验田","mettingAddress":null,"mettingTime":null,"categoryId":null}]}
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
         * JSESSIONID : 3695F6C764B46D8CFD66CC799D163919
         * rows : [{"id":114,"addPerson":null,"addTime":null,"content":null,"defaultImg":"land/1000/1/land/land15936799579754.jpg","introduce":"爱种田摘要","isTop":0,"source":"爱种田文案","status":0,"title":"爱种田新品","publishTime":1593680203000,"author":"爱种田杨二狗","publishUserId":null,"downlinePerson":null,"downlineTime":null,"isRead":null,"categoryName":"试验田","mettingAddress":null,"mettingTime":null,"categoryId":null}]
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
             * id : 114
             * addPerson : null
             * addTime : null
             * content : null
             * defaultImg : land/1000/1/land/land15936799579754.jpg
             * introduce : 爱种田摘要
             * isTop : 0
             * source : 爱种田文案
             * status : 0
             * title : 爱种田新品
             * publishTime : 1593680203000
             * author : 爱种田杨二狗
             * publishUserId : null
             * downlinePerson : null
             * downlineTime : null
             * isRead : null
             * categoryName : 试验田
             * mettingAddress : null
             * mettingTime : null
             * categoryId : null
             */

            private int id;
            private Object addPerson;
            private Object addTime;
            private Object content;
            private String defaultImg;
            private String introduce;
            private int isTop;
            private String source;
            private int status;
            private String title;
            private long publishTime;
            private String author;
            private Object publishUserId;
            private Object downlinePerson;
            private Object downlineTime;
            private Object isRead;
            private String categoryName;
            private Object mettingAddress;
            private Object mettingTime;
            private Object categoryId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getAddPerson() {
                return addPerson;
            }

            public void setAddPerson(Object addPerson) {
                this.addPerson = addPerson;
            }

            public Object getAddTime() {
                return addTime;
            }

            public void setAddTime(Object addTime) {
                this.addTime = addTime;
            }

            public Object getContent() {
                return content;
            }

            public void setContent(Object content) {
                this.content = content;
            }

            public String getDefaultImg() {
                return defaultImg;
            }

            public void setDefaultImg(String defaultImg) {
                this.defaultImg = defaultImg;
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

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public Object getPublishUserId() {
                return publishUserId;
            }

            public void setPublishUserId(Object publishUserId) {
                this.publishUserId = publishUserId;
            }

            public Object getDownlinePerson() {
                return downlinePerson;
            }

            public void setDownlinePerson(Object downlinePerson) {
                this.downlinePerson = downlinePerson;
            }

            public Object getDownlineTime() {
                return downlineTime;
            }

            public void setDownlineTime(Object downlineTime) {
                this.downlineTime = downlineTime;
            }

            public Object getIsRead() {
                return isRead;
            }

            public void setIsRead(Object isRead) {
                this.isRead = isRead;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

            public Object getMettingAddress() {
                return mettingAddress;
            }

            public void setMettingAddress(Object mettingAddress) {
                this.mettingAddress = mettingAddress;
            }

            public Object getMettingTime() {
                return mettingTime;
            }

            public void setMettingTime(Object mettingTime) {
                this.mettingTime = mettingTime;
            }

            public Object getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(Object categoryId) {
                this.categoryId = categoryId;
            }
        }
    }
}
