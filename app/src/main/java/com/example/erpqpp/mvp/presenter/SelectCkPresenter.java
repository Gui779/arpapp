package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.mode.CkMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.view.SelectCkView;
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

public class SelectCkPresenter extends BasePresenter<SelectCkView> {

    private final ApiStores apiService;

    public SelectCkPresenter(SelectCkView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void Selectck(String store_id){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
      /*  Disposable subscribe = apiService.getWarehousing(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CkMode>() {
                    @Override
                    public void accept(CkMode ckMode) throws Exception {
                      mvpView.getDataSuccess(ckMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.getWarehousing(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(ckMode -> {
                    mvpView.getDataSuccess(ckMode);
                },throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }
    
}
