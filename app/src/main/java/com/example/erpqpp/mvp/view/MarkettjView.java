package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.MarkettjMode;

public interface MarkettjView extends BaseView {
    void getDataSuccess(MarkettjMode markettjMode);


    void getDataFail(String msg);

}
