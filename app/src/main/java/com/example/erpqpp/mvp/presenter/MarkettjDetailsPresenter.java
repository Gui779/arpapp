package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.MarkettjDetailsMode;
import com.example.erpqpp.mvp.view.MarkettjDetailsView;
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


public class MarkettjDetailsPresenter extends BasePresenter<MarkettjDetailsView> {

    private final ApiStores apiService;

    public MarkettjDetailsPresenter(MarkettjDetailsView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void zsSaleList(String order_id){
        final Map<String,String> map=new HashMap<>();
        map.put("order_id",order_id);
       /* Disposable subscribe = apiService.zsSaleList(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MarkettjDetailsMode>() {
                    @Override
                    public void accept(MarkettjDetailsMode markettjDetailsMode) throws Exception {
                     mvpView.getDataSuccess(markettjDetailsMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                      mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.zsSaleList(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(markettjDetailsMode -> {
                    mvpView.getDataSuccess(markettjDetailsMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }



}
