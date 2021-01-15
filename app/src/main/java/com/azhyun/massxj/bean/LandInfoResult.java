package com.azhyun.massxj.bean;

import java.util.List;

/**
 * Auto-generated: 2018-09-06 19:43:55
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class LandInfoResult {

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
     * Auto-generated: 2018-09-06 19:43:55
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
         * Auto-generated: 2018-09-06 19:43:55
         *
         * @author bejson.com (i@bejson.com)
         * @website http://www.bejson.com/java2pojo/
         */
        public class Info {

            private String address;
            private double area;
            private List<AttaImgs1> attaImgs1;
            private List<AttaImgs2> attaImgs2;
            private List<AttaImgs3> attaImgs3;
            private List<AttaImgs4> attaImgs4;
            private String defaultImg;
            private String fullName;
            private int id;
            private String introduce;
            private String name;
            private String regionName;
            private int status;
            private String title;
            private String phone;
            private String note;

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            private String remark;


            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public void setAddress(String address) {
                this.address = address;
            }
            public String getAddress() {
                return address;
            }

            public void setArea(double area) {
                this.area = area;
            }
            public double getArea() {
                return area;
            }

            public void setAttaImgs1(List<AttaImgs1> attaImgs1) {
                this.attaImgs1 = attaImgs1;
            }
            public List<AttaImgs1> getAttaImgs1() {
                return attaImgs1;
            }

            public void setAttaImgs2(List<AttaImgs2> attaImgs2) {
                this.attaImgs2 = attaImgs2;
            }
            public List<AttaImgs2> getAttaImgs2() {
                return attaImgs2;
            }

            public void setAttaImgs3(List<AttaImgs3> attaImgs3) {
                this.attaImgs3 = attaImgs3;
            }
            public List<AttaImgs3> getAttaImgs3() {
                return attaImgs3;
            }

            public void setAttaImgs4(List<AttaImgs4> attaImgs4) {
                this.attaImgs4 = attaImgs4;
            }
            public List<AttaImgs4> getAttaImgs4() {
                return attaImgs4;
            }

            public void setDefaultImg(String defaultImg) {
                this.defaultImg = defaultImg;
            }
            public String getDefaultImg() {
                return defaultImg;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }
            public String getFullName() {
                return fullName;
            }

            public void setId(int id) {
                this.id = id;
            }
            public int getId() {
                return id;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }
            public String getIntroduce() {
                return introduce;
            }

            public void setName(String name) {
                this.name = name;
            }
            public String getName() {
                return name;
            }

            public void setRegionName(String regionName) {
                this.regionName = regionName;
            }
            public String getRegionName() {
                return regionName;
            }

            public void setStatus(int status) {
                this.status = status;
            }
            public int getStatus() {
                return status;
            }

            public void setTitle(String title) {
                this.title = title;
            }
            public String getTitle() {
                return title;
            }


            /**
             * Auto-generated: 2018-09-06 19:43:55
             *
             * @author bejson.com (i@bejson.com)
             * @website http://www.bejson.com/java2pojo/
             */
            public class AttaImgs4 {

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


            /**
             * Auto-generated: 2018-09-06 19:43:55
             *
             * @author bejson.com (i@bejson.com)
             * @website http://www.bejson.com/java2pojo/
             */
            public class AttaImgs3 {

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

            /**
             * Auto-generated: 2018-09-06 19:43:55
             *
             * @author bejson.com (i@bejson.com)
             * @website http://www.bejson.com/java2pojo/
             */
            public class AttaImgs2 {

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

            /**
             * Auto-generated: 2018-09-06 19:43:55
             *
             * @author bejson.com (i@bejson.com)
             * @website http://www.bejson.com/java2pojo/
             */
            public class AttaImgs1 {

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
     * Auto-generated: 2018-09-06 19:43:55
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