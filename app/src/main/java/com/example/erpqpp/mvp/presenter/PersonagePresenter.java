package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.LogOutMode;
import com.example.erpqpp.mvp.mode.MeMode;
import com.example.erpqpp.mvp.view.PersonageView;
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


public class PersonagePresenter extends BasePresenter<PersonageView> {

    private final ApiStores apiService;

    public PersonagePresenter(PersonageView view) {
        attachView(view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }


    public void Logout(String admin_id){
        final Map<String,String> map=new HashMap<>();
        map.put("admin_id",admin_id);
      /*  Disposable subscribe = apiService.Logout(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LogOutMode>() {
                    @Override
                    public void accept(LogOutMode logOutMode) throws Exception {
                    mvpView.getDataSuccess(logOutMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/
        Disposable subscribe = apiService.Logout(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(logOutMode -> {
                    mvpView.getDataSuccess(logOutMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });
    }

    public void getuser(String mobile){
        final Map<String,String> map=new HashMap<>();
        map.put("mobile",mobile);
     /*   Disposable subscribe = apiService.cGpersonal(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MeMode>() {
                    @Override
                    public void accept(MeMode meMode) throws Exception {
                        mvpView.getuserDataSuccess(meMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getuserDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe = apiService.cGpersonal(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(meMode -> {
                    mvpView.getuserDataSuccess(meMode);
                }, throwable -> {
                    mvpView.getuserDataFail(throwable.getMessage());
                });

    }



}
