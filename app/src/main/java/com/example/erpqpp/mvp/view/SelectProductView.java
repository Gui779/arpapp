package com.example.erpqpp.mvp.view;


import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.SelectMgMode;

public interface SelectProductView extends BaseView {
    void getDataSuccess(SelectMgMode selectMgMode);


    void getDataFail(String msg);
}
