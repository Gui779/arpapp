package com.example.erpqpp.mvp.mode;

import java.io.Serializable;
import java.util.List;

public class SelectMgMode {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"productAndUnitList":[{"pro_id":228,"pro_name":"山居秋明视听柜","unit_id":419,"unit_name":"中柜","wood_id":2,"wood_name":"刺猬紫檀","series":"山居秋明","price":"58600.00","cost_price":"0.00"},{"pro_id":229,"pro_name":"山居秋明书柜","unit_id":420,"unit_name":"高柜","wood_id":2,"wood_name":"刺猬紫檀","series":"山居秋明","price":"83200.00","cost_price":"0.00"},{"pro_id":229,"pro_name":"山居秋明书柜","unit_id":421,"unit_name":"矮柜","wood_id":2,"wood_name":"刺猬紫檀","series":"山居秋明","price":"83200.00","cost_price":"0.00"},{"pro_id":230,"pro_name":"山居秋明书柜高柜","unit_id":422,"unit_name":"柜","wood_id":2,"wood_name":"刺猬紫檀","series":"山居秋明","price":"19600.00","cost_price":"0.00"},{"pro_id":231,"pro_name":"山居秋明书柜矮柜","unit_id":423,"unit_name":"柜","wood_id":2,"wood_name":"刺猬紫檀","series":"山居秋明","price":"15200.00","cost_price":"0.00"},{"pro_id":232,"pro_name":"山居秋明书桌","unit_id":424,"unit_name":"台","wood_id":2,"wood_name":"刺猬紫檀","series":"山居秋明","price":"29200.00","cost_price":"0.00"},{"pro_id":232,"pro_name":"山居秋明书桌","unit_id":425,"unit_name":"椅","wood_id":2,"wood_name":"刺猬紫檀","series":"山居秋明","price":"29200.00","cost_price":"0.00"},{"pro_id":233,"pro_name":"山居秋明梳妆台","unit_id":426,"unit_name":"梳妆台","wood_id":2,"wood_name":"刺猬紫檀","series":"山居秋明","price":"16400.00","cost_price":"0.00"},{"pro_id":233,"pro_name":"山居秋明梳妆台","unit_id":427,"unit_name":"妆凳","wood_id":2,"wood_name":"刺猬紫檀","series":"山居秋明","price":"16400.00","cost_price":"0.00"},{"pro_id":234,"pro_name":"山居秋明鞋柜","unit_id":428,"unit_name":"柜","wood_id":2,"wood_name":"刺猬紫檀","series":"山居秋明","price":"18500.00","cost_price":"0.00"}],"productColor":[{"color_id":1,"color_name":"绿色"},{"color_id":2,"color_name":"生漆"},{"color_id":4,"color_name":"本色"},{"color_id":5,"color_name":"深色"},{"color_id":6,"color_name":"常规色"}]}
     */

    private String code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ProductAndUnitListBean> productAndUnitList;
        private List<ProductColorBean> productColor;

        public List<ProductAndUnitListBean> getProductAndUnitList() {
            return productAndUnitList;
        }

        public void setProductAndUnitList(List<ProductAndUnitListBean> productAndUnitList) {
            this.productAndUnitList = productAndUnitList;
        }

        public List<ProductColorBean> getProductColor() {
            return productColor;
        }

        public void setProductColor(List<ProductColorBean> productColor) {
            this.productColor = productColor;
        }

        public static class ProductAndUnitListBean implements Serializable {
            /**
             * pro_id : 228
             * pro_name : 山居秋明视听柜
             * unit_id : 419
             * unit_name : 中柜
             * wood_id : 2
             * wood_name : 刺猬紫檀
             * series : 山居秋明
             * price : 58600.00
             * cost_price : 0.00
             */

            private int pro_id;
            private String pro_name;
            private int unit_id;
            private String unit_name;
            private int wood_id;
            private String wood_name;
            private String series;
            private String price;
            private String cost_price;
            private String count;
            private boolean isselect=false;
            private String cangku;
            private String cangkuid;
            private String colorname;
            private String colorid;
            private String tjname;

            public String getTjname() {
                return tjname;
            }

            public void setTjname(String tjname) {
                this.tjname = tjname;
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

            public String getCangku() {
                return cangku;
            }

            public void setCangku(String cangku) {
                this.cangku = cangku;
            }

            public String getCangkuid() {
                return cangkuid;
            }

            public void setCangkuid(String cangkuid) {
                this.cangkuid = cangkuid;
            }

            public boolean isIsselect() {
                return isselect;
            }

            public void setIsselect(boolean isselect) {
                this.isselect = isselect;
            }




            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

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
        }

        public static class ProductColorBean {
            /**
             * color_id : 1
             * color_name : 绿色
             */

            private int color_id;
            private String color_name;

            public int getColor_id() {
                return color_id;
            }

            public void setColor_id(int color_id) {
                this.color_id = color_id;
            }

            public String getColor_name() {
                return color_name;
            }

            public void setColor_name(String color_name) {
                this.color_name = color_name;
            }
        }
    }
}
