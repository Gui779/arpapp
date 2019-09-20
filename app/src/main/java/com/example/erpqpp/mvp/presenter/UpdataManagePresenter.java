package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.mode.XzCzMode;
import com.example.erpqpp.mvp.view.UpManageView;
import com.example.erpqpp.mvp.view.UpdataManageView;
import com.example.erpqpp.retrofit.ApiStores;
import com.example.erpqpp.retrofit.RxSchedulers;
import com.example.erpqpp.utils.RxTransformer;
import com.lbb.mvplibrary.base.BasePresenter;
import com.lbb.mvplibrary.retrofit.ApiManager;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class UpdataManagePresenter extends BasePresenter<UpdataManageView> {

    private final ApiStores apiService;
    private UpdataManageView upManageView;
    public UpdataManagePresenter(UpdataManageView view) {
        this.upManageView=view;
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    //修改色号
    public void  updataManage(String color_num,String color_name,String color_id){
        if (color_num.isEmpty()){
          mvpView.toast("请输入色号编号");
          return;
        }
        if (color_name.isEmpty()){
            mvpView.toast("请输入色号名称");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("color_num",color_num);
        map.put("color_name",color_name);
        map.put("color_id",color_id);
        Disposable subscribe = apiService.colorEdit(map)
                .compose(RxTransformer.transform(upManageView))
                .subscribe(xzCzMode -> mvpView.getDataSuccess(xzCzMode),
                        throwable -> mvpView.getDataFail(throwable.getMessage()));

    }

    //添加色号
    public void addColor(String color_num,String color_name,String store_id){
        if (color_num.isEmpty()){
            mvpView.toast("请输入色号编号");
            return;
        }
        if (color_name.isEmpty()){
            mvpView.toast("请输入色号名称");
            return;
        }
        Map<String,String> map=new HashMap<>();
        map.put("color_num",color_num);
        map.put("color_name",color_name);
        map.put("store_id",store_id);
        Disposable subscribe = apiService.addColor(map)
                .compose(RxTransformer.transform(upManageView))
                .subscribe(xzCzMode -> mvpView.getDataSuccess(xzCzMode),
                        throwable -> mvpView.getDataFail(throwable.getMessage()));
    }

}
