package com.example.erpqpp.mvp.mode;

import java.util.List;

public class SqMode {

    /**
     * code : 1
     * msg : 操作成功
     * data : [{"id":7,"store_id":1,"store_name":"太兴-工厂","pro_id":367,"pro_num":"201906111161215","pro_name":"138山居秋明9件套","unit_id":700,"unit_name":"圆台","unit_num":10,"wood_id":2,"wood_name":"刺猬紫檀","add_time":"2019-06-12 11:17:09","source":2,"status":2},{"id":6,"store_id":1,"store_name":"太兴-工厂","pro_id":370,"pro_num":"201906111151286","pro_name":"180山居秋明大床","unit_id":705,"unit_name":"床头柜","unit_num":1,"wood_id":2,"wood_name":"刺猬紫檀","add_time":"2019-06-12 11:16:56","source":2,"status":2},{"id":3,"store_id":1,"store_name":"太兴-工厂","pro_id":369,"pro_num":"201906111665323","pro_name":"山居秋明餐椅","unit_id":703,"unit_name":"椅","unit_num":314,"wood_id":2,"wood_name":"刺猬紫檀","add_time":"2019-06-11 16:05:44","source":2,"status":1}]
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
         * id : 7
         * store_id : 1
         * store_name : 太兴-工厂
         * pro_id : 367
         * pro_num : 201906111161215
         * pro_name : 138山居秋明9件套
         * unit_id : 700
         * unit_name : 圆台
         * unit_num : 10
         * wood_id : 2
         * wood_name : 刺猬紫檀
         * add_time : 2019-06-12 11:17:09
         * source : 2
         * status : 2
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
        private String colorname;
        private String colorid;
        private String unit_num_s;

        public String getUnit_num_s() {
            return unit_num_s;
        }

        public void setUnit_num_s(String unit_num_s) {
            this.unit_num_s = unit_num_s;
        }

        public String getColorname() {
            return colorname;
        }

        public void setColorname(String colorname) {
            this.colorname = colorname;
        }

        public String getColorid() {
            return colorid;
        }

        public void setColorid(String colorid) {
            this.colorid = colorid;
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
