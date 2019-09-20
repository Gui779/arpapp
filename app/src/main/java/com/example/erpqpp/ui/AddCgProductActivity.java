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
import com.example.erpqpp.adapter.AddCgProductAdapter;
import com.example.erpqpp.mvp.mode.CpckMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.SelectCpCzMode;
import com.example.erpqpp.mvp.presenter.AddCgProductPresenter;
import com.example.erpqpp.mvp.view.AddCgProductView;
import com.example.erpqpp.myview.Item;
import com.example.erpqpp.myview.SwipeItemLayout;
import com.example.erpqpp.myview.TitleBarView;
import com.example.erpqpp.utils.MyListToString;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 添加仓管产品
 */
public class AddCgProductActivity extends MvpActivity<AddCgProductPresenter> implements AddCgProductView , TitleBarView.BtnClickListener{

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
    private List<TestAdd>  addlist = new ArrayList<>();;
    private AddCgProductAdapter addCgProductAdapter;
    private String cost_price;
    private String unit_name;
    private SelectCpCzMode.DataBean dataBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cg_product);
        ButterKnife.bind(this);
        initdata();

    }

    private void initdata() {

        tbvTitlebar.setMainTitle("添加");
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setRightTitleText("确定");

        dataBean = new SelectCpCzMode.DataBean();

        addCgProductAdapter = new AddCgProductAdapter(this,this);
        addCgProductAdapter.setList(addlist);

        addcgproductRc.setAdapter(addCgProductAdapter);
        addcgproductRc.setLayoutManager(new LinearLayoutManager(this));

        addcgproductRc.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));

        addImg.setOnClickListener(this);

        addCaizhi.setonclickListener(view -> {
            Intent intent1=new Intent(AddCgProductActivity.this,SelectCpCzActivity.class);
            startActivityForResult(intent1,111);

        });


    }

    @Override
    protected AddCgProductPresenter createPresenter() {
        return new AddCgProductPresenter(this);
    }

    @Override
    protected void initretry() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_img) {
            addlist.add(new TestAdd("单件", "比例"));
            addCgProductAdapter.notifyDataSetChanged();
        }
    }



    @Override
    public void mytoast(String msg) {
        toastShow(msg);
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
        List<TestAdd> list = addCgProductAdapter.getList();
        List<String> danjianlist=new ArrayList<>();
        List<String> bililist=new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            if (list.get(i).getName().equals("单件")){
                toastShow("请输入单件");
                return;
            }
            if (list.get(i).getCount().equals("比例")){
                toastShow("请输入比例");
                return;
            }
            danjianlist.add(list.get(i).getName());
            bililist.add(list.get(i).getCount());
        }

        if (danjianlist.size()>0&&bililist.size()>0) {
            cost_price = MyListToString.setbanner(danjianlist);
            unit_name = MyListToString.setbanner(bililist);
        }

        String store_id = (String) SPUtils.get(AddCgProductActivity.this, "store_id", "");
        String admin_id = (String) SPUtils.get(AddCgProductActivity.this, "admin_id", "");
        mvpPresenter.addCompileproduct(store_id,admin_id,addName.getText().trim(),
                addXilie.getText().trim(),
                dataBean.getWood_id()+"",
                addBiaojia.getText().trim(),
                addChengbenjia.getText().trim(),
                cost_price,unit_name,
                addCaizhi.getContentText().trim());

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2001&&resultCode==2002){
            if (data!=null){
                String subscript = data.getStringExtra("subscript");
                TestAdd testadd = (TestAdd) data.getSerializableExtra("testadd");
                List<TestAdd> list = addCgProductAdapter.getList();
                if (subscript!=null){
                    list.get(Integer.parseInt(subscript)).setName(testadd.getName());
                    list.get(Integer.parseInt(subscript)).setCount(testadd.getCount());
                    addCgProductAdapter.setList(addlist);
                    addCgProductAdapter.notifyDataSetChanged();
                }
            }
        }
        if (requestCode==111&&resultCode==222){
            if (data!=null){
                SelectCpCzMode.DataBean  dataBean1 = (SelectCpCzMode.DataBean) data.getSerializableExtra("dataBean");
                addCaizhi.setContentText(dataBean1.getWood_name());
                dataBean.setWood_id(dataBean1.getWood_id());
                dataBean.setWood_name(dataBean1.getWood_name());

            }
        }
    }
    @Override
    public void getDataSuccess(MgMode mgMode) {
        toastShow(mgMode.getMsg());
        if (mgMode.getCode().equals("1")){
            finish();
        }
    }

    @Override
    public void getDataFail(String msg) {
        LinLog.lLog(msg);
    }
}
