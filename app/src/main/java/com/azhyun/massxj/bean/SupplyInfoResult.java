package com.azhyun.massxj.bean;

import java.util.List;

/**
 * Auto-generated: 2018-08-31 12:59:56
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class SupplyInfoResult {

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



    /**
     * Auto-generated: 2018-08-31 13:30:49
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public class Data {

        private String JSESSIONID;
        private Info info;
        public void setJSESSIONID(String JSESSIONID) {
            this.JSESSIONID = JSESSIONID;
        }
        public String getJSESSIONID() {
            return JSESSIONID;
        }

        public void setInfo(Info info) {
            this.info = info;
        }
        public Info getInfo() {
            return info;
        }


        /**
         * Auto-generated: 2018-08-31 13:30:49
         *
         * @author bejson.com (i@bejson.com)
         * @website http://www.bejson.com/java2pojo/
         */
        public class Info {

            private String addTime;
            private String categoryName;
            private String contacts;
            private String defaultImg;
            private String description;
            private String fullName;
            private long id;
            private List<Imgs> imgs;
            private int managerRole;
            private String norms;
            private double num;
            private String phone;
            private double price;
            private String region;
            private String title;
            private String headPortrait;
            private String descriptionContent;
            private int type;

            public String getDescriptionContent() {
                return descriptionContent;
            }

            public void setDescriptionContent(String descriptionContent) {
                this.descriptionContent = descriptionContent;
            }

            public String getHeadPortrait() {
                return headPortrait;
            }

            public void setHeadPortrait(String headPortrait) {
                this.headPortrait = headPortrait;
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

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }
            public String getFullName() {
                return fullName;
            }

            public void setId(long id) {
                this.id = id;
            }
            public long getId() {
                return id;
            }

            public void setImgs(List<Imgs> imgs) {
                this.imgs = imgs;
            }
            public List<Imgs> getImgs() {
                return imgs;
            }

            public void setManagerRole(int managerRole) {
                this.managerRole = managerRole;
            }
            public int getManagerRole() {
                return managerRole;
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

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
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

            public void setType(int type) {
                this.type = type;
            }
            public int getType() {
                return type;
            }


            /**
             * Auto-generated: 2018-08-31 13:30:49
             *
             * @author bejson.com (i@bejson.com)
             * @website http://www.bejson.com/java2pojo/
             */
            public class Imgs {

                private long id;
                private int seq;
                private String url;
                public void setId(long id) {
                    this.id = id;
                }
                public long getId() {
                    return id;
                }

                public void setSeq(int seq) {
                    this.seq = seq;
                }
                public int getSeq() {
                    return seq;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
                public String getUrl() {
                    return url;
                }

            }

        }

    }

    /**
     * Auto-generated: 2018-08-31 12:59:56
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
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