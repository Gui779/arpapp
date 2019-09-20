package com.example.erpqpp.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 仓管
 */
public class StorehouseActivity extends BaseActivity {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.chanpin)
    LinearLayout chanpin;
    @BindView(R.id.sehao)
    LinearLayout sehao;
    @BindView(R.id.caizhi)
    LinearLayout caizhi;
    @BindView(R.id.canggaun)
    LinearLayout canggaun;
    @BindView(R.id.zhekou)
    LinearLayout zhekou;
    @BindView(R.id.mugong)
    LinearLayout mugong;
    @BindView(R.id.damo)
    LinearLayout damo;
    @BindView(R.id.shangqi)
    LinearLayout shangqi;
    @BindView(R.id.tongji)
    LinearLayout tongji;
    @BindView(R.id.weimo)
    LinearLayout weimo;
    @BindView(R.id.yimo)
    LinearLayout yimo;
    @BindView(R.id.youqi)
    LinearLayout youqi;
    @BindView(R.id.chengpin)
    LinearLayout chengpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storehouse);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        tbvTitlebar.hideleft();
        tbvTitlebar.setMainTitle("常用功能");
        tbvTitlebar.setMainTitleColor(Color.WHITE);

        chanpin.setOnClickListener(this);
        sehao.setOnClickListener(this);
        caizhi.setOnClickListener(this);
        zhekou.setOnClickListener(this);
        tongji.setOnClickListener(this);
        canggaun.setOnClickListener(this);
        mugong.setOnClickListener(this);
        damo.setOnClickListener(this);
        shangqi.setOnClickListener(this);
        weimo.setOnClickListener(this);
        yimo.setOnClickListener(this);
        youqi.setOnClickListener(this);
        chengpin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          //产品管理
          case R.id.chanpin:
             startActivity(ProductActivity.class);
              break;
              //色号管理
          case R.id.sehao:
             startActivity(ColourManageActivity.class);
              break;
              //材质管理
          case R.id.caizhi:
             startActivity(TextureManageActivity.class);
              break;

              //折扣列表
          case R.id.zhekou:
             startActivity(DiscountActivity.class);
              break;
              //库存统计
          case R.id.tongji:
             startActivity(StatisticsActivity.class);
              break;
              //仓管列表
          case R.id.canggaun:
             startActivity(WarehouseActivity.class);
              break;
              //木工管理
          case R.id.mugong:
             startActivity(MgmanageActivity.class);
              break;
              //打磨管理
          case R.id.damo:
             startActivity(DmmanageActivity.class);
              break;
              //上漆管理
          case R.id.shangqi:
             startActivity(SqmanageActivity.class);
              break;
              //未磨仓库
          case R.id.weimo:
             startActivity(WmWarehouseActivity.class);
              break;
              //已磨仓库
          case R.id.yimo:
             startActivity(YmWarehouseActivity.class);
              break;
              //已油漆仓库
          case R.id.youqi:
             startActivity(YyqWarehouseActivity.class);
              break;
              //成品仓库
          case R.id.chengpin:
             startActivity(CpWarehouseActivity.class);
              break;
      }
    }
}
