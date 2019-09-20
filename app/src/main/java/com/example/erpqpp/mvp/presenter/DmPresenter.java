package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.mode.DmMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.view.DmView;
import com.example.erpqpp.mvp.view.MgShView;
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

public class DmPresenter extends BasePresenter<DmView> {

    private final ApiStores apiService;

    public DmPresenter(DmView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void getDmList(String store_id,String p,String tabstatus){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("tabstatus",tabstatus);
        map.put("p",p);
       /* Disposable subscribe = apiService.getDmIndex(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DmMode>() {
                    @Override
                    public void accept(DmMode dmMode) throws Exception {
                      mvpView.getDataSuccess(dmMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.getDmIndex(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(dmMode -> {
                    mvpView.getDataSuccess(dmMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });
    }


    public void getDmListsousuo(String store_id,String p,String tabstatus,String pro_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("tabstatus",tabstatus);
        map.put("p",p);
        map.put("pro_name",pro_name);
      /*  Disposable subscribe = apiService.getDmIndex(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DmMode>() {
                    @Override
                    public void accept(DmMode dmMode) throws Exception {
                        mvpView.getDataSuccess(dmMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.getDmIndex(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(dmMode -> {
                    mvpView.getDataSuccess(dmMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }


    public void delect(String store_id,String id){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("id",id);
      /*  Disposable subscribe = apiService.Dmdelreceive(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.delectDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.delectDataFail(throwable.getMessage());
                    }
                });*/
        Disposable subscribe1 = apiService.Dmdelreceive(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.delectDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.delectDataFail(throwable.getMessage());
                });


    }
}
