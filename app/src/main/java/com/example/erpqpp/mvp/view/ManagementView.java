package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.ManagementMode;
import com.example.erpqpp.mvp.mode.MgMode;

public interface ManagementView extends BaseView {

    void getDataSuccess(ManagementMode managementMode);

    void getDataFail(String msg);

    void DelectDataSuccess(MgMode mgMode);
}
