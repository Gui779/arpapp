package com.example.erpqpp.mvp.view;


import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.Mymode;

public interface Myview extends BaseView {

    void getDataSuccess(Mymode model);



    void getDataFail(String msg);



}
