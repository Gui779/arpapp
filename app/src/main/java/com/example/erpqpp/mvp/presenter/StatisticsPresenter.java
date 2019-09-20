package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.StatisticsMode;
import com.example.erpqpp.mvp.view.StatisticsView;
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


public class StatisticsPresenter extends BasePresenter<StatisticsView> {

    private final ApiStores apiService;

    public StatisticsPresenter(StatisticsView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void Statistics(String store_id,String admin_id,String p){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("admin_id",admin_id);
        map.put("p",p);
    /*    Disposable subscribe = apiService.Statistics(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<StatisticsMode>() {
                    @Override
                    public void accept(StatisticsMode statisticsMode) throws Exception {
                        mvpView.getDataSuccess(statisticsMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/


        Disposable subscribe1 = apiService.Statistics(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(statisticsMode -> {
                    mvpView.getDataSuccess(statisticsMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });


    }


    public void Statisticssousuo(String store_id,String admin_id,String p,String pro_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("admin_id",admin_id);
        map.put("p",p);
        map.put("pro_name",pro_name);
      /*  Disposable subscribe = apiService.Statistics(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<StatisticsMode>() {
                    @Override
                    public void accept(StatisticsMode statisticsMode) throws Exception {
                        mvpView.getDataSuccess(statisticsMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });
*/
        Disposable subscribe1 = apiService.Statistics(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(statisticsMode -> {
                    mvpView.getDataSuccess(statisticsMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });


    }

}
