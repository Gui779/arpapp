package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.mode.ColourManageMode;
import com.example.erpqpp.mvp.view.ColourManageView;
import com.example.erpqpp.retrofit.ApiStores;
import com.example.erpqpp.utils.RxTransformer;
import com.lbb.mvplibrary.base.BasePresenter;
import com.lbb.mvplibrary.retrofit.ApiManager;

import java.lang.ref.Reference;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ColourManagePresenter extends BasePresenter<ColourManageView> {

    private final ApiStores apiService;
    private ColourManageView colourManageView;

    public ColourManagePresenter(ColourManageView view) {
        attachView( view);
        this.colourManageView=view;
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void saleColor(String store_id,String page){
        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("page",page);
        Disposable subscribe = apiService.saleColor(map)
                .compose(RxTransformer.transform(colourManageView))
                .subscribe(colourManageMode -> mvpView.getDataSuccess(colourManageMode),
                        throwable -> mvpView.getDataFail(throwable.getMessage()));

    }

    //删除色号
    public void delectColor(String color_id){
        Map<String,String> map=new HashMap<>();
        map.put("color_id",color_id);
        Disposable subscribe = apiService.deleteColor(map)
                .compose(RxTransformer.transform(colourManageView))
                .subscribe(xzCzMode -> mvpView.deleectDataSuccess(xzCzMode),
                        throwable -> mvpView.getDataFail(throwable.getMessage()));

    }
}
