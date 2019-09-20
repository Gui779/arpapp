package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.view.AddYqproductView;
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


public class AddYqproductPresenter extends BasePresenter<AddYqproductView> {

    private final ApiStores apiService;

    public AddYqproductPresenter(AddYqproductView view) {
        attachView(view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    //已油漆仓库新增产品提交
    public void Yqadd(String store_id,String admin_id,String warehouse_id,String pro_id,String pro_name,String unit_id,
                      String unit_name,String unit_num,String wood_id,String wood_name,String color_id,String color_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("admin_id",admin_id);
        map.put("warehouse_id",warehouse_id);
        map.put("pro_id",pro_id);
        map.put("pro_name",pro_name);
        map.put("unit_id",unit_id);
        map.put("unit_name",unit_name);
        map.put("unit_num",unit_num);
        map.put("wood_id",wood_id);
        map.put("wood_name",wood_name);
        map.put("color_id",color_id);
        map.put("color_name",color_name);
       /* Disposable subscribe = apiService.yqaddproduct(map)
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

        Disposable subscribe1 = apiService.yqaddproduct(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                           mvpView.getDataSuccess(mgMode);
                        },
                        throwable -> {
                           mvpView.getDataFail(throwable.getMessage());
                        });
    }


    //成品仓库新增产品提交
    public void Cpadd(String store_id,String admin_id,String warehouse_id,String pro_id,String pro_name,String unit_id,
                      String unit_name,String unit_num,String wood_id,String wood_name,String color_id,String color_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("admin_id",admin_id);
        map.put("warehouse_id",warehouse_id);
        map.put("pro_id",pro_id);
        map.put("pro_name",pro_name);
        map.put("unit_id",unit_id);
        map.put("unit_name",unit_name);
        map.put("unit_num",unit_num);
        map.put("wood_id",wood_id);
        map.put("wood_name",wood_name);
        map.put("color_id",color_id);
        map.put("color_name",color_name);
      /*  Disposable subscribe = apiService.Addcpproduct(map)
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

        Disposable subscribe1 = apiService.Addcpproduct(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                   mvpView.getDataSuccess(mgMode);
                }, throwable -> {
                   mvpView.getDataFail(throwable.getMessage());
                });


    }


    //已磨仓库新增产品提交
    public void Ymadd(String store_id,String admin_id,String warehouse_id,String pro_id,String pro_name,String unit_id,
                      String unit_name,String unit_num,String wood_id,String wood_name,String color_id,String color_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("admin_id",admin_id);
        map.put("warehouse_id",warehouse_id);
        map.put("pro_id",pro_id);
        map.put("pro_name",pro_name);
        map.put("unit_id",unit_id);
        map.put("unit_name",unit_name);
        map.put("unit_num",unit_num);
        map.put("wood_id",wood_id);
        map.put("wood_name",wood_name);
        map.put("color_id",color_id);
        map.put("color_name",color_name);
       /* Disposable subscribe = apiService.Ymadd(map)
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
        Disposable subscribe1 = apiService.Ymadd(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                   mvpView.getDataSuccess(mgMode);
                }, throwable -> {
                   mvpView.getDataFail(throwable.getMessage());
                });


    }


    //未磨仓库新增产品提交
    public void Wmadd(String store_id,String admin_id,String warehouse_id,String pro_id,String pro_name,String unit_id,
                      String unit_name,String unit_num,String wood_id,String wood_name,String color_id,String color_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("admin_id",admin_id);
        map.put("warehouse_id",warehouse_id);
        map.put("pro_id",pro_id);
        map.put("pro_name",pro_name);
        map.put("unit_id",unit_id);
        map.put("unit_name",unit_name);
        map.put("unit_num",unit_num);
        map.put("wood_id",wood_id);
        map.put("wood_name",wood_name);
        map.put("color_id",color_id);
        map.put("color_name",color_name);
      /*  Disposable subscribe = apiService.Wmadd(map)
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

        Disposable subscribe1 = apiService.Wmadd(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                   mvpView.getDataSuccess(mgMode);
                }, throwable -> {
                   mvpView.getDataFail(throwable.getMessage());
                });

    }
}
