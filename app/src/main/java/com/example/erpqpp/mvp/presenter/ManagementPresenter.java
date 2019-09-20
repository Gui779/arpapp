package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.ManagementMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.view.ManagementView;
import com.example.erpqpp.retrofit.ApiStores;
import com.example.erpqpp.retrofit.RxSchedulers;
import com.example.erpqpp.utils.RxTransformer;
import com.lbb.mvplibrary.base.BasePresenter;
import com.lbb.mvplibrary.retrofit.ApiManager;

import java.lang.ref.Reference;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class ManagementPresenter extends BasePresenter<ManagementView> {

    private final ApiStores apiService;
    private ManagementView view;

    public ManagementPresenter(ManagementView view) {
        attachView( view);
        this.view=view;
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }


   public void getManagement(String store_id,String page){
       Map<String,String> map=new HashMap<>();
       map.put("store_id",store_id);
       map.put("page",page);
     /*  Disposable subscribe = apiService.getindex(map)
               .subscribeOn(Schedulers.newThread())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Consumer<ManagementMode>() {
                   @Override
                   public void accept(ManagementMode managementMode) throws Exception {
                     mvpView.getDataSuccess(managementMode);
                   }

               }, new Consumer<Throwable>() {
                   @Override
                   public void accept(Throwable throwable) throws Exception {
                    mvpView.getDataFail(throwable.getMessage());
                   }
               });*/

       Disposable subscribe = apiService.getindex(map)
               .compose(RxTransformer.transform(view))
               .subscribe(managementMode -> {
                   mvpView.getDataSuccess(managementMode);
               }, throwable -> {
                   mvpView.getDataFail(throwable.getMessage());
               });
   }

    public void getManagementsousuo(String store_id,String page,String user_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
        map.put("user_name",user_name);
      /*  Disposable subscribe = apiService.getindex(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ManagementMode>() {
                    @Override
                    public void accept(ManagementMode managementMode) throws Exception {
                        mvpView.getDataSuccess(managementMode);
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.getindex(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(managementMode -> {
                    mvpView.getDataSuccess(managementMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });
    }


    public void Companydelete(String user_id){
        Map<String,String> map=new HashMap<>();
        map.put("user_id",user_id);
    /*    Disposable subscribe = apiService.Companydelete(map)
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
                });
*/
        Disposable subscribe1 = apiService.Companydelete(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.DelectDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });


    }

}
