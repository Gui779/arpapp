package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.MarkettjDetailsMode;

public interface MarkettjDetailsView extends BaseView {
    void getDataSuccess(MarkettjDetailsMode markettjDetailsMode);

    void getDataFail(String msg);
}
