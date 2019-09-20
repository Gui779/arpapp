package com.example.erpqpp.mvp.mode;

import java.util.List;

public class WmMode {


    /**
     * code : 1
     * msg : 操作成功
     * data : [{"id":3,"source":1,"status":1,"type":3,"store_id":1,"store_name":"太兴家具","pro_id":377,"pro_num":"2019062814133669656","pro_name":"明韵3号沙发","unit_id":721,"unit_name":"双人位","unit_num":94,"wood_id":19,"cost":"1000.00","wood_name":"其他","send_time":"2019-06-28 14:16:15","add_time":"2019-06-28 14:16:26","upd_time":"2019-07-02 10:11:17"},{"id":10,"source":1,"status":1,"type":3,"store_id":1,"store_name":"太兴-工厂","pro_id":123,"pro_num":"2019063009555848135","pro_name":"多喝水","unit_id":554,"unit_name":"担惊受恐","unit_num":1,"wood_id":56,"cost":"2000.00","wood_name":"敦煌石窟","send_time":"2019-07-26 00:00:00","add_time":"2019-07-02 14:52:36","upd_time":"2019-07-02 14:52:36"},{"id":13,"source":1,"status":1,"type":3,"store_id":1,"store_name":"太兴家具","pro_id":367,"pro_num":"2019062916482678964","pro_name":"138山居秋明9件套","unit_id":700,"unit_name":"圆台","unit_num":1,"wood_id":2,"cost":"0.00","wood_name":"刺猬紫檀","send_time":"2019-07-19 00:00:00","add_time":"2019-07-02 16:22:19","upd_time":"2019-07-02 16:22:19"},{"id":14,"source":1,"status":1,"type":3,"store_id":1,"store_name":"太兴-工厂","pro_id":230,"pro_num":"2019063011210511660","pro_name":"山居秋明书柜高柜","unit_id":422,"unit_name":"柜","unit_num":99,"wood_id":2,"cost":"0.00","wood_name":"刺猬紫檀","send_time":"2019-07-12 00:00:00","add_time":"2019-07-02 16:26:03","upd_time":"2019-07-02 16:26:03"}]
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
         * id : 3
         * source : 1
         * status : 1
         * type : 3
         * store_id : 1
         * store_name : 太兴家具
         * pro_id : 377
         * pro_num : 2019062814133669656
         * pro_name : 明韵3号沙发
         * unit_id : 721
         * unit_name : 双人位
         * unit_num : 94
         * wood_id : 19
         * cost : 1000.00
         * wood_name : 其他
         * send_time : 2019-06-28 14:16:15
         * add_time : 2019-06-28 14:16:26
         * upd_time : 2019-07-02 10:11:17
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
        private String cost;
        private String wood_name;
        private String send_time;
        private String add_time;
        private String upd_time;
        private boolean ischeck;

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

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getWood_name() {
            return wood_name;
        }

        public void setWood_name(String wood_name) {
            this.wood_name = wood_name;
        }

        public String getSend_time() {
            return send_time;
        }

        public void setSend_time(String send_time) {
            this.send_time = send_time;
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
