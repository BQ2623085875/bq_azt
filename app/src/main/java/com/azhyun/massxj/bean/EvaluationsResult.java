package com.azhyun.massxj.bean;

import java.util.List;

/**
 * Auto-generated: 2018-09-05 14:7:58
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class EvaluationsResult {

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
     * Auto-generated: 2018-09-05 14:7:58
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public class Data {

        private String JSESSIONID;
        private long haveImg;
        private int level1;
        private long level2;
        private int level3;
        private List<Rows> rows;
        private int totalrows;
        public void setJSESSIONID(String JSESSIONID) {
            this.JSESSIONID = JSESSIONID;
        }
        public String getJSESSIONID() {
            return JSESSIONID;
        }

        public void setHaveImg(long haveImg) {
            this.haveImg = haveImg;
        }
        public long getHaveImg() {
            return haveImg;
        }

        public void setLevel1(int level1) {
            this.level1 = level1;
        }
        public int getLevel1() {
            return level1;
        }

        public void setLevel2(long level2) {
            this.level2 = level2;
        }
        public long getLevel2() {
            return level2;
        }

        public void setLevel3(int level3) {
            this.level3 = level3;
        }
        public int getLevel3() {
            return level3;
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



        /**
         * Auto-generated: 2018-09-05 14:7:58
         *
         * @author bejson.com (i@bejson.com)
         * @website http://www.bejson.com/java2pojo/
         */
        public class Rows {

            private String addTime;
            private String content;
            private int deviceLevel;
            private String headPortrait;
            private long id;
            private List<Imgs> imgs;
            private int isImg;
            private int level;
            private int serviceLevel;
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

            public void setServiceLevel(int serviceLevel) {
                this.serviceLevel = serviceLevel;
            }
            public int getServiceLevel() {
                return serviceLevel;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
            public String getUserName() {
                return userName;
            }


            /**
             * Auto-generated: 2018-09-05 14:7:58
             *
             * @author bejson.com (i@bejson.com)
             * @website http://www.bejson.com/java2pojo/
             */
            public class Imgs {

                private int id;
                private String url;
                public void setId(int id) {
                    this.id = id;
                }
                public int getId() {
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
     * Auto-generated: 2018-09-05 14:7:58
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