package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.mode.SelectMgMode;
import com.example.erpqpp.mvp.view.SelectColorView;
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

public class SelectColorPresenter extends BasePresenter<SelectColorView> {

    private final ApiStores apiService;

    public SelectColorPresenter(SelectColorView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void SelectColor(String store_id){
        final Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
      /*  Disposable subscribe = apiService.SelectColor(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SelectMgMode>() {
                    @Override
                    public void accept(SelectMgMode selectMgMode) throws Exception {
                      mvpView.getDataSuccess(selectMgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe = apiService.SelectColor(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(selectMgMode -> {
                    mvpView.getDataSuccess(selectMgMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }
    
}
