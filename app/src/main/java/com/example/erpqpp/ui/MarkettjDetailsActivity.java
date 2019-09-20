package com.example.erpqpp.ui;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.MarkettjDetailsAdapter;
import com.example.erpqpp.mvp.mode.MarkettjDetailsMode;
import com.example.erpqpp.mvp.presenter.MarkettjDetailsPresenter;
import com.example.erpqpp.mvp.view.MarkettjDetailsView;
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
 * 销售统计详情
 */
public class MarkettjDetailsActivity extends MvpActivity<MarkettjDetailsPresenter> implements MarkettjDetailsView, TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.order_id)
    TextView orderId;
    @BindView(R.id.order_stat)
    TextView orderStat;
    @BindView(R.id.markettj_rc)
    RecyclerView markettjRc;
    @BindView(R.id.nc)
    NestedScrollView nc;
    @BindView(R.id.zprice)
    TextView zprice;
    @BindView(R.id.riqi)
    TextView riqi;
    private String order_id;
    private int page = 1;
    private StatusLayoutManager statusLayoutManager;
    private MarkettjDetailsAdapter markettjAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markettjdetails);
        ButterKnife.bind(this);
        initdata();

    }

    private void initdata() {
        tbvTitlebar.setMainTitle("销售统计");
        tbvTitlebar.setTitleBarListener(this);

        statusLayoutManager = setLayout(nc);
        statusLayoutManager.showLoadingLayout();

        order_id = (String) SPUtils.get(this, "order_id", "");

        markettjAdapter = new MarkettjDetailsAdapter(this);
        markettjRc.setAdapter(markettjAdapter);
        markettjRc.setLayoutManager(new LinearLayoutManager(this));
        markettjRc.setNestedScrollingEnabled(false);//禁止滑动
        markettjRc.setFocusable(false);


    }

    @Override
    protected MarkettjDetailsPresenter createPresenter() {
        return new MarkettjDetailsPresenter(this);
    }

    @Override
    protected void initretry() {
        order_id = (String) SPUtils.get(this, "order_id", "");
        mvpPresenter.zsSaleList(order_id);
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
    public void getDataSuccess(MarkettjDetailsMode markettjDetailsMode) {
        statusLayoutManager.showSuccessLayout();
        MarkettjDetailsMode.DataBean dataBean = markettjDetailsMode.getData().get(0);
        if (dataBean != null) {
            name.setText("客户名称: " + dataBean.getCustomer_name());
            orderId.setText("订单编号: " + dataBean.getOrder_num());
            orderStat.setText("状态: " + StatusUtils.getxsstatus(dataBean.getStatus()));
            zprice.setText("总额: "+dataBean.getTotal());
            riqi.setText("日期: "+dataBean.getAdd_time());
        }
        List<MarkettjDetailsMode.DataBean.ProListBean> pro_list = dataBean.getPro_list();
        if (pro_list.size() > 0) {
            if (page == 1) {
                markettjAdapter.setData(pro_list);
                markettjAdapter.notifyDataSetChanged();
            } else {
                markettjAdapter.getData().addAll(pro_list);
                markettjAdapter.notifyDataSetChanged();
            }
        } else {
            if (page == 1 && pro_list.size() == 0) {
                statusLayoutManager.showEmptyLayout();
            } else {
                page--;
                toastShow("没有更多数据了");
            }
        }
    }

    @Override
    public void getDataFail(String msg) {
        //加载失败
        statusLayoutManager.showErrorLayout();
        LinLog.lLog(msg);
    }
}
