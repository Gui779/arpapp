package com.example.erpqpp.mvp.mode;

import java.util.List;

public class XsListMode {

    /**
     * code : 1
     * ordersale : [{"admin_id":7,"admin_name":"太兴-测试销售专员","mobile":"18661350332","role_id":4},{"admin_id":14,"admin_name":"销售","mobile":"17710448926","role_id":4},{"admin_id":21,"admin_name":"皮杨洋","mobile":"13760379998","role_id":4},{"admin_id":22,"admin_name":"李四","mobile":"13760379999","role_id":4}]
     */

    private int code;
    private List<OrdersaleBean> ordersale;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<OrdersaleBean> getOrdersale() {
        return ordersale;
    }

    public void setOrdersale(List<OrdersaleBean> ordersale) {
        this.ordersale = ordersale;
    }

    public static class OrdersaleBean {
        /**
         * admin_id : 7
         * admin_name : 太兴-测试销售专员
         * mobile : 18661350332
         * role_id : 4
         */

        private int admin_id;
        private String admin_name;
        private String mobile;
        private int role_id;

        public int getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(int admin_id) {
            this.admin_id = admin_id;
        }

        public String getAdmin_name() {
            return admin_name;
        }

        public void setAdmin_name(String admin_name) {
            this.admin_name = admin_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getRole_id() {
            return role_id;
        }

        public void setRole_id(int role_id) {
            this.role_id = role_id;
        }
    }
}
