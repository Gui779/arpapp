package com.example.erpqpp.mvp.mode;

import java.io.Serializable;
import java.util.List;

public class SelectNameMode {

    /**
     * code : 1
     * message : 获取成功
     * data : {"list":[{"user_id":3,"user_name":"张来全","address":"北京西城区西直门大街6号院","tel":"17710448888","note":"1"}]}
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

        public static class ListBean implements Serializable {
            /**
             * user_id : 3
             * user_name : 张来全
             * address : 北京西城区西直门大街6号院
             * tel : 17710448888
             * note : 1
             */

            private int user_id;
            private String user_name;
            private String address;
            private String tel;
            private String note;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }
        }
    }
}
