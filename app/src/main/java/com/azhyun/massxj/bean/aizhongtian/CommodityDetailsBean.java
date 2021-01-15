package com.azhyun.massxj.bean.aizhongtian;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dell on 2020/7/6.
 */

public class CommodityDetailsBean implements Serializable {


    /**
     * result : {"code":"200","message":"操作成功！","data":{}}
     * data : {"id":166,"brand":"测试品牌","imgs":[{"id":{"itemId":166,"seq":0},"url":"store/1000/1/item/20200708/img15949196065090.jpg"},{"id":{"itemId":166,"seq":1},"url":"store/1000/1/item/20200708/img15941960813297.jpg"}],"companyId":24,"name":"测试商品","detail":"没有商品描述测试","skus":[{"imgs":[],"moq":500,"norm":"100kg/袋","itemId":166,"companyId":1,"name":"测试品牌测试商品 100kg/袋","id":151,"prices":[{"id":{"itemId":151},"retailPrice":230}],"seq":0},{"imgs":[],"moq":3,"norm":"200kg/袋","itemId":166,"companyId":1,"name":"测试品牌测试商品 200kg/袋","id":153,"prices":[{"id":{"itemId":153},"retailPrice":520}],"seq":0}],"company":"农田新","seq":6}
     */

    private ResultBean result;
    private DataBeanX data;

    @Override
    public String toString() {
        return "CommodityDetailsBean{" +
                "result=" + result +
                ", data=" + data +
                '}';
    }

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

    public static class ResultBean implements Serializable {
        /**
         * code : 200
         * message : 操作成功！
         * data : {}
         */

        private String code;
        private String message;
        private DataBean data;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "code='" + code + '\'' +
                    ", message='" + message + '\'' +
                    ", data=" + data +
                    '}';
        }

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

    public static class DataBeanX implements Serializable {
        /**
         * id : 166
         * brand : 测试品牌
         * imgs : [{"id":{"itemId":166,"seq":0},"url":"store/1000/1/item/20200708/img15949196065090.jpg"},{"id":{"itemId":166,"seq":1},"url":"store/1000/1/item/20200708/img15941960813297.jpg"}]
         * companyId : 24
         * name : 测试商品
         * detail : 没有商品描述测试
         * skus : [{"imgs":[],"moq":500,"norm":"100kg/袋","itemId":166,"companyId":1,"name":"测试品牌测试商品 100kg/袋","id":151,"prices":[{"id":{"itemId":151},"retailPrice":230}],"seq":0},{"imgs":[],"moq":3,"norm":"200kg/袋","itemId":166,"companyId":1,"name":"测试品牌测试商品 200kg/袋","id":153,"prices":[{"id":{"itemId":153},"retailPrice":520}],"seq":0}]
         * company : 农田新
         * seq : 6
         */

        private int id;
        private String brand;
        private String name;
        private String detail;
        private int seq;
        private String onlineTitle;
        private String adapRegion;
        private List<ImgsBean> imgs;
        private List<SkusBean> skus;

        @Override
        public String toString() {
            return "DataBeanX{" +
                    "id=" + id +
                    ", brand='" + brand + '\'' +
                    ", name='" + name + '\'' +
                    ", detail='" + detail + '\'' +
                    ", seq=" + seq +
                    ", onlineTitle='" + onlineTitle + '\'' +
                    ", adapRegion='" + adapRegion + '\'' +
                    ", imgs=" + imgs +
                    ", skus=" + skus +
                    '}';
        }

        public String getAdapRegion() {
            return adapRegion;
        }

        public void setAdapRegion(String adapRegion) {
            this.adapRegion = adapRegion;
        }


        public String getOnlineTitle() {
            return onlineTitle;
        }

        public void setOnlineTitle(String onlineTitle) {
            this.onlineTitle = onlineTitle;
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


        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
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
             * id : {"itemId":166,"seq":0}
             * url : store/1000/1/item/20200708/img15949196065090.jpg
             */

            private IdBean id;
            private String url;

            @Override
            public String toString() {
                return "ImgsBean{" +
                        "id=" + id +
                        ", url='" + url + '\'' +
                        '}';
            }

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

            public static class IdBean implements Serializable {
                /**
                 * itemId : 166
                 * seq : 0
                 */

                private int itemId;
                private int seq;

                @Override
                public String toString() {
                    return "IdBean{" +
                            "itemId=" + itemId +
                            ", seq=" + seq +
                            '}';
                }

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

        public static class SkusBean implements Serializable {
            /**
             * imgs : []
             * moq : 500
             * norm : 100kg/袋
             * itemId : 166
             * companyId : 1
             * name : 测试品牌测试商品 100kg/袋
             * id : 151
             * prices : [{"id":{"itemId":151},"retailPrice":230}]
             * seq : 0
             */
            private int supplierId;
            private String supplierName;
            private int moq;
            private String norm;
            private int itemId;
            private int companyId;
            private String name;
            private int id;
            private int seq;
            private String units;
            private List<?> imgs;
            private List<PricesBean> prices;

            @Override
            public String toString() {
                return "SkusBean{" +
                        "supplierId=" + supplierId +
                        ", supplierName='" + supplierName + '\'' +
                        ", moq=" + moq +
                        ", norm='" + norm + '\'' +
                        ", itemId=" + itemId +
                        ", companyId=" + companyId +
                        ", name='" + name + '\'' +
                        ", id=" + id +
                        ", seq=" + seq +
                        ", units='" + units + '\'' +
                        ", imgs=" + imgs +
                        ", prices=" + prices +
                        '}';
            }

            public int getSupplierId() {
                return supplierId;
            }

            public void setSupplierId(int supplierId) {
                this.supplierId = supplierId;
            }

            public String getSupplierName() {
                return supplierName;
            }

            public void setSupplierName(String supplierName) {
                this.supplierName = supplierName;
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

            public int getItemId() {
                return itemId;
            }

            public void setItemId(int itemId) {
                this.itemId = itemId;
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

            public List<?> getImgs() {
                return imgs;
            }

            public void setImgs(List<?> imgs) {
                this.imgs = imgs;
            }

            public List<PricesBean> getPrices() {
                return prices;
            }

            public void setPrices(List<PricesBean> prices) {
                this.prices = prices;
            }

            public static class PricesBean implements Serializable {
                /**
                 * id : {"itemId":151}
                 * retailPrice : 230.0
                 */

                private IdBeanX id;
                private double retailPrice;

                @Override
                public String toString() {
                    return "PricesBean{" +
                            "id=" + id +
                            ", retailPrice=" + retailPrice +
                            '}';
                }

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

                public static class IdBeanX implements Serializable {
                    /**
                     * itemId : 151
                     */

                    private int itemId;

                    @Override
                    public String toString() {
                        return "IdBeanX{" +
                                "itemId=" + itemId +
                                '}';
                    }

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
