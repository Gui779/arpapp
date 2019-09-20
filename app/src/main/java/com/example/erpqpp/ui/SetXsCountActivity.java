package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.erpqpp.R;
import com.example.erpqpp.myview.Item;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设置销售数量
 * */
public class SetXsCountActivity extends BaseActivity implements TitleBarView.BtnClickListener{

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.add_name)
    Item addName;
    private String select;
    private String zdcount;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_count);
        ButterKnife.bind(this);



        Intent intent = getIntent();
        select = intent.getStringExtra("select");
        zdcount = intent.getStringExtra("zdcount");
        type = intent.getStringExtra("type");

         if (type.equals("数量")) {
             tbvTitlebar.setMainTitle("数量");
         }else if (type.equals("单价")){
             tbvTitlebar.setMainTitle("单价");
             addName.setName("单价");
             addName.setInputHint("请输入单价");
             addName.setInputtype();
         }
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setRightTitleText("确定");

        addName.setText(zdcount);


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

        if (type.equals("数量")) {
            if (trim.isEmpty()) {
                toastShow("请输入数量");
                return;
            }
            if (Integer.parseInt(trim) <= 0) {
                toastShow("数量不能小于1");
                return;
            }
        }else if (type.equals("单价")){
            if (trim.isEmpty()) {
                toastShow("请输入单价");
                return;
            }
            if (Double.parseDouble(trim) <= 0) {
                toastShow("单价不能小于1");
                return;
            }
        }

        if (!trim.isEmpty()) {
                Intent intent = getIntent();
                intent.putExtra("count",trim);
                intent.putExtra("select",select);
                setResult(001,intent);
                finish();

        }
    }
}
