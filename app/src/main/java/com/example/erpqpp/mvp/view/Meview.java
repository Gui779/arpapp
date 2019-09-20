package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.MeMode;

public interface Meview extends BaseView {
    void getDataSuccess(MeMode meMode);

    void getDataFail(String msg);
}
