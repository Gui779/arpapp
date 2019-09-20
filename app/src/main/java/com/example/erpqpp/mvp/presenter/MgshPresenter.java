package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.MgShMode;
import com.example.erpqpp.mvp.view.MarkettjView;
import com.example.erpqpp.mvp.view.MgShView;
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


public class MgshPresenter extends BasePresenter<MgShView> {

    private final ApiStores apiService;

    public MgshPresenter(MgShView view) {
        attachView(view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }


    public void mgsh(String store_id,String p,String tabstatus){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("p",p);
        map.put("tabstatus",tabstatus);
       /* Disposable subscribe = apiService.Getmgsh(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgShMode>() {
                    @Override
                    public void accept(MgShMode mgShMode) throws Exception {
                        mvpView.getDataSuccess(mgShMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/
        Disposable subscribe1 = apiService.Getmgsh(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgShMode -> {
                    mvpView.getDataSuccess(mgShMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });


    }


    public void mgshsousuo(String store_id,String p,String tabstatus,String pro_name ){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("p",p);
        map.put("tabstatus",tabstatus);
        map.put("pro_name",pro_name);
       /* Disposable subscribe = apiService.Getmgsh(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgShMode>() {
                    @Override
                    public void accept(MgShMode mgShMode) throws Exception {
                        mvpView.getDataSuccess(mgShMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/
        Disposable subscribe = apiService.Getmgsh(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgShMode -> {
                    mvpView.getDataSuccess(mgShMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }

    public void delect(String store_id,String id){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("id",id);
       /* Disposable subscribe = apiService.delreceive(map)
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

        Disposable subscribe1 = apiService.delreceive(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.delectDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.delectDataFail(throwable.getMessage());
                });
    }


}
