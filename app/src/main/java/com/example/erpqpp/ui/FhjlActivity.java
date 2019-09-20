package com.example.erpqpp.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.FhjlAdapter;
import com.example.erpqpp.mvp.mode.FhjlMode;
import com.example.erpqpp.mvp.presenter.FhjlPresenter;
import com.example.erpqpp.mvp.view.FhjlView;
import com.example.erpqpp.myview.DialogUtils;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * 发货记录
 */
public class FhjlActivity extends MvpActivity<FhjlPresenter> implements FhjlView, TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.et_search_content)
    EditText etSearchContent;
    @BindView(R.id.iv_search_delete)
    ImageView ivSearchDelete;
    @BindView(R.id.statistics_rc)
    RecyclerView statisticsRc;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private StatusLayoutManager statusLayoutManager;
    private FhjlAdapter fhjlAdapter;
    private String store_id;
    private int page=1;
    private DialogUtils dialogUtils;
    private List<String> leixinglist;
    private String from="10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fhjl);
        ButterKnife.bind(this);
        initview();

    }

    private void initview() {
        dialogUtils = new DialogUtils();
        store_id = (String) SPUtils.get(this, "store_id", "");

        tbvTitlebar.setMainTitle("发货记录");
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setRightTitleText("未磨仓库");

        statusLayoutManager = setLayout(refreshLayout);
        statusLayoutManager.showLoadingLayout();

        fhjlAdapter = new FhjlAdapter(this);
        statisticsRc.setAdapter(fhjlAdapter);
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
                    mvpPresenter.record(store_id,"10",page+"");
                } else {
                    mvpPresenter.recordsousuo(store_id,"10",page+"",etSearchContent.getText().toString().trim());
                }
            }
        });

        leixinglist = new ArrayList<>();
        leixinglist.add("未磨仓库");
        leixinglist.add("已磨仓库");
        leixinglist.add("已油漆仓库");

    }

    @Override
    protected FhjlPresenter createPresenter() {
        return new FhjlPresenter(this);
    }

    @Override
    protected void initretry() {
     store_id = (String) SPUtils.get(this, "store_id", "");
      mvpPresenter.record(store_id,from,page+"");
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
        dialogUtils.setdialog(FhjlActivity.this, leixinglist);
        dialogUtils.setonclickListener(new DialogUtils.setOnclick() {
            @Override
            public void getSeletedItem(String selectname) {
                tbvTitlebar.setRightTitleText(selectname);
            }

            @Override
            public void getSeletedndex(int selectindex) {
                switch (selectindex){
                    case 0:
                        from="10";
                        mvpPresenter.record(store_id,from,page+"");
                        break;
                    case 1:
                        from="20";
                        mvpPresenter.record(store_id,from,page+"");
                        break;
                    case 2:
                        from="30";
                        mvpPresenter.record(store_id,from,page+"");
                        break;
                }
            }
        });
    }

    @Override
    public void getDataSuccess(FhjlMode fhjlMode) {
        statusLayoutManager.showSuccessLayout();
        List<FhjlMode.DataBean> FhjlMode = fhjlMode.getData();
        if (FhjlMode.size() > 0) {
            if (page == 1) {
                fhjlAdapter.setList(FhjlMode);
                fhjlAdapter.notifyDataSetChanged();
            } else {
                fhjlAdapter.getList().addAll(FhjlMode);
                fhjlAdapter.notifyDataSetChanged();
            }
        } else {
            if (page == 1 && FhjlMode.size() == 0) {
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
                mvpPresenter.record(store_id,from,page+"");
            } else {
                mvpPresenter.recordsousuo(store_id,from,page+"",etSearchContent.getText().toString().trim());
            }
            refreshlayout.finishRefresh();
        });


        //加载更多
        refreshLayout.setOnLoadmoreListener(refreshlayout -> {
            page++;
            if (etSearchContent.getText().toString().isEmpty()) {
                mvpPresenter.record(store_id,from,page+"");
            } else {
                mvpPresenter.recordsousuo(store_id,from,page+"",etSearchContent.getText().toString().trim());
            }
            refreshlayout.finishLoadmore();
        });

    }
}
