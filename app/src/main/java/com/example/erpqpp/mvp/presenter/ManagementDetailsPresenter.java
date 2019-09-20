package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.mode.ManagementListMode;
import com.example.erpqpp.mvp.view.ManagementDetailsView;
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

public class ManagementDetailsPresenter extends BasePresenter<ManagementDetailsView> {

    private final ApiStores apiService;

    public ManagementDetailsPresenter(ManagementDetailsView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void getuselist(String user_id){
        Map<String,String> map=new HashMap<>();
        map.put("user_id",user_id);
        /*Disposable subscribe = apiService.getuserList(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ManagementListMode>() {
                    @Override
                    public void accept(ManagementListMode managementListMode) throws Exception {
                     mvpView.getDataSuccess(managementListMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.getuserList(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(managementListMode -> {
                    mvpView.getDataSuccess(managementListMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }
}
