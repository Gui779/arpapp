package com.example.erpqpp.mvp.presenter;


import android.util.Log;

import com.example.erpqpp.mvp.mode.AddclientMode;
import com.example.erpqpp.mvp.view.AddclientView;
import com.example.erpqpp.retrofit.ApiStores;
import com.example.erpqpp.retrofit.RxSchedulers;
import com.lbb.mvplibrary.base.BasePresenter;
import com.lbb.mvplibrary.retrofit.ApiManager;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.Validator;

import java.lang.ref.Reference;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class AddclientPresenter extends BasePresenter<AddclientView> {

    private final ApiStores apiService;

    public AddclientPresenter(AddclientView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

    public void Addclient(String store_id,String name,String cards,String date,String tel,String addres,String telbz,String szaddres,String mddate,String type,String add_remark){
     if (name.isEmpty()){
        mvpView.mytoast("请输入客户名称");
         return;
     }
     if (cards.isEmpty()){
         cards="";
     }

    /*  if(!Validator.isIDCard(cards)){
         mvpView.mytoast("身份证号不正确");
          return;
      }*/

     if (date.equals("请选择出生日期")){
          date="";
     }
     if (tel.isEmpty()){
        mvpView.mytoast("请输入手机号");
         return;
     }

        if (!Validator.isMobile(tel)){
           mvpView.mytoast("手机号不正确");
            return;
        }

     if (addres.isEmpty()){
        mvpView.mytoast("请输入联系地址");
         return;
     }
    if (telbz.isEmpty()){
        telbz="";
     }

       /*  if (!Validator.isMobile(telbz)){
           mvpView.mytoast("手机号不正确");
            return;
        }*/

     if (szaddres.isEmpty()){
        mvpView.mytoast("请输入所在地区");
         return;
     }
     if (mddate.equals("请选择门店成立时间")){
         mddate="";

     }
     if (type.equals("请选择类型")){
        mvpView.mytoast("请选择类型");
         return;
     }
    /* if (add_remark.isEmpty()){
        mvpView.mytoast("请输入备注");
         return;
     }*/

     Map<String,String> map=new HashMap<>();
     map.put("store_id",store_id);
     map.put("user_name",name);
     map.put("idcard",cards);
     map.put("birthday",date);
     map.put("tel",tel);
     map.put("address",addres);
     map.put("phone",telbz);
     map.put("region",szaddres);
     map.put("create_time",mddate);
     map.put("note",add_remark);
     if (type.equals("零售")){
         map.put("type","1");
     }else if (type.equals("批发")){
         map.put("type","2");
     }else if(type.equals("加盟商")){
         map.put("type","3");
     }

        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            String value = map.get(key).toString();
            LinLog.lLog(key + "==" + value);
        }

    /*    Disposable subscribe = apiService.addUser(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddclientMode>() {
                    @Override
                    public void accept(AddclientMode addclientMode) throws Exception {
                       mvpView.getDataSuccess(addclientMode);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                       mvpView.getDataFail(throwable.getMessage());
                    }
                });*/

        Disposable subscribe1 = apiService.addUser(map)
                .compose(RxSchedulers.Obs_io_main())
                .subscribe(addclientMode -> {
                   mvpView.getDataSuccess(addclientMode);
                }, throwable -> {
                   mvpView.getDataFail(throwable.getMessage());
                });


    }

}
