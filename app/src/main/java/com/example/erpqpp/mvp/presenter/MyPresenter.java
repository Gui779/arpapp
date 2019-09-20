package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.Mymode;
import com.example.erpqpp.mvp.view.Myview;
import com.example.erpqpp.retrofit.ApiStores;
import com.lbb.mvplibrary.base.BasePresenter;
import com.lbb.mvplibrary.retrofit.ApiManager;


import java.lang.ref.Reference;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

public class MyPresenter extends BasePresenter<Myview> {

    private final ApiStores apiService;

    public MyPresenter(Myview view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }


    public void loadDataByRetrofitRxjava(final Map<String,String> map) {
       /* apiService.getIndex(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Mymode>() {
                    @Override
                    public void accept(Mymode mymode) throws Exception {
                      mvpView.getDataSuccess(mymode);
                    }
                }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                // 处理异常
                mvpView.getDataFail(throwable.toString());

            }
        });*/




    }



}
