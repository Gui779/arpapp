package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.CgShMode;
import com.example.erpqpp.mvp.mode.CzMode;

public interface CkShListView extends BaseView {
    void getDataSuccess(CgShMode cgShMode);

    void getDataFail(String msg);

    void ThDataSuccess(CzMode czMode);

    void ThDataFail(String msg);

}
