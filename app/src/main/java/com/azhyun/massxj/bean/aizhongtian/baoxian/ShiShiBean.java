package com.azhyun.massxj.bean.aizhongtian.baoxian;

import java.util.List;

/**
 * Created by wkk on 2020/9/17.
 */

public class ShiShiBean {
    private List<RowsBean> rowsBeans;

    public List<RowsBean> getRowsBeans() {
        return rowsBeans;
    }

    public void setRowsBeans(List<RowsBean> rowsBeans) {
        this.rowsBeans = rowsBeans;
    }

    public static class RowsBean {
        private String name;
        private boolean isCheck;

        @Override
        public String toString() {
            return "RowsBean{" +
                    "name='" + name + '\'' +
                    ", isCheck=" + isCheck +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }
    }
}
