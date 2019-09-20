package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.CzMode;

public interface GetCzView extends BaseView {
    void getDataSuccess(CzMode czMode);


    void getDataFail(String msg);
}
