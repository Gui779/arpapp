package com.example.erpqpp.mvp.mode;

import java.io.Serializable;
import java.util.List;

public class ProductMode {

    /**
     * code : 1
     * msg : 操作成功
     * data : [{"pro_id":459,"pro_name":"产品名称1","store_id":1,"store_name":"太兴-工厂","pro_num":"201907091471385","wood_id":11,"wood_name":"越南黄花梨","series":"产品系列1","price":"250000.00","cost_price":"1000.00","add_time":"2019-07-09 14:33:22"},{"pro_id":458,"pro_name":"产品名称1","store_id":1,"store_name":"太兴-工厂","pro_num":"201907091459901","wood_id":11,"wood_name":"越南黄花梨","series":"产品系列1","price":"250000.00","cost_price":"1000.00","add_time":"2019-07-09 14:32:50"},{"pro_id":456,"pro_name":"产品名称","store_id":1,"store_name":"太兴-工厂","pro_num":"201907091412302","wood_id":11,"wood_name":"越南黄花梨","series":"产品系列","price":"250000.00","cost_price":"1000.00","add_time":"2019-07-09 14:12:03"},{"pro_id":453,"pro_name":"颠三倒四","store_id":1,"store_name":"太兴-工厂","pro_num":"201907050953605","wood_id":1,"wood_name":"小叶紫檀","series":"43","price":"443.00","cost_price":"4343.00","add_time":"2019-07-05 09:29:02"},{"pro_id":452,"pro_name":"撒","store_id":1,"store_name":"太兴-工厂","pro_num":"201907050950292","wood_id":4,"wood_name":"古夷苏木","series":"撒","price":"22.00","cost_price":"2.00","add_time":"2019-07-05 09:27:06"},{"pro_id":451,"pro_name":"多少岁","store_id":1,"store_name":"太兴-工厂","pro_num":"201907050976636","wood_id":11,"wood_name":"越南黄花梨","series":"都是","price":"2.00","cost_price":"3.00","add_time":"2019-07-05 09:23:48"},{"pro_id":450,"pro_name":"多少岁","store_id":1,"store_name":"太兴-工厂","pro_num":"201907050973848","wood_id":11,"wood_name":"越南黄花梨","series":"都是","price":"2.00","cost_price":"3.00","add_time":"2019-07-05 09:23:47"},{"pro_id":449,"pro_name":"产品名称","store_id":1,"store_name":"太兴-工厂","pro_num":"201907050998186","wood_id":11,"wood_name":"越南黄花梨","series":"产品系列","price":"250000.00","cost_price":"1000.00","add_time":"2019-07-05 09:23:20"},{"pro_id":448,"pro_name":"产品名称","store_id":1,"store_name":"太兴-工厂","pro_num":"201907050987235","wood_id":11,"wood_name":"越南黄花梨","series":"产品系列","price":"250000.00","cost_price":"1000.00","add_time":"2019-07-05 09:23:08"},{"pro_id":447,"pro_name":"产品名称","store_id":1,"store_name":"太兴-工厂","pro_num":"201907050957366","wood_id":11,"wood_name":"越南黄花梨","series":"产品系列","price":"250000.00","cost_price":"1000.00","add_time":"2019-07-05 09:20:46"}]
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
         * pro_id : 459
         * pro_name : 产品名称1
         * store_id : 1
         * store_name : 太兴-工厂
         * pro_num : 201907091471385
         * wood_id : 11
         * wood_name : 越南黄花梨
         * series : 产品系列1
         * price : 250000.00
         * cost_price : 1000.00
         * add_time : 2019-07-09 14:33:22
         */

        private int pro_id;
        private String pro_name;
        private int store_id;
        private String store_name;
        private String pro_num;
        private int wood_id;
        private String wood_name;
        private String series;
        private String price;
        private String cost_price;
        private String add_time;

        public int getPro_id() {
            return pro_id;
        }

        public void setPro_id(int pro_id) {
            this.pro_id = pro_id;
        }

        public String getPro_name() {
            return pro_name;
        }

        public void setPro_name(String pro_name) {
            this.pro_name = pro_name;
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

        public String getPro_num() {
            return pro_num;
        }

        public void setPro_num(String pro_num) {
            this.pro_num = pro_num;
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

        public String getSeries() {
            return series;
        }

        public void setSeries(String series) {
            this.series = series;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCost_price() {
            return cost_price;
        }

        public void setCost_price(String cost_price) {
            this.cost_price = cost_price;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}
