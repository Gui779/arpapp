package com.example.erpqpp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.SelectColorAdapter;
import com.example.erpqpp.mvp.mode.SelectMgMode;
import com.example.erpqpp.mvp.presenter.SelectColorPresenter;
import com.example.erpqpp.mvp.view.SelectColorView;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * 选择颜色
 */
public class SelectColorActivity extends MvpActivity<SelectColorPresenter> implements SelectColorView, TitleBarView.BtnClickListener {

    @BindView(R.id.selectcolor_rc)
    RecyclerView selectcolorRc;
    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    private String select;
    private String store_id;
    private StatusLayoutManager statusLayoutManager;
    private SelectColorAdapter selectColorAdapter;
    private List<SelectMgMode.DataBean.ProductColorBean> productColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_color);
        ButterKnife.bind(this);

        tbvTitlebar.setMainTitle("选择颜色");
        tbvTitlebar.setTitleBarListener(this);

        statusLayoutManager = setLayout(selectcolorRc);
        statusLayoutManager.showLoadingLayout();

        Intent intent = getIntent();
        select = intent.getStringExtra("select");
        store_id = intent.getStringExtra("store_id");
         mvpPresenter.SelectColor(store_id);

        selectColorAdapter = new SelectColorAdapter(this);
        selectcolorRc.setAdapter(selectColorAdapter);
        selectcolorRc.setLayoutManager(new LinearLayoutManager(this));

        selectColorAdapter.huidiao((view, position) -> {
            Intent intent1 =new Intent();
            intent1.putExtra("select",select);
            intent1.putExtra("name",productColor.get(position).getColor_name());
            intent1.putExtra("color_id",productColor.get(position).getColor_id()+"");
            setResult(112, intent1);
            finish();
        });
    }

    @Override
    protected SelectColorPresenter createPresenter() {
        return new SelectColorPresenter(this);
    }

    @Override
    protected void initretry() {
    // mvpPresenter.SelectColor(store_id);
    }

    @Override
    public void getDataSuccess(SelectMgMode selectMgMode) {
        statusLayoutManager.showSuccessLayout();
        productColor = selectMgMode.getData().getProductColor();
        if (productColor.size()>0){
            selectColorAdapter.setList(productColor);
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
