package com.example.erpqpp.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;

import com.example.erpqpp.Myapp;
import com.example.erpqpp.R;
import com.example.erpqpp.adapter.ManagementAdapter;
import com.example.erpqpp.mvp.mode.ManagementMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.presenter.ManagementPresenter;
import com.example.erpqpp.mvp.view.ManagementView;
import com.example.erpqpp.myview.SwipeItemLayout;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * 客户管理
 */
public class ManagementActivity extends MvpActivity<ManagementPresenter> implements ManagementView, TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.et_search_content)
    EditText etSearchContent;
    @BindView(R.id.iv_search_delete)
    ImageView ivSearchDelete;
    @BindView(R.id.teachplan_rc)
    RecyclerView teachplanRc;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;



    private ManagementAdapter managementAdapter;
    private StatusLayoutManager statusLayoutManager;
    private int page = 1;
    private boolean issousuo = true;
    private String store_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        ButterKnife.bind(this);
        initdata();



    }




    private void initdata() {

        etSearchContent.setHint("客户名称搜索");

        statusLayoutManager = setLayout(refreshLayout);
        statusLayoutManager.showLoadingLayout();

        store_id = (String) SPUtils.get(this, "store_id", "");

        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleText("添加客户");
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setMainTitle("客户管理");

        ivSearchDelete.setOnClickListener(this);

        managementAdapter = new ManagementAdapter(this,this);
        teachplanRc.setAdapter(managementAdapter);
        teachplanRc.setLayoutManager(new LinearLayoutManager(this));

        teachplanRc.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));


        //刷新加载
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
                    issousuo = true;
                    mvpPresenter.getManagement(store_id, page + "");
                } else {
                    issousuo = false;
                    mvpPresenter.getManagementsousuo(store_id, page + "", etSearchContent.getText().toString().trim());
                }
            }
        });







    }

    @Override
    protected ManagementPresenter createPresenter() {
        return new ManagementPresenter(this);

    }


    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.getManagement(store_id, page + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_delete:
                etSearchContent.setText("");
                break;
                default:break;
        }
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

        startActivity(AddclientActivity.class);
    }


    @Override
    public void getDataSuccess(ManagementMode managementMode) {
        statusLayoutManager.showSuccessLayout();
        List<ManagementMode.DataBean.ListBean> list = managementMode.getData().getList();
        if (list.size() > 0) {
            if (page == 1) {
                managementAdapter.setList(list);
                managementAdapter.notifyDataSetChanged();
            } else {
                managementAdapter.getList().addAll(list);
                managementAdapter.notifyDataSetChanged();
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
    public void DelectDataSuccess(MgMode mgMode) {
        toastShow(mgMode.getMsg());
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.getManagement(store_id, page + "");
    }


    /**
     * 刷新加载
     */
    private void addListener() {

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 1;
            if (etSearchContent.getText().toString().isEmpty()) {
                mvpPresenter.getManagement(store_id, page + "");
            } else {
                mvpPresenter.getManagementsousuo(store_id, page + "", etSearchContent.getText().toString().trim());
            }

            refreshlayout.finishRefresh();
        });


        //加载更多
        refreshLayout.setOnLoadmoreListener(refreshlayout -> {
            page++;
            if (etSearchContent.getText().toString().isEmpty()) {
                mvpPresenter.getManagement(store_id, page + "");
            } else {
                mvpPresenter.getManagementsousuo(store_id, page + "", etSearchContent.getText().toString().trim());
            }
            refreshlayout.finishLoadmore();
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.getManagement(store_id, page + "");
    }


    public void Companydelete(String user_id){
        mvpPresenter.Companydelete(user_id);

    }


}
