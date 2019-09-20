package com.example.erpqpp.mvp.presenter;

import android.view.View;

import com.example.erpqpp.mvp.mode.FhjlMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.view.AddCgProductView;
import com.example.erpqpp.mvp.view.FhjlView;
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

public class FhjlPresenter extends BasePresenter<FhjlView> {

    private final ApiStores apiService;

    public FhjlPresenter(FhjlView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }


    public void record(String store_id,String from,String p){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("from",from);
        map.put("target","40");
        map.put("p",p);
        /*Disposable subscribe = apiService.record(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FhjlMode>() {
                    @Override
                    public void accept(FhjlMode fhjlMode) throws Exception {
                       mvpView.getDataSuccess(fhjlMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.record(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(fhjlMode -> {
                    mvpView.getDataSuccess(fhjlMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }
    public void recordsousuo(String store_id,String from,String p,String pro_name ){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("from",from);
        map.put("target","40");
        map.put("p",p);
        map.put("pro_name",pro_name);
      /*  Disposable subscribe = apiService.record(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FhjlMode>() {
                    @Override
                    public void accept(FhjlMode fhjlMode) throws Exception {
                       mvpView.getDataSuccess(fhjlMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.record(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(fhjlMode -> {
                    mvpView.getDataSuccess(fhjlMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });


    }

}
