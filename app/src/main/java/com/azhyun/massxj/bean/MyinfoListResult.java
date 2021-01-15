package com.azhyun.massxj.bean;

import java.util.List;

/**
 * Auto-generated: 2018-09-10 17:23:15
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class MyinfoListResult {

    private Result result;
    private Data data;
    public void setResult(Result result) {
        this.result = result;
    }
    public Result getResult() {
        return result;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }

    /**
     * Auto-generated: 2018-09-10 17:23:15
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


    /**
     * Auto-generated: 2018-09-10 17:23:15
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public class Data {

        private String JSESSIONID;
        private int totalrows;
        private List<Rows> rows;
        public void setJSESSIONID(String JSESSIONID) {
            this.JSESSIONID = JSESSIONID;
        }
        public String getJSESSIONID() {
            return JSESSIONID;
        }

        public void setTotalrows(int totalrows) {
            this.totalrows = totalrows;
        }
        public int getTotalrows() {
            return totalrows;
        }

        public void setRows(List<Rows> rows) {
            this.rows = rows;
        }
        public List<Rows> getRows() {
            return rows;
        }

        /**
         * Auto-generated: 2018-09-10 17:23:15
         *
         * @author bejson.com (i@bejson.com)
         * @website http://www.bejson.com/java2pojo/
         */
        public class Rows {

            private int workStatus;
            private AddTime addTime;
            private int isImg;
            private int id;
            private int type;
            private String addPerson;
            private int userId;
            private String content;
            private int workId;
            private int status;
            public void setWorkStatus(int workStatus) {
                this.workStatus = workStatus;
            }
            public int getWorkStatus() {
                return workStatus;
            }

            public void setAddTime(AddTime addTime) {
                this.addTime = addTime;
            }
            public AddTime getAddTime() {
                return addTime;
            }

            public void setIsImg(int isImg) {
                this.isImg = isImg;
            }
            public int getIsImg() {
                return isImg;
            }

            public void setId(int id) {
                this.id = id;
            }
            public int getId() {
                return id;
            }

            public void setType(int type) {
                this.type = type;
            }
            public int getType() {
                return type;
            }

            public void setAddPerson(String addPerson) {
                this.addPerson = addPerson;
            }
            public String getAddPerson() {
                return addPerson;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
            public int getUserId() {
                return userId;
            }

            public void setContent(String content) {
                this.content = content;
            }
            public String getContent() {
                return content;
            }

            public void setWorkId(int workId) {
                this.workId = workId;
            }
            public int getWorkId() {
                return workId;
            }

            public void setStatus(int status) {
                this.status = status;
            }
            public int getStatus() {
                return status;
            }

            /**
             * Auto-generated: 2018-09-10 17:23:15
             *
             * @author bejson.com (i@bejson.com)
             * @website http://www.bejson.com/java2pojo/
             */
            public class AddTime {

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
                public void setDate(int date) {
                    this.date = date;
                }
                public int getDate() {
                    return date;
                }

                public void setHours(int hours) {
                    this.hours = hours;
                }
                public int getHours() {
                    return hours;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }
                public int getSeconds() {
                    return seconds;
                }

                public void setMonth(int month) {
                    this.month = month;
                }
                public int getMonth() {
                    return month;
                }

                public void setNanos(int nanos) {
                    this.nanos = nanos;
                }
                public int getNanos() {
                    return nanos;
                }

                public void setTimezoneOffset(int timezoneOffset) {
                    this.timezoneOffset = timezoneOffset;
                }
                public int getTimezoneOffset() {
                    return timezoneOffset;
                }

                public void setYear(int year) {
                    this.year = year;
                }
                public int getYear() {
                    return year;
                }

                public void setMinutes(int minutes) {
                    this.minutes = minutes;
                }
                public int getMinutes() {
                    return minutes;
                }

                public void setTime(long time) {
                    this.time = time;
                }
                public long getTime() {
                    return time;
                }

                public void setDay(int day) {
                    this.day = day;
                }
                public int getDay() {
                    return day;
                }

            }

        }

    }
}