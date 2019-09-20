package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.FhjlMode;

public interface FhjlView extends BaseView {

    void getDataSuccess(FhjlMode fhjlMode);

    void getDataFail(String msg);

}
