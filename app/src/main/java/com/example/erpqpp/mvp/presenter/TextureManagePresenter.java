package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.TextureManageMode;
import com.example.erpqpp.mvp.mode.XzCzMode;
import com.example.erpqpp.mvp.view.TextureManageView;
import com.example.erpqpp.retrofit.ApiStores;
import com.example.erpqpp.retrofit.RxSchedulers;
import com.example.erpqpp.utils.RxTransformer;
import com.lbb.mvplibrary.base.BasePresenter;
import com.lbb.mvplibrary.retrofit.ApiManager;

import java.lang.ref.Reference;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class TextureManagePresenter extends BasePresenter<TextureManageView> {

    private final ApiStores apiService;
    private TextureManageView textureManageView;

    public TextureManagePresenter(TextureManageView view) {
        this.textureManageView=view;
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

   public void woodSale(String store_id,String page){
       Map<String,String> map=new HashMap<>();
       map.put("store_id",store_id);
       map.put("page",page);
       Disposable subscribe = apiService.woodSale(map)
               .compose(RxTransformer.transform(textureManageView))
               .subscribe(textureManageMode -> mvpView.getDataSuccess(textureManageMode),
                       throwable -> mvpView.getDataFail(throwable.getMessage()));

   }


    public void woodSalesousuo(String store_id,String page,String wood_name){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
        map.put("wood_name",wood_name);
        Disposable subscribe = apiService.woodSale(map)
                .compose(RxTransformer.transform(textureManageView))
                .subscribe(textureManageMode -> mvpView.getDataSuccess(textureManageMode),
                        throwable -> mvpView.getDataFail(throwable.getMessage()));

    }


    public void delectWood(String wood_id){
        Map<String,String> map=new HashMap<>();
        map.put("wood_id",wood_id);
        final Disposable subscribe = apiService.deleteWood(map)
                .compose(RxTransformer.transform(textureManageView))
                .subscribe(xzCzMode -> mvpView.delectDataSuccess(xzCzMode),
                        throwable -> mvpView.getDataFail(throwable.getMessage()));


    }

}
