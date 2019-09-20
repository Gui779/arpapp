package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.AddclientMode;
import com.example.erpqpp.mvp.mode.DmMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.WorkerMode;
import com.example.erpqpp.mvp.view.HarvestView;
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


public class HarvestPresenter extends BasePresenter<HarvestView> {

    private final ApiStores apiService;

    public HarvestPresenter(HarvestView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }


    //获取木工人员
    public void getmguser(String store_id){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
       /* Disposable subscribe = apiService.getworker(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WorkerMode>() {
                    @Override
                    public void accept(WorkerMode workerMode) throws Exception {
                     mvpView.getDataSuccess(workerMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                     mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.getworker(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(workerMode -> {
                    mvpView.getDataSuccess(workerMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }



      //木工收货
     public void Harvest(String renyuan,String date,String store_id,String worker_id,String return_num,String id){
        if (renyuan.equals("请选择收货人员")){
            mvpView.mytoast("请选择收货人员");
            return;
        }
        final Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("worker_id",worker_id);
        map.put("receive_time",date);
        map.put("return_num",return_num);
        map.put("id",id);

      /*   Disposable subscribe = apiService.mgreceiver(map)
                 .subscribeOn(Schedulers.newThread())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<MgMode>() {
                     @Override
                     public void accept(MgMode mgMode) throws Exception {
                     mvpView.getmgDataSuccess(mgMode);
                     }
                 }, new Consumer<Throwable>() {
                     @Override
                     public void accept(Throwable throwable) throws Exception {
                         mvpView.gemgtDataFail(throwable.getMessage());
                     }
                 });*/

         Disposable subscribe = apiService.mgreceiver(map)
                 .compose(RxSchedulers.Obs_io_main())
                 .subscribe(mgMode -> {
                     mvpView.getmgDataSuccess(mgMode);
                 }, throwable -> {
                     mvpView.gemgtDataFail(throwable.getMessage());
                 });


     }


     //木工发货
    public void Harvestfahuo(String renyuan,String date,String store_id,String worker_id,String return_num,String id){
        if (renyuan.equals("请选择收货人员")){
            mvpView.mytoast("请选择收货人员");
            return;
        }
        final Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("worker_id",worker_id);
        map.put("send_time",date);
        map.put("return_num",return_num);
        map.put("id",id);

      /*  Disposable subscribe = apiService.send(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.getmgDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.gemgtDataFail(throwable.getMessage());
                    }
                });*/

    }


    //木工发货撤回
    public void Harvestfahuochehui(String renyuan,String store_id,String admin_id,String return_num,String id){
        if (renyuan.equals("请选择收货人员")){
            mvpView.mytoast("请选择收货人员");
            return;
        }
        final Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("admin_id",admin_id);
        map.put("return_num",return_num);
        map.put("id",id);

      /*  Disposable subscribe = apiService.Sendrevoke(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.getmgDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.gemgtDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.Sendrevoke(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getmgDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.gemgtDataFail(throwable.getMessage());
                });


    }


    //木工待发货撤回
    public void Harveststayfahuochehui(String renyuan,String store_id,String admin_id,String return_num,String id){
        if (renyuan.equals("请选择收货人员")){
            mvpView.mytoast("请选择收货人员");
            return;
        }
        final Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("admin_id",admin_id);
        map.put("return_num",return_num);
        map.put("id",id);

       /* Disposable subscribe = apiService.Dsend_revoke(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.getmgDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.gemgtDataFail(throwable.getMessage());
                    }
                });*/
        Disposable subscribe1 = apiService.Dsend_revoke(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getmgDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.gemgtDataFail(throwable.getMessage());
                });


    }

    //打磨收货
  public void getreceive(String renyuan,String store_id,String worker_id,String receive_time,String return_num,String id){
      if (renyuan.equals("请选择收货人员")){
          mvpView.mytoast("请选择收货人员");
          return;
      }
      final Map<String,String> map=new HashMap<>();
      map.put("store_id",store_id);
      map.put("worker_id",worker_id);
      map.put("return_num",return_num);
      map.put("receive_time",receive_time);
      map.put("id",id);

     /* Disposable subscribe = apiService.getreceive(map)
              .subscribeOn(Schedulers.newThread())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Consumer<MgMode>() {
                  @Override
                  public void accept(MgMode mgMode) throws Exception {
                      mvpView.getmgDataSuccess(mgMode);
                  }
              }, new Consumer<Throwable>() {
                  @Override
                  public void accept(Throwable throwable) throws Exception {
                      mvpView.gemgtDataFail(throwable.getMessage());
                  }
              });*/

      Disposable subscribe1 = apiService.getreceive(map)
              .compose(RxSchedulers.Obs_io_main())
              .subscribe(mgMode -> {
                  mvpView.getmgDataSuccess(mgMode);
              }, throwable -> {
                  mvpView.gemgtDataFail(throwable.getMessage());
              });

  }


   //打磨收货退回
    public void getreceive_revoke(String renyuan,String store_id,String id,String return_num,String admin_id){
        if (renyuan.equals("请选择收货人员")){
            mvpView.mytoast("请选择收货人员");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("id",id);
        map.put("return_num",return_num);
        map.put("admin_id",admin_id);
       /* Disposable subscribe = apiService.getreceive_revoke(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.getmgDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.gemgtDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.getreceive_revoke(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getmgDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.gemgtDataFail(throwable.getMessage());
                });

    }


    //打磨发货
    public void DmSend(String renyuan,String store_id,String worker_id,String send_time,String return_num,String id){
        if (renyuan.equals("请选择收货人员")){
            mvpView.mytoast("请选择收货人员");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("worker_id",worker_id);
        map.put("send_time",send_time);
        map.put("return_num",return_num);
        map.put("id",id);
       /* Disposable subscribe = apiService.DmSend(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.getmgDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.gemgtDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.DmSend(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getmgDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.gemgtDataFail(throwable.getMessage());
                });


    }
    //打磨待发货撤回
    public void dmdsend_revoke(String renyuan,String store_id,String id,String return_num,String admin_id){
        if (renyuan.equals("请选择收货人员")){
            mvpView.mytoast("请选择收货人员");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("id",id);
        map.put("return_num",return_num);
        map.put("admin_id",admin_id);
       /* Disposable subscribe = apiService.dmdsend_revoke(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.getmgDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.gemgtDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.dmdsend_revoke(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getmgDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.gemgtDataFail(throwable.getMessage());
                });

    }


    //打磨发货撤回
    public void dmSend_revoke(String renyuan,String store_id,String id,String return_num,String admin_id){
        if (renyuan.equals("请选择收货人员")){
            mvpView.mytoast("请选择收货人员");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("id",id);
        map.put("return_num",return_num);
        map.put("admin_id",admin_id);
       /* Disposable subscribe = apiService.dmSend_revoke(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.getmgDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.gemgtDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe = apiService.dmSend_revoke(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getmgDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.gemgtDataFail(throwable.getMessage());
                });

    }

    //上漆管理收货
    public void Sqreceive(String renyuan,String store_id,String worker_id,String receive_time,String return_num,String id){
        if (renyuan.equals("请选择收货人员")){
            mvpView.mytoast("请选择收货人员");
            return;
        }
        final Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("worker_id",worker_id);
        map.put("return_num",return_num);
        map.put("receive_time",receive_time);
        map.put("id",id);

        /*Disposable subscribe = apiService.Sqreceive(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.getmgDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.gemgtDataFail(throwable.getMessage());
                    }
                });*/
        Disposable subscribe = apiService.Sqreceive(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getmgDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.gemgtDataFail(throwable.getMessage());
                });

    }

    //上漆收货撤回
    public void Sqreceive_revoke(String renyuan,String store_id,String id,String return_num,String admin_id){
        if (renyuan.equals("请选择收货人员")){
            mvpView.mytoast("请选择收货人员");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("id",id);
        map.put("return_num",return_num);
        map.put("admin_id",admin_id);
       /* Disposable subscribe = apiService.Sqsqreceive_revoke(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.getmgDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.gemgtDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe = apiService.Sqsqreceive_revoke(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getmgDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.gemgtDataFail(throwable.getMessage());
                });

    }


    //上漆待发货撤回
    public void Sqsend_revoke(String renyuan,String store_id,String id,String return_num,String admin_id){
        if (renyuan.equals("请选择收货人员")){
            mvpView.mytoast("请选择收货人员");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("id",id);
        map.put("return_num",return_num);
        map.put("admin_id",admin_id);
       /* Disposable subscribe = apiService.Sqsend_revoke(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.getmgDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.gemgtDataFail(throwable.getMessage());
                    }
                });*/
        Disposable subscribe = apiService.Sqsend_revoke(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getmgDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.gemgtDataFail(throwable.getMessage());
                });


    }


    //上漆发货
    public void Sqsend(String renyuan,String store_id,String worker_id,String send_time,String return_num,String id,String color_id,String color_name){
        if (renyuan.equals("请选择收货人员")){
            mvpView.mytoast("请选择收货人员");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("worker_id",worker_id);
        map.put("send_time",send_time);
        map.put("return_num",return_num);
        map.put("id",id);
        map.put("color_id",color_id);
        map.put("color_name",color_name);
       /* Disposable subscribe = apiService.Sqsend(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.getmgDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.gemgtDataFail(throwable.getMessage());
                    }
                });*/
        Disposable subscribe = apiService.Sqsend(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getmgDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.gemgtDataFail(throwable.getMessage());
                });


    }


    //上漆发货撤回
    public void Sqsendrevoke(String renyuan,String store_id,String id,String return_num,String admin_id){
        if (renyuan.equals("请选择收货人员")){
            mvpView.mytoast("请选择收货人员");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("id",id);
        map.put("return_num",return_num);
        map.put("admin_id",admin_id);
       /* Disposable subscribe = apiService.Sqsendrevoke(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MgMode>() {
                    @Override
                    public void accept(MgMode mgMode) throws Exception {
                        mvpView.getmgDataSuccess(mgMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.gemgtDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe = apiService.Sqsendrevoke(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                    mvpView.getmgDataSuccess(mgMode);
                }, throwable -> {
                    mvpView.gemgtDataFail(throwable.getMessage());
                });


    }


}
