package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.OrderMsgMode;
import com.example.erpqpp.mvp.view.OrderMsgView;
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


public class OrderMsgPresenter extends BasePresenter<OrderMsgView> {

    private final ApiStores apiService;

    public OrderMsgPresenter(OrderMsgView view) {
        attachView(view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void News(String store_id,String page){

        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
    /*    Disposable subscribe = apiService.orderMessage(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OrderMsgMode>() {
                    @Override
                    public void accept(OrderMsgMode orderMsgMode) throws Exception {
                        mvpView.getDataSuccess(orderMsgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe = apiService.orderMessage(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(orderMsgMode -> {
                    mvpView.getDataSuccess(orderMsgMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }

}
