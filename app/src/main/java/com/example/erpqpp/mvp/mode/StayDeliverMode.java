package com.example.erpqpp.mvp.mode;

import java.util.List;

public class StayDeliverMode {

    /**
     * code : 1
     * message : 获取成功
     * data : {"list":[{"order_id":9,"customer_name":"张来全","total":"58800.00","add_time":"2019-05-27","pro_list":[{"pro_name":"138山居秋明9件套","sale_price":"58800.00","sale_num":1}],"count":1,"sale_price":58800},{"order_id":7,"customer_name":"张来全","total":"28120.00","add_time":"2019-06-03","pro_list":[{"pro_name":"138山居秋明圆台","sale_price":"8040.00","sale_num":3},{"pro_name":"山居秋明餐椅","sale_price":"4000.00","sale_num":1}],"count":2,"sale_price":12040},{"order_id":6,"customer_name":"张来全","total":"500000.00","add_time":"2019-04-29","pro_list":[{"pro_name":"荷花宝座沙发","sale_price":"500000.00","sale_num":1}],"count":1,"sale_price":500000},{"order_id":5,"customer_name":"zhan三","total":"2635000.00","add_time":"2019-05-23","pro_list":[{"pro_name":"山居秋明办公台","sale_price":"2500000.00","sale_num":1},{"pro_name":"山居秋暝茶台","sale_price":"135000.00","sale_num":1}],"count":2,"sale_price":2635000},{"order_id":3,"customer_name":"木语轩","total":"30.00","add_time":"2019-05-23","pro_list":[{"pro_name":"1426测试","sale_price":"10.00","sale_num":2},{"pro_name":"1426测试","sale_price":"10.00","sale_num":1}],"count":2,"sale_price":20},{"order_id":1,"customer_name":"木语轩","total":"30.00","add_time":"2019-05-23","pro_list":[{"pro_name":"20190523测试","sale_price":"10.00","sale_num":1},{"pro_name":"20190523测试","sale_price":"10.00","sale_num":1},{"pro_name":"20190523测试","sale_price":"10.00","sale_num":1}],"count":3,"sale_price":30}],"count":6}
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
         * list : [{"order_id":9,"customer_name":"张来全","total":"58800.00","add_time":"2019-05-27","pro_list":[{"pro_name":"138山居秋明9件套","sale_price":"58800.00","sale_num":1}],"count":1,"sale_price":58800},{"order_id":7,"customer_name":"张来全","total":"28120.00","add_time":"2019-06-03","pro_list":[{"pro_name":"138山居秋明圆台","sale_price":"8040.00","sale_num":3},{"pro_name":"山居秋明餐椅","sale_price":"4000.00","sale_num":1}],"count":2,"sale_price":12040},{"order_id":6,"customer_name":"张来全","total":"500000.00","add_time":"2019-04-29","pro_list":[{"pro_name":"荷花宝座沙发","sale_price":"500000.00","sale_num":1}],"count":1,"sale_price":500000},{"order_id":5,"customer_name":"zhan三","total":"2635000.00","add_time":"2019-05-23","pro_list":[{"pro_name":"山居秋明办公台","sale_price":"2500000.00","sale_num":1},{"pro_name":"山居秋暝茶台","sale_price":"135000.00","sale_num":1}],"count":2,"sale_price":2635000},{"order_id":3,"customer_name":"木语轩","total":"30.00","add_time":"2019-05-23","pro_list":[{"pro_name":"1426测试","sale_price":"10.00","sale_num":2},{"pro_name":"1426测试","sale_price":"10.00","sale_num":1}],"count":2,"sale_price":20},{"order_id":1,"customer_name":"木语轩","total":"30.00","add_time":"2019-05-23","pro_list":[{"pro_name":"20190523测试","sale_price":"10.00","sale_num":1},{"pro_name":"20190523测试","sale_price":"10.00","sale_num":1},{"pro_name":"20190523测试","sale_price":"10.00","sale_num":1}],"count":3,"sale_price":30}]
         * count : 6
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
             * order_id : 9
             * customer_name : 张来全
             * total : 58800.00
             * add_time : 2019-05-27
             * pro_list : [{"pro_name":"138山居秋明9件套","sale_price":"58800.00","sale_num":1}]
             * count : 1
             * sale_price : 58800
             */

            private int order_id;
            private String customer_name;
            private String total;
            private String add_time;
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
                 * pro_name : 138山居秋明9件套
                 * sale_price : 58800.00
                 * sale_num : 1
                 */

                private String pro_name;
                private String sale_price;
                private int sale_num;

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
            }
        }
    }
}
