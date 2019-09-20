package com.example.erpqpp.mvp.mode;

import java.util.List;

public class DmMode {

    /**
     * code : 1
     * msg : 操作成功
     * data : [{"id":6,"store_id":1,"store_name":"太兴-工厂","pro_id":210,"pro_num":"201907011049523739","pro_name":"138山居秋明9件套","unit_id":390,"unit_name":"套","unit_num":10,"wood_id":1,"wood_name":"其它","add_time":"2019-07-01 10:49:52","source":2,"status":1},{"id":4,"store_id":1,"store_name":"太兴家具","pro_id":377,"pro_num":"2019062814134523565","pro_name":"明韵3号沙发","unit_id":722,"unit_name":"三人位","unit_num":7,"wood_id":19,"wood_name":"其他","add_time":"2019-06-28 14:16:26","source":1,"status":1},{"id":2,"store_id":1,"store_name":"太兴家具","pro_id":405,"pro_num":"201906281412598682","pro_name":"明韶罗汉床","unit_id":786,"unit_name":"床","unit_num":77,"wood_id":19,"wood_name":"其他","add_time":"2019-06-28 14:16:26","source":1,"status":1},{"id":1,"store_id":1,"store_name":"太兴家具","pro_id":372,"pro_num":"2019062814121358120","pro_name":"卷书宝座沙发","unit_id":707,"unit_name":"个","unit_num":77,"wood_id":6,"wood_name":"交趾黄檀","add_time":"2019-06-28 14:16:26","source":1,"status":1}]
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
         * id : 6
         * store_id : 1
         * store_name : 太兴-工厂
         * pro_id : 210
         * pro_num : 201907011049523739
         * pro_name : 138山居秋明9件套
         * unit_id : 390
         * unit_name : 套
         * unit_num : 10
         * wood_id : 1
         * wood_name : 其它
         * add_time : 2019-07-01 10:49:52
         * source : 2
         * status : 1
         */

        private int id;
        private int store_id;
        private String store_name;
        private int pro_id;
        private String pro_num;
        private String pro_name;
        private int unit_id;
        private String unit_name;
        private int unit_num;
        private int wood_id;
        private String wood_name;
        private String add_time;
        private int source;
        private int status;
        private boolean ischeck;
        private String unit_num_s;

        public String getUnit_num_s() {
            return unit_num_s;
        }

        public void setUnit_num_s(String unit_num_s) {
            this.unit_num_s = unit_num_s;
        }

        public boolean isIscheck() {
            return ischeck;
        }

        public void setIscheck(boolean ischeck) {
            this.ischeck = ischeck;
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

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public int getPro_id() {
            return pro_id;
        }

        public void setPro_id(int pro_id) {
            this.pro_id = pro_id;
        }

        public String getPro_num() {
            return pro_num;
        }

        public void setPro_num(String pro_num) {
            this.pro_num = pro_num;
        }

        public String getPro_name() {
            return pro_name;
        }

        public void setPro_name(String pro_name) {
            this.pro_name = pro_name;
        }

        public int getUnit_id() {
            return unit_id;
        }

        public void setUnit_id(int unit_id) {
            this.unit_id = unit_id;
        }

        public String getUnit_name() {
            return unit_name;
        }

        public void setUnit_name(String unit_name) {
            this.unit_name = unit_name;
        }

        public int getUnit_num() {
            return unit_num;
        }

        public void setUnit_num(int unit_num) {
            this.unit_num = unit_num;
        }

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

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
