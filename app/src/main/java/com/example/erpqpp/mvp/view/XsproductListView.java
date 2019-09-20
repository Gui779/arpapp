package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.XsproductListMode;

public interface XsproductListView extends BaseView {

    void getDataSuccess(XsproductListMode xsproductListMode);
    void getDataFail(String msg);
}
