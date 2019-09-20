package com.example.erpqpp.mvp.mode;

import java.util.List;

public class CkListMode {

    /**
     * code : 1
     * data : [{"proId":3,"proName":"百福隔厅柜","typeName":"成品（深色）","product_unit_list":[{"unitId":4,"unitName":"个","orderCount":1,"proCount":0,"depot":[{"id":1,"depotCount":2,"depotName":"成品仓库"},{"id":4,"depotCount":2,"depotName":"一号仓库"}]}]},{"proId":2,"proName":"220山居秋明电视柜","typeName":"成品（红色）","product_unit_list":[{"unitId":3,"unitName":"台","orderCount":1,"proCount":0,"depot":[{"id":2,"depotCount":2,"depotName":"成品仓库"},{"id":3,"depotCount":2,"depotName":"一号仓库"}]}]}]
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
         * product_unit_list : [{"unitId":4,"unitName":"个","orderCount":1,"proCount":0,"depot":[{"id":1,"depotCount":2,"depotName":"成品仓库"},{"id":4,"depotCount":2,"depotName":"一号仓库"}]}]
         */

        private int proId;
        private String proName;
        private String typeName;
        private boolean groupflag=false;
        private String saleNum;

        public String getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(String saleNum) {
            this.saleNum = saleNum;
        }

        public boolean isGroupflag() {
            return groupflag;
        }

        public void setGroupflag(boolean groupflag) {
            this.groupflag = groupflag;
        }

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

        public List<ProductUnitListBean> getProduct_unit_list() {
            return product_unit_list;
        }

        public void setProduct_unit_list(List<ProductUnitListBean> product_unit_list) {
            this.product_unit_list = product_unit_list;
        }

        public static class ProductUnitListBean {
            /**
             * unitId : 4
             * unitName : 个
             * orderCount : 1
             * proCount : 0
             * depot : [{"id":1,"depotCount":2,"depotName":"成品仓库"},{"id":4,"depotCount":2,"depotName":"一号仓库"}]
             */

            private int unitId;
            private String unitName;
            private int orderCount;
            private int proCount;
            private List<DepotBean> depot;

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

            public int getOrderCount() {
                return orderCount;
            }

            public void setOrderCount(int orderCount) {
                this.orderCount = orderCount;
            }

            public int getProCount() {
                return proCount;
            }

            public void setProCount(int proCount) {
                this.proCount = proCount;
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
                 * depotCount : 2
                 * depotName : 成品仓库
                 */

                private int id;
                private int depotCount;
                private String depotName;
                private boolean isflag;
                private int unitId;
                private String count="数量";

                public int getUnitId() {
                    return unitId;
                }

                public void setUnitId(int unitId) {
                    this.unitId = unitId;
                }

                public String getCount() {
                    return count;
                }

                public void setCount(String count) {
                    this.count = count;
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

                public int getDepotCount() {
                    return depotCount;
                }

                public void setDepotCount(int depotCount) {
                    this.depotCount = depotCount;
                }

                public String getDepotName() {
                    return depotName;
                }

                public void setDepotName(String depotName) {
                    this.depotName = depotName;
                }
            }
        }
    }
}
