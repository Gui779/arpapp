package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.CkMode;
import com.example.erpqpp.mvp.mode.MgMode;

public interface FallView extends BaseView {

    void getDataSuccess(CkMode ckMode);

    void getDataFail(String msg);
    void mytoast(String msg);

    void getfhDataSuccess(MgMode mgModeMode);

    void getfhDataFail(String msg);

}
