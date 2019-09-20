package com.example.erpqpp.mvp.view;


import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.SingletonMode;

public interface SingletonView extends BaseView {
    void getDataSuccess(SingletonMode singletonMode);

    void getDataFail(String msg);

}
