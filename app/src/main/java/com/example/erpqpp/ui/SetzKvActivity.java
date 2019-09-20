package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.erpqpp.R;
import com.example.erpqpp.myview.Item;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设置折扣率
 */
public class SetzKvActivity extends BaseActivity implements TitleBarView.BtnClickListener{

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.add_name)
    Item addName;
    private String select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setz_kv);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        select = intent.getStringExtra("select");

        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setMainTitle("折扣率");
        tbvTitlebar.setRightTitleText("确定");
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
        String trim = addName.getText().trim();
        if (trim.isEmpty()) {
            toastShow("请输入折扣率");
            return;
        }
        if (Integer.parseInt(trim) <= 0) {
            toastShow("折扣率不能小于1");
            return;
        }

        Intent intent = getIntent();
        intent.putExtra("count",trim);
        intent.putExtra("select",select);
        setResult(001,intent);
        finish();
    }
}
