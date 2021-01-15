package com.azhyun.massxj.bean.aizhongtian;

import java.util.List;

/**
 * Created by wkk on 2020/9/27.
 */

public class NbLieBiaoBean {

    /**
     * result : {"code":"-100","message":"未知错误！","data":{}}
     * data : {"rows":[{"itemQty":1,"saleName":"农保险服务商","id":706,"companyId":0,"insuranceItem":{"img":"azt/1000/1/item/20200915/img16100145463002.jpg","subject":"雨涝","companyName":"农保险服务商","responsibility":"全险","price":12,"insuranceId":46,"id":17,"amount":"160/亩","period":"5月1日-6月1日","companyId":46,"name":"水稻防倒伏","status":1},"status":1,"amount":12},{"itemQty":1,"saleName":"农保险服务商","id":705,"companyId":0,"insuranceItem":{"img":"azt/1000/1/item/20200915/img16050132942801.jpg","subject":"全险","companyName":"农保险服务商","responsibility":"全险","price":30,"insuranceId":46,"id":14,"amount":"150/亩","period":"5月1日-10月1日","companyId":46,"name":"玉米枯叶病保险","status":1},"status":20,"amount":30},{"itemQty":1,"saleName":"农保险服务商","id":704,"companyId":0,"insuranceItem":{"img":"azt/1000/1/item/20200915/img16100145463002.jpg","subject":"雨涝","companyName":"农保险服务商","responsibility":"全险","price":12,"insuranceId":46,"id":17,"amount":"160/亩","period":"5月1日-6月1日","companyId":46,"name":"水稻防倒伏","status":1},"status":20,"amount":12}]}
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
         * code : -100
         * message : 未知错误！
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
        private List<RowsBean> rows;

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * itemQty : 1
             * saleName : 农保险服务商
             * id : 706
             * companyId : 0
             * insuranceItem : {"img":"azt/1000/1/item/20200915/img16100145463002.jpg","subject":"雨涝","companyName":"农保险服务商","responsibility":"全险","price":12,"insuranceId":46,"id":17,"amount":"160/亩","period":"5月1日-6月1日","companyId":46,"name":"水稻防倒伏","status":1}
             * status : 1
             * amount : 12
             */

            private int itemQty;
            private String saleName;
            private int id;
            private int companyId;
            private InsuranceItemBean insuranceItem;
            private int status;
            private double amount;
            private String approveTime;
            private String deliveryTime;

            public String getApproveTime() {
                return approveTime;
            }

            public void setApproveTime(String approveTime) {
                this.approveTime = approveTime;
            }

            public String getDeliveryTime() {
                return deliveryTime;
            }

            public void setDeliveryTime(String deliveryTime) {
                this.deliveryTime = deliveryTime;
            }

            public int getItemQty() {
                return itemQty;
            }

            public void setItemQty(int itemQty) {
                this.itemQty = itemQty;
            }

            public String getSaleName() {
                return saleName;
            }

            public void setSaleName(String saleName) {
                this.saleName = saleName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCompanyId() {
                return companyId;
            }

            public void setCompanyId(int companyId) {
                this.companyId = companyId;
            }

            public InsuranceItemBean getInsuranceItem() {
                return insuranceItem;
            }

            public void setInsuranceItem(InsuranceItemBean insuranceItem) {
                this.insuranceItem = insuranceItem;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public static class InsuranceItemBean {
                /**
                 * img : azt/1000/1/item/20200915/img16100145463002.jpg
                 * subject : 雨涝
                 * companyName : 农保险服务商
                 * responsibility : 全险
                 * price : 12
                 * insuranceId : 46
                 * id : 17
                 * amount : 160/亩
                 * period : 5月1日-6月1日
                 * companyId : 46
                 * name : 水稻防倒伏
                 * status : 1
                 */

                private String img;
                private String subject;
                private String companyName;
                private String responsibility;
                private double price;
                private int insuranceId;
                private int id;
                private String amount;
                private String period;
                private int companyId;
                private String name;
                private int status;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getSubject() {
                    return subject;
                }

                public void setSubject(String subject) {
                    this.subject = subject;
                }

                public String getCompanyName() {
                    return companyName;
                }

                public void setCompanyName(String companyName) {
                    this.companyName = companyName;
                }

                public String getResponsibility() {
                    return responsibility;
                }

                public void setResponsibility(String responsibility) {
                    this.responsibility = responsibility;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public int getInsuranceId() {
                    return insuranceId;
                }

                public void setInsuranceId(int insuranceId) {
                    this.insuranceId = insuranceId;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }

                public String getPeriod() {
                    return period;
                }

                public void setPeriod(String period) {
                    this.period = period;
                }

                public int getCompanyId() {
                    return companyId;
                }

                public void setCompanyId(int companyId) {
                    this.companyId = companyId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }
        }
    }
}
