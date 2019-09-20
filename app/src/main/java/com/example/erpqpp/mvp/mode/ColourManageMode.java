package com.example.erpqpp.mvp.mode;

import java.util.List;

public class ColourManageMode {

    /**
     * code : 1
     * data : [{"color_id":1,"store_id":1,"color_num":"001","color_name":"红色","add_time":null,"upd_time":null},{"color_id":2,"store_id":1,"color_num":"002","color_name":"浅色","add_time":null,"upd_time":null},{"color_id":3,"store_id":1,"color_num":"003","color_name":"原色","add_time":null,"upd_time":null},{"color_id":4,"store_id":1,"color_num":"004","color_name":"红色","add_time":null,"upd_time":null}]
     * count : 4
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
         * color_id : 1
         * store_id : 1
         * color_num : 001
         * color_name : 红色
         * add_time : null
         * upd_time : null
         */

        private int color_id;
        private int store_id;
        private String color_num;
        private String color_name;
        private Object add_time;
        private Object upd_time;

        public int getColor_id() {
            return color_id;
        }

        public void setColor_id(int color_id) {
            this.color_id = color_id;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getColor_num() {
            return color_num;
        }

        public void setColor_num(String color_num) {
            this.color_num = color_num;
        }

        public String getColor_name() {
            return color_name;
        }

        public void setColor_name(String color_name) {
            this.color_name = color_name;
        }

        public Object getAdd_time() {
            return add_time;
        }

        public void setAdd_time(Object add_time) {
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
