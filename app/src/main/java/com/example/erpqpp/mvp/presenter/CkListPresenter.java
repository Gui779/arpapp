package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.view.CkListView;
import com.example.erpqpp.retrofit.ApiStores;
import com.example.erpqpp.retrofit.RxSchedulers;
import com.lbb.mvplibrary.base.BasePresenter;
import com.lbb.mvplibrary.retrofit.ApiManager;
import java.util.HashMap;
import java.util.Map;
import io.reactivex.disposables.Disposable;



public class CkListPresenter extends BasePresenter<CkListView> {

    private final ApiStores apiService;

    public CkListPresenter(CkListView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    /**
     * 获取产品仓库列表信息2
     * */
    public void ckList(String orderId, String page){
        Map<String,String> map=new HashMap<>();
        map.put("order_id",orderId);
        map.put("page",page);
        Disposable subscribe1 = apiService.productList(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(ckListMode ->
                    mvpView.getDataSuccess(ckListMode)
                , throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });
    }


    //出货
    public void outProduct(String orderId, String proId, String id, String outCount){
        Map<String,String> map=new HashMap<>();
        map.put("order_id",orderId);
        map.put("proId",proId);
        map.put("id",id);
        map.put("outCount",outCount);


        Disposable subscribe1 = apiService.outProduct(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(czMode -> {
                    mvpView.outDataSuccess(czMode);
                }, throwable -> {
                    mvpView.outDataFail(throwable.getMessage());
                });


    }


}
