package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.SelectXsDjMode;

public interface SelectXsDjView extends BaseView {
    void getDataSuccess(SelectXsDjMode selectXsDjMode);

    void getDataFail(String msg);
}
