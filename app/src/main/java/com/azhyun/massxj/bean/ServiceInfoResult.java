package com.azhyun.massxj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2018-09-03 16:42:19
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ServiceInfoResult implements Serializable{

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
     * Auto-generated: 2018-09-03 16:42:19
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



        public class Info implements Serializable{

            private String defaultImg;
            private String description;
            private String descriptionContent;
            private int evalNum;
            private List<Evaluations> evaluations;
            private int id;
            private List<Imgs> imgs;
            private int minimumQty;
            private String name;
            private String categoryName;
            private double price;
            private String regionName;
            private String introduce;

            public String getDescriptionContent() {
                return descriptionContent;
            }

            public void setDescriptionContent(String descriptionContent) {
                this.descriptionContent = descriptionContent;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
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

            public void setEvalNum(int evalNum) {
                this.evalNum = evalNum;
            }
            public int getEvalNum() {
                return evalNum;
            }

            public void setEvaluations(List<Evaluations> evaluations) {
                this.evaluations = evaluations;
            }
            public List<Evaluations> getEvaluations() {
                return evaluations;
            }

            public void setId(int id) {
                this.id = id;
            }
            public int getId() {
                return id;
            }

            public void setImgs(List<Imgs> imgs) {
                this.imgs = imgs;
            }
            public List<Imgs> getImgs() {
                return imgs;
            }

            public void setMinimumQty(int minimumQty) {
                this.minimumQty = minimumQty;
            }
            public int getMinimumQty() {
                return minimumQty;
            }

            public void setName(String name) {
                this.name = name;
            }
            public String getName() {
                return name;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public void setRegionName(String regionName) {
                this.regionName = regionName;
            }
            public String getRegionName() {
                return regionName;
            }


            /**
             * Auto-generated: 2018-09-03 16:42:19
             *
             * @author bejson.com (i@bejson.com)
             * @website http://www.bejson.com/java2pojo/
             */
            public class Evaluations implements Serializable{

                private String addTime;
                private String content;
                private int deviceLevel;
                private String headPortrait;
                private long id;
                private List<Imgs> imgs;
                private int isImg;
                private int level;
                private long serviceLevel;
                private String userName;
                public void setAddTime(String addTime) {
                    this.addTime = addTime;
                }
                public String getAddTime() {
                    return addTime;
                }

                public void setContent(String content) {
                    this.content = content;
                }
                public String getContent() {
                    return content;
                }

                public void setDeviceLevel(int deviceLevel) {
                    this.deviceLevel = deviceLevel;
                }
                public int getDeviceLevel() {
                    return deviceLevel;
                }

                public void setHeadPortrait(String headPortrait) {
                    this.headPortrait = headPortrait;
                }
                public String getHeadPortrait() {
                    return headPortrait;
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

                public void setIsImg(int isImg) {
                    this.isImg = isImg;
                }
                public int getIsImg() {
                    return isImg;
                }

                public void setLevel(int level) {
                    this.level = level;
                }
                public int getLevel() {
                    return level;
                }

                public void setServiceLevel(long serviceLevel) {
                    this.serviceLevel = serviceLevel;
                }
                public long getServiceLevel() {
                    return serviceLevel;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }
                public String getUserName() {
                    return userName;
                }

            }

            public class Imgs implements Serializable{

                private long id;
                private String url;
                public void setId(long id) {
                    this.id = id;
                }
                public long getId() {
                    return id;
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
     * Auto-generated: 2018-09-03 16:42:19
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