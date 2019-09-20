package com.example.erpqpp.mvp.mode;

import java.util.List;

public class MdMode {

    /**
     * code : 1
     * store : [{"id":1,"store_name":"一号门店"}]
     */

    private int code;
    private List<StoreBean> store;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<StoreBean> getStore() {
        return store;
    }

    public void setStore(List<StoreBean> store) {
        this.store = store;
    }

    public static class StoreBean {
        /**
         * id : 1
         * store_name : 一号门店
         */

        private int id;
        private String store_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }
    }
}
