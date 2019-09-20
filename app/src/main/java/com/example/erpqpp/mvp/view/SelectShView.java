package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.SelectShMode;

public interface SelectShView extends BaseView {

    void getDataSuccess(SelectShMode selectShMode);


    void getDataFail(String msg);
}
