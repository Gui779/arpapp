package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.mode.CgShMode;
import com.example.erpqpp.mvp.mode.CkResponse;
import com.example.erpqpp.mvp.mode.CzMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.view.AddCgProductView;
import com.example.erpqpp.mvp.view.CkShListView;
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

public class CkShListPresenter extends BasePresenter<CkShListView> {

    private final ApiStores apiService;

    public CkShListPresenter(CkShListView view) {
        attachView(view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

     public void warehous_list(String order_id,String page){
        Map<String,String> map=new HashMap<>();
        map.put("order_id",order_id);
        map.put("page",page);
        /* Disposable subscribe = apiService.warehous_list(map)
                 .subscribeOn(Schedulers.newThread())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<CgShMode>() {
                     @Override
                     public void accept(CgShMode cgShMode) throws Exception {
                       mvpView.getDataSuccess(cgShMode);
                     }
                 }, new Consumer<Throwable>() {
                     @Override
                     public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                     }
                 });*/
         Disposable subscribe1 = apiService.warehous_list(map)
                 .compose(RxSchedulers.Obs_io_main())
                 .subscribe(cgShMode -> {
                     mvpView.getDataSuccess(cgShMode);
                 }, throwable -> {
                     mvpView.getDataFail(throwable.getMessage());
                 });
     }

     //收货
    public void returnGoods(CkResponse ckResponse){
       /* Disposable subscribe = apiService.returnGoods(ckResponse)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CzMode>() {
                    @Override
                    public void accept(CzMode czMode) throws Exception {
                        mvpView.ThDataSuccess(czMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.ThDataFail(throwable.getMessage());
                    }
                });*/
        Disposable subscribe = apiService.returnGoods(ckResponse)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(czMode -> {
                    mvpView.ThDataSuccess(czMode);
                }, throwable -> {
                    mvpView.ThDataFail(throwable.getMessage());
                });

    }
}
