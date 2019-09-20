package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.view.AddglproductView;
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


public class AddglproductPresenter extends BasePresenter<AddglproductView> {

    private final ApiStores apiService;

    public AddglproductPresenter(AddglproductView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void Addglproduct(String store_id,String admin_id,String pro_name,String series,String wood_name,String wood_id,String price,String cost_price,String unit_name,String rate){
        if (pro_name.isEmpty()){
           mvpView.mytoast("请输入产品名称");
            return;
        }
      /*  if (series.isEmpty()){
           mvpView.mytoast("请输入产品系列");
            return;
        }*/
        if (wood_name.equals("请选择产品材质")){
           mvpView.mytoast("请选择产品材质");
            return;
        }
      /*  if (price.isEmpty()){
           mvpView.mytoast("请输入标价");
            return;
        }
        if (cost_price.isEmpty()){
           mvpView.mytoast("请输入成本价");
            return;
        }*/

        Map<String,String> map=new HashMap<>();
        map.put("store_id",store_id);
        map.put("admin_id",admin_id);
        map.put("pro_name",pro_name);
        map.put("series",series);
        map.put("wood_id",wood_id);
        map.put("price",price);
        map.put("cost_price",cost_price);
        map.put("unit_name",unit_name);
        map.put("rate",rate);

      /*  Disposable subscribe = apiService.AddWood(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mgMode ->mvpView.getDataSuccess(mgMode), throwable ->mvpView.getDataFail(throwable.getMessage()));*/




        Disposable subscribe1 = apiService.AddWood(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode ->
                   mvpView.getDataSuccess(mgMode)
                , throwable ->
                   mvpView.getDataFail(throwable.getMessage())
                );
    }



}
