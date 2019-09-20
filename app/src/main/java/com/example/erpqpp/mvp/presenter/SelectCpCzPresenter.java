package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.mode.SelectCpCzMode;
import com.example.erpqpp.mvp.view.SelectCpCzView;
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

public class SelectCpCzPresenter extends BasePresenter<SelectCpCzView> {

    private final ApiStores apiService;

    public SelectCpCzPresenter(SelectCpCzView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void selectcz(String store_id){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
       /* Disposable subscribe = apiService.Selectcz(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SelectCpCzMode>() {
                    @Override
                    public void accept(SelectCpCzMode selectCpCzMode) throws Exception {
                      mvpView.getDataSuccess(selectCpCzMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/
        Disposable subscribe = apiService.Selectcz(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(selectCpCzMode -> {
                    mvpView.getDataSuccess(selectCpCzMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });


    }

}
