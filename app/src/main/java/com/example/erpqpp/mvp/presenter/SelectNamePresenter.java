package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.SelectNameMode;
import com.example.erpqpp.mvp.view.AddCgProductView;
import com.example.erpqpp.mvp.view.SelectNameView;
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

public class SelectNamePresenter extends BasePresenter<SelectNameView> {

    private final ApiStores apiService;

    public SelectNamePresenter(SelectNameView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void SelectName(String store_id,String user_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("user_name",user_name);
       /* Disposable subscribe = apiService.user(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SelectNameMode>() {
                    @Override
                    public void accept(SelectNameMode selectNameMode) throws Exception {
                     mvpView.getDataSuccess(selectNameMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.user(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(selectNameMode -> {
                    mvpView.getDataSuccess(selectNameMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }

}
