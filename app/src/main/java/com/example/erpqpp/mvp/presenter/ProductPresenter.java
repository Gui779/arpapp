package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.ProductMode;
import com.example.erpqpp.mvp.view.ProductView;
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


public class ProductPresenter extends BasePresenter<ProductView> {

    private final ApiStores apiService;

    public ProductPresenter(ProductView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void productList(String store_id, final String p){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("p",p);
  /*      Disposable subscribe = apiService.ProductList(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProductMode>() {
                    @Override
                    public void accept(ProductMode productMode) throws Exception {
                        mvpView.getDataSuccess(productMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.ProductList(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(productMode -> {
                    mvpView.getDataSuccess(productMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }

    public void productListsousuo(String store_id, final String p,String pro_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("p",p);
        map.put("pro_name",pro_name);
        /*Disposable subscribe = apiService.ProductList(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProductMode>() {
                    @Override
                    public void accept(ProductMode productMode) throws Exception {
                        mvpView.getDataSuccess(productMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.ProductList(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(productMode -> {
                    mvpView.getDataSuccess(productMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }

    public void delect(String store_id,String pro_id){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("pro_id",pro_id);
      /*  Disposable subscribe = apiService.Delectproduct(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.DelectDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.Delectproduct(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.DelectDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });


    }


}
