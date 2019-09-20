package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.WorkerMode;

public interface HarvestView extends BaseView {

    void getDataSuccess(WorkerMode workerMode);

    void getDataFail(String msg);


    void getmgDataSuccess(MgMode mgMode);

    void gemgtDataFail(String msg);

    void mytoast(String msg);

}
