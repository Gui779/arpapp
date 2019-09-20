package com.example.erpqpp.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.WarehouseAdapter;
import com.example.erpqpp.mvp.mode.WarehouseStatisticsMode;
import com.example.erpqpp.mvp.presenter.WarehouseStatisticsPresenter;
import com.example.erpqpp.mvp.view.WarehouseStatisticsView;
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
 * 仓库统计
 */
public class WarehouseStatisticsActivity extends MvpActivity<WarehouseStatisticsPresenter> implements WarehouseStatisticsView, TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.statistics_rc)
    RecyclerView statisticsRc;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.et_search_content)
    EditText etSearchContent;
    @BindView(R.id.iv_search_delete)
    ImageView ivSearchDelete;
    private StatusLayoutManager statusLayoutManager;
    private int page = 1;
    private String store_id;
    private WarehouseAdapter warehouseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse_statistics);
        ButterKnife.bind(this);
        initdata();
    }

    @Override
    protected WarehouseStatisticsPresenter createPresenter() {
        return new WarehouseStatisticsPresenter(this);
    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.getKctj(store_id,  page+"");
    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.et_search_content:
             etSearchContent.setText("");
             break;
     }
    }

    private void initdata() {
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setMainTitle("库存统计");

        store_id = (String) SPUtils.get(this, "store_id", "");

        statusLayoutManager = setLayout(refreshLayout);
        statusLayoutManager.showLoadingLayout();

        warehouseAdapter = new WarehouseAdapter(this);
        statisticsRc.setAdapter(warehouseAdapter);
        statisticsRc.setLayoutManager(new LinearLayoutManager(this));

        ivSearchDelete.setOnClickListener(this);

        addListener();

        etSearchContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                page = 1;
                if (editable.toString().trim().equals("")) {
                    mvpPresenter.getKctj(store_id,  page+"");
                } else {
                    mvpPresenter.getKctjsousuo(store_id, page + "", etSearchContent.getText().toString().trim());
                }
            }
        });
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }

    @Override
    public void getDataSuccess(WarehouseStatisticsMode warehouseStatisticsMode) {
        statusLayoutManager.showSuccessLayout();
        List<WarehouseStatisticsMode.DataBean.ListBeanX.ListBean> listBeans = warehouseStatisticsMode.getData().getList().getList();
        if (listBeans.size() > 0) {
            if (page == 1) {
                warehouseAdapter.setList(listBeans);
                warehouseAdapter.notifyDataSetChanged();
            } else {
                warehouseAdapter.getList().addAll(listBeans);
                warehouseAdapter.notifyDataSetChanged();
            }
        } else {
            if (page == 1 && listBeans.size() == 0) {
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

    /**
     * 刷新加载
     */
    private void addListener() {

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 1;
            mvpPresenter.getKctj(store_id,  page+"");
            refreshlayout.finishRefresh();
        });


        //加载更多
        refreshLayout.setOnLoadmoreListener(refreshlayout -> {
            page++;
            if (etSearchContent.getText().toString().isEmpty()) {
                mvpPresenter.getKctj(store_id, page + "");
            } else {
                mvpPresenter.getKctjsousuo(store_id, page + "", etSearchContent.getText().toString().trim());
            }
            refreshlayout.finishLoadmore();
        });

    }
}
