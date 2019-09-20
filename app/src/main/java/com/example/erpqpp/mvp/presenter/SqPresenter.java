package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.mode.SelectMgMode;
import com.example.erpqpp.mvp.mode.SqMode;
import com.example.erpqpp.mvp.view.SqView;
import com.example.erpqpp.retrofit.ApiStores;
import com.example.erpqpp.retrofit.RxSchedulers;
import com.lbb.mvplibrary.base.BasePresenter;
import com.lbb.mvplibrary.retrofit.ApiManager;
import com.lbb.mvplibrary.utils.LinLog;

import java.lang.ref.Reference;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SqPresenter extends BasePresenter<SqView> {

    private final ApiStores apiService;

    public SqPresenter(SqView view) {
        attachView(view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void getSqindex(String store_id,String p,String tabstatus){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("tabstatus",tabstatus);
        map.put("p",p);
       /* Disposable subscribe = apiService.getSqindex(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SqMode>() {
                    @Override
                    public void accept(SqMode sqMode) throws Exception {
                     mvpView.getDataSuccess(sqMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                     mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.getSqindex(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(sqMode -> {
                    mvpView.getDataSuccess(sqMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }

    public void getSqindexsousuo(String store_id,String p,String tabstatus,String pro_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("tabstatus",tabstatus);
        map.put("pro_name",pro_name);
        map.put("p",p);
     /*   Disposable subscribe = apiService.getSqindex(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SqMode>() {
                    @Override
                    public void accept(SqMode sqMode) throws Exception {
                        mvpView.getDataSuccess(sqMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe = apiService.getSqindex(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(sqMode -> {
                    mvpView.getDataSuccess(sqMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }

    public void Sqdelect(String store_id,String id){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("id",id);
       /* Disposable subscribe = apiService.Sqdelect(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SqMode>() {
                    @Override
                    public void accept(SqMode sqMode) throws Exception {
                     if (sqMode.getCode().equals("1")){
                       mvpView.mytoast(sqMode.getMsg());
                     }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LinLog.lLog(throwable.getMessage());
                    }
                });*/
        Disposable subscribe = apiService.Sqdelect(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(sqMode -> {
                    mvpView.mytoast(sqMode.getMsg());
                }, throwable -> {
                    LinLog.lLog(throwable.getMessage());
                });

    }




}
