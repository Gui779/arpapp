package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.mode.XzCzMode;
import com.example.erpqpp.mvp.view.UpManageView;
import com.example.erpqpp.retrofit.ApiStores;
import com.example.erpqpp.retrofit.RxSchedulers;
import com.example.erpqpp.utils.RxTransformer;
import com.lbb.mvplibrary.base.BasePresenter;
import com.lbb.mvplibrary.retrofit.ApiManager;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class UpManagePresenter extends BasePresenter<UpManageView> {

    private final ApiStores apiService;
    private UpManageView upManageView;
    public UpManagePresenter(UpManageView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);
        this.upManageView=view;
    }

    //修改材质
    public void upManage(String wood_name,String wood_id){
        if (wood_name.isEmpty()){
            mvpView.mytoast("请输入材质名称");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("wood_name",wood_name);
        map.put("wood_id",wood_id);
        Disposable subscribe = apiService.editWood(map)
                .compose(RxTransformer.transform(upManageView))
                .subscribe(xzCzMode -> mvpView.getDataSuccess(xzCzMode),
                        throwable -> mvpView.getDataFail(throwable.getMessage()));

    }

    //添加材质
    public void addManage(String wood_name,String store_id){
        if (wood_name.isEmpty()){
            mvpView.mytoast("请输入材质名称");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("wood_name",wood_name);
        map.put("store_id",store_id);
        Disposable subscribe = apiService.addWood(map)
                .compose(RxTransformer.transform(upManageView))
                .subscribe(xzCzMode -> mvpView.getDataSuccess(xzCzMode),
                        throwable -> mvpView.getDataFail(throwable.getMessage()));

    }
}
