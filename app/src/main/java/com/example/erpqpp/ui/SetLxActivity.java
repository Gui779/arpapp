package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设置类型
 */
public class SetLxActivity extends BaseActivity implements TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.chengpin)
    TextView chengpin;
    @BindView(R.id.mgbcp)
    TextView mgbcp;
    @BindView(R.id.dmbcp)
    TextView dmbcp;
    private String select;
    private String name;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_lx);
        ButterKnife.bind(this);

         intent = getIntent();
        select = intent.getStringExtra("select");

        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setMainTitle("选择类型");
        chengpin.setOnClickListener(this);
        mgbcp.setOnClickListener(this);
        dmbcp.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){

         case R.id.chengpin:
             intent.putExtra("name", "成品");
             intent.putExtra("select", select);
             intent.putExtra("id", 3+"");
             setResult(001, intent);
             finish();
             break;
         case R.id.mgbcp:
             intent.putExtra("name", "木工半成品");
             intent.putExtra("select", select);
             intent.putExtra("id", 1+"");
             setResult(001, intent);
             finish();
             break;
         case R.id.dmbcp:
             intent.putExtra("name", "打磨半成品");
             intent.putExtra("select", select);
             intent.putExtra("id", 2+"");
             setResult(001, intent);
             finish();
             break;
     }
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {


    }
}
