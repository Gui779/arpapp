package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.ColourManageAdapter;
import com.example.erpqpp.mvp.mode.ColourManageMode;
import com.example.erpqpp.mvp.mode.XzCzMode;
import com.example.erpqpp.mvp.presenter.ColourManagePresenter;
import com.example.erpqpp.mvp.view.ColourManageView;
import com.example.erpqpp.myview.SwipeItemLayout;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * 色号管理
 */
public class ColourManageActivity extends MvpActivity<ColourManagePresenter> implements ColourManageView, TitleBarView.BtnClickListener {


    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.colourmanage_rc)
    RecyclerView colourmanageRc;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ColourManageAdapter colourManageAdapter;
    private String store_id;
    private int page=1;
    private StatusLayoutManager statusLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_manage);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {


        statusLayoutManager = setLayout(refreshLayout);
        statusLayoutManager.showLoadingLayout();

        tbvTitlebar.setMainTitle("色号管理");
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleText("添加");
        tbvTitlebar.setRightTitleColor(Color.WHITE);

        colourManageAdapter = new ColourManageAdapter(this,this);
        colourmanageRc.setLayoutManager(new LinearLayoutManager(this));
        colourmanageRc.setAdapter(colourManageAdapter);
        colourmanageRc.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));

        addListener();

    }

    @Override
    protected ColourManagePresenter createPresenter() {
        return new ColourManagePresenter(this);
    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.saleColor(store_id,page+"");
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
        Intent intent = new Intent(this, UpdataManageActivity.class);
        intent.putExtra("title","添加色号");
        startActivity(intent);
    }


    @Override
    public void getDataSuccess(ColourManageMode colourManageMode) {
        statusLayoutManager.showSuccessLayout();
        List<ColourManageMode.DataBean> list = colourManageMode.getData();
        if (list.size() > 0) {
            if (page == 1) {
                colourManageAdapter.setList(list);
                colourManageAdapter.notifyDataSetChanged();
            } else {
                colourManageAdapter.getList().addAll(list);
                colourManageAdapter.notifyDataSetChanged();
            }
        } else {
            if (page == 1 && list.size() == 0) {
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

    @Override
    public void deleectDataSuccess(XzCzMode xzCzMode) {
        toastShow(xzCzMode.getMessage());
    }


    /**
     * 刷新加载
     */
    private void addListener() {

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 1;
            mvpPresenter.saleColor(store_id,page+"");
            refreshlayout.finishRefresh();
        });

        //加载更多
        refreshLayout.setOnLoadmoreListener(refreshlayout -> {
            page++;
            mvpPresenter.saleColor(store_id,page+"");
            refreshlayout.finishLoadmore();
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.saleColor(store_id,page+"");
    }

    public void deleteColor(String color_id){
        mvpPresenter.delectColor(color_id);
    }
}
