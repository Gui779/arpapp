package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.mode.SelectMgMode;
import com.example.erpqpp.mvp.view.SelectProductView;
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

public class SelectProductPresenter extends BasePresenter<SelectProductView> {

    private final ApiStores apiService;

    public SelectProductPresenter(SelectProductView view) {
        attachView(view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void Selectmg(String store_id,String p){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("p",p);
       /* Disposable subscribe = apiService.Addproduct(map)
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
                });
*/
        Disposable subscribe1 = apiService.Addproduct(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(selectMgMode -> {
                    mvpView.getDataSuccess(selectMgMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }

    public void Selectmgsousuo(String store_id,String p,String pro_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("p",p);
        map.put("pro_name",pro_name);
      /*  Disposable subscribe = apiService.Addproduct(map)
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

        Disposable subscribe1 = apiService.Addproduct(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(selectMgMode -> {
                    mvpView.getDataSuccess(selectMgMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });


    }


    public void Selectdm(String store_id,String p){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("p",p);
       /* Disposable subscribe = apiService.dmadd_product(map)
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

        Disposable subscribe = apiService.dmadd_product(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(selectMgMode -> {
                    mvpView.getDataSuccess(selectMgMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });


    }

    public void Selectdmsousuo(String store_id,String p,String pro_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("p",p);
        map.put("pro_name",pro_name);
       /* Disposable subscribe = apiService.dmadd_product(map)
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

        Disposable subscribe = apiService.dmadd_product(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(selectMgMode -> {
                    mvpView.getDataSuccess(selectMgMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }
}
