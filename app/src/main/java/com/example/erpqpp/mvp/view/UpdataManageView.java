package com.example.erpqpp.mvp.view;

import com.example.erpqpp.mvp.mode.XzCzMode;
import com.lbb.mvplibrary.base.BaseView;

public interface UpdataManageView extends BaseView {

    void getDataSuccess(XzCzMode xzCzMode);

    void getDataFail(String msg);

    void toast(String msg);
}
