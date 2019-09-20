package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.MarkettjMode;
import com.example.erpqpp.mvp.view.MarkettjView;
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


public class MarkettjPresenter extends BasePresenter<MarkettjView> {

    private final ApiStores apiService;

    public MarkettjPresenter(MarkettjView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void getSalesosuouo(String store_id,String page,String pro_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
        map.put("customer_name",pro_name);
    /*    Disposable subscribe = apiService.sale(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MarkettjMode>() {
                    @Override
                    public void accept(MarkettjMode markettjMode) throws Exception {
                        mvpView.getDataSuccess(markettjMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.sale(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(markettjMode -> {
                    mvpView.getDataSuccess(markettjMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });


    }
    public void getSale(String store_id,String page){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
        /*Disposable subscribe = apiService.sale(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MarkettjMode>() {
                    @Override
                    public void accept(MarkettjMode markettjMode) throws Exception {
                        mvpView.getDataSuccess(markettjMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/
        Disposable subscribe1 = apiService.sale(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(markettjMode -> {
                    mvpView.getDataSuccess(markettjMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });


    }


}
