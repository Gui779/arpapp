package com.example.erpqpp.mvp.mode;

import java.util.List;

public class SendGoodsDetailsMode {


    /**
     * code : 1
     * message : 获取成功
     * data : [{"order_id":1,"customer_name":"木语轩","total":"30.00","status":2,"order_num":"666","add_time":"2019-05-23","pro_list":[{"id":1,"order_id":1,"pro_id":1,"series":"","pro_name":"20190523测试","wood_name":"小叶紫檀","color_name":"绿色","color_id":1,"sale_num":1,"sale_price":"10.00","remark":null,"unit":3,"rate_price":null,"price":"10","cost_price":"10","per_rate":"100","unit_list":[]},{"id":2,"order_id":1,"pro_id":1,"series":"","pro_name":"20190523测试","wood_name":"小叶紫檀","color_name":"绿色","color_id":1,"sale_num":1,"sale_price":"10.00","remark":null,"unit":3,"rate_price":null,"price":"10","cost_price":"10","per_rate":"100","unit_list":[]},{"id":3,"order_id":1,"pro_id":1,"series":"","pro_name":"20190523测试","wood_name":"小叶紫檀","color_name":"绿色","color_id":1,"sale_num":1,"sale_price":"10.00","remark":null,"unit":3,"rate_price":null,"price":"10","cost_price":"10","per_rate":"100","unit_list":[{"unit_id":1,"unit_name":"单人位","count":0,"rate":1},{"unit_id":2,"unit_name":"双人位","count":8,"rate":4}]}]}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * order_id : 1
         * customer_name : 木语轩
         * total : 30.00
         * status : 2
         * order_num : 666
         * add_time : 2019-05-23
         * pro_list : [{"id":1,"order_id":1,"pro_id":1,"series":"","pro_name":"20190523测试","wood_name":"小叶紫檀","color_name":"绿色","color_id":1,"sale_num":1,"sale_price":"10.00","remark":null,"unit":3,"rate_price":null,"price":"10","cost_price":"10","per_rate":"100","unit_list":[]},{"id":2,"order_id":1,"pro_id":1,"series":"","pro_name":"20190523测试","wood_name":"小叶紫檀","color_name":"绿色","color_id":1,"sale_num":1,"sale_price":"10.00","remark":null,"unit":3,"rate_price":null,"price":"10","cost_price":"10","per_rate":"100","unit_list":[]},{"id":3,"order_id":1,"pro_id":1,"series":"","pro_name":"20190523测试","wood_name":"小叶紫檀","color_name":"绿色","color_id":1,"sale_num":1,"sale_price":"10.00","remark":null,"unit":3,"rate_price":null,"price":"10","cost_price":"10","per_rate":"100","unit_list":[{"unit_id":1,"unit_name":"单人位","count":0,"rate":1},{"unit_id":2,"unit_name":"双人位","count":8,"rate":4}]}]
         */

        private int order_id;
        private String customer_name;
        private String total;
        private int status;
        private String order_num;
        private String add_time;
        private List<ProListBean> pro_list;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public List<ProListBean> getPro_list() {
            return pro_list;
        }

        public void setPro_list(List<ProListBean> pro_list) {
            this.pro_list = pro_list;
        }

        public static class ProListBean {
            /**
             * id : 1
             * order_id : 1
             * pro_id : 1
             * series :
             * pro_name : 20190523测试
             * wood_name : 小叶紫檀
             * color_name : 绿色
             * color_id : 1
             * sale_num : 1
             * sale_price : 10.00
             * remark : null
             * unit : 3
             * rate_price : null
             * price : 10
             * cost_price : 10
             * per_rate : 100
             * unit_list : []
             */

            private int id;
            private int order_id;
            private int pro_id;
            private String series;
            private String pro_name;
            private String wood_name;
            private String color_name;
            private int color_id;
            private int sale_num;
            private String sale_price;
            private Object remark;
            private int unit;
            private Object rate_price;
            private String price;
            private String cost_price;
            private String per_rate;
            private List<Unit> unit_list;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public int getPro_id() {
                return pro_id;
            }

            public void setPro_id(int pro_id) {
                this.pro_id = pro_id;
            }

            public String getSeries() {
                return series;
            }

            public void setSeries(String series) {
                this.series = series;
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

            public String getColor_name() {
                return color_name;
            }

            public void setColor_name(String color_name) {
                this.color_name = color_name;
            }

            public int getColor_id() {
                return color_id;
            }

            public void setColor_id(int color_id) {
                this.color_id = color_id;
            }

            public int getSale_num() {
                return sale_num;
            }

            public void setSale_num(int sale_num) {
                this.sale_num = sale_num;
            }

            public String getSale_price() {
                return sale_price;
            }

            public void setSale_price(String sale_price) {
                this.sale_price = sale_price;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public int getUnit() {
                return unit;
            }

            public void setUnit(int unit) {
                this.unit = unit;
            }

            public Object getRate_price() {
                return rate_price;
            }

            public void setRate_price(Object rate_price) {
                this.rate_price = rate_price;
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

            public String getPer_rate() {
                return per_rate;
            }

            public void setPer_rate(String per_rate) {
                this.per_rate = per_rate;
            }

            public List<Unit> getUnit_list() {
                return unit_list;
            }

            public void setUnit_list(List<Unit> unit_list) {
                this.unit_list = unit_list;
            }

            public static   class Unit{

                /**
                 * unit_id : 1
                 * unit_name : 单人位
                 * count : 0
                 * rate : 1
                 */

                private int unit_id;
                private String unit_name;
                private int count;
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

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
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
