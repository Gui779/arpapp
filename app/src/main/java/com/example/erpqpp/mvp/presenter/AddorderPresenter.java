package com.example.erpqpp.mvp.presenter;


import android.view.View;

import com.example.erpqpp.mvp.mode.AddMode;
import com.example.erpqpp.mvp.mode.MdMode;
import com.example.erpqpp.mvp.mode.OrderAdd;
import com.example.erpqpp.mvp.mode.PostProduct;
import com.example.erpqpp.mvp.mode.PostXsBean;
import com.example.erpqpp.mvp.mode.XsListMode;
import com.example.erpqpp.mvp.mode.XsproductListMode;
import com.example.erpqpp.mvp.view.AddorderView;
import com.example.erpqpp.myview.Item;
import com.example.erpqpp.retrofit.ApiStores;
import com.example.erpqpp.retrofit.RxSchedulers;
import com.lbb.mvplibrary.base.BasePresenter;
import com.lbb.mvplibrary.retrofit.ApiManager;
import com.lbb.mvplibrary.utils.LinLog;

import java.lang.ref.Reference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class AddorderPresenter extends BasePresenter<AddorderView> {

    private final ApiStores apiService;
    private PostXsBean postXsBean;
    private BigDecimal add;

    public AddorderPresenter(AddorderView view) {
        attachView( view);
        apiService = ApiManager.getInstance().getApiService(ApiStores.class);

    }

   public void  Addorder(String store_id,String name, String orderdate, String price, String xiaoshou, String mendian, String jhdate, String orderid, String addres, String tel,
                         String peijian, String zuofa, String remark, Item priceitem
                        , String uid, String xsid, String mdid, List<XsproductListMode.DataBean.ListBean> ddlist){


          if (name.equals("请输入客户名称")){
             mvpView.mytost("请输入客户名称");
              return;
          }
          if (orderdate.equals("请选择订单日期")){
             mvpView.mytost("请选择订单日期");
              return;
          }

         if (priceitem.getVisibility()== View.VISIBLE) {
             if (price.isEmpty()) {
                mvpView.mytost("请输入预付款金额");
                 return;
             }
         }
       if (xiaoshou.equals("请选择销售人员")){
          mvpView.mytost("请选择销售人员");
           return;
       }
       if (mendian.equals("请选择门店")){
          mvpView.mytost("请选择门店");
           return;
       }
       if (jhdate.equals("请选择交货日期")){
          mvpView.mytost("请选择交货日期");
           return;
       }

     /*  if (orderid.isEmpty()){
          mvpView.mytost("请输入订单编号");
           return;
       }*/
       if (addres.isEmpty()){
          mvpView.mytost("请输入收货地址");
           return;
       }
       if (tel.isEmpty()){
          mvpView.mytost("请输入联系方式");
           return;
       }
      /* if (peijian.isEmpty()){
          mvpView.mytost("请输入配件");
           return;
       }*/
      /* if (zuofa.isEmpty()){
          mvpView.mytost("请输入做法");
           return;
       }*/
    /*   if (remark.isEmpty()){
          mvpView.mytost("请输入备注");
           return;
       }*/


       Map<String,Object> map=new HashMap<>();
       map.put("store_id",store_id);
       map.put("order_detail",postXsBean);
       List<PostProduct> pro_list=new ArrayList();
       int total=0;
      // BigDecimal num1 = new BigDecimal(total);
       if (ddlist!=null) {
           for (XsproductListMode.DataBean.ListBean listBean : ddlist) {
               PostProduct postProduct = new PostProduct(listBean.getPro_id() + "", listBean.getPro_name(),
                       listBean.getColorid(), listBean.getColor(), listBean.getSeries(), listBean.getWood_name(),
                       listBean.getLeixingid(), listBean.getJine(), listBean.getCount(), listBean.getCost_price(),
                       listBean.getPrice(), listBean.getZhekoulv());
               pro_list.add(postProduct);
              total+=Double.parseDouble(listBean.getJine());

           }

           map.put("pro_list", pro_list);
       }


       //预付款
       if (priceitem.getVisibility()== View.VISIBLE) {
           postXsBean = new PostXsBean(store_id,uid,orderdate,price,xsid,mdid,jhdate,orderid
                   ,addres,tel,peijian,zuofa,remark,total+"");

       }else {
           postXsBean=new PostXsBean(store_id,uid,orderdate,"","-1",xsid,mdid,jhdate,orderid
                   ,addres,tel,peijian,zuofa,remark,total+"");
       }
       if (pro_list.size()>0) {
           OrderAdd orderAdd = new OrderAdd(store_id, postXsBean, pro_list);
         /*  Disposable subscribe = apiService.Sale_add(orderAdd)
                   .subscribeOn(Schedulers.newThread())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new Consumer<AddMode>() {
                       @Override
                       public void accept(AddMode addMode) throws Exception {
                          mvpView.addDataSuccess(addMode);
                       }
                   }, new Consumer<Throwable>() {
                       @Override
                       public void accept(Throwable throwable) throws Exception {
                          mvpView.addDataFail(throwable.getMessage());
                       }
                   });*/
           Disposable subscribe1 = apiService.Sale_add(orderAdd)
                   .compose(RxSchedulers.Obs_io_main())
                   .subscribe(addMode -> {
                      mvpView.addDataSuccess(addMode);
                   }, throwable -> {
                      mvpView.addDataFail(throwable.getMessage());
                   });
       }else {
          mvpView.mytost("请选择订单信息");
       }

   }


   //销售列表
   public void xsList(String store_id){
       Map<String,String> map=new HashMap<>();
       map.put("store_id",store_id);
      /* Disposable subscribe = apiService.orderSale(map)
               .subscribeOn(Schedulers.newThread())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Consumer<XsListMode>() {
                   @Override
                   public void accept(XsListMode xsListMode) throws Exception {
                     mvpView.getXsDataSuccess(xsListMode);
                   }
               }, new Consumer<Throwable>() {
                   @Override
                   public void accept(Throwable throwable) throws Exception {
                     mvpView.getXsDataFail(throwable.getMessage());
                   }
               });
*/
       Disposable subscribe1 = apiService.orderSale(map)
               .compose(RxSchedulers.Obs_io_main())
               .subscribe(xsListMode -> {
                  mvpView.getXsDataSuccess(xsListMode);
               }, throwable -> {
                  mvpView.getXsDataFail(throwable.getMessage());
               });

   }

   //门店列表
   public void mdList(String store_id){
       Map<String,String> map=new HashMap<>();
       map.put("store_id",store_id);
       /*Disposable subscribe = apiService.store(map)
               .subscribeOn(Schedulers.newThread())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Consumer<MdMode>() {
                   @Override
                   public void accept(MdMode mdMode) throws Exception {
                    mvpView.getMdDataSuccess(mdMode);
                   }
               }, new Consumer<Throwable>() {
                   @Override
                   public void accept(Throwable throwable) throws Exception {
                      mvpView.getMdDataFail(throwable.getMessage());
                   }
               });*/

       Disposable subscribe1 = apiService.store(map)
               .compose(RxSchedulers.Obs_io_main())
               .subscribe((mdMode -> {
                  mvpView.getMdDataSuccess(mdMode);
               }), throwable -> {
                  mvpView.getMdDataFail(throwable.getMessage());
               });


   }


}
