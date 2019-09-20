package com.example.erpqpp.mvp.mode;

import java.io.Serializable;
import java.util.List;

public class SelectCpCzMode {

    /**
     * code : 1
     * msg :
     * data : [{"wood_id":1,"wood_name":"小叶紫檀"},{"wood_id":2,"wood_name":"刺猬紫檀"},{"wood_id":3,"wood_name":"檀香系列"},{"wood_id":4,"wood_name":"古夷苏木"},{"wood_id":5,"wood_name":"大果紫檀"},{"wood_id":6,"wood_name":"交趾黄檀"},{"wood_id":8,"wood_name":"古夷苏木"},{"wood_id":16,"wood_name":"金丝楠"},{"wood_id":17,"wood_name":"大红酸枝"},{"wood_id":18,"wood_name":"缅花"},{"wood_id":19,"wood_name":"其他"}]
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
         * wood_id : 1
         * wood_name : 小叶紫檀
         */

        private int wood_id;
        private String wood_name;

        public int getWood_id() {
            return wood_id;
        }

        public void setWood_id(int wood_id) {
            this.wood_id = wood_id;
        }

        public String getWood_name() {
            return wood_name;
        }

        public void setWood_name(String wood_name) {
            this.wood_name = wood_name;
        }
    }
}
