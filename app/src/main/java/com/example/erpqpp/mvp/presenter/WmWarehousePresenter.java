package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.WmMode;
import com.example.erpqpp.mvp.view.WmWarehouseView;
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


public class WmWarehousePresenter extends BasePresenter<WmWarehouseView> {

    private final ApiStores apiService;

    public WmWarehousePresenter(WmWarehouseView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void Wmindex(String store_id,String p){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("p",p);
     /*   Disposable subscribe = apiService.WmIndex(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WmMode>() {
                    @Override
                    public void accept(WmMode wmMode) throws Exception {
                     mvpView.getDataSuccess(wmMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.WmIndex(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(wmMode -> {
                   mvpView.getDataSuccess(wmMode);
                }, throwable -> {
                   mvpView.getDataFail(throwable.getMessage());
                });

    }



    public void Wmindexsousuo(String store_id,String p,String pro_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("p",p);
        map.put("pro_name",pro_name);
      /*  Disposable subscribe = apiService.WmIndex(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WmMode>() {
                    @Override
                    public void accept(WmMode wmMode) throws Exception {
                     mvpView.getDataSuccess(wmMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());

                }); }*/

        Disposable subscribe1 = apiService.WmIndex(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(wmMode -> {
                   mvpView.getDataSuccess(wmMode);
                }, throwable -> {
                   mvpView.getDataFail(throwable.getMessage());
                });

    }


    public void wedelect(String store_id,String admin_id,String id){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("admin_id",admin_id);
        map.put("id",id);
     /*   Disposable subscribe = apiService.WmDelect(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                     mvpView.delectDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                     mvpView.delectDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.WmDelect(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                   mvpView.delectDataSuccess(mgMode);
                }, throwable -> {
                   mvpView.delectDataFail(throwable.getMessage());
                });


    }


}
