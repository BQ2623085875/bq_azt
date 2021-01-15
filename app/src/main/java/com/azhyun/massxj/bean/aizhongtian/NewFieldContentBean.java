package com.azhyun.massxj.bean.aizhongtian;

/**
 * Created by dell on 2020/7/8.
 */

public class NewFieldContentBean {

    /**
     * result : {"code":"200","message":"操作成功！","data":{}}
     * data : {"JSESSIONID":"ABD42B7798034710273944A6E467C593","title":"爱种田新品","defaultImg":"land/1000/1/land/land15936799579754.jpg","introduce":"爱种田摘要","content":"20200702/arts/art15936799192143.html","source":"爱种田文案","author":"爱种田杨二狗","id":114,"publishTime":{"date":2,"hours":16,"seconds":43,"month":6,"nanos":0,"timezoneOffset":-480,"year":120,"minutes":56,"time":1593680203000,"day":4},"publishPerson":"一号员工","mettingTime":"","mettingAddress":"","contentStr":"爱种田内容<br>"}
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
         * JSESSIONID : ABD42B7798034710273944A6E467C593
         * title : 爱种田新品
         * defaultImg : land/1000/1/land/land15936799579754.jpg
         * introduce : 爱种田摘要
         * content : 20200702/arts/art15936799192143.html
         * source : 爱种田文案
         * author : 爱种田杨二狗
         * id : 114
         * publishTime : {"date":2,"hours":16,"seconds":43,"month":6,"nanos":0,"timezoneOffset":-480,"year":120,"minutes":56,"time":1593680203000,"day":4}
         * publishPerson : 一号员工
         * mettingTime :
         * mettingAddress :
         * contentStr : 爱种田内容<br>
         */

        private String JSESSIONID;
        private String title;
        private String defaultImg;
        private String introduce;
        private String content;
        private String source;
        private String author;
        private int id;
        private PublishTimeBean publishTime;
        private String publishPerson;
        private String mettingTime;
        private String mettingAddress;
        private String contentStr;

        public String getJSESSIONID() {
            return JSESSIONID;
        }

        public void setJSESSIONID(String JSESSIONID) {
            this.JSESSIONID = JSESSIONID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public PublishTimeBean getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(PublishTimeBean publishTime) {
            this.publishTime = publishTime;
        }

        public String getPublishPerson() {
            return publishPerson;
        }

        public void setPublishPerson(String publishPerson) {
            this.publishPerson = publishPerson;
        }

        public String getMettingTime() {
            return mettingTime;
        }

        public void setMettingTime(String mettingTime) {
            this.mettingTime = mettingTime;
        }

        public String getMettingAddress() {
            return mettingAddress;
        }

        public void setMettingAddress(String mettingAddress) {
            this.mettingAddress = mettingAddress;
        }

        public String getContentStr() {
            return contentStr;
        }

        public void setContentStr(String contentStr) {
            this.contentStr = contentStr;
        }

        public static class PublishTimeBean {
            /**
             * date : 2
             * hours : 16
             * seconds : 43
             * month : 6
             * nanos : 0
             * timezoneOffset : -480
             * year : 120
             * minutes : 56
             * time : 1593680203000
             * day : 4
             */

            private int date;
            private int hours;
            private int seconds;
            private int month;
            private int nanos;
            private int timezoneOffset;
            private int year;
            private int minutes;
            private long time;
            private int day;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }
        }
    }
}
