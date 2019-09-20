package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.SelectXsDjAdapter;
import com.example.erpqpp.mvp.mode.SelectXsDjMode;
import com.example.erpqpp.mvp.presenter.SelectXsDjPresenter;
import com.example.erpqpp.mvp.view.SelectXsDjView;
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
 * 选择销售单价
 */
public class SelectXsDjActivity extends MvpActivity<SelectXsDjPresenter> implements SelectXsDjView, TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.selectxsdj_rc)
    RecyclerView selectxsdjRc;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.count)
    TextView count;
    @BindView(R.id.ly)
    LinearLayout ly;
    private StatusLayoutManager statusLayoutManager;
    private SelectXsDjAdapter selectXsDjAdapter;
    private String pro_id;
    private String select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_xs_dj);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {

        Intent intent = getIntent();
        select = intent.getStringExtra("select");

        pro_id = (String) SPUtils.get(this, "xspro_id", "");
        statusLayoutManager = setLayout(ly);
        statusLayoutManager.showLoadingLayout();

        tbvTitlebar.setMainTitle("添加单件");
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setRightTitleText("确定");

        selectXsDjAdapter = new SelectXsDjAdapter(this,this);
        selectxsdjRc.setAdapter(selectXsDjAdapter);
        selectxsdjRc.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected SelectXsDjPresenter createPresenter() {
        return new SelectXsDjPresenter(this);
    }

    @Override
    protected void initretry() {
        pro_id = (String) SPUtils.get(this, "xspro_id", "");
        mvpPresenter.SelectXsDj(pro_id);
    }

    @Override
    public void getDataSuccess(SelectXsDjMode selectXsDjMode) {
        statusLayoutManager.showSuccessLayout();
        List<SelectXsDjMode.DataBean.ProListBean> list = selectXsDjMode.getData().getList();
        if (list.size() > 0) {
            selectXsDjAdapter.setList(list);
            selectXsDjAdapter.notifyDataSetChanged();
        } else {
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
        List<SelectXsDjMode.DataBean.ProListBean> list = selectXsDjAdapter.getList();
        Intent intent=new Intent();
        intent.putExtra("select", select);
        intent.putExtra("djlist", (Serializable) list);
        setResult(001, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1012&&resultCode==001){
            String count = data.getStringExtra("count");
            String select = data.getStringExtra("select");
            if (select!=null&&count!=null) {
                selectXsDjAdapter.getList().get(Integer.parseInt(select)).setRate(Integer.parseInt(count));
            }
            selectXsDjAdapter.notifyDataSetChanged();
        }
    }
}
