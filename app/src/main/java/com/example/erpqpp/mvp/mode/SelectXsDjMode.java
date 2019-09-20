package com.example.erpqpp.mvp.mode;

import java.io.Serializable;
import java.util.List;

public class SelectXsDjMode {

    /**
     * code : 1
     * message : 获取成功
     * data : {"list":[{"unit_id":205,"unit_name":"台","rate":1},{"unit_id":206,"unit_name":"椅","rate":7},{"unit_id":886,"unit_name":"高几","rate":1},{"unit_id":887,"unit_name":"平几","rate":1},{"unit_id":888,"unit_name":"凳子","rate":4},{"unit_id":889,"unit_name":"高几","rate":1},{"unit_id":890,"unit_name":"平几","rate":1},{"unit_id":891,"unit_name":"凳子","rate":4},{"unit_id":892,"unit_name":"高几","rate":1},{"unit_id":893,"unit_name":"平几","rate":1},{"unit_id":894,"unit_name":"凳子","rate":4}]}
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
        private List<ProListBean> list;

        public List<ProListBean> getList() {
            return list;
        }

        public void setList(List<ProListBean> list) {
            this.list = list;
        }

        public static class ProListBean implements Serializable {
            /**
             * unit_id : 205
             * unit_name : 台
             * rate : 1
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
