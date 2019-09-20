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
 * 设置仓管退换货数量
 */
public class SetCgThActivity extends BaseActivity implements TitleBarView.BtnClickListener{

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.add_name)
    Item addName;
    private String select;
    private String zdcount;
    private String select1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_count);
        ButterKnife.bind(this);

        tbvTitlebar.setMainTitle("数量");
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setRightTitleText("确定");

        Intent intent = getIntent();
        select = intent.getStringExtra("select");
        zdcount = intent.getStringExtra("zdcount");
        select1 = intent.getStringExtra("select1");

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
        if (trim.isEmpty()){
            toastShow("请输入数量");
            return;
        }
        if (Integer.parseInt(trim)<=0){
            toastShow("数量不能小于1");
            return;
        }

        if (!trim.isEmpty()) {
            if (Integer.parseInt(trim) > Integer.parseInt(zdcount)) {
             toastShow("输入的数量不能大于订单数量");
            }else {
                Intent intent = getIntent();
                intent.putExtra("count",trim);
                intent.putExtra("select",select);
                intent.putExtra("select1",select1);
                setResult(001,intent);
                finish();

            }
        }
    }
}
