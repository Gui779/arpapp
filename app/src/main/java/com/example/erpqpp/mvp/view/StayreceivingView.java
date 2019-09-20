package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.StayDeliverMode;

public interface StayreceivingView extends BaseView {
    void getDataSuccess(StayDeliverMode stayDeliverMode );

    void getDataFail(String msg);


    void DataSuccess(MgMode mgMode );

}
