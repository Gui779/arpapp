package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.view.AddproductView;
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

public class AddproductPresenter extends BasePresenter<AddproductView> {

    private final ApiStores apiService;

    public AddproductPresenter(AddproductView view) {
        attachView(view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    //木工
    public void addproduct(String store_id,String pro_id,String unit_id,String wood_id,
    String unit_num,String pro_name,String unit_name,String wood_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("pro_id",pro_id);
        map.put("unit_id",unit_id);
        map.put("wood_id",wood_id);
        map.put("unit_num",unit_num);
        map.put("pro_name",pro_name);
        map.put("unit_name",unit_name);
        map.put("wood_name",wood_name);
    /*    Disposable subscribe = apiService.AddproductHandle(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                     mvpView.getDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                      mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.AddproductHandle(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                   mvpView.getDataSuccess(mgMode);
                }, throwable -> {
                   mvpView.getDataFail(throwable.getMessage());
                });


    }

    //打磨
    public void addDmproduct(String store_id,String pro_id,String unit_id,String wood_id,
                           String unit_num,String pro_name,String unit_name,String wood_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("pro_id",pro_id);
        map.put("unit_id",unit_id);
        map.put("wood_id",wood_id);
        map.put("unit_num",unit_num);
        map.put("pro_name",pro_name);
        map.put("unit_name",unit_name);
        map.put("wood_name",wood_name);
      /*  Disposable subscribe = apiService.AddDmproductHandle(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                       mvpView.getDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                       mvpView.getDataFail(throwable.getMessage());
                    }
                });
*/
        Disposable subscribe1 = apiService.AddDmproductHandle(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                   mvpView.getDataSuccess(mgMode);
                }, throwable -> {
                   mvpView.getDataFail(throwable.getMessage());
                });


    }

    //上漆
    public void addSqproduct(String store_id,String pro_id,String unit_id,String wood_id,
                             String unit_num,String pro_name,String unit_name,String wood_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("pro_id",pro_id);
        map.put("unit_id",unit_id);
        map.put("wood_id",wood_id);
        map.put("unit_num",unit_num);
        map.put("pro_name",pro_name);
        map.put("unit_name",unit_name);
        map.put("wood_name",wood_name);
       /* Disposable subscribe = apiService.AddSqproductHandle(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                       mvpView.getDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                       mvpView.getDataFail(throwable.getMessage());
                    }
                });*/


        Disposable subscribe1 = apiService.AddSqproductHandle(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                   mvpView.getDataSuccess(mgMode);
                }, throwable -> {
                   mvpView.getDataFail(throwable.getMessage());
                });

    }

}
