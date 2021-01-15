package com.azhyun.massxj.bean.aizhongtian;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wkk on 2020/9/7.
 */

public class CaiGouXQBean implements Serializable {


    /**
     * result : {"code":"200","message":"操作成功！","data":{}}
     * data : {"id":202,"brand":"爱种牌","isHarvestItem":1,"imgs":[{"id":{"itemId":202,"seq":0},"url":"azt/1000/1/item/20200907/img15994771440022.jpg"}],"name":"采收测试","detail":"","skus":[{"supplierId":47,"units":"袋","moq":5,"norm":"21","id":190,"prices":[{"id":{"itemId":190},"retailPrice":23.6}],"seq":0,"supplierName":"测试采收","imgs":[],"itemId":202,"name":"爱种牌采收测试 21"}],"supplierId":0,"units":"","onlineTitle":"好","seq":5,"adapRegion":""}
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

    public static class ResultBean implements Serializable{
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

    public static class DataBeanX implements Serializable{
        /**
         * id : 202
         * brand : 爱种牌
         * isHarvestItem : 1
         * imgs : [{"id":{"itemId":202,"seq":0},"url":"azt/1000/1/item/20200907/img15994771440022.jpg"}]
         * name : 采收测试
         * detail :
         * skus : [{"supplierId":47,"units":"袋","moq":5,"norm":"21","id":190,"prices":[{"id":{"itemId":190},"retailPrice":23.6}],"seq":0,"supplierName":"测试采收","imgs":[],"itemId":202,"name":"爱种牌采收测试 21"}]
         * supplierId : 0
         * units :
         * onlineTitle : 好
         * seq : 5
         * adapRegion :
         */

        private int id;
        private String brand;
        private int isHarvestItem;
        private String regionIds;
        private String name;
        private String detail;
        private int supplierId;
        private String units;
        private String onlineTitle;
        private int seq;
        private String adapRegion;
        private List<ImgsBean> imgs;
        private List<SkusBean> skus;

        public String getRegionIds() {
            return regionIds;
        }

        public void setRegionIds(String regionIds) {
            this.regionIds = regionIds;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public int getIsHarvestItem() {
            return isHarvestItem;
        }

        public void setIsHarvestItem(int isHarvestItem) {
            this.isHarvestItem = isHarvestItem;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(int supplierId) {
            this.supplierId = supplierId;
        }

        public String getUnits() {
            return units;
        }

        public void setUnits(String units) {
            this.units = units;
        }

        public String getOnlineTitle() {
            return onlineTitle;
        }

        public void setOnlineTitle(String onlineTitle) {
            this.onlineTitle = onlineTitle;
        }

        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public String getAdapRegion() {
            return adapRegion;
        }

        public void setAdapRegion(String adapRegion) {
            this.adapRegion = adapRegion;
        }

        public List<ImgsBean> getImgs() {
            return imgs;
        }

        public void setImgs(List<ImgsBean> imgs) {
            this.imgs = imgs;
        }

        public List<SkusBean> getSkus() {
            return skus;
        }

        public void setSkus(List<SkusBean> skus) {
            this.skus = skus;
        }

        public static class ImgsBean implements Serializable {
            /**
             * id : {"itemId":202,"seq":0}
             * url : azt/1000/1/item/20200907/img15994771440022.jpg
             */

            private IdBean id;
            private String url;

            public IdBean getId() {
                return id;
            }

            public void setId(IdBean id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public static class IdBean implements  Serializable{
                /**
                 * itemId : 202
                 * seq : 0
                 */

                private int itemId;
                private int seq;

                public int getItemId() {
                    return itemId;
                }

                public void setItemId(int itemId) {
                    this.itemId = itemId;
                }

                public int getSeq() {
                    return seq;
                }

                public void setSeq(int seq) {
                    this.seq = seq;
                }
            }
        }

        public static class SkusBean implements Serializable{
            /**
             * supplierId : 47
             * units : 袋
             * moq : 5
             * norm : 21
             * id : 190
             * prices : [{"id":{"itemId":190},"retailPrice":23.6}]
             * seq : 0
             * supplierName : 测试采收
             * imgs : []
             * itemId : 202
             * name : 爱种牌采收测试 21
             */

            private int supplierId;
            private String units;
            private int moq;
            private String norm;
            private int id;
            private int seq;
            private String supplierName;
            private int itemId;
            private String name;
            private List<PricesBean> prices;
            private List<?> imgs;

            public int getSupplierId() {
                return supplierId;
            }

            public void setSupplierId(int supplierId) {
                this.supplierId = supplierId;
            }

            public String getUnits() {
                return units;
            }

            public void setUnits(String units) {
                this.units = units;
            }

            public int getMoq() {
                return moq;
            }

            public void setMoq(int moq) {
                this.moq = moq;
            }

            public String getNorm() {
                return norm;
            }

            public void setNorm(String norm) {
                this.norm = norm;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSeq() {
                return seq;
            }

            public void setSeq(int seq) {
                this.seq = seq;
            }

            public String getSupplierName() {
                return supplierName;
            }

            public void setSupplierName(String supplierName) {
                this.supplierName = supplierName;
            }

            public int getItemId() {
                return itemId;
            }

            public void setItemId(int itemId) {
                this.itemId = itemId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<PricesBean> getPrices() {
                return prices;
            }

            public void setPrices(List<PricesBean> prices) {
                this.prices = prices;
            }

            public List<?> getImgs() {
                return imgs;
            }

            public void setImgs(List<?> imgs) {
                this.imgs = imgs;
            }

            public static class PricesBean implements Serializable{
                /**
                 * id : {"itemId":190}
                 * retailPrice : 23.6
                 */

                private IdBeanX id;
                private double retailPrice;

                public IdBeanX getId() {
                    return id;
                }

                public void setId(IdBeanX id) {
                    this.id = id;
                }

                public double getRetailPrice() {
                    return retailPrice;
                }

                public void setRetailPrice(double retailPrice) {
                    this.retailPrice = retailPrice;
                }

                public static class IdBeanX implements Serializable{
                    /**
                     * itemId : 190
                     */

                    private int itemId;

                    public int getItemId() {
                        return itemId;
                    }

                    public void setItemId(int itemId) {
                        this.itemId = itemId;
                    }
                }
            }
        }
    }
}
