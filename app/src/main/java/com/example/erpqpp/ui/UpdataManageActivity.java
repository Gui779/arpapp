package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.XzCzMode;
import com.example.erpqpp.mvp.presenter.UpdataManagePresenter;
import com.example.erpqpp.mvp.view.UpdataManageView;
import com.example.erpqpp.myview.Item;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 修改色号
 */
public class UpdataManageActivity extends MvpActivity<UpdataManagePresenter> implements UpdataManageView, TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.bianhao)
    Item bianhao;
    @BindView(R.id.add_biaojia)
    Item addBiaojia;
    private String color_id;
    private String title;
    private String store_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata_manage);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleText("确定");
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        color_id = intent.getStringExtra("color_id");
       String name = intent.getStringExtra("name");
       String num = intent.getStringExtra("num");
        if (title !=null){
            tbvTitlebar.setMainTitle(title);
        }
        if (name!=null){
            addBiaojia.setText(name);
        }
        if (num!=null){
            bianhao.setText(num);
        }

        store_id = (String) SPUtils.get(this, "store_id", "");
    }

    @Override
    protected UpdataManagePresenter createPresenter() {
        return new UpdataManagePresenter(this);
    }

    @Override
    protected void initretry() {

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
        if(title.equals("修改色号")){
            mvpPresenter.updataManage(bianhao.getText().trim(),addBiaojia.getText().trim(),color_id);
        }else if (title.equals("添加色号")){
           mvpPresenter.addColor(bianhao.getText().trim(),addBiaojia.getText().trim(),store_id);
        }
    }

    @Override
    public void getDataSuccess(XzCzMode xzCzMode) {
     if (xzCzMode.getCode()==1){
         finish();
     }
     toastShow(xzCzMode.getMessage());
    }

    @Override
    public void getDataFail(String msg) {
        LinLog.lLog(msg);
    }

    @Override
    public void toast(String msg) {
        toastShow(msg);
    }
}
