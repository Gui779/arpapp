package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.OrderMsgMode;

public interface OrderMsgView extends BaseView {
    void getDataSuccess(OrderMsgMode orderMsgMode);

    void getDataFail(String msg);
}
