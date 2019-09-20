package com.example.erpqpp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.OrderMsgAdapter;
import com.example.erpqpp.mvp.mode.OrderMsgMode;
import com.example.erpqpp.mvp.presenter.OrderMsgPresenter;
import com.example.erpqpp.mvp.view.OrderMsgView;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * 订单消息
 */
public class OrderMsgActivity extends MvpActivity<OrderMsgPresenter> implements OrderMsgView , TitleBarView.BtnClickListener{

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.ordermsg_rc)
    RecyclerView ordermsgRc;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int page = 1;
    private String store_id;
    private StatusLayoutManager statusLayoutManager;
    private OrderMsgAdapter orderMsgAdapter;
    private List<OrderMsgMode.DataBean> data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_msg);
        ButterKnife.bind(this);
        initdata();
        addListener();
    }

    private void initdata() {
        store_id = (String) SPUtils.get(this, "store_id", "");

        statusLayoutManager = setLayout(refreshLayout);
        statusLayoutManager.showLoadingLayout();

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        if (type.equals("0")){
            tbvTitlebar.setMainTitle("订单消息");
        }else if (type.equals("1")){
            tbvTitlebar.setMainTitle("生产基地");
        }


        tbvTitlebar.setTitleBarListener(this);

        orderMsgAdapter = new OrderMsgAdapter(this);
        ordermsgRc.setAdapter(orderMsgAdapter);
        ordermsgRc.setLayoutManager(new LinearLayoutManager(this));

        orderMsgAdapter.huidiao(new OrderMsgAdapter.Itemclick() {
            @Override
            public void itemclick(View view, int position) {
                int status = data.get(position).getStatus();
                Bundle bundle=new Bundle();
                bundle.putString("status",status+"");
                startActivity(WarehouseActivity.class,bundle);
                finish();
            }
        });

    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.News(store_id,page+"");
    }

    @Override
    protected OrderMsgPresenter createPresenter() {
        return new OrderMsgPresenter(this);
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

    }


    @Override
    public void getDataSuccess(OrderMsgMode orderMsgMode) {
       statusLayoutManager.showSuccessLayout();
       if(orderMsgMode.getCode()==1) {
           data = orderMsgMode.getData();
           if (data.size() > 0) {
               if (page == 1) {
                   orderMsgAdapter.setList(data);
                   orderMsgAdapter.notifyDataSetChanged();
               } else {
                   orderMsgAdapter.getList().addAll(data);
                   orderMsgAdapter.notifyDataSetChanged();
               }
           } else {
               if (page == 1 && data.size() == 0) {
                   statusLayoutManager.showEmptyLayout();
               } else {
                   page--;
                   toastShow("没有更多数据了");

               }
           }
       }else {
           toastShow("没有数据了");
       }
    }

    @Override
    public void getDataFail(String msg) {
        //加载失败
        statusLayoutManager.showErrorLayout();
        LinLog.lLog(msg);
    }


    /**
     * 刷新加载
     */
    private void addListener() {

        refreshLayout.setOnRefreshListener(refreshlayout -> {
                mvpPresenter.News(store_id, page + "");
            refreshlayout.finishRefresh();
        });


        //加载更多
        refreshLayout.setOnLoadmoreListener(refreshlayout -> {
            page++;
            mvpPresenter.News(store_id, page + "");
            refreshlayout.finishLoadmore();
        });

    }
}
