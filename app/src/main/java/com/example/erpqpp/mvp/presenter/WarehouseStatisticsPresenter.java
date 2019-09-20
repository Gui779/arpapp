package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.WarehouseStatisticsMode;
import com.example.erpqpp.mvp.view.WarehouseStatisticsView;
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


public class WarehouseStatisticsPresenter extends BasePresenter<WarehouseStatisticsView> {

    private final ApiStores apiService;

    public WarehouseStatisticsPresenter(WarehouseStatisticsView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void getKctj(String store_id,String p){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",p);
      /*  Disposable subscribe = apiService.Kctj(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WarehouseStatisticsMode>() {
                    @Override
                    public void accept(WarehouseStatisticsMode warehouseStatisticsMode) throws Exception {
                    mvpView.getDataSuccess(warehouseStatisticsMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                     mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

   /*     Disposable subscribe1 = apiService.Kctj(map)
                .compose( RxSchedulers.Obs_io_main())
                .subscribe((warehouseStatisticsMode) -> {
                mvpView.getDataSuccess(warehouseStatisticsMode);
                }, (throwable -> {
                   mvpView.getDataFail(throwable.getMessage());
                }));*/

        Disposable subscribe = apiService.Kctj(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(warehouseStatisticsMode -> {
                   mvpView.getDataSuccess(warehouseStatisticsMode);
                }, throwable -> {
                   mvpView.getDataFail(throwable.getMessage());
                });


    }
    public void getKctjsousuo(String store_id,String p,String pro_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",p);
        map.put("pro_name",pro_name);
      /*  Disposable subscribe = apiService.Kctj(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WarehouseStatisticsMode>() {
                    @Override
                    public void accept(WarehouseStatisticsMode warehouseStatisticsMode) throws Exception {
                    mvpView.getDataSuccess(warehouseStatisticsMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                     mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.Kctj(map)
                .compose( RxSchedulers.Obs_io_main())
                .subscribe((warehouseStatisticsMode) -> {
                   mvpView.getDataSuccess(warehouseStatisticsMode);
                }, (throwable -> {
                   mvpView.getDataFail(throwable.getMessage());
                }));
    }


}
