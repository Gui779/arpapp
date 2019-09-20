package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.AddMode;
import com.example.erpqpp.mvp.mode.DiscountMode;

public interface DiscountView extends BaseView {
    void getDataSuccess(DiscountMode discountMode);

    void getDataFail(String msg);

    void discountNoDataSuccess(AddMode discountMode);

    void discountNoDataFail(String msg);
}
