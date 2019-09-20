package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.SingletonAdapter;
import com.example.erpqpp.mvp.mode.SingletonMode;
import com.example.erpqpp.mvp.presenter.SingletonPresenter;
import com.example.erpqpp.mvp.view.AddproductView;
import com.example.erpqpp.mvp.view.SingletonView;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * 单件选择
 */
public class SingletonActivity extends MvpActivity<SingletonPresenter> implements TitleBarView.BtnClickListener, SingletonView {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.danjain_rc)
    RecyclerView danjainRc;
    private String store_id;
    private String pro_id;
    private StatusLayoutManager statusLayoutManager;
    private SingletonAdapter singletonAdapter;
    private String select;
    private List<SingletonMode.DataBean> data=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton);
        ButterKnife.bind(this);
        statusLayoutManager = setLayout(danjainRc);
        statusLayoutManager.showLoadingLayout();


        tbvTitlebar.setMainTitle("选择单件");
        tbvTitlebar.setTitleBarListener(this);

        singletonAdapter = new SingletonAdapter(this);
        danjainRc.setAdapter(singletonAdapter);
        danjainRc.setLayoutManager(new LinearLayoutManager(this));

        store_id = (String) SPUtils.get(this, "store_id", "");
        pro_id = getIntent().getStringExtra("pro_id");
        select = getIntent().getStringExtra("select");
        mvpPresenter.getproductunit(store_id,pro_id);

        singletonAdapter.huidiao(new SingletonAdapter.Itemclick() {
            @Override
            public void itemclick(View view, int position) {
                Intent intent=new Intent();
                intent.putExtra("select",select);
                intent.putExtra("name",data.get(position).getUnit_name());
                intent.putExtra("unit_id",data.get(position).getUnit_id()+"");
                setResult(111,intent);
                finish();
            }
        });
    }

    @Override
    protected SingletonPresenter createPresenter() {
        return new SingletonPresenter(this);
    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        pro_id = getIntent().getStringExtra("pro_id");
        mvpPresenter.getproductunit(store_id,pro_id);
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void getDataSuccess(SingletonMode singletonMode) {
        statusLayoutManager.showSuccessLayout();
        data = singletonMode.getData();
        if (data.size()>0){
            singletonAdapter.setList(data);
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
