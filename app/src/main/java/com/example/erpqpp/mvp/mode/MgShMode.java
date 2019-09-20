package com.example.erpqpp.mvp.mode;

import java.io.Serializable;
import java.util.List;

public class MgShMode {

    /**
     * code : 1
     * msg : 操作成功
     * data : [{"id":8,"source":1,"status":1,"type":1,"store_id":1,"store_name":"太兴-工厂","pro_id":367,"pro_num":"201906061061456","pro_name":"138山居秋明9件套","unit_id":700,"unit_name":"圆台","unit_num":1,"wood_id":2,"wood_name":"刺猬紫檀","revoke_time":"","add_time":"2019-06-21 15:58:39","upd_time":"2019-06-21 15:58:39"},{"id":7,"source":1,"status":1,"type":1,"store_id":1,"store_name":"太兴-工厂","pro_id":1,"pro_num":"201905241019308","pro_name":"2米高靠背椅茶台8件套","unit_id":205,"unit_name":"台","unit_num":1,"wood_id":9,"wood_name":"交趾黄檀","revoke_time":"","add_time":"2019-06-17 14:59:12","upd_time":"2019-06-17 14:59:12"},{"id":5,"source":1,"status":1,"type":1,"store_id":1,"store_name":"太兴-工厂","pro_id":369,"pro_num":"201906061041781","pro_name":"山居秋明餐椅","unit_id":703,"unit_name":"椅","unit_num":69,"wood_id":2,"wood_name":"刺猬紫檀","revoke_time":"","add_time":"2019-06-12 10:21:40","upd_time":"2019-06-12 10:22:14"},{"id":1,"source":1,"status":1,"type":1,"store_id":1,"store_name":"太兴家具","pro_id":9,"pro_num":"2019052317560876280","pro_name":"荷花宝座沙发","unit_id":23,"unit_name":"大平几","unit_num":1,"wood_id":3,"wood_name":"檀香系列","revoke_time":"","add_time":"2019-05-23 17:56:08","upd_time":"2019-05-23 20:03:03"}]
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

    public static class DataBean implements Serializable {
        /**
         * id : 8
         * source : 1
         * status : 1
         * type : 1
         * store_id : 1
         * store_name : 太兴-工厂
         * pro_id : 367
         * pro_num : 201906061061456
         * pro_name : 138山居秋明9件套
         * unit_id : 700
         * unit_name : 圆台
         * unit_num : 1
         * wood_id : 2
         * wood_name : 刺猬紫檀
         * revoke_time :
         * add_time : 2019-06-21 15:58:39
         * upd_time : 2019-06-21 15:58:39
         */

        private int id;
        private int source;
        private int status;
        private int type;
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
        private String revoke_time;
        private String add_time;
        private String upd_time;
        private boolean ischeck=false;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public String getRevoke_time() {
            return revoke_time;
        }

        public void setRevoke_time(String revoke_time) {
            this.revoke_time = revoke_time;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getUpd_time() {
            return upd_time;
        }

        public void setUpd_time(String upd_time) {
            this.upd_time = upd_time;
        }
    }
}
