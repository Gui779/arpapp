package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.view.XsAddproductView;
import com.example.erpqpp.retrofit.ApiStores;
import com.lbb.mvplibrary.base.BasePresenter;
import com.lbb.mvplibrary.retrofit.ApiManager;

import java.lang.ref.Reference;


public class XsAddproductPresenter extends BasePresenter<XsAddproductView> {

    private final ApiStores apiService;

    public XsAddproductPresenter(XsAddproductView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

}
