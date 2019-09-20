package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.StayDeliverMode;
import com.example.erpqpp.mvp.mode.XsMode;
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

public class AlreadyreceivingPresenter extends BasePresenter<StayreceivingView> {

    private final ApiStores apiService;

    public AlreadyreceivingPresenter(StayreceivingView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void Stayreceiving(String store_id,String page){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
       /* Disposable subscribe = apiService.sGoods(map)
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

        Disposable subscribe1 = apiService.sGoods(map)
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
       /* Disposable subscribe = apiService.sGoods(map)
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
        Disposable subscribe1 = apiService.sGoods(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(stayDeliverMode -> {
                    mvpView.getDataSuccess(stayDeliverMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }

    public void order_id(String order_id){
        Map<String,String> map=new HashMap<>();
        map.put("order_id",order_id);
       /* Disposable subscribe = apiService.agree(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.DataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.agree(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.DataSuccess(mgMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });


    }
}
