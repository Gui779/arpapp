package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.view.StaySenddetailsView;
import com.example.erpqpp.retrofit.ApiStores;
import com.lbb.mvplibrary.base.BasePresenter;
import com.lbb.mvplibrary.retrofit.ApiManager;

import java.lang.ref.Reference;

public class StaySenddetailsPresenter extends BasePresenter<StaySenddetailsView> {

    private final ApiStores apiService;

    public StaySenddetailsPresenter(StaySenddetailsView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }
}
