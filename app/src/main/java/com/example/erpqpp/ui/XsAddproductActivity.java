package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.XsAddproductAdapter;
import com.example.erpqpp.mvp.mode.SelectXsDjMode;
import com.example.erpqpp.mvp.mode.XsproductListMode;
import com.example.erpqpp.mvp.presenter.XsAddproductPresenter;
import com.example.erpqpp.mvp.view.XsAddproductView;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 添加销售产品
 */
public class XsAddproductActivity extends MvpActivity<XsAddproductPresenter> implements  TitleBarView.BtnClickListener, XsAddproductView {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.addproduct_rc)
    RecyclerView addproductRc;
    @BindView(R.id.add_img)
    ImageView addImg;
    private String store_id;
    private XsAddproductAdapter xsAddproductAdapter;
    List<XsproductListMode.DataBean.ListBean> adaplist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xs_addproduct);
        ButterKnife.bind(this);
        
        initdata();
    }

    @Override
    protected XsAddproductPresenter createPresenter() {
        return new XsAddproductPresenter(this);
    }

    @Override
    protected void initretry() {

    }

    private void initdata() {

        store_id = (String) SPUtils.get(this, "store_id", "");

        tbvTitlebar.setMainTitle("添加产品");
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setRightTitleText("确定");

        addImg.setOnClickListener(this);


        xsAddproductAdapter = new XsAddproductAdapter(this,this);
        addproductRc.setAdapter(xsAddproductAdapter);
        addproductRc.setLayoutManager(new LinearLayoutManager(this));


        Intent intent = getIntent();
        List<XsproductListMode.DataBean.ListBean> ddlist = (List<XsproductListMode.DataBean.ListBean>) intent.getSerializableExtra("ddlist");
        if (ddlist!=null){
            adaplist.addAll(ddlist);
            xsAddproductAdapter.setList(adaplist);
            xsAddproductAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
        List<XsproductListMode.DataBean.ListBean> list = xsAddproductAdapter.getList();
        if (list.size()>0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getCount()==null){
                    toastShow("请输入数量");
                    return;
                }
                if (list.get(i).getColor()==null){
                    toastShow("请选择色号");
                    return;
                }
                if (list.get(i).getLeixing()==null){
                    toastShow("请选择类型");
                    return;
                }
                if (list.get(i).getZhekoulv()==null){
                    toastShow("请输入折扣率");
                    return;
                }
            }

            Intent intent = getIntent();
            intent.putExtra("name", "成品");
            intent.putExtra("list", (Serializable) list);
            setResult(002, intent);
            finish();
        }

       /* for (XsproductListMode.DataBean.ListBean listbean:list) {
            LinLog.lLog(listbean.toString());
            List<XsproductListMode.DataBean.ListBean.ProListBean> pro_list = listbean.getPro_list();
            for (int i = 0; i < pro_list.size(); i++) {
                LinLog.lLog(pro_list.get(i).getRate()+"----"+pro_list.get(i).getUnit_name());
            }
        }*/
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.add_img:
              Intent intent=new Intent(XsAddproductActivity.this,XsproductListActivity.class);
              startActivityForResult(intent,1010);
              break;
              default:break;
      }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1010&&resultCode==1012){
            XsproductListMode.DataBean.ListBean listBean = (XsproductListMode.DataBean.ListBean) data.getSerializableExtra("list");
            listBean.setDanjian("整套");
            adaplist.add(listBean);
            xsAddproductAdapter.setList(adaplist);
            xsAddproductAdapter.notifyDataSetChanged();
        }
        //数量
        if (requestCode==1011&&resultCode==001){
            String select = data.getStringExtra("select");
            String count = data.getStringExtra("count");
            if (select!=null&&count!=null){
                xsAddproductAdapter.getList().get(Integer.parseInt(select)).setCount(count);
                String zhekoulv = xsAddproductAdapter.getList().get(Integer.parseInt(select)).getZhekoulv();
                String price = xsAddproductAdapter.getList().get(Integer.parseInt(select)).getPrice();
                if (zhekoulv!=null){
                    BigDecimal f1 = new BigDecimal(zhekoulv);
                    BigDecimal f2 = new BigDecimal(price);
                    BigDecimal f3 = new BigDecimal(100);
                    BigDecimal danjia = f1.multiply(f2).divide(f3);
                    xsAddproductAdapter.getList().get(Integer.parseInt(select)).setDanjia(danjia+"");
                    String shuliang = xsAddproductAdapter.getList().get(Integer.parseInt(select)).getCount();
                    if (shuliang!=null) {
                        BigDecimal f4 = new BigDecimal(danjia+"");
                        BigDecimal f5 = new BigDecimal(shuliang);
                        xsAddproductAdapter.getList().get(Integer.parseInt(select)).setJine(f4.multiply(f5)+"");
                    }
                }
            }
            xsAddproductAdapter.notifyDataSetChanged();
        }
        //折扣率
        if (requestCode==1012&&resultCode==001){
            String select = data.getStringExtra("select");
            String count = data.getStringExtra("count");
            if (select!=null&&count!=null){
                xsAddproductAdapter.getList().get(Integer.parseInt(select)).setZhekoulv(count);
                String price = xsAddproductAdapter.getList().get(Integer.parseInt(select)).getPrice();
                if (price!=null){
                    BigDecimal f1 = new BigDecimal(price);
                    BigDecimal f2 = new BigDecimal(count);
                    BigDecimal f3 = new BigDecimal(100);
                    BigDecimal danjia = f1.multiply(f2).divide(f3);
                    xsAddproductAdapter.getList().get(Integer.parseInt(select)).setDanjia(f1.multiply(f2).divide(f3)+"");
                    String shuliang = xsAddproductAdapter.getList().get(Integer.parseInt(select)).getCount();
                    if (shuliang!=null){
                        BigDecimal f4 = new BigDecimal(danjia+"");
                        BigDecimal f5 = new BigDecimal(shuliang);
                        xsAddproductAdapter.getList().get(Integer.parseInt(select)).setJine(f4.multiply(f5)+"");
                    }
                }
            }
            xsAddproductAdapter.notifyDataSetChanged();
        }
        //类型
        if (requestCode==1013&&resultCode==001){
            String select = data.getStringExtra("select");
            String name = data.getStringExtra("name");
            String id = data.getStringExtra("id");
            if (select!=null&&name!=null){
                xsAddproductAdapter.getList().get(Integer.parseInt(select)).setLeixing(name);
                xsAddproductAdapter.getList().get(Integer.parseInt(select)).setLeixingid(id);
            }
            xsAddproductAdapter.notifyDataSetChanged();
        }

        //色号
        if (requestCode==1014&&resultCode==112){
            String select = data.getStringExtra("select");
            String color_id = data.getStringExtra("color_id");
            String name = data.getStringExtra("name");
            if (select!=null&&name!=null&&color_id!=null){
                xsAddproductAdapter.getList().get(Integer.parseInt(select)).setColor(name);
                xsAddproductAdapter.getList().get(Integer.parseInt(select)).setColorid(color_id);
            }
            xsAddproductAdapter.notifyDataSetChanged();
        }

        //单件
        if (requestCode==1015&&resultCode==001){
            String select = data.getStringExtra("select");
            List<SelectXsDjMode.DataBean.ProListBean>  djlist = (List<SelectXsDjMode.DataBean.ProListBean>) data.getSerializableExtra("djlist");
           if (select!=null) {
               xsAddproductAdapter.getList().get(Integer.parseInt(select)).setDanjian("组合");
               List<XsproductListMode.DataBean.ListBean.ProListBean> pro_list = xsAddproductAdapter.getList().get(Integer.parseInt(select)).getPro_list();
               for (int i = 0; i < pro_list.size(); i++) {
                   pro_list.get(i).setRate(djlist.get(i).getRate());
                   pro_list.get(i).setUnit_name(djlist.get(i).getUnit_name());
               }

           }
            xsAddproductAdapter.notifyDataSetChanged();
        }

    }
}
