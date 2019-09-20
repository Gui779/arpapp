package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.WarehouseStatisticsMode;

public interface WarehouseStatisticsView extends BaseView {
    void getDataSuccess(WarehouseStatisticsMode warehouseStatisticsMode);


    void getDataFail(String msg);
}
