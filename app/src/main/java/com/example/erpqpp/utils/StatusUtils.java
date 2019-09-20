package com.example.erpqpp.utils;

public class StatusUtils {

    public static String getstatus(int ststus){
        switch (ststus){
            case 1:
                return "收货";
            case 2:
                return "发货";
            case 3:
                return "收货撤回";
            case 4:
                return "待发货撤回";
            case 5:
                return "发货撤回";
            case 6:
                return "新增";
        }
        return "";
    }


    public static String getxsstatus(int ststus){
        switch (ststus){
            case 1:
                return "审核中";
            case 2:
                return "待出货";
            case 3:
                return "已出货";
            case 4:
                return "已完成";
            case 5:
                return "已收货";
            case 6:
                return "退货完成";
            case 7:
                return "审核拒绝";
            case 8:
                return "店长审核";
            case 9:
                return "折扣待审批";
            case 10:
                return "折扣完成";
            case 11:
                return "待收货";
        }
        return "";
    }

    public static String getsource(int source){
        switch (source){
            case 1:
                return "生产";
            case 2:
                return "打磨外购";
            case 3:
                return "上漆外购";
        }
        return "";
    }

    public static String getleixing(int unit){
        switch (unit){
            case 1:
                return "木工半成品";
            case 2:
                return "打磨半成品";
            case 3:
                return "成品(上漆产品)";
        }
        return "";
    }
}
