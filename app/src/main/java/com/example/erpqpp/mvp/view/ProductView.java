package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.ProductMode;

public interface ProductView extends BaseView {
    void getDataSuccess(ProductMode productMode);


    void getDataFail(String msg);

    void DelectDataSuccess(MgMode mgMode);


    void DelectDataFail(String msg);
}
