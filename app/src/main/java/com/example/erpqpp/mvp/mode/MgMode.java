package com.example.erpqpp.mvp.mode;

public class MgMode {

    /**
     * code : 0
     * msg : 产品数量预减待收货数量后不能为负数!产品数量预减待收货数量后的数量分别为:3,-1
     */

    private String code;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
