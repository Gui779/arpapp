package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.AddYqproductAdapter;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.SelectMgMode;
import com.example.erpqpp.mvp.presenter.AddYqproductPresenter;
import com.example.erpqpp.mvp.view.AddYqproductView;
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
 * 填加产品
 * */
public class AddYqproductActivity extends MvpActivity<AddYqproductPresenter> implements AddYqproductView, TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.addproduct_rc)
    RecyclerView addproductRc;
    @BindView(R.id.add_img)
    ImageView addImg;
    private String store_id;
    private String title;
    private AddYqproductAdapter addYqproductAdapter;
    List<SelectMgMode.DataBean.ProductAndUnitListBean> adaplist=new ArrayList<>();
    private String admin_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_yqproduct);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {

        store_id = (String) SPUtils.get(this, "store_id", "");
        admin_id = (String) SPUtils.get(this, "admin_id", "");

        tbvTitlebar.setMainTitle("添加产品");
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setRightTitleText("确定");

        title = getIntent().getStringExtra("type");

        addImg.setOnClickListener(this);

        addYqproductAdapter = new AddYqproductAdapter(this, this);
        addproductRc.setLayoutManager(new LinearLayoutManager(this));
        addproductRc.setAdapter(addYqproductAdapter);

        if (title.equals("已磨仓库")||title.equals("未磨仓库")){
            addYqproductAdapter.setIscolor(false);
        }else {
            addYqproductAdapter.setIscolor(true);
        }

    }



    @Override
    protected AddYqproductPresenter createPresenter() {
        return new AddYqproductPresenter(this);
    }

    @Override
    protected void initretry() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_img:
                    Intent intent=new Intent(AddYqproductActivity.this,SelectProductActivity.class);
                    startActivityForResult(intent,1001);
                break;
        }
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

        List<String> pro_idlist = new ArrayList<>();
        List<String> unit_idlist = new ArrayList<>();
        List<String> wood_idlist = new ArrayList<>();
        List<String> unit_numlist = new ArrayList<>();
        List<String> pro_namelist = new ArrayList<>();
        List<String> unit_namelist = new ArrayList<>();
        List<String> wood_namelist = new ArrayList<>();
        List<String> warehouse_idlist = new ArrayList<>();
        List<String> color_idlist = new ArrayList<>();
        List<String> color_namelist = new ArrayList<>();
        if (adaplist.size() > 0) {
            for (int i = 0; i < adaplist.size(); i++) {
                if (adaplist.get(i).getCount() == null) {
                    toastShow("请输入单件数量");
                    return;
                }

                if (title.equals("已油漆仓库")||title.equals("成品仓库")){

                if (adaplist.get(i).getColorname()==null){
                    toastShow("请选择色号");
                    return;
                }
                }

                if (adaplist.get(i).getCangku()==null){
                    toastShow("请选择收货仓库");
                    return;
                }

                pro_idlist.add(adaplist.get(i).getPro_id() + "");
                unit_idlist.add(adaplist.get(i).getUnit_id() + "");
                wood_idlist.add(adaplist.get(i).getWood_id() + "");
                unit_numlist.add(adaplist.get(i).getCount());
                pro_namelist.add(adaplist.get(i).getPro_name());
                unit_namelist.add(adaplist.get(i).getUnit_name());
                wood_namelist.add(adaplist.get(i).getWood_name());
                warehouse_idlist.add(adaplist.get(i).getCangkuid());
                color_idlist.add(adaplist.get(i).getColorid());
                color_namelist.add(adaplist.get(i).getColorname());
            }
            String pro_id = MyListToString.setbanner(pro_idlist);
            String unit_id = MyListToString.setbanner(unit_idlist);
            String wood_id = MyListToString.setbanner(wood_idlist);
            String unit_num = MyListToString.setbanner(unit_numlist);
            String pro_name = MyListToString.setbanner(pro_namelist);
            String unit_name = MyListToString.setbanner(unit_namelist);
            String wood_name = MyListToString.setbanner(wood_namelist);
            String warehouse_id = MyListToString.setbanner(warehouse_idlist);
            String color_id = MyListToString.setbanner(color_idlist);
            String color_name = MyListToString.setbanner(color_namelist);

             if (title.equals("已油漆仓库")) {
                 mvpPresenter.Yqadd(store_id, admin_id, warehouse_id, pro_id, pro_name,
                         unit_id, unit_name, unit_num, wood_id, wood_name, color_id, color_name);
             }else if (title.equals("成品仓库")){
                 mvpPresenter.Cpadd(store_id, admin_id, warehouse_id, pro_id, pro_name,
                         unit_id, unit_name, unit_num, wood_id, wood_name, color_id, color_name);
             }else if (title.equals("已磨仓库")){
                 mvpPresenter.Ymadd(store_id, admin_id, warehouse_id, pro_id, pro_name,
                         unit_id, unit_name, unit_num, wood_id, wood_name, color_id, color_name);
             }else if (title.equals("未磨仓库")){
                 mvpPresenter.Wmadd(store_id, admin_id, warehouse_id, pro_id, pro_name,
                         unit_id, unit_name, unit_num, wood_id, wood_name, color_id, color_name);
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
                addYqproductAdapter.setList(adaplist);
                addYqproductAdapter.notifyDataSetChanged();
            }
        }
        if (requestCode==123&&resultCode==456){
            String count = data.getStringExtra("count");
            String select = data.getStringExtra("select");
            adaplist.get(Integer.parseInt(select)).setCount(count);
            addYqproductAdapter.notifyDataSetChanged();
        }
        if (requestCode==123&&resultCode==111){
            String name = data.getStringExtra("name");
            String select = data.getStringExtra("select");
            String unitid = data.getStringExtra("unit_id");
            adaplist.get(Integer.parseInt(select)).setUnit_id(Integer.parseInt(unitid));
            adaplist.get(Integer.parseInt(select)).setUnit_name(name);
            addYqproductAdapter.notifyDataSetChanged();
        }

        if (requestCode==123&&resultCode==112){
            String name = data.getStringExtra("name");
            String select = data.getStringExtra("select");
            String colorid = data.getStringExtra("color_id");
            adaplist.get(Integer.parseInt(select)).setColorid(colorid);
            adaplist.get(Integer.parseInt(select)).setColorname(name);
            addYqproductAdapter.notifyDataSetChanged();
        }

        if (requestCode==123&&resultCode==114){
            String name = data.getStringExtra("name");
            String select = data.getStringExtra("select");
            String warehouse_id = data.getStringExtra("warehouse_id");
            adaplist.get(Integer.parseInt(select)).setCangkuid(warehouse_id);
            adaplist.get(Integer.parseInt(select)).setCangku(name);
            addYqproductAdapter.notifyDataSetChanged();
        }

        if (requestCode==123&&resultCode==105){
            String name = data.getStringExtra("name");
            String select = data.getStringExtra("select");
            boolean isselect = data.getBooleanExtra("isselect",false);
            adaplist.get(Integer.parseInt(select)).setTjname(name);
            adaplist.get(Integer.parseInt(select)).setIsselect(isselect);
            addYqproductAdapter.notifyDataSetChanged();
        }



    }

    @Override
    public void getDataSuccess(MgMode mgMode) {
        if (mgMode.getCode().equals("1")) {
            toastShow(mgMode.getMsg());
        if (title.equals("已油漆仓库")){
            startActivity(YyqWarehouseActivity.class);
            AppManager.getInstance().killActivity(AddActivity.class);
            AppManager.getInstance().killActivity(YyqWarehouseActivity.class);
            finish();
        } else if (title.equals("成品仓库")){
            startActivity(CpWarehouseActivity.class);
            AppManager.getInstance().killActivity(AddActivity.class);
            AppManager.getInstance().killActivity(CpWarehouseActivity.class);
            finish();
        }else if (title.equals("已磨仓库")){
            startActivity(YmWarehouseActivity.class);
            AppManager.getInstance().killActivity(AddActivity.class);
            AppManager.getInstance().killActivity(YmWarehouseActivity.class);
            finish();
        }else if (title.equals("未磨仓库")){
            startActivity(WmWarehouseActivity.class);
            AppManager.getInstance().killActivity(AddActivity.class);
            AppManager.getInstance().killActivity(WmWarehouseActivity.class);
            finish();
        }
            }
        else {
            toastShow(mgMode.getMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        LinLog.lLog(msg);
    }
}
