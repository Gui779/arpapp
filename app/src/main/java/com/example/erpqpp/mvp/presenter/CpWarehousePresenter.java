package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.CpckMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.view.CpWarehouseView;
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


public class CpWarehousePresenter extends BasePresenter<CpWarehouseView> {

    private final ApiStores apiService;

    public CpWarehousePresenter(CpWarehouseView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

     public void Cpck(String store_id,String p,String admin_id){
         Map<String,String> map=new HashMap<>();
         map.put("store_id",store_id);
         map.put("p",p);
         map.put("admin_id",admin_id);
        /* Disposable subscribe = apiService.cpckIndex(map)
                 .subscribeOn(Schedulers.newThread())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<CpckMode>() {
                     @Override
                     public void accept(CpckMode cpckMode) throws Exception {
                        mvpView.getDataSuccess(cpckMode);
                     }
                 }, new Consumer<Throwable>() {
                     @Override
                     public void accept(Throwable throwable) throws Exception {
                       mvpView.getDataFail(throwable.getMessage());
                     }
                 });*/

         Disposable subscribe1 = apiService.cpckIndex(map)
                 .compose(RxSchedulers.Obs_io_main())
                 .subscribe(cpckMode -> {
                     mvpView.getDataSuccess(cpckMode);
                 }, throwable -> {
                     mvpView.getDataFail(throwable.getMessage());
                 });

     }


    public void Cpcksousuo(String store_id,String p,String pro_name,String admin_id){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("p",p);
        map.put("pro_name",pro_name);
        map.put("admin_id",admin_id);
       /* Disposable subscribe = apiService.cpckIndex(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CpckMode>() {
                    @Override
                    public void accept(CpckMode cpckMode) throws Exception {
                        mvpView.getDataSuccess(cpckMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });
*/
        Disposable subscribe1 = apiService.cpckIndex(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(cpckMode -> {
                    mvpView.getDataSuccess(cpckMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }

    public void yqdelect(String store_id, String admin_id, String id) {
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("admin_id",admin_id);
        map.put("id",id);
      /*  Disposable subscribe = apiService.cpckDelete(map)
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

        Disposable subscribe1 = apiService.cpckDelete(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.delectDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.delectDataFail(throwable.getMessage());
                });


    }
}
