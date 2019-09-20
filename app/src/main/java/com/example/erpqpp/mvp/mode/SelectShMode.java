package com.example.erpqpp.mvp.mode;

import java.util.List;

public class SelectShMode {

    /**
     * code : 1
     * message : 获取成功
     * data : {"list":[{"color_id":1,"color_num":"1","color_name":"绿色"},{"color_id":2,"color_num":"1","color_name":"生漆"},{"color_id":4,"color_num":"A00101","color_name":"本色"},{"color_id":5,"color_num":"005","color_name":"深色"},{"color_id":6,"color_num":"5","color_name":"常规色"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * color_id : 1
             * color_num : 1
             * color_name : 绿色
             */

            private int color_id;
            private String color_num;
            private String color_name;

            public int getColor_id() {
                return color_id;
            }

            public void setColor_id(int color_id) {
                this.color_id = color_id;
            }

            public String getColor_num() {
                return color_num;
            }

            public void setColor_num(String color_num) {
                this.color_num = color_num;
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
