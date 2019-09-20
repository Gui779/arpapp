package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.AddMode;
import com.example.erpqpp.mvp.mode.DiscountMode;
import com.example.erpqpp.mvp.view.DiscountView;
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


public class DiscountPresenter extends BasePresenter<DiscountView> {

    private final ApiStores apiService;

    public DiscountPresenter(DiscountView view) {
        attachView(view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void Discount(String store_id,String page){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
     /*   Disposable subscribe = apiService.Discount(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DiscountMode>() {
                    @Override
                    public void accept(DiscountMode discountMode) throws Exception {
                       mvpView.getDataSuccess(discountMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                      mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.Discount(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(discountMode -> {
                    mvpView.getDataSuccess(discountMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });


    }


    public void Discountsousuo(String store_id,String page,String customer_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
        map.put("customer_name",customer_name);
      /*  Disposable subscribe = apiService.Discount(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DiscountMode>() {
                    @Override
                    public void accept(DiscountMode discountMode) throws Exception {
                        mvpView.getDataSuccess(discountMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.Discount(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(discountMode -> {
                    mvpView.getDataSuccess(discountMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });


    }

    //折扣列表否决按钮
    public void discountNo(String order_id){
        Map<String,String> map=new HashMap<>();
        map.put("order_id",order_id);
       /* Disposable subscribe = apiService.discountNo(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddMode>() {
                    @Override
                    public void accept(AddMode addMode) throws Exception {
                       mvpView.discountNoDataSuccess(addMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.discountNoDataFail(throwable.getMessage());
                    }
                });
*/
        Disposable subscribe1 = apiService.discountNo(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(addMode -> {
                    mvpView.discountNoDataSuccess(addMode);
                }, throwable -> {
                    mvpView.discountNoDataFail(throwable.getMessage());
                });

    }


    //折扣列表同意按钮
    public void discountAgree(String order_id){
        Map<String,String> map=new HashMap<>();
        map.put("order_id",order_id);
      /*  Disposable subscribe = apiService.discountAgree(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddMode>() {
                    @Override
                    public void accept(AddMode addMode) throws Exception {
                        mvpView.discountNoDataSuccess(addMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.discountNoDataFail(throwable.getMessage());
                    }
                });
*/

        Disposable subscribe1 = apiService.discountAgree(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(addMode -> {
                    mvpView.discountNoDataSuccess(addMode);
                }, throwable -> {
                    mvpView.discountNoDataFail(throwable.getMessage());
                });

    }
}
