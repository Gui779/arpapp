package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.SqMode;

public interface SqView extends BaseView {

    void getDataSuccess(SqMode sqMode);


    void getDataFail(String msg);

    void mytoast(String msg);
}
