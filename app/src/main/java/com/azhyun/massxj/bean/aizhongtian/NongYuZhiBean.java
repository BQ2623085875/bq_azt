package com.azhyun.massxj.bean.aizhongtian;

import java.util.List;

/**
 * Created by dell on 2020/8/6.
 */

public class NongYuZhiBean {

    /**
     * result : {"code":"200","message":"操作成功！"}
     * currency : {"currency":30,"details":[{"addTime":"2020-08-03 17:40:39","remark":"积分兑换农誉","detail":"+50","type":1},{"addTime":"2020-08-03 17:40:08","remark":"农誉购买商品","detail":"-30","type":2},{"addTime":"2020-08-03 17:39:46","remark":"积分兑换农誉","detail":"+200","type":1}],"credit":200}
     */

    private ResultBean result;
    private CurrencyBean currency;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public CurrencyBean getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyBean currency) {
        this.currency = currency;
    }

    public static class ResultBean {
        /**
         * code : 200
         * message : 操作成功！
         */

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

    public static class CurrencyBean {
        /**
         * currency : 30
         * details : [{"addTime":"2020-08-03 17:40:39","remark":"积分兑换农誉","detail":"+50","type":1},{"addTime":"2020-08-03 17:40:08","remark":"农誉购买商品","detail":"-30","type":2},{"addTime":"2020-08-03 17:39:46","remark":"积分兑换农誉","detail":"+200","type":1}]
         * credit : 200
         */

        private String currency;
        private String credit;
        private List<DetailsBean> details;

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public List<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsBean> details) {
            this.details = details;
        }

        public static class DetailsBean {
            /**
             * addTime : 2020-08-03 17:40:39
             * remark : 积分兑换农誉
             * detail : +50
             * type : 1
             */

            private String addTime;
            private String remark;
            private String detail;
            private int type;

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
