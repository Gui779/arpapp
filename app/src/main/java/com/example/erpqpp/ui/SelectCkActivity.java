package com.example.erpqpp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.SelectCkAdapter;
import com.example.erpqpp.mvp.mode.CkMode;
import com.example.erpqpp.mvp.presenter.SelectCkPresenter;
import com.example.erpqpp.mvp.view.SelectCkView;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * 选择仓库
 */
public class SelectCkActivity extends MvpActivity<SelectCkPresenter> implements SelectCkView , TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.selectck_rc)
    RecyclerView selectckRc;
    private StatusLayoutManager statusLayoutManager;
    private String store_id;
    private SelectCkAdapter selectCkAdapter;
    private String select;
    private List<CkMode.DataBean> ckModeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_ck);
        ButterKnife.bind(this);

        tbvTitlebar.setMainTitle("选择颜色");
        tbvTitlebar.setTitleBarListener(this);

        Intent intent = getIntent();
        select = intent.getStringExtra("select");

        store_id = (String) SPUtils.get(this, "store_id", "");

        statusLayoutManager = setLayout(selectckRc);
        statusLayoutManager.showLoadingLayout();


        selectCkAdapter = new SelectCkAdapter(this);
        selectckRc.setAdapter(selectCkAdapter);
        selectckRc.setLayoutManager(new LinearLayoutManager(this));

     
        
        selectCkAdapter.huidiao((view, position) -> {
            Intent intent1 =new Intent();
            intent1.putExtra("select",select);
            intent1.putExtra("name",ckModeData.get(position).getWarehousename());
            intent1.putExtra("warehouse_id",ckModeData.get(position).getWarehouse_id()+"");
            setResult(114, intent1);
            finish();
        });
    }

    @Override
    protected SelectCkPresenter createPresenter() {
        return new SelectCkPresenter(this);
    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.Selectck(store_id);
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

    @Override
    public void getDataSuccess(CkMode ckMode) {
        statusLayoutManager.showSuccessLayout();
        ckModeData = ckMode.getData();
        if (ckModeData.size()>0){
            selectCkAdapter.setList(ckModeData);
        }else {
            statusLayoutManager.showEmptyLayout();
        }
    }

    @Override
    public void getDataFail(String msg) {
        statusLayoutManager.showErrorLayout();
        LinLog.lLog(msg);
    }
}
