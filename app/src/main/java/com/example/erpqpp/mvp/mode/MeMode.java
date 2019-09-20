package com.example.erpqpp.mvp.mode;

public class MeMode {


    /**
     * code : 1
     * msg : 获取成功
     * mobile : {"admin_name":"太兴仓管APP端","sex":1,"head_pic":"1","role_name":"仓管专员"}
     */

    private int code;
    private String msg;
    private MobileBean mobile;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MobileBean getMobile() {
        return mobile;
    }

    public void setMobile(MobileBean mobile) {
        this.mobile = mobile;
    }

    public static class MobileBean {
        /**
         * admin_name : 太兴仓管APP端
         * sex : 1
         * head_pic : 1
         * role_name : 仓管专员
         */

        private String admin_name;
        private int sex;
        private String head_pic;
        private String role_name;

        public String getAdmin_name() {
            return admin_name;
        }

        public void setAdmin_name(String admin_name) {
            this.admin_name = admin_name;
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

        public String getRole_name() {
            return role_name;
        }

        public void setRole_name(String role_name) {
            this.role_name = role_name;
        }
    }
}
