package com.example.erpqpp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.myview.TitleBarView;
import com.example.erpqpp.ui.AddclientActivity;
import com.example.erpqpp.ui.AddorderActivity;
import com.example.erpqpp.ui.ManagementActivity;
import com.example.erpqpp.ui.MarkettjActivity;
import com.example.erpqpp.ui.MarkettjDetailsActivity;
import com.example.erpqpp.ui.SendGoodsActivity;
import com.example.erpqpp.ui.StorehouseActivity;
import com.example.erpqpp.ui.WarehouseStatisticsActivity;
import com.lbb.mvplibrary.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 常用功能
 */
public class FunctionFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    Unbinder unbinder;
    @BindView(R.id.function_manage)
    LinearLayout functionManage;
    @BindView(R.id.add_function)
    LinearLayout addFunction;
    @BindView(R.id.statistics)
    LinearLayout statistics;
    @BindView(R.id.add_order)
    LinearLayout addOrder;
    @BindView(R.id.send_goods)
    LinearLayout sendGoods;
    @BindView(R.id.receive_goods)
    LinearLayout receiveGoods;
    @BindView(R.id.receive)
    LinearLayout receive;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_function, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initview();
        return inflate;
    }

    private void initview() {
        tbvTitlebar.hideleft();
        tbvTitlebar.setMainTitle("常用功能");
        tbvTitlebar.setMainTitleColor(Color.WHITE);

        functionManage.setOnClickListener(this);
        addFunction.setOnClickListener(this);
        statistics.setOnClickListener(this);
        addOrder.setOnClickListener(this);
        sendGoods.setOnClickListener(this);
        receiveGoods.setOnClickListener(this);
        receive.setOnClickListener(this);

        functionManage.setOnClickListener(v -> {
               startActivity(ManagementActivity.class);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onClick(View v) {
         switch (v.getId()){
             //客户管理
             case R.id.function_manage:
               //   startActivity(ManagementActivity.class);
                 break;
                 //添加客户
             case R.id.add_function:
                  startActivity(AddclientActivity.class);
                 break;
                 //销售统计
             case R.id.statistics:
                  startActivity(MarkettjActivity.class);
                 break;
                 //添加订单
             case R.id.add_order:
                  startActivity(AddorderActivity.class);
                 break;
                 //发货
             case R.id.send_goods:
                 Bundle bundle1=new Bundle();
                 bundle1.putString("name","发货");
                 startActivity(SendGoodsActivity.class,bundle1);
                 break;
                 //收货
             case R.id.receive_goods:
                 Bundle bundle=new Bundle();
                 bundle.putString("name","收货");
                  startActivity(SendGoodsActivity.class,bundle);
                 break;
                 //仓管
             case R.id.receive:
                 startActivity(WarehouseStatisticsActivity.class);
              //    startActivity(StorehouseActivity.class);
                 break;
         }
    }
}
