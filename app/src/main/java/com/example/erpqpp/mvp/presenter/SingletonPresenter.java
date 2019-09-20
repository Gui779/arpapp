package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.SingletonMode;
import com.example.erpqpp.mvp.view.SingletonView;
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


public class SingletonPresenter extends BasePresenter<SingletonView> {

    private final ApiStores apiService;

    public SingletonPresenter(SingletonView view) {
        attachView(view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

     public void getproductunit(String store_id,String pro_id){
         Map<String,String> map=new HashMap<>();
         map.put("store_id",store_id);
         map.put("pro_id",pro_id);
       /*  Disposable subscribe = apiService.getProductunit(map)
                 .subscribeOn(Schedulers.newThread())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<SingletonMode>() {
                     @Override
                     public void accept(SingletonMode singletonMode) throws Exception {
                         mvpView.getDataSuccess(singletonMode);
                     }
                 }, new Consumer<Throwable>() {
                     @Override
                     public void accept(Throwable throwable) throws Exception {
                         mvpView.getDataFail(throwable.getMessage());
                     }
                 });*/

         Disposable subscribe = apiService.getProductunit(map)
                 .compose(RxSchedulers.Obs_io_main())
                 .subscribe(singletonMode -> {
                     mvpView.getDataSuccess(singletonMode);
                 }, throwable -> {
                     mvpView.getDataFail(throwable.getMessage());
                 });


     }

}
