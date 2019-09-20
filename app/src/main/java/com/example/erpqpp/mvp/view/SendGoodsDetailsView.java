package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.SendGoodsDetailsMode;

public interface SendGoodsDetailsView extends BaseView {
    void getDataSuccess(SendGoodsDetailsMode sendGoodsDetailsMode);

    void getDataFail(String msg);
}
