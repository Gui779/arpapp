package com.example.erpqpp.mvp.mode;

import java.util.List;

public class MarkettjMode {


    /**
     * code : 1
     * message : 获取成功
     * data : {"list":[{"order_id":3,"customer_name":"木语轩","add_time":"2019-05-23","total":"30.00","pro_list":[{"pro_name":"1426测试","sale_price":"10.00","sale_num":2},{"pro_name":"1426测试","sale_price":"10.00","sale_num":1}],"count":2,"sale_price":20}],"count":12,"sum":20,"order_unit":"3439050.00"}
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
         * list : [{"order_id":3,"customer_name":"木语轩","add_time":"2019-05-23","total":"30.00","pro_list":[{"pro_name":"1426测试","sale_price":"10.00","sale_num":2},{"pro_name":"1426测试","sale_price":"10.00","sale_num":1}],"count":2,"sale_price":20}]
         * count : 12
         * sum : 20
         * order_unit : 3439050.00
         */

        private int count;
        private int sum;
        private String order_unit;
        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public String getOrder_unit() {
            return order_unit;
        }

        public void setOrder_unit(String order_unit) {
            this.order_unit = order_unit;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * order_id : 3
             * customer_name : 木语轩
             * add_time : 2019-05-23
             * total : 30.00
             * pro_list : [{"pro_name":"1426测试","sale_price":"10.00","sale_num":2},{"pro_name":"1426测试","sale_price":"10.00","sale_num":1}]
             * count : 2
             * sale_price : 20
             */

            private int order_id;
            private String customer_name;
            private String add_time;
            private String total;
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

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
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
                 * pro_name : 1426测试
                 * sale_price : 10.00
                 * sale_num : 2
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
