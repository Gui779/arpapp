package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.DmMode;
import com.example.erpqpp.mvp.mode.MgMode;

public interface DmView extends BaseView {

    void getDataSuccess(DmMode dmMode);


    void getDataFail(String msg);

    void delectDataSuccess(MgMode mgMode);

    void delectDataFail(String msg);
}
