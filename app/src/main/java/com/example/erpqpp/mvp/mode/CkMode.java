package com.example.erpqpp.mvp.mode;

import java.util.List;

public class CkMode {

    /**
     * code : 1
     * msg : 操作成功
     * data : [{"warehouse_id":1,"warehousename":"成品仓库1"},{"warehouse_id":2,"warehousename":"成品仓库2"},{"warehouse_id":3,"warehousename":"成品仓库3"},{"warehouse_id":4,"warehousename":"成品仓库4"}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * warehouse_id : 1
         * warehousename : 成品仓库1
         */

        private int warehouse_id;
        private String warehousename;

        public int getWarehouse_id() {
            return warehouse_id;
        }

        public void setWarehouse_id(int warehouse_id) {
            this.warehouse_id = warehouse_id;
        }

        public String getWarehousename() {
            return warehousename;
        }

        public void setWarehousename(String warehousename) {
            this.warehousename = warehousename;
        }
    }
}
