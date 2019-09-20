package com.example.erpqpp.mvp.presenter;

import com.example.erpqpp.mvp.view.AddCgProductView;
import com.example.erpqpp.retrofit.ApiStores;
import com.example.erpqpp.retrofit.RxSchedulers;
import com.lbb.mvplibrary.base.BasePresenter;
import com.lbb.mvplibrary.retrofit.ApiManager;
import java.util.HashMap;
import java.util.Map;
import io.reactivex.disposables.Disposable;


public class AddCgProductPresenter extends BasePresenter<AddCgProductView> {

    private final ApiStores apiService;

    public AddCgProductPresenter(AddCgProductView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void addCompileproduct(String storeId, String adminid, String proname, String series, String woodid,
                                  String price, String costprice, String unitname, String rate, String woodname){
        if (proname.isEmpty()){
           mvpView.mytoast("请输入产品名称");
            return;
        }

        final String name="请选择产品材质";
        if (woodname.equals(name)){
           mvpView.mytoast("请选择产品材质");
            return;
        }

        Map<String,String> map=new HashMap<>();
        map.put("store_id",storeId);
        map.put("admin_id",adminid);
        map.put("pro_name",proname);
        map.put("series",series);
        map.put("wood_id",woodid);
        map.put("price",price);
        map.put("cost_price",costprice);
        if (unitname!=null){
            map.put("unit_name",unitname);
        }
        if (rate!=null){
            map.put("rate", rate);
        }



        Disposable subscribe1 = apiService.Addood(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(mgMode -> {
                           mvpView.getDataSuccess(mgMode);
                        }
                        , throwable -> {
                           mvpView.getDataFail(throwable.getMessage());
                        });


    }
}
