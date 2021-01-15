package com.azhyun.massxj.bean.aizhongtian;

import java.util.List;

/**
 * Created by wkk on 2020/8/11.
 */

public class GongXuFenLeiBean {

    /**
     * result : {"code":"200","message":"操作成功！","data":{}}
     * data : [{"name":"玉米","id":4,"parentId":0,"child":[{"name":"玉米种子","id":21,"parentId":4,"child":[]}]},{"name":"新品种","id":13,"parentId":0,"child":[]},{"name":"炼金","id":19,"parentId":0,"child":[{"name":"丹药","id":20,"parentId":19,"child":[]}]}]
     */

    private ResultBean result;
    private List<DataBeanX> data;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public List<DataBeanX> getData() {
        return data;
    }

    public void setData(List<DataBeanX> data) {
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
         * name : 玉米
         * id : 4
         * parentId : 0
         * child : [{"name":"玉米种子","id":21,"parentId":4,"child":[]}]
         */

        private String name;
        private int id;
        private int parentId;
        private List<ChildBean> child;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public List<ChildBean> getChild() {
            return child;
        }

        public void setChild(List<ChildBean> child) {
            this.child = child;
        }

        public static class ChildBean {
            /**
             * name : 玉米种子
             * id : 21
             * parentId : 4
             * child : []
             */
            private int p = -1;
            private int a = 0;
            private String name;
            private int id;
            private int parentId;
            private List<?> child;
            public int getA() {
                return a;
            }

            public void setA(int a) {
                this.a = a;
            }

            public void setP(int p) {
                this.p = p;
            }

            public int getP() {
                return p;
            }


            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public List<?> getChild() {
                return child;
            }

            public void setChild(List<?> child) {
                this.child = child;
            }
        }
    }
}
