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
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.MarkettjAdapter;
import com.example.erpqpp.mvp.mode.MarkettjMode;
import com.example.erpqpp.mvp.presenter.MarkettjPresenter;
import com.example.erpqpp.mvp.view.MarkettjView;
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
 * 销售统计
 * */
public class MarkettjActivity extends MvpActivity<MarkettjPresenter> implements MarkettjView, TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.et_search_content)
    EditText etSearchContent;
    @BindView(R.id.iv_search_delete)
    ImageView ivSearchDelete;
    @BindView(R.id.zonge)
    TextView zonge;
    @BindView(R.id.jine)
    TextView jine;
    @BindView(R.id.selectproduct_rc)
    RecyclerView selectproductRc;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int page = 1;
    private String store_id;
    private StatusLayoutManager statusLayoutManager;
    private MarkettjAdapter markettjAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markettj);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        tbvTitlebar.setMainTitle("销售统计");
        tbvTitlebar.setTitleBarListener(this);
        etSearchContent.setHint("客户名称搜索");

        ivSearchDelete.setOnClickListener(this);

        statusLayoutManager = setLayout(refreshLayout);
        statusLayoutManager.showLoadingLayout();

        store_id = (String) SPUtils.get(this, "store_id", "");
        markettjAdapter = new MarkettjAdapter(this);
        selectproductRc.setAdapter(markettjAdapter);
        selectproductRc.setLayoutManager(new LinearLayoutManager(this));

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
                    mvpPresenter.getSale(store_id, page + "");
                } else {
                    mvpPresenter.getSalesosuouo(store_id, page + "", etSearchContent.getText().toString().trim());
                }
            }
        });

    }

    @Override
    protected MarkettjPresenter createPresenter() {
        return new MarkettjPresenter(this);
    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.getSale(store_id, page + "");
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
     startActivity(AddorderActivity.class);
    }


    @Override
    public void getDataSuccess(MarkettjMode markettjMode) {

        statusLayoutManager.showSuccessLayout();
        MarkettjMode.DataBean data = markettjMode.getData();
        if (data!=null){
            zonge.setText("销售总额: "+data.getOrder_unit()+"");
            jine.setText("销售金额: "+data.getSum());
        }
        List<MarkettjMode.DataBean.ListBean> list = markettjMode.getData().getList();
        if (list.size() > 0) {
            if (page == 1) {
                markettjAdapter.setList(list);
                markettjAdapter.notifyDataSetChanged();
            } else {
                markettjAdapter.getList().addAll(list);
                markettjAdapter.notifyDataSetChanged();
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

    /**
     * 刷新加载
     */
    private void addListener() {

        refreshLayout.setOnRefreshListener(refreshlayout -> {
            page=1;
            if (etSearchContent.getText().toString().isEmpty()) {
                mvpPresenter.getSale(store_id, page + "");
            } else {
                mvpPresenter.getSalesosuouo(store_id, page + "", etSearchContent.getText().toString().trim());
            }
            refreshlayout.finishRefresh();
        });


        //加载更多
        refreshLayout.setOnLoadmoreListener(refreshlayout -> {
            page++;
            if (etSearchContent.getText().toString().isEmpty()) {
                mvpPresenter.getSale(store_id, page + "");
            } else {
                mvpPresenter.getSalesosuouo(store_id, page + "", etSearchContent.getText().toString().trim());
            }
            refreshlayout.finishLoadmore();
        });

    }
}
