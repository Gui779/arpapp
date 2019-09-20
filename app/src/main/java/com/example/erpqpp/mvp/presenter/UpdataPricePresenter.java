package com.example.erpqpp.mvp.presenter;



import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.view.UpdataPriceView;
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


public class UpdataPricePresenter extends BasePresenter<UpdataPriceView> {

    private final ApiStores apiService;

    public UpdataPricePresenter(UpdataPriceView view) {
        attachView(view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    //修改已油漆仓库成本价
    public void UpdataPrice(String store_id,String cost,String id){
        if (cost.isEmpty()){
           mvpView.mytoast("请输入成本价");
            return;
        }
        if (Integer.parseInt(cost)<1){
           mvpView.mytoast("成本价不能小于1");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("cost",cost);
        map.put("id",id);
      /*  Disposable subscribe = apiService.Updataprice(map)
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


        Disposable subscribe1 = apiService.Updataprice(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                   mvpView.getDataSuccess(mgMode);
                }, throwable -> {
                   mvpView.getDataFail(throwable.getMessage());
                });


    }

    //修改成品仓库
    public void UpdataCpPrice(String store_id,String cost,String id){
        if (cost.isEmpty()){
           mvpView.mytoast("请输入成本价");
            return;
        }
        if (Integer.parseInt(cost)<1){
           mvpView.mytoast("成本价不能小于1");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("cost",cost);
        map.put("id",id);
      /*  Disposable subscribe = apiService.UpdataCpprice(map)
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
        Disposable subscribe1 = apiService.UpdataCpprice(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                   mvpView.getDataSuccess(mgMode);
                }, throwable -> {
                   mvpView.getDataFail(throwable.getMessage());
                });


    }


    //未磨仓库修改成本价
    public void UpdataWmPrice(String store_id,String cost,String id){
        if (cost.isEmpty()){
           mvpView.mytoast("请输入成本价");
            return;
        }
        if (Integer.parseInt(cost)<1){
           mvpView.mytoast("成本价不能小于1");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("cost",cost);
        map.put("id",id);
      /*  Disposable subscribe = apiService.UpdataWmprice(map)
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

        Disposable subscribe = apiService.UpdataWmprice(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                   mvpView.getDataSuccess(mgMode);
                }, throwable -> {
                   mvpView.getDataFail(throwable.getMessage());
                });


    }


    //已磨仓库修改成本价
    public void UpdataYmPrice(String store_id,String cost,String id){
        if (cost.isEmpty()){
           mvpView.mytoast("请输入成本价");
            return;
        }
        if (Integer.parseInt(cost)<1){
           mvpView.mytoast("成本价不能小于1");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("cost",cost);
        map.put("id",id);
       /* Disposable subscribe = apiService.UpdataYmprice(map)
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

        Disposable subscribe = apiService.UpdataYmprice(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                   mvpView.getDataSuccess(mgMode);
                }, throwable -> {
                   mvpView.getDataFail(throwable.getMessage());
                });


    }


}
