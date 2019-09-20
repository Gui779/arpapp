package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.erpqpp.R;
import com.example.erpqpp.TestAdd;
import com.example.erpqpp.adapter.AddglproductAdapter;
import com.example.erpqpp.adapter.CompileproductAdapter;
import com.example.erpqpp.mvp.mode.CzMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.presenter.AddglproductPresenter;
import com.example.erpqpp.mvp.view.AddglproductView;
import com.example.erpqpp.mvp.view.GetCzView;
import com.example.erpqpp.myview.Item;
import com.example.erpqpp.myview.SwipeItemLayout;
import com.example.erpqpp.myview.TitleBarView;
import com.example.erpqpp.utils.MyListToString;
import com.lbb.mvplibrary.app.AppManager;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 新增管理产品
 */
public class AddglproductActivity extends MvpActivity<AddglproductPresenter> implements AddglproductView,TitleBarView.BtnClickListener{

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.add_name)
    Item addName;
    @BindView(R.id.add_xilie)
    Item addXilie;
    @BindView(R.id.add_caizhi)
    Item addCaizhi;
    @BindView(R.id.add_biaojia)
    Item addBiaojia;
    @BindView(R.id.add_chengbenjia)
    Item addChengbenjia;
    @BindView(R.id.addcgproduct_rc)
    RecyclerView addcgproductRc;
    @BindView(R.id.add_img)
    ImageView addImg;
    List<TestAdd> addlist = new ArrayList<>();
    private AddglproductAdapter addglproductAdapter;
    private String title;
    private List<CzMode.DataBean> czModeData;
    private String select;
    private int wood_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addglproduct);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        tbvTitlebar.setMainTitle(title);
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setRightTitleText("确定");

        addglproductAdapter = new AddglproductAdapter(this,this);
        addglproductAdapter.setList(addlist);

        addcgproductRc.setAdapter(addglproductAdapter);
        addcgproductRc.setLayoutManager(new LinearLayoutManager(this));

        addcgproductRc.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));

        addImg.setOnClickListener(this);

        addCaizhi.setonclickListener(view -> {
            Intent intent1 =new Intent(AddglproductActivity.this, GetCzActivity.class);
            startActivityForResult(intent1,2001);
        });
    }

    @Override
    protected AddglproductPresenter createPresenter() {
        return new AddglproductPresenter(this);
    }

    @Override
    protected void initretry() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_img:
                addlist.add(new TestAdd("材质名称","材质数量"));
                addglproductAdapter.notifyDataSetChanged();
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
        List<TestAdd> list = addglproductAdapter.getList();
        List<String> namelist=new ArrayList<>();
        List<String> countlist=new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            if (list.get(i).getName().equals("材质名称")){
                toastShow("请输入材质名称");
                return;
            }
            if (list.get(i).getCount().equals("材质数量")){
                toastShow("请输入材质数量");
                return;
            }
            namelist.add(list.get(i).getName());
            countlist.add(list.get(i).getCount());
        }

        if (select!=null) {
            wood_id = czModeData.get(Integer.parseInt(select)).getWood_id();
        }
        String unit_name = MyListToString.setbanner(namelist);
        String rate = MyListToString.setbanner(countlist);
        String store_id = (String) SPUtils.get(this, "store_id", "");
        String admin_id = (String) SPUtils.get(this, "admin_id", "");

          mvpPresenter.Addglproduct(store_id,admin_id,addName.getText().toString().trim(),
                  addXilie.getText().toString().trim(), addCaizhi.getContentText().toString().trim(),wood_id+"",
                  addBiaojia.getText().toString().trim(),addChengbenjia.getText().toString().trim(),
                  unit_name,rate);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2001&&resultCode==2002){
            if (data!=null){
                String subscript = data.getStringExtra("subscript");
                TestAdd testadd = (TestAdd) data.getSerializableExtra("testadd");
                List<TestAdd> list = addglproductAdapter.getList();
                if (subscript!=null){
                    list.get(Integer.parseInt(subscript)).setName(testadd.getName());
                    list.get(Integer.parseInt(subscript)).setCount(testadd.getCount());
                    addglproductAdapter.setList(addlist);
                    addglproductAdapter.notifyDataSetChanged();
                }

            }
        }
        if (requestCode==2001&&resultCode==111){
            String name = data.getStringExtra("name");
            select = data.getStringExtra("select");
            czModeData = (List<CzMode.DataBean>) data.getSerializableExtra("camode");
            addCaizhi.setContentText(name);

        }
    }


    @Override
    public void getDataSuccess(MgMode mgMode) {
        if (mgMode.getCode().equals("1")){
            toastShow(mgMode.getMsg());
            if (title.equals("已油漆仓库")){
                startActivity(YyqWarehouseActivity.class);
                AppManager.getInstance().killActivity(AddActivity.class);
                AppManager.getInstance().killActivity(YyqWarehouseActivity.class);
                finish();
            }else if (title.equals("成品仓库")){
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
            }else if (title.equals("木工管理")){
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

    @Override
    public void mytoast(String msg) {
        toastShow(msg);
    }
}
