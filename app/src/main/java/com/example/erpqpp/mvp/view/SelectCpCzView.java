package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.SelectCpCzMode;

public interface SelectCpCzView extends BaseView {

    void getDataSuccess(SelectCpCzMode selectCpCzMode);
    void getDataFail(String msg);
}
