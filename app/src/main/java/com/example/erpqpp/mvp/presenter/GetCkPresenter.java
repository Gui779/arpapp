package com.example.erpqpp.mvp.presenter;


import android.view.View;

import com.example.erpqpp.mvp.mode.CzMode;
import com.example.erpqpp.mvp.view.GetCzView;
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


public class GetCkPresenter extends BasePresenter<GetCzView> {

    private final ApiStores apiService;

    public GetCkPresenter(GetCzView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public  void getCz(String store_id){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
       /* Disposable subscribe = apiService.getWood(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CzMode>() {
                    @Override
                    public void accept(CzMode czMode) throws Exception {
                      mvpView.getDataSuccess(czMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.getWood(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(czMode -> {
                    mvpView.getDataSuccess(czMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });
    }


}
