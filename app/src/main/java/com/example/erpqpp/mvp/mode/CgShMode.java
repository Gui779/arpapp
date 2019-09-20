package com.example.erpqpp.mvp.mode;

import java.util.List;

public class CgShMode {

    /**
     * code : 1
     * data : [{"proId":3,"proName":"百福隔厅柜","typeName":"成品（深色）","order_unit_id":1,"product_unit_list":[{"unit":3,"unitId":4,"unitName":"个","colorId":1,"colorName":"深色","orderCount":1,"depot":[{"id":1,"store_id":1,"depotName":"成品仓库","depotCount":0},{"id":2,"store_id":1,"depotName":"一号仓库","depotCount":2}]}]}]
     */

    private int code;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * proId : 3
         * proName : 百福隔厅柜
         * typeName : 成品（深色）
         * order_unit_id : 1
         * product_unit_list : [{"unit":3,"unitId":4,"unitName":"个","colorId":1,"colorName":"深色","orderCount":1,"depot":[{"id":1,"store_id":1,"depotName":"成品仓库","depotCount":0},{"id":2,"store_id":1,"depotName":"一号仓库","depotCount":2}]}]
         */

        private int proId;
        private String proName;
        private String typeName;
        private int order_unit_id;
        private List<ProductUnitListBean> product_unit_list;

        public int getProId() {
            return proId;
        }

        public void setProId(int proId) {
            this.proId = proId;
        }

        public String getProName() {
            return proName;
        }

        public void setProName(String proName) {
            this.proName = proName;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public int getOrder_unit_id() {
            return order_unit_id;
        }

        public void setOrder_unit_id(int order_unit_id) {
            this.order_unit_id = order_unit_id;
        }

        public List<ProductUnitListBean> getProduct_unit_list() {
            return product_unit_list;
        }

        public void setProduct_unit_list(List<ProductUnitListBean> product_unit_list) {
            this.product_unit_list = product_unit_list;
        }

        public static class ProductUnitListBean {
            /**
             * unit : 3
             * unitId : 4
             * unitName : 个
             * colorId : 1
             * colorName : 深色
             * orderCount : 1
             * depot : [{"id":1,"store_id":1,"depotName":"成品仓库","depotCount":0},{"id":2,"store_id":1,"depotName":"一号仓库","depotCount":2}]
             */

            private int unit;
            private int unitId;
            private String unitName;
            private int colorId;
            private String colorName;
            private int orderCount;
            private List<DepotBean> depot;

            public int getUnit() {
                return unit;
            }

            public void setUnit(int unit) {
                this.unit = unit;
            }

            public int getUnitId() {
                return unitId;
            }

            public void setUnitId(int unitId) {
                this.unitId = unitId;
            }

            public String getUnitName() {
                return unitName;
            }

            public void setUnitName(String unitName) {
                this.unitName = unitName;
            }

            public int getColorId() {
                return colorId;
            }

            public void setColorId(int colorId) {
                this.colorId = colorId;
            }

            public String getColorName() {
                return colorName;
            }

            public void setColorName(String colorName) {
                this.colorName = colorName;
            }

            public int getOrderCount() {
                return orderCount;
            }

            public void setOrderCount(int orderCount) {
                this.orderCount = orderCount;
            }

            public List<DepotBean> getDepot() {
                return depot;
            }

            public void setDepot(List<DepotBean> depot) {
                this.depot = depot;
            }

            public static class DepotBean {
                /**
                 * id : 1
                 * store_id : 1
                 * depotName : 成品仓库
                 * depotCount : 0
                 */

                private int id;
                private int store_id;
                private String depotName;
                private int depotCount;
                private String outCount="数量";
                private boolean isflag;

                public String getOutCount() {
                    return outCount;
                }

                public void setOutCount(String outCount) {
                    this.outCount = outCount;
                }

                public boolean isIsflag() {
                    return isflag;
                }

                public void setIsflag(boolean isflag) {
                    this.isflag = isflag;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getStore_id() {
                    return store_id;
                }

                public void setStore_id(int store_id) {
                    this.store_id = store_id;
                }

                public String getDepotName() {
                    return depotName;
                }

                public void setDepotName(String depotName) {
                    this.depotName = depotName;
                }

                public int getDepotCount() {
                    return depotCount;
                }

                public void setDepotCount(int depotCount) {
                    this.depotCount = depotCount;
                }
            }
        }
    }
}
