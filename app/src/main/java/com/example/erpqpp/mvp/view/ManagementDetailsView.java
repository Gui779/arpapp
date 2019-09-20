package com.example.erpqpp.mvp.view;

import com.lbb.mvplibrary.base.BaseView;
import com.example.erpqpp.mvp.mode.ManagementListMode;

public interface ManagementDetailsView extends BaseView {

    void getDataSuccess(ManagementListMode managementListMode);

    void getDataFail(String msg);
}
