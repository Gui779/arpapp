package com.example.erpqpp.mvp.mode;

import java.util.List;

public class OrderMsgMode {


    /**
     * code : 1
     * message : 获取成功
     * count : 5
     * data : [{"order_id":14,"add_time":"2019-07-11","status":2,"store_id":"1"},{"order_id":13,"add_time":"2019-07-11","status":2,"store_id":"1"},{"order_id":8,"add_time":"2019-07-22","status":3,"store_id":"1"},{"order_id":3,"add_time":"2019-07-01","status":2,"store_id":"1"},{"order_id":2,"add_time":"2019-07-01","status":2,"store_id":"1"}]
     */

    private int code;
    private String message;
    private int count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * order_id : 14
         * add_time : 2019-07-11
         * status : 2
         * store_id : 1
         */

        private int order_id;
        private String add_time;
        private int status;
        private String store_id;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
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

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }
    }
}
