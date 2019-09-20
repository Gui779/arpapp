package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.CgStayGoMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.XsMode;
import com.example.erpqpp.mvp.view.CglistView;
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


public class CglistPresenter extends BasePresenter<CglistView> {

    private final ApiStores apiService;

    public CglistPresenter(CglistView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    //仓管待出货
    public void  Cglist(String store_id,String page){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
      /*  Disposable subscribe = apiService.warehouseListA(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CgStayGoMode>() {
                    @Override
                    public void accept(CgStayGoMode cgStayGoMode) throws Exception {
                        mvpView.getDataSuccess(cgStayGoMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.warehouseListA(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(cgStayGoMode -> {
                    mvpView.getDataSuccess(cgStayGoMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }



    public void  Cglistsousuo(String store_id,String page,String customer_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
        map.put("customer_name",customer_name);
      /*  Disposable subscribe = apiService.warehouseListA(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CgStayGoMode>() {
                    @Override
                    public void accept(CgStayGoMode cgStayGoMode) throws Exception {
                        mvpView.getDataSuccess(cgStayGoMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.warehouseListA(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(cgStayGoMode -> {
                    mvpView.getDataSuccess(cgStayGoMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });
    }

    //  //仓管已出货
    public void  CgYClist(String store_id,String page){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
   /*     Disposable subscribe = apiService.warehouseListB(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CgStayGoMode>() {
                    @Override
                    public void accept(CgStayGoMode cgStayGoMode) throws Exception {
                        mvpView.getDataSuccess(cgStayGoMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.warehouseListB(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(cgStayGoMode -> {
                    mvpView.getDataSuccess(cgStayGoMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });
    }


    //  //仓管已出货
    public void  CgYClistsousuo(String store_id,String page,String customer_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
        map.put("customer_name",customer_name);
      /*  Disposable subscribe = apiService.warehouseListB(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CgStayGoMode>() {
                    @Override
                    public void accept(CgStayGoMode cgStayGoMode) throws Exception {
                        mvpView.getDataSuccess(cgStayGoMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.warehouseListB(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(cgStayGoMode -> {
                    mvpView.getDataSuccess(cgStayGoMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }


      //仓管列表（待收货）
    public void  CgDslist(String store_id,String page){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
       /* Disposable subscribe = apiService.warehouseListC(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CgStayGoMode>() {
                    @Override
                    public void accept(CgStayGoMode cgStayGoMode) throws Exception {
                        mvpView.getDataSuccess(cgStayGoMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.warehouseListC(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(cgStayGoMode -> {
                    mvpView.getDataSuccess(cgStayGoMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }
      //仓管列表（待收货）
    public void  CgDslistsousuo(String store_id,String page,String customer_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
        map.put("customer_name",customer_name);
        /*Disposable subscribe = apiService.warehouseListC(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CgStayGoMode>() {
                    @Override
                    public void accept(CgStayGoMode cgStayGoMode) throws Exception {
                        mvpView.getDataSuccess(cgStayGoMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/
        Disposable subscribe1 = apiService.warehouseListC(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(cgStayGoMode -> {
                    mvpView.getDataSuccess(cgStayGoMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });
    }



    //仓管列表（已收货）
    public void  CgYslist(String store_id,String page){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
       /* Disposable subscribe = apiService.warehouseListD(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CgStayGoMode>() {
                    @Override
                    public void accept(CgStayGoMode cgStayGoMode) throws Exception {
                        mvpView.getDataSuccess(cgStayGoMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.warehouseListD(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(cgStayGoMode -> {
                    mvpView.getDataSuccess(cgStayGoMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });
    }

    public void  CgYslistsousuo(String store_id,String page,String customer_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
        map.put("customer_name",customer_name);
       /* Disposable subscribe = apiService.warehouseListD(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CgStayGoMode>() {
                    @Override
                    public void accept(CgStayGoMode cgStayGoMode) throws Exception {
                        mvpView.getDataSuccess(cgStayGoMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                }
                });*/
        Disposable subscribe1 = apiService.warehouseListD(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(cgStayGoMode -> {
                    mvpView.getDataSuccess(cgStayGoMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });
    }

    //仓库收货页面列表1
    public void warehouse_c(String order_id){
        Map<String,String> map=new HashMap<>();
        map.put("order_id",order_id);
      /*  Disposable subscribe = apiService.warehouse_c(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                       mvpView.getYzDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getYzDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.warehouse_c(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getYzDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.getYzDataFail(throwable.getMessage());
                });
    }



    //退货页面列表1
    public void returnGoodsPage(String order_id){
        Map<String,String> map=new HashMap<>();
        map.put("order_id",order_id);
      /*  Disposable subscribe = apiService.returnGoodsPage(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.getYzDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getYzDataFail(throwable.getMessage());
                    }
                });*/
        Disposable subscribe1 = apiService.returnGoodsPage(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getYzDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.getYzDataFail(throwable.getMessage());
                });


    }
}
