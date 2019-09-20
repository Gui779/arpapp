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
import com.example.erpqpp.adapter.ProductAdapter;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.ProductMode;
import com.example.erpqpp.mvp.presenter.ProductPresenter;
import com.example.erpqpp.mvp.view.ProductView;
import com.example.erpqpp.myview.SwipeItemLayout;
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
 * 产品管理
 */
public class ProductActivity extends MvpActivity<ProductPresenter> implements ProductView, TitleBarView.BtnClickListener {

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
    private ProductAdapter productAdapter;
    private StatusLayoutManager statusLayoutManager;
    private String store_id;
    private int page=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {

        statusLayoutManager = setLayout(refreshLayout);
        statusLayoutManager.showLoadingLayout();
        store_id = (String) SPUtils.get(this, "store_id", "");

        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleText("添加产品");
        tbvTitlebar.setMainTitle("产品管理");
        tbvTitlebar.setRightTitleColor(Color.WHITE);


        productAdapter = new ProductAdapter(this,this);
        teachplanRc.setAdapter(productAdapter);
        teachplanRc.setLayoutManager(new LinearLayoutManager(this));

        teachplanRc.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));


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
                    mvpPresenter.productList(store_id, page+"");
                } else {
                    mvpPresenter.productListsousuo(store_id, page+"",etSearchContent.getText().toString().trim());
                }
            }
        });

    }

    @Override
    protected ProductPresenter createPresenter() {
        return new ProductPresenter(this);
    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.productList(store_id, page+"");
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
     startActivity(AddCgProductActivity.class);
    }


    @Override
    public void getDataSuccess(ProductMode productMode) {
        statusLayoutManager.showSuccessLayout();
        List<ProductMode.DataBean> ProductData = productMode.getData();
        if (ProductData.size() > 0) {
            if (page == 1) {
                productAdapter.setList(ProductData);
                productAdapter.notifyDataSetChanged();
            } else {
                productAdapter.getList().addAll(ProductData);
                productAdapter.notifyDataSetChanged();
            }
        } else {
            if (page == 1 && ProductData.size() == 0) {
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
    public void DelectDataSuccess(MgMode mgMode) {
        toastShow(mgMode.getMsg());
    }

    @Override
    public void DelectDataFail(String msg) {
        LinLog.lLog(msg);
    }

    /**
     * 刷新加载
     */
    private void addListener() {

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page=1;
            mvpPresenter.productList(store_id, page+"");
            refreshlayout.finishRefresh();
        });


        //加载更多
        refreshLayout.setOnLoadmoreListener(refreshlayout -> {
            page++;
            if (etSearchContent.getText().toString().isEmpty()) {

                mvpPresenter.productList(store_id, page+"");
            } else {
                mvpPresenter.productListsousuo(store_id, page + "", etSearchContent.getText().toString().trim());
            }
            refreshlayout.finishLoadmore();
        });

    }

    public void delect(String store_id,String pro_id){
        mvpPresenter.delect(store_id,pro_id);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.productList(store_id, page+"");
    }
}
