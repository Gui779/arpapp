package com.example.erpqpp.mvp.mode;

import java.util.List;

public class CpckMode {

    /**
     * code : 1
     * msg : 操作成功
     * data : [{"id":59,"reception_office":1,"source":1,"warehouse_id":1,"status":1,"store_id":1,"store_name":"太兴-工厂","pro_id":123,"pro_num":"2019063009555848135","pro_name":"多喝水","unit_id":554,"unit_name":"担惊受恐","unit_num":3,"wood_id":56,"wood_name":"敦煌石窟","color_id":"","cost":"2000.00","color_name":"","send_time":"","add_time":"2019-07-02 14:52:36","upd_time":"2019-07-02 14:52:36","bz":"","is_rowspan":1,"pro_count":0,"rowspan":1},{"id":46,"reception_office":1,"source":2,"warehouse_id":2,"status":1,"store_id":1,"store_name":"太兴-工厂","pro_id":203,"pro_num":"201907011925034177","pro_name":"家春秋太师椅","unit_id":378,"unit_name":"个","unit_num":9,"wood_id":1,"wood_name":"小叶紫檀","color_id":"","cost":"0.00","color_name":"","send_time":"","add_time":"2019-07-01 19:25:03","upd_time":"2019-07-01 19:25:03","bz":"","is_rowspan":1,"pro_count":9,"rowspan":1},{"id":45,"reception_office":1,"source":2,"warehouse_id":2,"status":1,"store_id":1,"store_name":"太兴-工厂","pro_id":210,"pro_num":"201907011049523739","pro_name":"138山居秋明9件套","unit_id":390,"unit_name":"套","unit_num":5,"wood_id":1,"wood_name":"其它","color_id":"","cost":"0.00","color_name":"","send_time":"","add_time":"2019-07-01 10:49:52","upd_time":"2019-07-01 15:36:41","bz":"","is_rowspan":1,"pro_count":0,"rowspan":1},{"id":60,"reception_office":3,"source":2,"warehouse_id":1,"status":1,"store_id":1,"store_name":"太兴-工厂","pro_id":369,"pro_num":"201906111665323","pro_name":"山居秋明餐椅","unit_id":703,"unit_name":"椅","unit_num":1,"wood_id":2,"wood_name":"刺猬紫檀","color_id":2,"cost":"0.00","color_name":"生漆","send_time":"","add_time":"2019-06-12 15:12:55","upd_time":"2019-06-12 15:12:55","bz":"","is_rowspan":1,"pro_count":1,"rowspan":1},{"id":47,"reception_office":1,"source":1,"warehouse_id":4,"status":1,"store_id":1,"store_name":"太兴家具","pro_id":377,"pro_num":"2019062814133669656","pro_name":"明韵3号沙发","unit_id":721,"unit_name":"双人位","unit_num":1,"wood_id":19,"wood_name":"其他","color_id":"","cost":"0.00","color_name":"","send_time":"","add_time":"2019-06-28 14:16:26","upd_time":"2019-07-02 10:11:17","bz":"","is_rowspan":1,"pro_count":0,"rowspan":2},{"id":48,"reception_office":1,"source":1,"warehouse_id":1,"status":1,"store_id":1,"store_name":"太兴家具","pro_id":377,"pro_num":"2019062814133669656","pro_name":"明韵3号沙发","unit_id":721,"unit_name":"双人位","unit_num":14,"wood_id":19,"wood_name":"其他","color_id":"","cost":"0.00","color_name":"","send_time":"","add_time":"2019-06-28 14:16:26","upd_time":"2019-07-02 10:11:17","bz":"","is_rowspan":0,"377_":1},{"id":44,"reception_office":1,"source":1,"warehouse_id":2,"status":1,"store_id":1,"store_name":"太兴家具","pro_id":405,"pro_num":"201906281412598682","pro_name":"明韶罗汉床","unit_id":786,"unit_name":"床","unit_num":77,"wood_id":19,"wood_name":"其他","color_id":"","cost":"0.00","color_name":"","send_time":"","add_time":"2019-06-28 14:16:26","upd_time":"2019-06-29 14:14:41","bz":"","is_rowspan":1,"pro_count":0,"rowspan":1},{"id":61,"reception_office":3,"source":3,"warehouse_id":1,"status":1,"store_id":1,"store_name":"太兴家具","pro_id":432,"pro_num":"2019070219220688592","pro_name":"123财源滚滚沙发6件套","unit_id":843,"unit_name":"单人位","unit_num":100,"wood_id":2,"wood_name":"刺猬紫檀","color_id":1,"cost":"0.00","color_name":"绿色","send_time":"","add_time":"2019-07-02 19:22:06","upd_time":"","bz":"","is_rowspan":1,"pro_count":0,"rowspan":2},{"id":64,"reception_office":3,"source":3,"warehouse_id":3,"status":1,"store_id":1,"store_name":"太兴家具","pro_id":432,"pro_num":"2019070219220665008","pro_name":"123财源滚滚沙发6件套","unit_id":845,"unit_name":"三人位","unit_num":100,"wood_id":2,"wood_name":"刺猬紫檀","color_id":1,"cost":"0.00","color_name":"绿色","send_time":"","add_time":"2019-07-02 19:22:06","upd_time":"","bz":"","is_rowspan":0,"432_1":1},{"id":63,"reception_office":3,"source":3,"warehouse_id":1,"status":1,"store_id":1,"store_name":"太兴家具","pro_id":432,"pro_num":"2019070219214167459","pro_name":"123财源滚滚沙发6件套","unit_id":845,"unit_name":"三人位","unit_num":2,"wood_id":2,"wood_name":"刺猬紫檀","color_id":2,"cost":"0.00","color_name":"生漆","send_time":"","add_time":"2019-07-02 19:21:41","upd_time":"","bz":"","is_rowspan":1,"pro_count":0,"rowspan":1}]
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
         * id : 59
         * reception_office : 1
         * source : 1
         * warehouse_id : 1
         * status : 1
         * store_id : 1
         * store_name : 太兴-工厂
         * pro_id : 123
         * pro_num : 2019063009555848135
         * pro_name : 多喝水
         * unit_id : 554
         * unit_name : 担惊受恐
         * unit_num : 3
         * wood_id : 56
         * wood_name : 敦煌石窟
         * color_id :
         * cost : 2000.00
         * color_name :
         * send_time :
         * add_time : 2019-07-02 14:52:36
         * upd_time : 2019-07-02 14:52:36
         * bz :
         * is_rowspan : 1
         * pro_count : 0
         * rowspan : 1
         * 377_ : 1
         * 432_1 : 1
         */

        private int id;
        private int reception_office;
        private int source;
        private int warehouse_id;
        private int status;
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
        private String color_id;
        private String cost;
        private String color_name;
        private String send_time;
        private String add_time;
        private String upd_time;
        private String bz;
        private int is_rowspan;
        private int pro_count;
        private int rowspan;
        private int _$377_;
        private int _$432_1;
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

        public int getReception_office() {
            return reception_office;
        }

        public void setReception_office(int reception_office) {
            this.reception_office = reception_office;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public int getWarehouse_id() {
            return warehouse_id;
        }

        public void setWarehouse_id(int warehouse_id) {
            this.warehouse_id = warehouse_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public String getColor_id() {
            return color_id;
        }

        public void setColor_id(String color_id) {
            this.color_id = color_id;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getColor_name() {
            return color_name;
        }

        public void setColor_name(String color_name) {
            this.color_name = color_name;
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

        public String getBz() {
            return bz;
        }

        public void setBz(String bz) {
            this.bz = bz;
        }

        public int getIs_rowspan() {
            return is_rowspan;
        }

        public void setIs_rowspan(int is_rowspan) {
            this.is_rowspan = is_rowspan;
        }

        public int getPro_count() {
            return pro_count;
        }

        public void setPro_count(int pro_count) {
            this.pro_count = pro_count;
        }

        public int getRowspan() {
            return rowspan;
        }

        public void setRowspan(int rowspan) {
            this.rowspan = rowspan;
        }

        public int get_$377_() {
            return _$377_;
        }

        public void set_$377_(int _$377_) {
            this._$377_ = _$377_;
        }

        public int get_$432_1() {
            return _$432_1;
        }

        public void set_$432_1(int _$432_1) {
            this._$432_1 = _$432_1;
        }
    }
}
