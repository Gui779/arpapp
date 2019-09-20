package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.SendGoodsDetailsMode;
import com.example.erpqpp.mvp.view.SendGoodsDetailsView;
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

public class SendGoodsDetailsPresenter extends BasePresenter<SendGoodsDetailsView> {

    private final ApiStores apiService;

    public SendGoodsDetailsPresenter(SendGoodsDetailsView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    //发货（待发货详情页面）
    public void DSendGoodsDetails(String order_id){
        Map<String,String> map=new HashMap<>();
        map.put("order_id",order_id);
      /*  Disposable subscribe = apiService.SendDetailsM(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SendGoodsDetailsMode>() {
                    @Override
                    public void accept(SendGoodsDetailsMode sendGoodsDetailsMode) throws Exception {
                      mvpView.getDataSuccess(sendGoodsDetailsMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe = apiService.SendDetailsM(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(sendGoodsDetailsMode -> {
                    mvpView.getDataSuccess(sendGoodsDetailsMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });


    }


  //  已发货详情页面
    public void YSendGoodsDetails(String order_id){
        Map<String,String> map=new HashMap<>();
        map.put("order_id",order_id);
      /*  Disposable subscribe = apiService.shGoods(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SendGoodsDetailsMode>() {
                    @Override
                    public void accept(SendGoodsDetailsMode sendGoodsDetailsMode) throws Exception {
                      mvpView.getDataSuccess(sendGoodsDetailsMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe = apiService.shGoods(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(sendGoodsDetailsMode -> {
                    mvpView.getDataSuccess(sendGoodsDetailsMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });


    }


  //  收货（待收货详情）
    public void Goodsxq(String order_id){
        Map<String,String> map=new HashMap<>();
        map.put("order_id",order_id);
    /*    Disposable subscribe = apiService.goodsxq(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SendGoodsDetailsMode>() {
                    @Override
                    public void accept(SendGoodsDetailsMode sendGoodsDetailsMode) throws Exception {
                      mvpView.getDataSuccess(sendGoodsDetailsMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.goodsxq(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(sendGoodsDetailsMode -> {
                    mvpView.getDataSuccess(sendGoodsDetailsMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });


    }
  //  收货（待收货详情）
    public void Goodsyshxq(String order_id){
        Map<String,String> map=new HashMap<>();
        map.put("order_id",order_id);
       /* Disposable subscribe = apiService.goodsyshxq(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SendGoodsDetailsMode>() {
                    @Override
                    public void accept(SendGoodsDetailsMode sendGoodsDetailsMode) throws Exception {
                      mvpView.getDataSuccess(sendGoodsDetailsMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });
*/
        Disposable subscribe1 = apiService.goodsyshxq(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(sendGoodsDetailsMode -> {
                    mvpView.getDataSuccess(sendGoodsDetailsMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }

}
