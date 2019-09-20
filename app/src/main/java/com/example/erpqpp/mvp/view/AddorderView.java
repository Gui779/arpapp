package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.AddMode;
import com.example.erpqpp.mvp.mode.MdMode;
import com.example.erpqpp.mvp.mode.XsListMode;

public interface AddorderView extends BaseView {
    void getXsDataSuccess(XsListMode xsListMode);

    void getMdDataFail(String msg);

    void addDataSuccess(AddMode addMode);

    void addDataFail(String msg);

    void getMdDataSuccess(MdMode xsListMode);

    void getXsDataFail(String msg);
    void mytost(String msg);
}
