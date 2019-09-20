package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.StatisticsMode;

public interface StatisticsView extends BaseView {

    void getDataSuccess(StatisticsMode statisticsMode);


    void getDataFail(String msg);
}
