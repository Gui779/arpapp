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
import com.example.erpqpp.adapter.SelectDmProductAdapter;
import com.example.erpqpp.mvp.mode.SelectMgMode;
import com.example.erpqpp.mvp.presenter.SelectProductPresenter;
import com.example.erpqpp.mvp.view.SelectProductView;
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
 * 选择打磨产品
 */
public class SelectDmProductActivity extends MvpActivity<SelectProductPresenter> implements SelectProductView , TitleBarView.BtnClickListener{

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.et_search_content)
    EditText etSearchContent;
    @BindView(R.id.iv_search_delete)
    ImageView ivSearchDelete;
    @BindView(R.id.selectproduct_rc)
    RecyclerView selectproductRc;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String store_id;
    private int page = 1;
    private StatusLayoutManager statusLayoutManager;
    private SelectDmProductAdapter SelectDmProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_product);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {

        store_id = (String) SPUtils.get(this, "store_id", "");

        statusLayoutManager = setLayout(refreshLayout);
        statusLayoutManager.showLoadingLayout();

        tbvTitlebar.setMainTitle("选择产品");
        tbvTitlebar.setTitleBarListener(this);

        ivSearchDelete.setOnClickListener(this);

        SelectDmProductAdapter = new SelectDmProductAdapter(this,this);
        selectproductRc.setAdapter(SelectDmProductAdapter);
        selectproductRc.setLayoutManager(new LinearLayoutManager(this));

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
                    mvpPresenter.Selectdm(store_id, page + "");
                } else {
                    mvpPresenter.Selectdmsousuo(store_id, page + "", etSearchContent.getText().toString().trim());
                }
            }
        });


    }

    @Override
    protected SelectProductPresenter createPresenter() {
        return new SelectProductPresenter(this);
    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.Selectdm(store_id,page+"");
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
    public void getDataSuccess(SelectMgMode selectMgMode) {
        statusLayoutManager.showSuccessLayout();
        List<SelectMgMode.DataBean.ProductAndUnitListBean> productAndUnitList = selectMgMode.getData().getProductAndUnitList();
        if (productAndUnitList.size() > 0) {
            if (page == 1) {
                SelectDmProductAdapter.setList(productAndUnitList);
                SelectDmProductAdapter.notifyDataSetChanged();
            } else {
                SelectDmProductAdapter.getList().addAll(productAndUnitList);
                SelectDmProductAdapter.notifyDataSetChanged();
            }
        } else {
            if (page == 1 && productAndUnitList.size() == 0) {
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
            if (etSearchContent.getText().toString().isEmpty()) {
                mvpPresenter.Selectdm(store_id, page + "");
            } else {
                mvpPresenter.Selectdmsousuo(store_id, page + "", etSearchContent.getText().toString().trim());
            }

            refreshlayout.finishRefresh();
        });


        //加载更多
        refreshLayout.setOnLoadmoreListener(refreshlayout -> {
            page++;
            if (etSearchContent.getText().toString().isEmpty()) {
                mvpPresenter.Selectdm(store_id, page + "");
            } else {
                mvpPresenter.Selectdmsousuo(store_id, page + "", etSearchContent.getText().toString().trim());
            }
            refreshlayout.finishLoadmore();
        });

    }

}
