package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.LogOutMode;
import com.example.erpqpp.mvp.mode.MeMode;

public interface PersonageView extends BaseView {
    void getDataSuccess(LogOutMode logOutMode);
    void getDataFail(String msg);

    void getuserDataSuccess(MeMode meMode);
    void getuserDataFail(String msg);
}
