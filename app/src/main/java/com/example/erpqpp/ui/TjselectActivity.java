package com.example.erpqpp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 套件选择
 */
public class TjselectActivity extends BaseActivity implements TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.taojian)
    TextView taojian;
    @BindView(R.id.danjian)
    TextView danjian;
    private String select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tjselect);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        select = intent.getStringExtra("select");

        tbvTitlebar.setMainTitle("套件选择");
        tbvTitlebar.setTitleBarListener(this);

        taojian.setOnClickListener(this);
        danjian.setOnClickListener(this);

    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.taojian:
              Intent intent=new Intent();
              intent.putExtra("select",select);
              intent.putExtra("name","套件");
              intent.putExtra("isselect",false);
              setResult(105,intent);
              finish();
              break;

          case R.id.danjian:
              Intent intent1=new Intent();
              intent1.putExtra("select",select);
              intent1.putExtra("name","单件");
              intent1.putExtra("isselect",true);
              setResult(105,intent1);
              finish();
              break;
      }
    }
}
