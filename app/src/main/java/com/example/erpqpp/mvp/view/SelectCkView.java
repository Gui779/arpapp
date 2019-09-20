package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.CkMode;

public interface SelectCkView extends BaseView {

    void getDataSuccess(CkMode ckMode);

    void getDataFail(String msg);
}
