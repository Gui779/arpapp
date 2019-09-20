package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.SelectShMode;
import com.example.erpqpp.mvp.view.SelectShView;
import com.example.erpqpp.retrofit.ApiStores;
import com.example.erpqpp.retrofit.RxSchedulers;
import com.lbb.mvplibrary.base.BasePresenter;
import com.lbb.mvplibrary.retrofit.ApiManager;

import java.lang.ref.Reference;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SelectShPresenter extends BasePresenter<SelectShView> {

    private final ApiStores apiService;

    public SelectShPresenter(SelectShView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void SelectSh(String store_id){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
       /* Disposable subscribe = apiService.colorlist(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SelectShMode>() {
                    @Override
                    public void accept(SelectShMode selectShMode) throws Exception {
                       mvpView.getDataSuccess(selectShMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                      mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe = apiService.colorlist(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(selectShMode -> {
                    mvpView.getDataSuccess(selectShMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }



}
