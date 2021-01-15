package com.azhyun.massxj.bean;

import java.util.List;

/**
 * Created by tl on 2019/1/16.
 */

public class HomeArticleShowResult {
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    private Data data;
    private Result result;

    public Result getResult() {
        return result;
    }

    public class Result {
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

    public class Data {
        private String JSESSIONID ;
        private List<Rows> rows;
        private int totalrows;

        public String getJSESSIONID() {
            return JSESSIONID;
        }

        public void setJSESSIONID(String JSESSIONID) {
            this.JSESSIONID = JSESSIONID;
        }

        public List<Rows> getRows() {
            return rows;
        }

        public void setRows(List<Rows> rows) {
            this.rows = rows;
        }

        public int getTotalrows() {
            return totalrows;
        }

        public void setTotalrows(int totalrows) {
            this.totalrows = totalrows;
        }


        public class Rows {
            private String author;
            private String categoryName;
            private String defaultImg;
            private int id;
            private String introduce;
            private int isRead;
            private int isTop;
            private String mettingAddress;
            private long mettingTime;
            private long publishTime;
            private String source;
            private String  title;

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

            public String getDefaultImg() {
                return defaultImg;
            }

            public void setDefaultImg(String defaultImg) {
                this.defaultImg = defaultImg;
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

            public int getIsRead() {
                return isRead;
            }

            public void setIsRead(int isRead) {
                this.isRead = isRead;
            }

            public int getIsTop() {
                return isTop;
            }

            public void setIsTop(int isTop) {
                this.isTop = isTop;
            }

            public String getMettingAddress() {
                return mettingAddress;
            }

            public void setMettingAddress(String mettingAddress) {
                this.mettingAddress = mettingAddress;
            }

            public long getMettingTime() {
                return mettingTime;
            }

            public void setMettingTime(long mettingTime) {
                this.mettingTime = mettingTime;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
