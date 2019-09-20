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
import com.example.erpqpp.adapter.DiscountAdapter;
import com.example.erpqpp.mvp.mode.AddMode;
import com.example.erpqpp.mvp.mode.DiscountMode;
import com.example.erpqpp.mvp.presenter.DiscountPresenter;
import com.example.erpqpp.mvp.view.DiscountView;
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
 * 折扣列表
 */
public class DiscountActivity extends MvpActivity<DiscountPresenter> implements DiscountView , TitleBarView.BtnClickListener{

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.et_search_content)
    EditText etSearchContent;
    @BindView(R.id.iv_search_delete)
    ImageView ivSearchDelete;
    @BindView(R.id.discount_rc)
    RecyclerView discountRc;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String store_id;
    private StatusLayoutManager statusLayoutManager;
    private DiscountAdapter discountAdapter;
    private int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);
        ButterKnife.bind(this);
        initdata();

    }

    private void initdata() {

        etSearchContent.setHint("客户名称搜索");

        statusLayoutManager = setLayout(refreshLayout);
        statusLayoutManager.showLoadingLayout();

        store_id = (String) SPUtils.get(this, "store_id", "");

        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setMainTitle("折扣列表");

        discountAdapter = new DiscountAdapter(this,this);
        discountRc.setAdapter(discountAdapter);
        discountRc.setLayoutManager(new LinearLayoutManager(this));

        ivSearchDelete.setOnClickListener(this);


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
                    mvpPresenter.Discount(store_id, page + "");
                } else {
                    mvpPresenter.Discountsousuo(store_id, page + "", etSearchContent.getText().toString().trim());
                }
            }
        });


    }

    @Override
    protected DiscountPresenter createPresenter() {
        return new DiscountPresenter(this);
    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.Discount(store_id, page + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
    public void getDataSuccess(DiscountMode discountMode) {
        statusLayoutManager.showSuccessLayout();
        List<DiscountMode.DataBean.ListBean> list = discountMode.getData().getList();
        if (list.size() > 0) {
            if (page == 1) {
                discountAdapter.setList(list);
                discountAdapter.notifyDataSetChanged();
            } else {
                discountAdapter.getList().addAll(list);
                discountAdapter.notifyDataSetChanged();
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
        statusLayoutManager.showErrorLayout();
        LinLog.lLog(msg);
    }

    @Override
    public void discountNoDataSuccess(AddMode discountMode) {
        if (discountMode.getCode()==1){
            finish();
        }
    }

    @Override
    public void discountNoDataFail(String msg) {
        LinLog.lLog(msg);
    }

    /**
     * 刷新加载
     */
    private void addListener() {

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page = 1;
            if (etSearchContent.getText().toString().isEmpty()) {
                mvpPresenter.Discount(store_id, page + "");
            } else {
                mvpPresenter.Discountsousuo(store_id, page + "", etSearchContent.getText().toString().trim());
            }

            refreshlayout.finishRefresh();
        });


        //加载更多
        refreshLayout.setOnLoadmoreListener(refreshlayout -> {
            page++;
            if (etSearchContent.getText().toString().isEmpty()) {
                mvpPresenter.Discount(store_id, page + "");
            } else {
                mvpPresenter.Discountsousuo(store_id, page + "", etSearchContent.getText().toString().trim());
            }
            refreshlayout.finishLoadmore();
        });

    }

    //折扣列表否决按钮
    public void discountNo(String order_id){
      mvpPresenter.discountNo(order_id);
    }

    //折扣列表同意按钮
    public void discountAgree(String order_id){
        mvpPresenter.discountAgree(order_id);
    }
}
