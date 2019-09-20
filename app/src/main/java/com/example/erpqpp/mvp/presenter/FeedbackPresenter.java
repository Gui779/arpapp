package com.example.erpqpp.mvp.presenter;


import com.example.erpqpp.mvp.mode.CzMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.view.FeedbackView;
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


public class FeedbackPresenter extends BasePresenter<FeedbackView> {

    private final ApiStores apiService;

    public FeedbackPresenter(FeedbackView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void Feedback(String y_text,String tel){
        if (y_text.isEmpty()){
            mvpView.mytoast("请输入意见反馈");
            return;
        }   Map<String,String> map=new HashMap<>();
        map.put("tel",tel);
        map.put("y_text",y_text);
        /*Disposable subscribe = apiService.Feedback(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CzMode>() {
                    @Override
                    public void accept(CzMode czMode) throws Exception {
                        mvpView.getDataSuccess(czMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.Feedback(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(czMode -> {
                    mvpView.getDataSuccess(czMode);
                }, throwable -> {
                    mvpView.getDataFail(throwable.getMessage());
                });

    }


}
