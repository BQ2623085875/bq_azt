package com.azhyun.massxj.bean;

import java.util.List;

/**
 * Auto-generated: 2018-09-08 15:11:23
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class MyManageListResult {

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
     * Auto-generated: 2018-09-08 15:11:23
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public class Data {

        private String JSESSIONID;
        private double areaSum;
        private String county;
        private int managerRole;
        private String region;
        private List<Rows> rows;
        private int totalrows;
        private String town;
        private String village;
        public void setJSESSIONID(String JSESSIONID) {
            this.JSESSIONID = JSESSIONID;
        }
        public String getJSESSIONID() {
            return JSESSIONID;
        }

        public void setAreaSum(double areaSum) {
            this.areaSum = areaSum;
        }
        public double getAreaSum() {
            return areaSum;
        }

        public void setCounty(String county) {
            this.county = county;
        }
        public String getCounty() {
            return county;
        }

        public void setManagerRole(int managerRole) {
            this.managerRole = managerRole;
        }
        public int getManagerRole() {
            return managerRole;
        }

        public void setRegion(String region) {
            this.region = region;
        }
        public String getRegion() {
            return region;
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

        public void setTown(String town) {
            this.town = town;
        }
        public String getTown() {
            return town;
        }

        public void setVillage(String village) {
            this.village = village;
        }
        public String getVillage() {
            return village;
        }


        /**
         * Auto-generated: 2018-09-08 15:11:23
         *
         * @author bejson.com (i@bejson.com)
         * @website http://www.bejson.com/java2pojo/
         */
        public class Rows {

            private int applyRole;
            private double areaSum;
            private String mobilePhone;
            private String name;
            private String sex;
            private String regionName;
            private int userId;

            public String getRegionName() {
                return regionName;
            }

            public void setRegionName(String regionName) {
                this.regionName = regionName;
            }

            public void setApplyRole(int applyRole) {
                this.applyRole = applyRole;
            }
            public int getApplyRole() {
                return applyRole;
            }

            public void setArea(double area) {
                this.areaSum = area;
            }
            public double getArea() {
                return areaSum;
            }

            public void setMobilePhone(String mobilePhone) {
                this.mobilePhone = mobilePhone;
            }
            public String getMobilePhone() {
                return mobilePhone;
            }

            public void setName(String name) {
                this.name = name;
            }
            public String getName() {
                return name;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }
            public String getSex() {
                return sex;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
            public int getUserId() {
                return userId;
            }

        }

    }

    /**
     * Auto-generated: 2018-09-08 15:11:23
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