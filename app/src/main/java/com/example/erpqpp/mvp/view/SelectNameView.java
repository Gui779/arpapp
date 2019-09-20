package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.SelectNameMode;

public interface SelectNameView extends BaseView {
    void getDataSuccess(SelectNameMode selectNameMode);
    void getDataFail(String msg);
}
