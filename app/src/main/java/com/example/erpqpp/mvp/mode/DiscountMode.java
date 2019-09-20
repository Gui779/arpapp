package com.example.erpqpp.mvp.mode;

import java.util.List;

public class DiscountMode {


    /**
     * code : 1
     * message : 获取成功
     * data : {"list":[{"order_id":6,"customer_name":"傻涛","total":"250443.00","add_time":"2019-07-09","status":9,"order_num":"344","pro_list":[{"pro_name":"产品名称","sale_price":"250000.00","sale_num":1,"wood_name":"越南黄花梨","color_name":"绿色","per_rate":"100","unit":3,"add_time":"2019-07-18 16:12:04"},{"pro_name":"颠三倒四","sale_price":"443.00","sale_num":1,"wood_name":"小叶紫檀","color_name":"深色","per_rate":"100","unit":3,"add_time":"2019-07-18 16:12:04"}],"count":2,"sale_price":250443}],"count":1}
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
         * list : [{"order_id":6,"customer_name":"傻涛","total":"250443.00","add_time":"2019-07-09","status":9,"order_num":"344","pro_list":[{"pro_name":"产品名称","sale_price":"250000.00","sale_num":1,"wood_name":"越南黄花梨","color_name":"绿色","per_rate":"100","unit":3,"add_time":"2019-07-18 16:12:04"},{"pro_name":"颠三倒四","sale_price":"443.00","sale_num":1,"wood_name":"小叶紫檀","color_name":"深色","per_rate":"100","unit":3,"add_time":"2019-07-18 16:12:04"}],"count":2,"sale_price":250443}]
         * count : 1
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

        public static class ListBean {
            /**
             * order_id : 6
             * customer_name : 傻涛
             * total : 250443.00
             * add_time : 2019-07-09
             * status : 9
             * order_num : 344
             * pro_list : [{"pro_name":"产品名称","sale_price":"250000.00","sale_num":1,"wood_name":"越南黄花梨","color_name":"绿色","per_rate":"100","unit":3,"add_time":"2019-07-18 16:12:04"},{"pro_name":"颠三倒四","sale_price":"443.00","sale_num":1,"wood_name":"小叶紫檀","color_name":"深色","per_rate":"100","unit":3,"add_time":"2019-07-18 16:12:04"}]
             * count : 2
             * sale_price : 250443
             */

            private int order_id;
            private String customer_name;
            private String total;
            private String add_time;
            private int status;
            private String order_num;
            private int count;
            private int sale_price;
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

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
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

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getSale_price() {
                return sale_price;
            }

            public void setSale_price(int sale_price) {
                this.sale_price = sale_price;
            }

            public List<ProListBean> getPro_list() {
                return pro_list;
            }

            public void setPro_list(List<ProListBean> pro_list) {
                this.pro_list = pro_list;
            }

            public static class ProListBean {
                /**
                 * pro_name : 产品名称
                 * sale_price : 250000.00
                 * sale_num : 1
                 * wood_name : 越南黄花梨
                 * color_name : 绿色
                 * per_rate : 100
                 * unit : 3
                 * add_time : 2019-07-18 16:12:04
                 */

                private String pro_name;
                private String sale_price;
                private int sale_num;
                private String wood_name;
                private String color_name;
                private String per_rate;
                private int unit;
                private String add_time;

                public String getPro_name() {
                    return pro_name;
                }

                public void setPro_name(String pro_name) {
                    this.pro_name = pro_name;
                }

                public String getSale_price() {
                    return sale_price;
                }

                public void setSale_price(String sale_price) {
                    this.sale_price = sale_price;
                }

                public int getSale_num() {
                    return sale_num;
                }

                public void setSale_num(int sale_num) {
                    this.sale_num = sale_num;
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

                public String getPer_rate() {
                    return per_rate;
                }

                public void setPer_rate(String per_rate) {
                    this.per_rate = per_rate;
                }

                public int getUnit() {
                    return unit;
                }

                public void setUnit(int unit) {
                    this.unit = unit;
                }

                public String getAdd_time() {
                    return add_time;
                }

                public void setAdd_time(String add_time) {
                    this.add_time = add_time;
                }
            }
        }
    }
}
