package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.WmMode;

public interface WmWarehouseView extends BaseView {


    void getDataSuccess(WmMode wmMode);


    void getDataFail(String msg);

    void delectDataSuccess(MgMode mgMode);

    void delectDataFail(String msg);
}
