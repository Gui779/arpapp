package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.XsproductListMode;
import com.example.erpqpp.mvp.view.XsproductListView;
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

public class XsproductListPresenter extends BasePresenter<XsproductListView> {

    private final ApiStores apiService;

    public XsproductListPresenter(XsproductListView view) {
        attachView(view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void XsproductList(String store_id,String page){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
     /*   Disposable subscribe = apiService.productAdd(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<XsproductListMode>() {
                    @Override
                    public void accept(XsproductListMode xsproductListMode) throws Exception {
                     mvpView.getDataSuccess(xsproductListMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                       mvpView.getDataFail(throwable.getMessage());
                    }
                });
*/
        Disposable subscribe = apiService.productAdd(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(xsproductListMode -> {
                   mvpView.getDataSuccess(xsproductListMode);
                }, throwable -> {
                   mvpView.getDataFail(throwable.getMessage());
                });

    }


    public void XsproductListsousuo(String store_id,String page,String pro_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
        map.put("pro_name",pro_name);
     /*   Disposable subscribe = apiService.productAdd(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<XsproductListMode>() {
                    @Override
                    public void accept(XsproductListMode xsproductListMode) throws Exception {
                       mvpView.getDataSuccess(xsproductListMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                       mvpView.getDataFail(throwable.getMessage());
                    }
                });*/
        Disposable subscribe1 = apiService.productAdd(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(xsproductListMode -> {
                   mvpView.getDataSuccess(xsproductListMode);
                }, throwable -> {
                   mvpView.getDataFail(throwable.getMessage());
                });


    }

}
