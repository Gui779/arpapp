package com.example.erpqpp.mvp.view;

import com.example.erpqpp.mvp.mode.TextureManageMode;
import com.example.erpqpp.mvp.mode.XzCzMode;
import com.lbb.mvplibrary.base.BaseView;

public interface TextureManageView extends BaseView {

    void getDataSuccess(TextureManageMode textureManageMode);

    void delectDataSuccess(XzCzMode xzCzMode);

    void getDataFail(String msg);
}
