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
import com.example.erpqpp.adapter.MgjllistAdapter;
import com.example.erpqpp.adapter.XsSelectProductAdapter;
import com.example.erpqpp.mvp.mode.XsproductListMode;
import com.example.erpqpp.mvp.presenter.XsproductListPresenter;
import com.example.erpqpp.mvp.view.XsproductListView;
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
 * 销售产品列表
 */
public class XsproductListActivity extends MvpActivity<XsproductListPresenter> implements XsproductListView,TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.et_search_content)
    EditText etSearchContent;
    @BindView(R.id.iv_search_delete)
    ImageView ivSearchDelete;
    @BindView(R.id.stayreceiving_rc)
    RecyclerView stayreceivingRc;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String store_id;
    private int page=1;
    private StatusLayoutManager statusLayoutManager;
    private XsSelectProductAdapter xsSelectProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xsproduct_list);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {

        statusLayoutManager = setLayout(refreshLayout);
        statusLayoutManager.showLoadingLayout();

        etSearchContent.setHint("产品名称搜索");
        store_id = (String) SPUtils.get(this, "store_id", "");
        tbvTitlebar.setMainTitle("选择产品");
        tbvTitlebar.setTitleBarListener(this);
        ivSearchDelete.setOnClickListener(this);

        xsSelectProductAdapter = new XsSelectProductAdapter(this,this);
        stayreceivingRc.setAdapter(xsSelectProductAdapter);
        stayreceivingRc.setLayoutManager(new LinearLayoutManager(this));

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
                    mvpPresenter.XsproductList(store_id,page+"");
                } else {
                    mvpPresenter.XsproductListsousuo(store_id,page+"", etSearchContent.getText().toString().trim());
                }
            }
        });
    }

    @Override
    protected XsproductListPresenter createPresenter() {
        return new XsproductListPresenter(this);
    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.XsproductList(store_id,page+"");
    }

    @Override
    public void getDataSuccess(XsproductListMode xsproductListMode) {
        statusLayoutManager.showSuccessLayout();
        List<XsproductListMode.DataBean.ListBean> list = xsproductListMode.getData().getList();
        if (list.size() > 0) {
            if (page == 1) {
                xsSelectProductAdapter.setList(list);
                xsSelectProductAdapter.notifyDataSetChanged();
            } else {
                xsSelectProductAdapter.getList().addAll(list);
                xsSelectProductAdapter.notifyDataSetChanged();
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


    /**
     * 刷新加载
     */
    private void addListener() {

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 1;
            if (etSearchContent.getText().toString().isEmpty()) {
                mvpPresenter.XsproductList(store_id,page+"");
            } else {
                mvpPresenter.XsproductListsousuo(store_id,page+"", etSearchContent.getText().toString().trim());
            }
            refreshlayout.finishRefresh();
        });


        //加载更多
        refreshLayout.setOnLoadmoreListener(refreshlayout -> {
            page++;
            if (etSearchContent.getText().toString().isEmpty()) {
                mvpPresenter.XsproductList(store_id,page+"");
            } else {
                mvpPresenter.XsproductListsousuo(store_id,page+"", etSearchContent.getText().toString().trim());
            }
            refreshlayout.finishLoadmore();
        });

    }
}
