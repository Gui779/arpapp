package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.XzCzMode;
import com.example.erpqpp.mvp.presenter.UpManagePresenter;
import com.example.erpqpp.mvp.view.UpManageView;
import com.example.erpqpp.myview.Item;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 修改材质
 */
public class UpManageActivity extends MvpActivity<UpManagePresenter> implements UpManageView, TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.bianhao)
    Item bianhao;
    private String wood_id;
    private String title;
    private String store_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_manage);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleText("确定");
        tbvTitlebar.setRightTitleColor(Color.WHITE);

        store_id = (String) SPUtils.get(this, "store_id", "");

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        wood_id = intent.getStringExtra("wood_id");
       String wood_name = intent.getStringExtra("wood_name");
        if (title !=null){
            tbvTitlebar.setMainTitle(title);
        }
        if (wood_name!=null){
            bianhao.setText(wood_name);
        }
    }

    @Override
    protected UpManagePresenter createPresenter() {
        return new UpManagePresenter(this);
    }

    @Override
    protected void initretry() {

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
        if (title.equals("修改材质")) {
            mvpPresenter.upManage(bianhao.getText().trim(), wood_id);
        }else if (title.equals("添加材质")){
          mvpPresenter.addManage(bianhao.getText().trim(),store_id);
        }
    }
}
