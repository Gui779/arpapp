package com.example.erpqpp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.SendGoodsDetailsAdapter;
import com.example.erpqpp.mvp.mode.SendGoodsDetailsMode;
import com.example.erpqpp.mvp.presenter.SendGoodsDetailsPresenter;
import com.example.erpqpp.mvp.view.SendGoodsDetailsView;
import com.example.erpqpp.myview.TitleBarView;
import com.example.erpqpp.utils.StatusUtils;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * 发货详情
 */
public class SendGoodsDetailsActivity extends MvpActivity<SendGoodsDetailsPresenter> implements TitleBarView.BtnClickListener, SendGoodsDetailsView {


    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.order_id)
    TextView orderId;
    @BindView(R.id.order_stat)
    TextView orderStat;
    @BindView(R.id.zprice)
    TextView zprice;
    @BindView(R.id.riqi)
    TextView riqi;
    @BindView(R.id.markettj_rc)
    RecyclerView markettjRc;
    @BindView(R.id.nc)
    NestedScrollView nc;
    private String order_id;
    private StatusLayoutManager statusLayoutManager;
    private SendGoodsDetailsAdapter sendGoodsDetailsAdapter;
    private String typename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_goods_details);
        ButterKnife.bind(this);
        initdata();


    }

    @Override
    protected SendGoodsDetailsPresenter createPresenter() {
        return new SendGoodsDetailsPresenter(this);
    }

    @Override
    protected void initretry() {
        String fh = (String) SPUtils.get(this, "fh", "");
        String typename = (String) SPUtils.get(this, "name", "");
        if (typename.equals("已发货")){
            mvpPresenter.YSendGoodsDetails(fh);
        }else if (typename.equals("待发货")){
            mvpPresenter.DSendGoodsDetails(fh);
        }else if (typename.equals("待收货")){
            mvpPresenter.Goodsxq(fh);
        } else if (typename.equals("已收货")){
            mvpPresenter.Goodsyshxq(fh);
        }

    }

    private void initdata() {

        statusLayoutManager = setLayout(nc);
        statusLayoutManager.showLoadingLayout();

        Intent intent = getIntent();
        typename = intent.getStringExtra("name");
        order_id = intent.getStringExtra("type");
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setMainTitle(typename);

        sendGoodsDetailsAdapter = new SendGoodsDetailsAdapter(this);
        markettjRc.setAdapter(sendGoodsDetailsAdapter);
        markettjRc.setLayoutManager(new LinearLayoutManager(this));

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
    public void getDataSuccess(SendGoodsDetailsMode sendGoodsDetailsMode) {
        statusLayoutManager.showSuccessLayout();
        SendGoodsDetailsMode.DataBean dataBean = sendGoodsDetailsMode.getData().get(0);
        if (dataBean != null) {
            name.setText("客户名称: " + dataBean.getCustomer_name());
            orderId.setText("订单编号: " + dataBean.getOrder_num());
            orderStat.setText("状态: " + StatusUtils.getxsstatus(dataBean.getStatus()));
            zprice.setText("总额: " + dataBean.getTotal());
            riqi.setText("日期: " + dataBean.getAdd_time());
        }

        List<SendGoodsDetailsMode.DataBean.ProListBean> pro_list = dataBean.getPro_list();
        if (pro_list.size() > 0) {
            sendGoodsDetailsAdapter.setData(pro_list);
        } else {
            statusLayoutManager.showEmptyLayout();
        }
    }

    @Override
    public void getDataFail(String msg) {

    }


}
