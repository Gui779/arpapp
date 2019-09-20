package com.example.erpqpp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.SelectColorAdapter;
import com.example.erpqpp.adapter.SelectShAdapter;
import com.example.erpqpp.mvp.mode.SelectShMode;
import com.example.erpqpp.mvp.presenter.SelectShPresenter;
import com.example.erpqpp.mvp.view.SelectShView;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * 选择销售色号
 */
public class SelectShActivity extends MvpActivity<SelectShPresenter> implements SelectShView, TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.selectcolor_rc)
    RecyclerView selectcolorRc;
    private StatusLayoutManager statusLayoutManager;
    private String select;
    private String store_id;
    private SelectShAdapter selectShAdapter;
    private List<SelectShMode.DataBean.ListBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sh);
        ButterKnife.bind(this);

        tbvTitlebar.setMainTitle("选择颜色");
        tbvTitlebar.setTitleBarListener(this);

        statusLayoutManager = setLayout(selectcolorRc);
        statusLayoutManager.showLoadingLayout();

        Intent intent = getIntent();
        select = intent.getStringExtra("select");
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.SelectSh(store_id);

        selectShAdapter = new SelectShAdapter(this);
        selectcolorRc.setAdapter(selectShAdapter);
        selectcolorRc.setLayoutManager(new LinearLayoutManager(this));


        selectShAdapter.huidiao((view, position) -> {
            Intent intent1 =new Intent();
            intent1.putExtra("select",select);
            intent1.putExtra("name",list.get(position).getColor_name());
            intent1.putExtra("color_id",list.get(position).getColor_id()+"");
            setResult(112, intent1);
            finish();
        });
    }

    @Override
    protected SelectShPresenter createPresenter() {
        return new SelectShPresenter(this);
    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.SelectSh(store_id);
    }

    @Override
    public void getDataSuccess(SelectShMode selectShMode) {
        statusLayoutManager.showSuccessLayout();
        list = selectShMode.getData().getList();
        if (list.size()>0){
            selectShAdapter.setList(list);
        }else {
            statusLayoutManager.showEmptyLayout();
        }

    }

    @Override
    public void getDataFail(String msg) {
        statusLayoutManager.showErrorLayout();
        LinLog.lLog(msg);
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

    }
}
