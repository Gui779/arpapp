package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.CkMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.view.FallView;
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


public class FallPresenter extends BasePresenter<FallView> {

    private final ApiStores apiService;

    public FallPresenter(FallView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void getWarehousing(String store_id){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
     /*   Disposable subscribe = apiService.getWarehousing(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CkMode>() {
                    @Override
                    public void accept(CkMode ckMode) throws Exception {
                       mvpView.getDataSuccess(ckMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.getWarehousing(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(ckMode -> {
                    mvpView.getDataSuccess(ckMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }


    //油漆发货
    public void YqFh(String cangku,String store_id,String admin_id,String planWarehousing,String selectedIds){
        if (cangku.equals("请选择仓库")){
            mvpView.mytoast("请选择仓库");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("admin_id",admin_id);
        map.put("planWarehousing",planWarehousing);
        map.put("selectedIds",selectedIds);

       /* Disposable subscribe = apiService.yqreceipt(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                       mvpView.getfhDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getfhDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.yqreceipt(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getfhDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.getfhDataFail(throwable.getMessage());
                });


    }


    //油漆撤回
    public void YqCh(String cangku,String store_id,String admin_id,String selectedIds){
        if (cangku.equals("请选择仓库")){
            mvpView.mytoast("请选择仓库");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("admin_id",admin_id);
        map.put("selectedIds",selectedIds);

      /*  Disposable subscribe = apiService.ryqeturnProduct(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.getfhDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getfhDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.ryqeturnProduct(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getfhDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.getfhDataFail(throwable.getMessage());
                });


    }

    //成品调货
    public void CpDh(String cangku,String store_id,String admin_id,String planWarehousing,String selectedIds){
        if (cangku.equals("请选择仓库")){
            mvpView.mytoast("请选择仓库");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("admin_id",admin_id);
        map.put("planWarehousing",planWarehousing);
        map.put("selectedIds",selectedIds);

       /* Disposable subscribe = apiService.CpDh(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.getfhDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getfhDataFail(throwable.getMessage());
                    }
                });
*/
        Disposable subscribe1 = apiService.CpDh(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getfhDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.getfhDataFail(throwable.getMessage());
                });


    }
    //成品撤回
    public void CpCh(String cangku,String store_id,String admin_id,String selectedIds){
        if (cangku.equals("请选择仓库")){
            mvpView.mytoast("请选择仓库");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("admin_id",admin_id);
        map.put("selectedIds",selectedIds);

        /*Disposable subscribe = apiService.CpCh(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.getfhDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getfhDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.CpCh(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getfhDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.getfhDataFail(throwable.getMessage());
                });


    }


    //未磨撤回
    public void WmCh(String cangku,String store_id,String admin_id,String selectedIds){
        if (cangku.equals("请选择仓库")){
            mvpView.mytoast("请选择仓库");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("admin_id",admin_id);
        map.put("selectedIds",selectedIds);

      /*  Disposable subscribe = apiService.WmCh(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.getfhDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getfhDataFail(throwable.getMessage());
                    }
                });*/
        Disposable subscribe1 = apiService.WmCh(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getfhDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.getfhDataFail(throwable.getMessage());
                });


    }



    //未磨发货
    public void WmFh(String cangku,String store_id,String admin_id,String planWarehousing,String selectedIds){
        if (cangku.equals("请选择仓库")){
            mvpView.mytoast("请选择仓库");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("admin_id",admin_id);
        map.put("planWarehousing",planWarehousing);
        map.put("selectedIds",selectedIds);

       /* Disposable subscribe = apiService.WmFh(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.getfhDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getfhDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.WmFh(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getfhDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.getfhDataFail(throwable.getMessage());
                });


    }



    //已磨发货
    public void YmFh(String cangku,String store_id,String admin_id,String planWarehousing,String selectedIds){
        if (cangku.equals("请选择仓库")){
            mvpView.mytoast("请选择仓库");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("admin_id",admin_id);
        map.put("planWarehousing",planWarehousing);
        map.put("selectedIds",selectedIds);

       /* Disposable subscribe = apiService.ymReceipt(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.getfhDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getfhDataFail(throwable.getMessage());
                    }
                });
*/
        Disposable subscribe1 = apiService.ymReceipt(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getfhDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.getfhDataFail(throwable.getMessage());
                });

    }



    //已磨仓库撤回
    public void YmCh(String cangku,String store_id,String admin_id,String selectedIds){
        if (cangku.equals("请选择仓库")){
            mvpView.mytoast("请选择仓库");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("admin_id",admin_id);
        map.put("selectedIds",selectedIds);

       /* Disposable subscribe = apiService.ymReturnProduct(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.getfhDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getfhDataFail(throwable.getMessage());
                    }
                });*/
        Disposable subscribe = apiService.ymReturnProduct(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getfhDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.getfhDataFail(throwable.getMessage());
                });

    }
}
