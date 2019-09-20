package com.example.erpqpp.mvp.mode;

import java.util.List;

public class StatisticsMode {


    /**
     * code : 1
     * msg : 操作成功
     * data : [{"pro_id":444,"store_id":1,"pro_name":"产品名称2","unit_id":852,"unit_name":"高几2","pro_num":"201907040922631","sn1_mgdsh":0,"sn2_mgsh":0,"sn3_wmck":0,"sn4_dmwsh":0,"sn5_dmsh":0,"sn6_ymck":22,"sn7_yqdsh":22,"sn8_yqsh":0,"sn9_yqsh":0,"sn10_cp":22,"sn11_yxswjh":0,"sn12_ythwjh":0,"sn13_yl":44,"woodWarehousePay":0,"polishingReceiptPay":0,"paintWarehousePay":0,"productWarehousePay":"0.00","work_mg_pro_NUm":0},{"pro_id":444,"store_id":1,"pro_name":"产品名称2","unit_id":853,"unit_name":"平几2","pro_num":"201907040922631","sn1_mgdsh":0,"sn2_mgsh":0,"sn3_wmck":11,"sn4_dmwsh":11,"sn5_dmsh":0,"sn6_ymck":11,"sn7_yqdsh":11,"sn8_yqsh":0,"sn9_yqsh":0,"sn10_cp":0,"sn11_yxswjh":0,"sn12_ythwjh":0,"sn13_yl":22,"woodWarehousePay":0,"polishingReceiptPay":0,"paintWarehousePay":0,"productWarehousePay":0,"work_mg_pro_NUm":0},{"pro_id":444,"store_id":1,"pro_name":"产品名称2","unit_id":854,"unit_name":"凳子2","pro_num":"201907040922631","sn1_mgdsh":0,"sn2_mgsh":0,"sn3_wmck":0,"sn4_dmwsh":0,"sn5_dmsh":0,"sn6_ymck":0,"sn7_yqdsh":0,"sn8_yqsh":0,"sn9_yqsh":0,"sn10_cp":0,"sn11_yxswjh":0,"sn12_ythwjh":0,"sn13_yl":0,"woodWarehousePay":0,"polishingReceiptPay":0,"paintWarehousePay":0,"productWarehousePay":0,"work_mg_pro_NUm":0},{"pro_id":443,"store_id":1,"pro_name":"产品名称1","unit_id":851,"unit_name":"'凳子1'","pro_num":"201907040911007","sn1_mgdsh":0,"sn2_mgsh":0,"sn3_wmck":0,"sn4_dmwsh":0,"sn5_dmsh":0,"sn6_ymck":0,"sn7_yqdsh":0,"sn8_yqsh":0,"sn9_yqsh":0,"sn10_cp":0,"sn11_yxswjh":0,"sn12_ythwjh":0,"sn13_yl":0,"woodWarehousePay":0,"polishingReceiptPay":0,"paintWarehousePay":0,"productWarehousePay":0,"work_mg_pro_NUm":0},{"pro_id":443,"store_id":1,"pro_name":"产品名称1","unit_id":849,"unit_name":"'高几1'","pro_num":"201907040911007","sn1_mgdsh":0,"sn2_mgsh":0,"sn3_wmck":0,"sn4_dmwsh":0,"sn5_dmsh":0,"sn6_ymck":0,"sn7_yqdsh":0,"sn8_yqsh":0,"sn9_yqsh":0,"sn10_cp":0,"sn11_yxswjh":0,"sn12_ythwjh":0,"sn13_yl":0,"woodWarehousePay":0,"polishingReceiptPay":0,"paintWarehousePay":0,"productWarehousePay":0,"work_mg_pro_NUm":0},{"pro_id":443,"store_id":1,"pro_name":"产品名称1","unit_id":850,"unit_name":"'平几1'","pro_num":"201907040911007","sn1_mgdsh":0,"sn2_mgsh":0,"sn3_wmck":0,"sn4_dmwsh":0,"sn5_dmsh":0,"sn6_ymck":0,"sn7_yqdsh":0,"sn8_yqsh":0,"sn9_yqsh":0,"sn10_cp":0,"sn11_yxswjh":0,"sn12_ythwjh":0,"sn13_yl":0,"woodWarehousePay":0,"polishingReceiptPay":0,"paintWarehousePay":0,"productWarehousePay":0,"work_mg_pro_NUm":0},{"pro_id":432,"store_id":1,"pro_name":"123财源滚滚沙发6件套","unit_id":843,"unit_name":"单人位","pro_num":"201907021423852","sn1_mgdsh":0,"sn2_mgsh":0,"sn3_wmck":0,"sn4_dmwsh":0,"sn5_dmsh":0,"sn6_ymck":0,"sn7_yqdsh":0,"sn8_yqsh":0,"sn9_yqsh":2,"sn10_cp":101,"sn11_yxswjh":0,"sn12_ythwjh":0,"sn13_yl":103,"woodWarehousePay":0,"polishingReceiptPay":0,"paintWarehousePay":0,"productWarehousePay":"11111.00","work_mg_pro_NUm":0},{"pro_id":432,"store_id":1,"pro_name":"123财源滚滚沙发6件套","unit_id":844,"unit_name":"双人位","pro_num":"201907021423852","sn1_mgdsh":0,"sn2_mgsh":0,"sn3_wmck":0,"sn4_dmwsh":0,"sn5_dmsh":0,"sn6_ymck":0,"sn7_yqdsh":0,"sn8_yqsh":0,"sn9_yqsh":2,"sn10_cp":1,"sn11_yxswjh":0,"sn12_ythwjh":0,"sn13_yl":3,"woodWarehousePay":0,"polishingReceiptPay":0,"paintWarehousePay":0,"productWarehousePay":"22222.00","work_mg_pro_NUm":0},{"pro_id":432,"store_id":1,"pro_name":"123财源滚滚沙发6件套","unit_id":845,"unit_name":"三人位","pro_num":"201907021423852","sn1_mgdsh":0,"sn2_mgsh":0,"sn3_wmck":0,"sn4_dmwsh":0,"sn5_dmsh":0,"sn6_ymck":0,"sn7_yqdsh":0,"sn8_yqsh":0,"sn9_yqsh":200,"sn10_cp":103,"sn11_yxswjh":0,"sn12_ythwjh":0,"sn13_yl":303,"woodWarehousePay":0,"polishingReceiptPay":0,"paintWarehousePay":0,"productWarehousePay":"33333.00","work_mg_pro_NUm":0},{"pro_id":432,"store_id":1,"pro_name":"123财源滚滚沙发6件套","unit_id":846,"unit_name":"花几","pro_num":"201907021423852","sn1_mgdsh":0,"sn2_mgsh":0,"sn3_wmck":0,"sn4_dmwsh":0,"sn5_dmsh":0,"sn6_ymck":0,"sn7_yqdsh":0,"sn8_yqsh":0,"sn9_yqsh":4,"sn10_cp":2,"sn11_yxswjh":0,"sn12_ythwjh":0,"sn13_yl":6,"woodWarehousePay":0,"polishingReceiptPay":0,"paintWarehousePay":0,"productWarehousePay":"44444.00","work_mg_pro_NUm":0}]
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
         * pro_id : 444
         * store_id : 1
         * pro_name : 产品名称2
         * unit_id : 852
         * unit_name : 高几2
         * pro_num : 201907040922631
         * sn1_mgdsh : 0
         * sn2_mgsh : 0
         * sn3_wmck : 0
         * sn4_dmwsh : 0
         * sn5_dmsh : 0
         * sn6_ymck : 22
         * sn7_yqdsh : 22
         * sn8_yqsh : 0
         * sn9_yqsh : 0
         * sn10_cp : 22
         * sn11_yxswjh : 0
         * sn12_ythwjh : 0
         * sn13_yl : 44
         * woodWarehousePay : 0
         * polishingReceiptPay : 0
         * paintWarehousePay : 0
         * productWarehousePay : 0.00
         * work_mg_pro_NUm : 0
         */

        private int pro_id;
        private int store_id;
        private String pro_name;
        private int unit_id;
        private String unit_name;
        private String pro_num;
        private int sn1_mgdsh;
        private int sn2_mgsh;
        private int sn3_wmck;
        private int sn4_dmwsh;
        private int sn5_dmsh;
        private int sn6_ymck;
        private int sn7_yqdsh;
        private int sn8_yqsh;
        private int sn9_yqsh;
        private int sn10_cp;
        private int sn11_yxswjh;
        private int sn12_ythwjh;
        private int sn13_yl;
        private int woodWarehousePay;
        private int polishingReceiptPay;
        private int paintWarehousePay;
        private String productWarehousePay;
        private int work_mg_pro_NUm;

        public int getPro_id() {
            return pro_id;
        }

        public void setPro_id(int pro_id) {
            this.pro_id = pro_id;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
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

        public String getPro_num() {
            return pro_num;
        }

        public void setPro_num(String pro_num) {
            this.pro_num = pro_num;
        }

        public int getSn1_mgdsh() {
            return sn1_mgdsh;
        }

        public void setSn1_mgdsh(int sn1_mgdsh) {
            this.sn1_mgdsh = sn1_mgdsh;
        }

        public int getSn2_mgsh() {
            return sn2_mgsh;
        }

        public void setSn2_mgsh(int sn2_mgsh) {
            this.sn2_mgsh = sn2_mgsh;
        }

        public int getSn3_wmck() {
            return sn3_wmck;
        }

        public void setSn3_wmck(int sn3_wmck) {
            this.sn3_wmck = sn3_wmck;
        }

        public int getSn4_dmwsh() {
            return sn4_dmwsh;
        }

        public void setSn4_dmwsh(int sn4_dmwsh) {
            this.sn4_dmwsh = sn4_dmwsh;
        }

        public int getSn5_dmsh() {
            return sn5_dmsh;
        }

        public void setSn5_dmsh(int sn5_dmsh) {
            this.sn5_dmsh = sn5_dmsh;
        }

        public int getSn6_ymck() {
            return sn6_ymck;
        }

        public void setSn6_ymck(int sn6_ymck) {
            this.sn6_ymck = sn6_ymck;
        }

        public int getSn7_yqdsh() {
            return sn7_yqdsh;
        }

        public void setSn7_yqdsh(int sn7_yqdsh) {
            this.sn7_yqdsh = sn7_yqdsh;
        }

        public int getSn8_yqsh() {
            return sn8_yqsh;
        }

        public void setSn8_yqsh(int sn8_yqsh) {
            this.sn8_yqsh = sn8_yqsh;
        }

        public int getSn9_yqsh() {
            return sn9_yqsh;
        }

        public void setSn9_yqsh(int sn9_yqsh) {
            this.sn9_yqsh = sn9_yqsh;
        }

        public int getSn10_cp() {
            return sn10_cp;
        }

        public void setSn10_cp(int sn10_cp) {
            this.sn10_cp = sn10_cp;
        }

        public int getSn11_yxswjh() {
            return sn11_yxswjh;
        }

        public void setSn11_yxswjh(int sn11_yxswjh) {
            this.sn11_yxswjh = sn11_yxswjh;
        }

        public int getSn12_ythwjh() {
            return sn12_ythwjh;
        }

        public void setSn12_ythwjh(int sn12_ythwjh) {
            this.sn12_ythwjh = sn12_ythwjh;
        }

        public int getSn13_yl() {
            return sn13_yl;
        }

        public void setSn13_yl(int sn13_yl) {
            this.sn13_yl = sn13_yl;
        }

        public int getWoodWarehousePay() {
            return woodWarehousePay;
        }

        public void setWoodWarehousePay(int woodWarehousePay) {
            this.woodWarehousePay = woodWarehousePay;
        }

        public int getPolishingReceiptPay() {
            return polishingReceiptPay;
        }

        public void setPolishingReceiptPay(int polishingReceiptPay) {
            this.polishingReceiptPay = polishingReceiptPay;
        }

        public int getPaintWarehousePay() {
            return paintWarehousePay;
        }

        public void setPaintWarehousePay(int paintWarehousePay) {
            this.paintWarehousePay = paintWarehousePay;
        }

        public String getProductWarehousePay() {
            return productWarehousePay;
        }

        public void setProductWarehousePay(String productWarehousePay) {
            this.productWarehousePay = productWarehousePay;
        }

        public int getWork_mg_pro_NUm() {
            return work_mg_pro_NUm;
        }

        public void setWork_mg_pro_NUm(int work_mg_pro_NUm) {
            this.work_mg_pro_NUm = work_mg_pro_NUm;
        }
    }
}
