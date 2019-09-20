package com.example.erpqpp.mvp.mode;

import java.util.List;

public class TextureManageMode {

    /**
     * code : 1
     * data : [{"wood_id":6,"wood_name":"名称","store_id":1,"wood_price":"0.00","price_unit":"","add_time":"2019-08-15 17:01:35","upd_time":null},{"wood_id":5,"wood_name":"微凹黄檀","store_id":1,"wood_price":"0.00","price_unit":"","add_time":"2019-08-12 10:30:10","upd_time":null},{"wood_id":4,"wood_name":"非洲紫檀","store_id":1,"wood_price":"0.00","price_unit":"","add_time":"2019-08-12 10:29:59","upd_time":null},{"wood_id":3,"wood_name":"刺猬紫檀","store_id":1,"wood_price":"0.00","price_unit":"","add_time":"2019-08-12 10:29:51","upd_time":null},{"wood_id":2,"wood_name":"交趾黄檀","store_id":1,"wood_price":"0.00","price_unit":"","add_time":"2019-08-12 10:29:44","upd_time":null}]
     * count : 5
     */

    private int code;
    private int count;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * wood_id : 6
         * wood_name : 名称
         * store_id : 1
         * wood_price : 0.00
         * price_unit :
         * add_time : 2019-08-15 17:01:35
         * upd_time : null
         */

        private int wood_id;
        private String wood_name;
        private int store_id;
        private String wood_price;
        private String price_unit;
        private String add_time;
        private Object upd_time;

        public int getWood_id() {
            return wood_id;
        }

        public void setWood_id(int wood_id) {
            this.wood_id = wood_id;
        }

        public String getWood_name() {
            return wood_name;
        }

        public void setWood_name(String wood_name) {
            this.wood_name = wood_name;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getWood_price() {
            return wood_price;
        }

        public void setWood_price(String wood_price) {
            this.wood_price = wood_price;
        }

        public String getPrice_unit() {
            return price_unit;
        }

        public void setPrice_unit(String price_unit) {
            this.price_unit = price_unit;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public Object getUpd_time() {
            return upd_time;
        }

        public void setUpd_time(Object upd_time) {
            this.upd_time = upd_time;
        }
    }
}
