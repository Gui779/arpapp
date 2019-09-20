package com.example.erpqpp.mvp.mode;

import java.io.Serializable;
import java.util.List;

public class SingletonMode {

    /**
     * code : 1
     * msg :
     * data : [{"unit_id":254,"unit_name":"张","pro_id":23}]
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

    public static class DataBean implements Serializable {
        /**
         * unit_id : 254
         * unit_name : 张
         * pro_id : 23
         */

        private int unit_id;
        private String unit_name;
        private int pro_id;

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

        public int getPro_id() {
            return pro_id;
        }

        public void setPro_id(int pro_id) {
            this.pro_id = pro_id;
        }
    }
}
