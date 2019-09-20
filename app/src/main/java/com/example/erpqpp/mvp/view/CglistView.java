package com.example.erpqpp.mvp.view;


import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.CgStayGoMode;
import com.example.erpqpp.mvp.mode.MgMode;

public interface CglistView extends BaseView {
    void getDataSuccess(CgStayGoMode cgStayGoMode);

    void getDataFail(String msg);


    void getYzDataSuccess(MgMode mgMode);

    void getYzDataFail(String msg);



}
