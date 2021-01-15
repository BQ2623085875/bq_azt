package com.azhyun.massxj.bean;

import java.util.List;

/**
 * Created by tl on 2018/8/28.
 */


public class SupplyListResult {

    private Data data;
    private Result result;
    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }

    public void setResult(Result result) {
        this.result = result;
    }
    public Result getResult() {
        return result;
    }




    public class Data {

        private String JSESSIONID;
        private List<Rows> rows;
        private int totalrows;
        public void setJSESSIONID(String JSESSIONID) {
            this.JSESSIONID = JSESSIONID;
        }
        public String getJSESSIONID() {
            return JSESSIONID;
        }

        public void setRows(List<Rows> rows) {
            this.rows = rows;
        }
        public List<Rows> getRows() {
            return rows;
        }

        public void setTotalrows(int totalrows) {
            this.totalrows = totalrows;
        }
        public int getTotalrows() {
            return totalrows;
        }


        public class Rows {

            private String addTime;
            private String categoryName;
            private String contacts;
            private String defaultImg;
            private String description;
            private String descriptionContent;
            private int id;
            private int isTop;
            private String norms;
            private double num;
            private double price;
            private String region;
            private String title;

            public String getDescriptionContent() {
                return descriptionContent;
            }

            public void setDescriptionContent(String descriptionContent) {
                this.descriptionContent = descriptionContent;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }
            public String getAddTime() {
                return addTime;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }
            public String getCategoryName() {
                return categoryName;
            }

            public void setContacts(String contacts) {
                this.contacts = contacts;
            }
            public String getContacts() {
                return contacts;
            }

            public void setDefaultImg(String defaultImg) {
                this.defaultImg = defaultImg;
            }
            public String getDefaultImg() {
                return defaultImg;
            }

            public void setDescription(String description) {
                this.description = description;
            }
            public String getDescription() {
                return description;
            }

            public void setId(int id) {
                this.id = id;
            }
            public int getId() {
                return id;
            }

            public void setIsTop(int isTop) {
                this.isTop = isTop;
            }
            public int getIsTop() {
                return isTop;
            }

            public void setNorms(String norms) {
                this.norms = norms;
            }
            public String getNorms() {
                return norms;
            }

            public void setNum(double num) {
                this.num = num;
            }
            public double getNum() {
                return num;
            }

            public void setPrice(double price) {
                this.price = price;
            }
            public double getPrice() {
                return price;
            }

            public void setRegion(String region) {
                this.region = region;
            }
            public String getRegion() {
                return region;
            }

            public void setTitle(String title) {
                this.title = title;
            }
            public String getTitle() {
                return title;
            }

        }
    }

    public class Result {

        private String code;
        private String message;
        public void setCode(String code) {
            this.code = code;
        }
        public String getCode() {
            return code;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }

    }
}