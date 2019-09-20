package com.example.erpqpp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.erpqpp.R;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 添加
 */
public class AddActivity extends BaseActivity implements TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.add_product)
    RelativeLayout addProduct;
    @BindView(R.id.add_increase)
    RelativeLayout addIncrease;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        tbvTitlebar.setMainTitle(title);
        tbvTitlebar.setTitleBarListener(this);

        addProduct.setOnClickListener(this);
        addIncrease.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         //添加产品
         case R.id.add_product:
             if (title.equals("已油漆仓库")){
                 Bundle bundle1 = new Bundle();
                 bundle1.putString("type", title);
                 startActivity(AddYqproductActivity.class, bundle1);
             }else if (title.equals("成品仓库")){
                 Bundle bundle1 = new Bundle();
                 bundle1.putString("type", title);
                 startActivity(AddYqproductActivity.class, bundle1);
             }else if (title.equals("未磨仓库")){
                 Bundle bundle1 = new Bundle();
                 bundle1.putString("type", title);
                startActivity(AddYqproductActivity.class, bundle1);
             }else if (title.equals("已磨仓库")){
                 Bundle bundle1 = new Bundle();
                 bundle1.putString("type", title);
                  startActivity(AddYqproductActivity.class, bundle1);
             }else {
                 Bundle bundle1 = new Bundle();
                 bundle1.putString("type", title);
                 startActivity(AddproductActivity.class, bundle1);
             }
             break;
             //新增产品
         case R.id.add_increase:
             Bundle bundle=new Bundle();
             bundle.putString("title",title);
             startActivity(AddglproductActivity.class,bundle);
             break;

             default:break;
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
