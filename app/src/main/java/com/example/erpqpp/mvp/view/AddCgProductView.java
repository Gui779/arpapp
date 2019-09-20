package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.MgMode;

public interface AddCgProductView extends BaseView {
    void getDataSuccess(MgMode mgMode);

    void getDataFail(String msg);
    void mytoast(String msg);
}
