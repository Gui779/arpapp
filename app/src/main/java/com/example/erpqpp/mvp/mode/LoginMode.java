package com.example.erpqpp.mvp.mode;

public class LoginMode {
    /**
     * code : 1
     * msg :
     * data : {"admin_id":4,"nickname":"太兴仓管","mobile":"13212345678","role_id":5,"store_id":1,"sex":1,"head_pic":"1"}
     */

    private String code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "admin_id=" + admin_id +
                    ", nickname='" + nickname + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", role_id=" + role_id +
                    ", store_id=" + store_id +
                    ", sex=" + sex +
                    ", head_pic='" + head_pic + '\'' +
                    '}';
        }

        /**
         * admin_id : 4
         * nickname : 太兴仓管
         * mobile : 13212345678
         * role_id : 5
         * store_id : 1
         * sex : 1
         * head_pic : 1
         */

        private int admin_id;
        private String nickname;
        private String mobile;
        private int role_id;
        private int store_id;
        private int sex;
        private String head_pic;

        public int getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(int admin_id) {
            this.admin_id = admin_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
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

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
        }
    }
}
