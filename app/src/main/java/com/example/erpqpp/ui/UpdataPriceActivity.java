package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.presenter.UpdataPricePresenter;
import com.example.erpqpp.mvp.view.UpdataPriceView;
import com.example.erpqpp.myview.Item;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 修改成本价
 */
public class UpdataPriceActivity extends MvpActivity<UpdataPricePresenter> implements UpdataPriceView, TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.add_region)
    Item addRegion;
    private String store_id;
    private String id;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata_price);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        store_id = intent.getStringExtra("store_id");
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");

        tbvTitlebar.setMainTitle(name+"成本价");
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setRightTitleText("确定");

    }

    @Override
    protected UpdataPricePresenter createPresenter() {
        return new UpdataPricePresenter(this);
    }

    @Override
    protected void initretry() {

    }

    @Override
    public void getDataSuccess(MgMode mgMode) {
        if (mgMode.getCode().equals("1")){
            toastShow(mgMode.getMsg());
            setResult(888);
            finish();
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

    @Override
    public void onClick(View v) {

    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
        if (name.equals("成品仓库")){
            mvpPresenter.UpdataCpPrice(store_id,addRegion.getText(),id);
        }else if (name.equals("已油漆仓库")){
            mvpPresenter.UpdataPrice(store_id,addRegion.getText(),id);
        }else if (name.equals("未磨仓库")){
           mvpPresenter.UpdataWmPrice(store_id,addRegion.getText(),id);
        }else if (name.equals("已磨仓库")){
            mvpPresenter.UpdataYmPrice(store_id,addRegion.getText(),id);
        }
    }
}
