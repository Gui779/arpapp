package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.mode.MeMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.view.AddCgProductView;
import com.example.erpqpp.mvp.view.Meview;
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

public class MePresenter extends BasePresenter<Meview> {

    private final ApiStores apiService;

    public MePresenter(Meview view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void getuser(String mobile){
        Map<String,String> map=new HashMap<>();
        map.put("mobile",mobile);
      /*  Disposable subscribe = apiService.cGpersonal(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MeMode>() {
                    @Override
                    public void accept(MeMode meMode) throws Exception {
                       mvpView.getDataSuccess(meMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/
        Disposable subscribe1 = apiService.cGpersonal(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(meMode -> {
                    mvpView.getDataSuccess(meMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });


    }

}
