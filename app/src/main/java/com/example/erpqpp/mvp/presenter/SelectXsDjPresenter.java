package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.SelectXsDjMode;
import com.example.erpqpp.mvp.view.SelectXsDjView;
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

public class SelectXsDjPresenter extends BasePresenter<SelectXsDjView> {

    private final ApiStores apiService;

    public SelectXsDjPresenter(SelectXsDjView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void SelectXsDj(String pro_id){
        Map<String,String> map=new HashMap<>();
        map.put("pro_id",pro_id);
       /* Disposable subscribe = apiService.singleton(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SelectXsDjMode>() {
                    @Override
                    public void accept(SelectXsDjMode selectXsDjMode) throws Exception {
                       mvpView.getDataSuccess(selectXsDjMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                      mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe = apiService.singleton(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(selectXsDjMode -> {
                    mvpView.getDataSuccess(selectXsDjMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });


    }

        }
