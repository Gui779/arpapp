package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.CkListMode;
import com.example.erpqpp.mvp.mode.CzMode;

public interface CkListView extends BaseView {
    void getDataSuccess(CkListMode ckListMode);


    void getDataFail(String msg);


    void outDataSuccess(CzMode czMode);


    void outDataFail(String msg);
}
