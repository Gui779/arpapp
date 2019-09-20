package com.example.erpqpp.mvp.mode;

import java.util.List;

public class FhjlMode {

    /**
     * code : 1
     * msg : 操作成功
     * data : [{"id":53,"pro_num":"2019072916095124575","pro_name":"秋明圆台9件套","wood_name":"缅花","unit_name":"台","cost":"0.00","add_time":"2019-07-29 16:20:53","unit_num":90},{"id":37,"pro_num":"201907221951365221","pro_name":"秋明圆台9件套","wood_name":"缅花","unit_name":"台","cost":"0.00","add_time":"2019-07-29 15:30:43","unit_num":2},{"id":34,"pro_num":"2019072219512127541","pro_name":"220山居秋明电视柜","wood_name":"金丝黄檀","unit_name":"台","cost":"0.00","add_time":"2019-07-29 15:23:20","unit_num":10},{"id":22,"pro_num":"2019072219510595019","pro_name":"百福隔厅柜","wood_name":"缅花","unit_name":"个","cost":"0.00","add_time":"2019-07-22 19:51:43","unit_num":10},{"id":7,"pro_num":"201907191666784","pro_name":"百福隔厅柜","wood_name":"缅花","unit_name":"个","cost":"0.00","add_time":"2019-07-22 17:58:26","unit_num":100},{"id":3,"pro_num":"201907191623973","pro_name":"220山居秋明电视柜","wood_name":"金丝黄檀","unit_name":"台","cost":"0.00","add_time":"2019-07-19 17:44:42","unit_num":4},{"id":2,"pro_num":"201907191666784","pro_name":"百福隔厅柜","wood_name":"缅花","unit_name":"个","cost":"0.00","add_time":"2019-07-19 17:44:42","unit_num":4},{"id":1,"pro_num":"201907191666784","pro_name":"百福隔厅柜","wood_name":"缅花","unit_name":"个","cost":"0.00","add_time":"2019-07-19 17:18:02","unit_num":1}]
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
         * id : 53
         * pro_num : 2019072916095124575
         * pro_name : 秋明圆台9件套
         * wood_name : 缅花
         * unit_name : 台
         * cost : 0.00
         * add_time : 2019-07-29 16:20:53
         * unit_num : 90
         */

        private int id;
        private String pro_num;
        private String pro_name;
        private String wood_name;
        private String unit_name;
        private String cost;
        private String add_time;
        private int unit_num;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getWood_name() {
            return wood_name;
        }

        public void setWood_name(String wood_name) {
            this.wood_name = wood_name;
        }

        public String getUnit_name() {
            return unit_name;
        }

        public void setUnit_name(String unit_name) {
            this.unit_name = unit_name;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public int getUnit_num() {
            return unit_num;
        }

        public void setUnit_num(int unit_num) {
            this.unit_num = unit_num;
        }
    }
}
