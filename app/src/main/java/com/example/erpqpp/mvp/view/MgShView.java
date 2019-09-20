package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.MgShMode;

public interface MgShView extends BaseView {
    void getDataSuccess(MgShMode mgShMode);

    void getDataFail(String msg);


    void delectDataSuccess(MgMode mgMode);

    void delectDataFail(String msg);

}
