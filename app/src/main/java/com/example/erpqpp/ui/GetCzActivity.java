package com.example.erpqpp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.CzAdapter;
import com.example.erpqpp.adapter.SingletonAdapter;
import com.example.erpqpp.mvp.mode.CzMode;
import com.example.erpqpp.mvp.presenter.GetCkPresenter;
import com.example.erpqpp.mvp.view.GetCzView;
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
 * 获取材质
 */
public class GetCzActivity extends MvpActivity<GetCkPresenter> implements TitleBarView.BtnClickListener, GetCzView {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.caizhi_rc)
    RecyclerView caizhiRc;
    private StatusLayoutManager statusLayoutManager;
    private String store_id;
    private CzAdapter czAdapter;
    private List<CzMode.DataBean> czModeData;
    List<String> czlist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_cz);
        ButterKnife.bind(this);

        statusLayoutManager = setLayout(caizhiRc);
        statusLayoutManager.showLoadingLayout();

        tbvTitlebar.setMainTitle("选择材质");
        tbvTitlebar.setTitleBarListener(this);

        czAdapter = new CzAdapter(this);
        caizhiRc.setAdapter(czAdapter);
        caizhiRc.setLayoutManager(new LinearLayoutManager(this));

        store_id = (String) SPUtils.get(this, "store_id", "");

        czAdapter.huidiao(new CzAdapter.Itemclick() {
            @Override
            public void itemclick(View view, int position) {
                Intent intent=new Intent();
                intent.putExtra("name",czModeData.get(position).getWood_name()+"");
                intent.putExtra("camode", (Serializable) czModeData);
                intent.putExtra("select", position+"");
                setResult(111,intent);
                finish();
            }
        });
    }

    @Override
    protected GetCkPresenter createPresenter() {
        return new GetCkPresenter(this);
    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.getCz(store_id);
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }

    @Override
    public void getDataSuccess(CzMode czMode) {
        statusLayoutManager.showSuccessLayout();
        czModeData = czMode.getData();
        if (czModeData.size()>0){
            czAdapter.setList(czModeData);

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
}
