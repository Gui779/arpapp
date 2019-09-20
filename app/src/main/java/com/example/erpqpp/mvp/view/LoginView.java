package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.LoginMode;

public interface LoginView extends BaseView {

    void getDataSuccess(LoginMode loginMode);
    void getDataFail(String msg);
    void mytoast(String msg);
}
