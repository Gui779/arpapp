package com.example.erpqpp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.SelectNameAdapter;
import com.example.erpqpp.mvp.mode.SelectNameMode;
import com.example.erpqpp.mvp.presenter.SelectNamePresenter;
import com.example.erpqpp.mvp.view.SelectNameView;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * 选择客户名称
 */
public class SelectNameActivity extends MvpActivity<SelectNamePresenter> implements SelectNameView, TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.et_search_content)
    EditText etSearchContent;
    @BindView(R.id.iv_search_delete)
    ImageView ivSearchDelete;
    @BindView(R.id.caizhi_rc)
    RecyclerView caizhiRc;
    private String store_id;
    private StatusLayoutManager statusLayoutManager;
    private SelectNameAdapter selectNameAdapter;
    private List<SelectNameMode.DataBean.ListBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_name);
        ButterKnife.bind(this);
        initdata();

    }

    private void initdata() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        tbvTitlebar.setMainTitle("搜索客户名称");
        tbvTitlebar.setTitleBarListener(this);
        etSearchContent.setOnClickListener(this);
        etSearchContent.setHint("客户名称搜索");

        statusLayoutManager = setLayout(caizhiRc);

        etSearchContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().equals("")){
                    mvpPresenter.SelectName(store_id,"");
                }else {
                    mvpPresenter.SelectName(store_id, s.toString().trim());
                }
            }
        });

        selectNameAdapter = new SelectNameAdapter(this);
        caizhiRc.setAdapter(selectNameAdapter);
        caizhiRc.setLayoutManager(new LinearLayoutManager(this));

        selectNameAdapter.huidiao((view, position) -> {
            Intent intent=new Intent();
            SelectNameMode.DataBean.ListBean listBean = list.get(position);
            intent.putExtra("user",listBean);
            setResult(112,intent);
            finish();
        });

    }

    @Override
    protected SelectNamePresenter createPresenter() {
        return new SelectNamePresenter(this);
    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.SelectName(store_id,"");
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.et_search_content:
              etSearchContent.setText("");
              break;
      }
    }

    @Override
    public void getDataSuccess(SelectNameMode selectNameMode) {
        statusLayoutManager.showSuccessLayout();
        list = selectNameMode.getData().getList();
        if (list.size()>0){
            selectNameAdapter.setList(list);
            selectNameAdapter.notifyDataSetChanged();
        }else {
            statusLayoutManager.showEmptyLayout();
        }
    }

    @Override
    public void getDataFail(String msg) {
        LinLog.lLog(msg);
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }
}
