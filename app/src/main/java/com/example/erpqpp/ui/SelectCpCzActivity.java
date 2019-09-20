package com.example.erpqpp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.CpCzAdapter;
import com.example.erpqpp.mvp.mode.SelectCpCzMode;
import com.example.erpqpp.mvp.presenter.SelectCpCzPresenter;
import com.example.erpqpp.mvp.view.SelectCpCzView;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * 查询材质查询材质
 */
public class SelectCpCzActivity extends MvpActivity<SelectCpCzPresenter> implements SelectCpCzView,TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.caizhi_rc)
    RecyclerView caizhiRc;
    private StatusLayoutManager statusLayoutManager;
    private String store_id;
    private List<SelectCpCzMode.DataBean> selectCpCzModeData;
    private CpCzAdapter cpcpCzAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp_cz);
        ButterKnife.bind(this);

        statusLayoutManager = setLayout(caizhiRc);
        statusLayoutManager.showLoadingLayout();

        store_id = (String) SPUtils.get(this, "store_id", "");

        tbvTitlebar.setMainTitle("选择材质");
        tbvTitlebar.setTitleBarListener(this);

        cpcpCzAdapter = new CpCzAdapter(this);
        caizhiRc.setAdapter(cpcpCzAdapter);
        caizhiRc.setLayoutManager(new LinearLayoutManager(this));

        store_id = (String) SPUtils.get(this, "store_id", "");

        cpcpCzAdapter.huidiao((view, position) -> {
            SelectCpCzMode.DataBean dataBean = selectCpCzModeData.get(position);
            Intent intent = new Intent();
            intent.putExtra("dataBean", (Serializable) dataBean);
            setResult(222,intent);
            finish();
        });
    }

    @Override
    protected SelectCpCzPresenter createPresenter() {
        return new SelectCpCzPresenter(this);
    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.selectcz(store_id);
    }

    @Override
    public void getDataSuccess(SelectCpCzMode selectCpCzMode) {
        statusLayoutManager.showSuccessLayout();
        selectCpCzModeData = selectCpCzMode.getData();
        if (selectCpCzModeData.size()>0){
            cpcpCzAdapter.setList(selectCpCzModeData);

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
