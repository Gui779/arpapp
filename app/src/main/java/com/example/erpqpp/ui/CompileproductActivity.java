package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.erpqpp.R;
import com.example.erpqpp.TestAdd;
import com.example.erpqpp.adapter.CompileproductAdapter;
import com.example.erpqpp.mvp.mode.CpdwMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.ProductMode;
import com.example.erpqpp.mvp.mode.SelectCpCzMode;
import com.example.erpqpp.mvp.presenter.CompileproductPresenter;
import com.example.erpqpp.mvp.view.CompileproductView;
import com.example.erpqpp.myview.Item;
import com.example.erpqpp.myview.SwipeItemLayout;
import com.example.erpqpp.myview.TitleBarView;
import com.example.erpqpp.utils.MyListToString;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * 编辑产品
 */
public class CompileproductActivity extends MvpActivity<CompileproductPresenter> implements CompileproductView, TitleBarView.BtnClickListener {


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
    @BindView(R.id.ns)
    NestedScrollView ns;

    private CompileproductAdapter compileproductAdapter;
    private ProductMode.DataBean dataBean;
    private String cost_price;
    private String unit_name;
    private StatusLayoutManager statusLayoutManager;
    private List<CpdwMode.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compileproduct);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {


        tbvTitlebar.setMainTitle("修改产品");
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setRightTitleText("确定");

        statusLayoutManager = setLayout(ns);
        statusLayoutManager.showLoadingLayout();

        Intent intent = getIntent();
        dataBean = (ProductMode.DataBean) intent.getSerializableExtra("dataBean");

        addName.setText(dataBean.getPro_name());
        addXilie.setText(dataBean.getSeries());
        addCaizhi.setContentText(dataBean.getWood_name());
        addBiaojia.setText(dataBean.getPrice());
        addChengbenjia.setText(dataBean.getCost_price());

        String store_id = (String) SPUtils.get(CompileproductActivity.this, "store_id", "");
        mvpPresenter.getCpdw(store_id, dataBean.getPro_id() + "");


        compileproductAdapter = new CompileproductAdapter(this, this);

        addcgproductRc.setAdapter(compileproductAdapter);
        addcgproductRc.setLayoutManager(new LinearLayoutManager(this));

        addcgproductRc.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));

        addImg.setOnClickListener(this);


        addCaizhi.setonclickListener(view -> {
            Intent intent1 = new Intent(CompileproductActivity.this, SelectCpCzActivity.class);
            startActivityForResult(intent1, 111);

        });
    }

    @Override
    protected CompileproductPresenter createPresenter() {
        return new CompileproductPresenter(this);
    }

    @Override
    protected void initretry() {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_img:
                //   addlist.add(new TestAdd("单件", "比例"));
                CpdwMode.DataBean dataBean = new CpdwMode.DataBean();
                dataBean.setUnit_name("单件");
                data.add(dataBean);
                compileproductAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

        List<CpdwMode.DataBean> list = compileproductAdapter.getList();
        List<String> danjianlist = new ArrayList<>();
        List<String> bililist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUnit_name().equals("单件")) {
                toastShow("请输入单件");
                return;
            }
           /* if (list.get(i).getRate()==0){
                toastShow("请输入比例");
                return;
            }
*/
            danjianlist.add(list.get(i).getUnit_name());
            bililist.add(list.get(i).getRate()+"");
        }

        if (danjianlist.size() > 0 ) {
            cost_price = MyListToString.setbanner(danjianlist);
        }
        if (bililist.size() > 0){
            unit_name = MyListToString.setbanner(bililist);

        }
        String store_id = (String) SPUtils.get(CompileproductActivity.this, "store_id", "");
        String admin_id = (String) SPUtils.get(CompileproductActivity.this, "admin_id", "");
        mvpPresenter.Compileproduct(store_id, admin_id, addName.getText().toString().trim(),
                addXilie.getText().toString().trim(),
                dataBean.getWood_id() + "",
                addBiaojia.getText().toString().trim(),
                addChengbenjia.getText().toString().trim(),
                cost_price, unit_name,
                addCaizhi.getContentText().toString().trim(),
                dataBean.getPro_id() + ""
        );
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2001 && resultCode == 2002) {
            if (data != null) {
                String subscript = data.getStringExtra("subscript");
                TestAdd testadd = (TestAdd) data.getSerializableExtra("testadd");
                List<CpdwMode.DataBean> list = compileproductAdapter.getList();
                if (subscript != null) {
                    list.get(Integer.parseInt(subscript)).setUnit_name(testadd.getName());
                   list.get(Integer.parseInt(subscript)).setRate(Integer.parseInt(testadd.getCount()));
                    compileproductAdapter.setList(list);
                    compileproductAdapter.notifyDataSetChanged();
                }

            }
        }
        if (requestCode == 111 && resultCode == 222) {
            if (data != null) {
                SelectCpCzMode.DataBean dataBean1 = (SelectCpCzMode.DataBean) data.getSerializableExtra("dataBean");
                addCaizhi.setContentText(dataBean1.getWood_name());
                dataBean.setWood_id(dataBean1.getWood_id());
                dataBean.setWood_name(dataBean1.getWood_name());
            }
        }
    }

    @Override
    public void getDataSuccess(MgMode mgMode) {
        toastShow(mgMode.getMsg());
        if (mgMode.getCode().equals("1")) {
            finish();
        }
    }

    @Override
    public void getDataFail(String msg) {
        LinLog.lLog(msg);
    }

    @Override
    public void getCpdwDataSuccess(CpdwMode cpdwMode) {
        statusLayoutManager.showSuccessLayout();
        data = cpdwMode.getData();
        if (data.size() > 0) {
            compileproductAdapter.setList(data);
            compileproductAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getCpdwDataFail(String msg) {
        LinLog.lLog(msg);
    }

    @Override
    public void mytoast(String msg) {
        toastShow(msg);
    }
}
