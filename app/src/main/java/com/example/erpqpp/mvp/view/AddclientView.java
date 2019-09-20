package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.AddclientMode;

public interface AddclientView extends BaseView {


    void getDataSuccess(AddclientMode addclientMode);

    void getDataFail(String msg);

    void mytoast(String msg);


}
