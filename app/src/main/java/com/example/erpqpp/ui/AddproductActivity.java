package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.AddproductAdapter;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.SelectMgMode;
import com.example.erpqpp.mvp.presenter.AddproductPresenter;
import com.example.erpqpp.mvp.view.AddproductView;
import com.example.erpqpp.myview.TitleBarView;
import com.example.erpqpp.utils.MyListToString;
import com.lbb.mvplibrary.app.AppManager;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 添加产品
 */
public class AddproductActivity extends MvpActivity<AddproductPresenter> implements AddproductView, TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.add_img)
    ImageView addImg;
    @BindView(R.id.addproduct_rc)
    RecyclerView addproductRc;
    private AddproductAdapter addproductAdapter;
    List<SelectMgMode.DataBean.ProductAndUnitListBean> adaplist=new ArrayList<>();
    private String title;
    private String store_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {

        store_id = (String) SPUtils.get(this, "store_id", "");
        
        tbvTitlebar.setMainTitle("添加产品");
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setRightTitleText("确定");

        title = getIntent().getStringExtra("type");

        addImg.setOnClickListener(this);

        addproductAdapter = new AddproductAdapter(this,this);
        addproductRc.setLayoutManager(new LinearLayoutManager(this));
        addproductRc.setAdapter(addproductAdapter);

    }

    @Override
    protected AddproductPresenter createPresenter() {
        return new AddproductPresenter(this);
    }

    @Override
    protected void initretry() {

    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.add_img:
              if (title.equals("木工管理")){
                  Intent intent=new Intent(AddproductActivity.this,SelectProductActivity.class);
                  startActivityForResult(intent,1001);
              }else if (title.equals("打磨管理")){
                  Intent intent=new Intent(AddproductActivity.this,SelectDmProductActivity.class);
                  startActivityForResult(intent,1001);
              }else if (title.equals("上漆管理")){
                  Intent intent=new Intent(AddproductActivity.this,SelectSqProductActivity.class);
                  startActivityForResult(intent,1001);
              }
             break;
              default:break;
     }
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
        List<String> pro_idlist=new ArrayList<>();
        List<String> unit_idlist=new ArrayList<>();
        List<String> wood_idlist=new ArrayList<>();
        List<String> unit_numlist=new ArrayList<>();
        List<String> pro_namelist=new ArrayList<>();
        List<String> unit_namelist=new ArrayList<>();
        List<String> wood_namelist=new ArrayList<>();
         if (adaplist.size()>0) {
             for (int i = 0; i < adaplist.size(); i++) {
                 if (adaplist.get(i).getCount()==null){
                     toastShow("请输入单件数量");
                     return;
                 }
                 pro_idlist.add(adaplist.get(i).getPro_id()+"");
                 unit_idlist.add(adaplist.get(i).getUnit_id()+"");
                 wood_idlist.add(adaplist.get(i).getWood_id()+"");
                 unit_numlist.add(adaplist.get(i).getCount());
                 pro_namelist.add(adaplist.get(i).getPro_name());
                 unit_namelist.add(adaplist.get(i).getUnit_name());
                 wood_namelist.add(adaplist.get(i).getWood_name());
             }
             String pro_id = MyListToString.setbanner(pro_idlist);
             String unit_id = MyListToString.setbanner(unit_idlist);
             String wood_id = MyListToString.setbanner(wood_idlist);
             String unit_num = MyListToString.setbanner(unit_numlist);
             String pro_name = MyListToString.setbanner(pro_namelist);
             String unit_name = MyListToString.setbanner(unit_namelist);
             String wood_name = MyListToString.setbanner(wood_namelist);

             LinLog.lLog(pro_id+"---"+unit_id+"---"+wood_id+"--"+unit_num+"---"+
                     pro_name+"---"+unit_name+"---"+wood_name);
             if (title.equals("木工管理")){
             mvpPresenter.addproduct(store_id,pro_id,unit_id,wood_id,unit_num,
                     pro_name,unit_name,wood_name);
             }else if (title.equals("打磨管理")){
                 mvpPresenter.addDmproduct(store_id,pro_id,unit_id,wood_id,unit_num,
                         pro_name,unit_name,wood_name);
             }else if (title.equals("上漆管理")){
                 mvpPresenter.addSqproduct(store_id,pro_id,unit_id,wood_id,unit_num,
                         pro_name,unit_name,wood_name);
             }


         }else {
             toastShow("请至少添加一个产品");
         }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1001&&resultCode==1002){
            if (data!=null){
                SelectMgMode.DataBean.ProductAndUnitListBean list = (SelectMgMode.DataBean.ProductAndUnitListBean) data.getSerializableExtra("list");
                adaplist.add(list);
                addproductAdapter.setList(adaplist);
                addproductAdapter.notifyDataSetChanged();
            }
        }
        if (requestCode==123&&resultCode==456){
            String count = data.getStringExtra("count");
            String select = data.getStringExtra("select");
            adaplist.get(Integer.parseInt(select)).setCount(count);
            addproductAdapter.notifyDataSetChanged();
        }
        if (requestCode==123&&resultCode==111){
            String name = data.getStringExtra("name");
            String select = data.getStringExtra("select");
            String unitid = data.getStringExtra("unit_id");
            adaplist.get(Integer.parseInt(select)).setUnit_id(Integer.parseInt(unitid));
            adaplist.get(Integer.parseInt(select)).setUnit_name(name);
            addproductAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void getDataSuccess(MgMode mgMode) {
         if (mgMode.getCode().equals("1")){
             if (title.equals("木工管理")){
             startActivity(MgmanageActivity.class);
             AppManager.getInstance().killActivity(AddActivity.class);
             AppManager.getInstance().killActivity(MgmanageActivity.class);
             finish();
             }else if (title.equals("打磨管理")){
                 startActivity(DmmanageActivity.class);
                 AppManager.getInstance().killActivity(AddActivity.class);
                 AppManager.getInstance().killActivity(DmmanageActivity.class);
                 finish();
             }else if (title.equals("上漆管理")){
                 startActivity(SqmanageActivity.class);
                 AppManager.getInstance().killActivity(AddActivity.class);
                 AppManager.getInstance().killActivity(SqmanageActivity.class);
                 finish();
             }
         }else {
             toastShow(mgMode.getMsg());
         }
    }

    @Override
    public void getDataFail(String msg) {
        LinLog.lLog(msg);
    }
}
