package com.example.erpqpp.mvp.mode;

import java.io.Serializable;
import java.util.List;

public class XsproductListMode {


    /**
     * code : 1
     * message : 获取成功
     * data : {"list":[{"pro_id":383,"pro_name":"明韵架子床（不用打磨）","series":"","price":"0.00","cost_price":"0.00","wood_name":"其他","pro_list":[{"unit_id":746,"unit_name":"床头柜","rate":2},{"unit_id":745,"unit_name":"床","rate":1}]},{"pro_id":391,"pro_name":"明韵架子床（不要架子（不打磨","series":"","price":"0.00","cost_price":"0.00","wood_name":"其他","pro_list":[{"unit_id":760,"unit_name":"床头柜","rate":2},{"unit_id":759,"unit_name":"床","rate":1}]}],"count":2}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"pro_id":383,"pro_name":"明韵架子床（不用打磨）","series":"","price":"0.00","cost_price":"0.00","wood_name":"其他","pro_list":[{"unit_id":746,"unit_name":"床头柜","rate":2},{"unit_id":745,"unit_name":"床","rate":1}]},{"pro_id":391,"pro_name":"明韵架子床（不要架子（不打磨","series":"","price":"0.00","cost_price":"0.00","wood_name":"其他","pro_list":[{"unit_id":760,"unit_name":"床头柜","rate":2},{"unit_id":759,"unit_name":"床","rate":1}]}]
         * count : 2
         */

        private int count;
        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            @Override
            public String toString() {
                return "ListBean{" +
                        "pro_id=" + pro_id +
                        ", pro_name='" + pro_name + '\'' +
                        ", series='" + series + '\'' +
                        ", price='" + price + '\'' +
                        ", cost_price='" + cost_price + '\'' +
                        ", wood_name='" + wood_name + '\'' +
                        ", danjian='" + danjian + '\'' +
                        ", count='" + count + '\'' +
                        ", danjia='" + danjia + '\'' +
                        ", jine='" + jine + '\'' +
                        ", zhekoulv='" + zhekoulv + '\'' +
                        ", leixing='" + leixing + '\'' +
                        ", color='" + color + '\'' +
                        ", colorid='" + colorid + '\'' +
                        '}';
            }

            /**
             * pro_id : 383
             * pro_name : 明韵架子床（不用打磨）
             * series :
             * price : 0.00
             * cost_price : 0.00
             * wood_name : 其他
             * pro_list : [{"unit_id":746,"unit_name":"床头柜","rate":2},{"unit_id":745,"unit_name":"床","rate":1}]
             */


            private int pro_id;
            private String pro_name;
            private String series;
            private String price;
            private String cost_price;
            private String wood_name;
            private String danjian;
            private String count;
            private String danjia;
            private String jine;
            private String zhekoulv;
            private String leixing;
            private String leixingid;
            private String color;
            private String colorid;

            public String getLeixingid() {
                return leixingid;
            }

            public void setLeixingid(String leixingid) {
                this.leixingid = leixingid;
            }



            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getColorid() {
                return colorid;
            }

            public void setColorid(String colorid) {
                this.colorid = colorid;
            }

            public String getLeixing() {
                return leixing;
            }

            public void setLeixing(String leixing) {
                this.leixing = leixing;
            }

            public String getDanjian() {
                return danjian;
            }

            public void setDanjian(String danjian) {
                this.danjian = danjian;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getDanjia() {
                return danjia;
            }

            public void setDanjia(String danjia) {
                this.danjia = danjia;
            }

            public String getJine() {
                return jine;
            }

            public void setJine(String jine) {
                this.jine = jine;
            }

            public String getZhekoulv() {
                return zhekoulv;
            }

            public void setZhekoulv(String zhekoulv) {
                this.zhekoulv = zhekoulv;
            }

            private List<ProListBean> pro_list;

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

            public String getWood_name() {
                return wood_name;
            }

            public void setWood_name(String wood_name) {
                this.wood_name = wood_name;
            }

            public List<ProListBean> getPro_list() {
                return pro_list;
            }

            public void setPro_list(List<ProListBean> pro_list) {
                this.pro_list = pro_list;
            }

            public static class ProListBean implements Serializable{
                /**
                 * unit_id : 746
                 * unit_name : 床头柜
                 * rate : 2
                 */

                private int unit_id;
                private String unit_name;
                private int rate;

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

                public int getRate() {
                    return rate;
                }

                public void setRate(int rate) {
                    this.rate = rate;
                }
            }
        }
    }
}
