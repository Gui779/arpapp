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
import com.example.erpqpp.adapter.StatisticsAdapter;
import com.example.erpqpp.mvp.mode.SqMode;
import com.example.erpqpp.mvp.mode.StatisticsMode;
import com.example.erpqpp.mvp.presenter.StatisticsPresenter;
import com.example.erpqpp.mvp.view.StatisticsView;
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
 * 库存统计
 */
public class StatisticsActivity extends MvpActivity<StatisticsPresenter> implements StatisticsView, TitleBarView.BtnClickListener {

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
    private String store_id;
    private int page=1;
    private String admin_id;
    private StatisticsAdapter statisticsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {

        statusLayoutManager = setLayout(refreshLayout);
        statusLayoutManager.showLoadingLayout();

        store_id = (String) SPUtils.get(this, "store_id", "");
        admin_id = (String) SPUtils.get(this, "admin_id", "");

        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setMainTitle("库存统计");
        ivSearchDelete.setOnClickListener(this);

        statisticsAdapter = new StatisticsAdapter(this);
        statisticsRc.setAdapter(statisticsAdapter);
        statisticsRc.setLayoutManager(new LinearLayoutManager(this));
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
                    mvpPresenter.Statistics(store_id, admin_id,page+"");
                } else {
                    mvpPresenter.Statisticssousuo(store_id, admin_id,page+"",etSearchContent.getText().toString().trim());
                }
            }
        });

    }

    @Override
    protected StatisticsPresenter createPresenter() {
        return new StatisticsPresenter(this);
    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        admin_id = (String) SPUtils.get(this, "admin_id", "");
        mvpPresenter.Statistics(store_id, admin_id,page+"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_delete:
                etSearchContent.setText("");
                break;

        }
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }


    @Override
    public void getDataSuccess(StatisticsMode statisticsMode) {
        statusLayoutManager.showSuccessLayout();
        List<StatisticsMode.DataBean> StatisticsModeData = statisticsMode.getData();
        if (StatisticsModeData.size() > 0) {
            if (page == 1) {
                statisticsAdapter.setList(StatisticsModeData);
                statisticsAdapter.notifyDataSetChanged();
            } else {
                statisticsAdapter.getList().addAll(StatisticsModeData);
                statisticsAdapter.notifyDataSetChanged();
            }
        } else {
            if (page == 1 && StatisticsModeData.size() == 0) {
                statusLayoutManager.showEmptyLayout();
            } else {
                page--;
                toastShow("没有更多数据了");
            }
        }
    }

    @Override
    public void getDataFail(String msg) {
        statusLayoutManager.showErrorLayout();
        LinLog.lLog(msg);
    }


    /**
     * 刷新加载
     */
    private void addListener() {

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page=1;
            if (etSearchContent.getText().toString().isEmpty()) {
                mvpPresenter.Statistics(store_id, admin_id,page+"");
            } else {
                mvpPresenter.Statisticssousuo(store_id, admin_id,page+"",etSearchContent.getText().toString().trim());
            }
            refreshlayout.finishRefresh();
        });


        //加载更多
        refreshLayout.setOnLoadmoreListener(refreshlayout -> {
            page++;
            if (etSearchContent.getText().toString().isEmpty()) {
                mvpPresenter.Statistics(store_id, admin_id,page+"");
            } else {
                mvpPresenter.Statisticssousuo(store_id, admin_id,page+"",etSearchContent.getText().toString().trim());
            }
            refreshlayout.finishLoadmore();
        });

    }

}
