package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.TextureManageAdapter;
import com.example.erpqpp.mvp.mode.TextureManageMode;
import com.example.erpqpp.mvp.mode.XzCzMode;
import com.example.erpqpp.mvp.presenter.TextureManagePresenter;
import com.example.erpqpp.mvp.view.TextureManageView;
import com.example.erpqpp.myview.SwipeItemLayout;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * 材质管理
 */
public class TextureManageActivity extends MvpActivity<TextureManagePresenter> implements TextureManageView , TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.et_search_content)
    EditText etSearchContent;
    @BindView(R.id.iv_search_delete)
    ImageView ivSearchDelete;
    @BindView(R.id.texturemanage_rc)
    RecyclerView texturemanageRc;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private TextureManageAdapter textureManageAdapter;
    private StatusLayoutManager statusLayoutManager;
    private String store_id;
    private int page=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texture_manage);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {

        statusLayoutManager = setLayout(refreshLayout);
        statusLayoutManager.showLoadingLayout();

        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setMainTitle("材质管理");
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setRightTitleText("添加材质");

        textureManageAdapter = new TextureManageAdapter(this,this);
        texturemanageRc.setAdapter(textureManageAdapter);
        texturemanageRc.setLayoutManager(new LinearLayoutManager(this));
        texturemanageRc.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));

        ivSearchDelete.setOnClickListener(this);

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
                    mvpPresenter.woodSale(store_id, page + "");
                } else {
                    mvpPresenter.woodSalesousuo(store_id, page + "", etSearchContent.getText().toString().trim());
                }
            }
        });
    }


    @Override
    protected TextureManagePresenter createPresenter() {
        return new TextureManagePresenter(this);
    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.woodSale(store_id,page+"");
    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.iv_search_delete:
             etSearchContent.setText("");
             break;

             default:break;
     }
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
        Intent intent = new Intent(this, UpManageActivity.class);
        intent.putExtra("title","添加材质");
        startActivity(intent);
    }


    @Override
    public void getDataSuccess(TextureManageMode textureManageMode) {
        statusLayoutManager.showSuccessLayout();
        List<TextureManageMode.DataBean> data = textureManageMode.getData();
        if (data.size() > 0) {
            if (page == 1) {
                textureManageAdapter.setList(data);
                textureManageAdapter.notifyDataSetChanged();
            } else {
                textureManageAdapter.getList().addAll(data);
                textureManageAdapter.notifyDataSetChanged();
            }
        } else {
            if (page == 1 && data.size() == 0) {
                statusLayoutManager.showEmptyLayout();
            } else {
                page--;
                toastShow("没有更多数据了");

            }
        }
    }

    @Override
    public void delectDataSuccess(XzCzMode xzCzMode) {
        toastShow(xzCzMode.getMessage());
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
                mvpPresenter.woodSale(store_id, page + "");
            } else {
                mvpPresenter.woodSalesousuo(store_id, page + "", etSearchContent.getText().toString().trim());
            }
            refreshlayout.finishRefresh();
        });


        //加载更多
        refreshLayout.setOnLoadmoreListener(refreshlayout -> {
            page++;
            if (etSearchContent.getText().toString().isEmpty()) {
                mvpPresenter.woodSale(store_id, page + "");
            } else {
                mvpPresenter.woodSalesousuo(store_id, page + "", etSearchContent.getText().toString().trim());
            }
            refreshlayout.finishLoadmore();
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.woodSale(store_id, page + "");
    }

    public void delectWood(String wood_id){
      mvpPresenter.delectWood(wood_id);
    }

}
