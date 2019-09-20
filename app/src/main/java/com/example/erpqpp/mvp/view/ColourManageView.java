package com.example.erpqpp.mvp.view;

import com.example.erpqpp.mvp.mode.ColourManageMode;
import com.example.erpqpp.mvp.mode.XzCzMode;
import com.lbb.mvplibrary.base.BaseView;

public interface ColourManageView extends BaseView {

    void getDataSuccess(ColourManageMode colourManageMode);

    void getDataFail(String msg);

    void deleectDataSuccess(XzCzMode xzCzMode);
}
