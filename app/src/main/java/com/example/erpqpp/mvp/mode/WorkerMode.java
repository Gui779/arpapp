package com.example.erpqpp.mvp.mode;

import java.util.List;

public class WorkerMode {

    /**
     * code : 1
     * msg :
     * data : [{"worker_id":1,"worker_name":"张三","dep_id":1,"dep_name":"木工部"}]
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

    public static class DataBean {
        /**
         * worker_id : 1
         * worker_name : 张三
         * dep_id : 1
         * dep_name : 木工部
         */

        private int worker_id;
        private String worker_name;
        private int dep_id;
        private String dep_name;

        public int getWorker_id() {
            return worker_id;
        }

        public void setWorker_id(int worker_id) {
            this.worker_id = worker_id;
        }

        public String getWorker_name() {
            return worker_name;
        }

        public void setWorker_name(String worker_name) {
            this.worker_name = worker_name;
        }

        public int getDep_id() {
            return dep_id;
        }

        public void setDep_id(int dep_id) {
            this.dep_id = dep_id;
        }

        public String getDep_name() {
            return dep_name;
        }

        public void setDep_name(String dep_name) {
            this.dep_name = dep_name;
        }
    }
}
