package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.mode.StayDeliverMode;
import com.example.erpqpp.mvp.view.StayreceivingView;
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

public class StayreceivingPresenter extends BasePresenter<StayreceivingView> {

    private final ApiStores apiService;

    public StayreceivingPresenter(StayreceivingView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void Stayreceiving(String store_id,String page){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
       /* Disposable subscribe = apiService.goodsDsh(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<StayDeliverMode>() {
                    @Override
                    public void accept(StayDeliverMode stayDeliverMode) throws Exception {
                        mvpView.getDataSuccess(stayDeliverMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.goodsDsh(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(stayDeliverMode -> {
                    mvpView.getDataSuccess(stayDeliverMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }


    public void Stayreceivingsousuo(String store_id,String page,String customer_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
        map.put("customer_name",customer_name);
       /* Disposable subscribe = apiService.goodsDsh(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<StayDeliverMode>() {
                    @Override
                    public void accept(StayDeliverMode stayDeliverMode) throws Exception {
                        mvpView.getDataSuccess(stayDeliverMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe = apiService.goodsDsh(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(stayDeliverMode -> {
                    mvpView.getDataSuccess(stayDeliverMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }
}
