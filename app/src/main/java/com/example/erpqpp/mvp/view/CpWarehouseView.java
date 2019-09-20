package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.CpckMode;
import com.example.erpqpp.mvp.mode.MgMode;

public interface CpWarehouseView extends BaseView {
    void getDataSuccess(CpckMode cpckMode);


    void getDataFail(String msg);


    void delectDataSuccess(MgMode mgMode);


    void delectDataFail(String msg);

}
