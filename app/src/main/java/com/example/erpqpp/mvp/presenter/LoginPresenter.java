package com.example.erpqpp.mvp.presenter;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.erpqpp.mvp.mode.LoginMode;
import com.example.erpqpp.mvp.mode.Mymode;
import com.example.erpqpp.mvp.view.LoginView;
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

public class LoginPresenter extends BasePresenter<LoginView> {

    private final ApiStores apiService;

    public LoginPresenter(LoginView view) {
        attachView(view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

   public void login(String tel, String pwd, boolean ispwd){
       final Map<String,String> map=new HashMap<>();
        if (tel.isEmpty()){
            mvpView.mytoast("请输入登录账号");
            return;
        }


        if (ispwd){
            if (pwd.isEmpty()){
                mvpView.mytoast("请输入密码");
                return;
            }
            map.clear();
            map.put("mobile",tel);
            map.put("password",pwd);
        /*    Disposable subscribe = apiService.Login(map)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<LoginMode>() {
                        @Override
                        public void accept(LoginMode loginMode) throws Exception {
                          mvpView.getDataSuccess(loginMode);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            mvpView.getDataFail(throwable.getMessage());
                        }
                    });*/

            Disposable subscribe1 = apiService.Login(map)
                    .compose(RxSchedulers.Obs_io_main())
                    .subscribe(loginMode -> {
                        mvpView.getDataSuccess(loginMode);
                    }, throwable -> {
                        mvpView.getDataFail(throwable.getMessage());
                    });


        }else {
            if (pwd.isEmpty()){
                mvpView.mytoast("请输入验证码");
                return;
            }
        }


   }

   public void getcode(String tel,final TextView code){
        if (tel.isEmpty()){
            mvpView.mytoast("请输入手机号");
            return;
        }

       CountDownTimer countDownTimer = new CountDownTimer(60*1000,1000) {

           @Override
           public void onTick(long millisUntilFinished) {
               code.setText(millisUntilFinished/1000+"秒");
               code.setClickable(false);
           }

           @Override
           public void onFinish() {
               code.setText("获取验证码");
               code.setClickable(true);
           }
       }.start();
   }
}
