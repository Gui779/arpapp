package com.example.erpqpp.utils;

import com.example.erpqpp.mvp.mode.Mgbean;

import java.util.List;

public class MyListToString {

    public static  String  setbanner(List<String> list){
        StringBuilder sb=new StringBuilder();
        if (list!=null&&list.size()>0) {
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i) + ",");
            }

           String substring = sb.substring(0, sb.toString().length() - 1);
            return substring;
        }
        return "";
    }
}
