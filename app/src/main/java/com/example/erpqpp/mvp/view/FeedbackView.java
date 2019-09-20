package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.CzMode;

public interface FeedbackView extends BaseView {
    void getDataSuccess(CzMode mgMode);


    void getDataFail(String msg);

    void mytoast(String msg);
}
