package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.adapter.YyqAdapter;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.MgShMode;
import com.example.erpqpp.mvp.mode.Mgbean;
import com.example.erpqpp.mvp.mode.WmMode;
import com.example.erpqpp.mvp.presenter.WarehousePresenter;
import com.example.erpqpp.mvp.view.WarehouseView;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * 已油漆仓库
 */
public class YyqWarehouseActivity extends MvpActivity<WarehousePresenter> implements WarehouseView, TitleBarView.BtnClickListener  {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.et_search_content)
    EditText etSearchContent;
    @BindView(R.id.iv_search_delete)
    ImageView ivSearchDelete;
    @BindView(R.id.stayreceiving_rc)
    RecyclerView stayreceivingRc;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.qx)
    CheckBox qx;
    @BindView(R.id.fahuo)
    TextView fahuo;
    @BindView(R.id.fanhui)
    TextView fanhui;
    @BindView(R.id.rl)
    RelativeLayout rl;
    private boolean isqx=false;
    private YyqAdapter yyqAdapter;
    private int page = 1;
    private String store_id;
    private StatusLayoutManager statusLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yyq_warehouse);
        ButterKnife.bind(this);
        initdata();
    }


    private void initdata() {

        statusLayoutManager = setLayout(refreshLayout);
        statusLayoutManager.showLoadingLayout();

        store_id = (String) SPUtils.get(this, "store_id", "");

        tbvTitlebar.setMainTitle("已油漆仓库");
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setRightTitleText("新增");

        yyqAdapter = new YyqAdapter(this,this);
        stayreceivingRc.setAdapter(yyqAdapter);
        stayreceivingRc.setLayoutManager(new LinearLayoutManager(this));

        ivSearchDelete.setOnClickListener(this);
        qx.setOnClickListener(this);
        fahuo.setOnClickListener(this);
        fahuo.setOnClickListener(this);
        fanhui.setOnClickListener(this);


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
                    mvpPresenter.Warehouse(store_id, page + "");
                } else {
                    mvpPresenter.Warehousesousuo(store_id, page + "",  etSearchContent.getText().toString().trim());
                }
            }
        });


    }

    @Override
    protected WarehousePresenter createPresenter() {
        return new WarehousePresenter(this);
    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.Warehouse(store_id, page + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_delete:
                etSearchContent.setText("");
                break;
            case R.id.qx:
                isqx = !isqx;
                myqx(isqx);
                qx.setChecked(isqx);
                break;
            case R.id.fahuo:
                List<Mgbean> listcount = new ArrayList<>();
                List<WmMode.DataBean> list = yyqAdapter.getList();
                for (int i = 0; i < list.size() ; i++) {
                    if (list.get(i).isIscheck()){
                        Mgbean mgbean=new Mgbean(list.get(i).getId()+"",list.get(i).getStore_id()+"",list.get(i).getUnit_num()+"");
                        listcount.add(mgbean);
                    }
                }
                if (listcount.size() > 0) {
                    Intent intent=new Intent(this,FallActivity.class);
                    intent.putExtra("title","已油漆仓库");
                    intent.putExtra("type","油漆发货");
                    intent.putExtra("listcount", (Serializable) listcount);
                    startActivityForResult(intent,666);
                } else {
                    toastShow("请您至少选择一个");
                }
                break;
            case R.id.fanhui:
                List<Mgbean> listcount1=new ArrayList<>();
                List<WmMode.DataBean> list1 = yyqAdapter.getList();
                for (int i = 0; i <list1.size() ; i++) {
                    if (list1.get(i).isIscheck()){
                        Mgbean mgbean=new Mgbean(list1.get(i).getId()+"",list1.get(i).getStore_id()+"",list1.get(i).getUnit_num()+"");
                        listcount1.add(mgbean);
                    }
                }
                if (listcount1.size() > 0) {
                    Intent intent=new Intent(this,FallActivity.class);
                    intent.putExtra("title","已油漆仓库");
                    intent.putExtra("type","油漆退回");
                    intent.putExtra("listcount", (Serializable) listcount1);
                    startActivityForResult(intent,666);
                } else {
                    toastShow("请您至少选择一个");
                }
                break;
        }
    }


    private void myqx(boolean flag) {
        List<WmMode.DataBean> list = yyqAdapter.getList();
        if (list!=null) {
            for (int i = 0; i <list.size(); i++) {
                list.get(i).setIscheck(flag);
            }
        }
        yyqAdapter.notifyDataSetChanged();
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
        Bundle bundle=new Bundle();
        bundle.putString("title","已油漆仓库");
        startActivity(AddActivity.class,bundle);
    }




    @Override
    public void getDataSuccess(WmMode wmMode) {
        statusLayoutManager.showSuccessLayout();
        List<WmMode.DataBean> WmModeData = wmMode.getData();
        if (WmModeData.size() > 0) {
            fahuo.setEnabled(true);
            fanhui.setEnabled(true);
            qx.setEnabled(true);

            if (page == 1) {
                yyqAdapter.setList(WmModeData);
                yyqAdapter.notifyDataSetChanged();
            } else {
                yyqAdapter.getList().addAll(WmModeData);
                yyqAdapter.notifyDataSetChanged();
            }
        } else {
            fahuo.setEnabled(false);
            fanhui.setEnabled(false);
            qx.setEnabled(false);
            if (page == 1 && WmModeData.size() == 0) {
                statusLayoutManager.showEmptyLayout();
            } else {
                page--;
                toastShow("没有更多数据了");
                fahuo.setEnabled(true);
                fanhui.setEnabled(true);
                qx.setEnabled(true);
            }
        }
    }

    @Override
    public void getDataFail(String msg) {
        //加载失败
        statusLayoutManager.showErrorLayout();
        LinLog.lLog(msg);
    }

    @Override
    public void delectDataSuccess(MgMode mgMode) {
       toastShow(mgMode.getMsg());
    }

    @Override
    public void delectDataFail(String msg) {
        LinLog.lLog(msg);
    }


    /**
     * 刷新加载
     */
    private void addListener() {

        refreshLayout.setOnRefreshListener(refreshlayout -> refreshlayout.finishRefresh());


        //加载更多
        refreshLayout.setOnLoadmoreListener(refreshlayout -> {
            page++;
            if (etSearchContent.getText().toString().isEmpty()) {
                mvpPresenter.Warehouse(store_id, page + "");
            } else {
                mvpPresenter.Warehousesousuo(store_id, page + "", etSearchContent.getText().toString().trim());
            }
            refreshlayout.finishLoadmore();
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1001&&resultCode==001){
            List<WmMode.DataBean> WmModeData = yyqAdapter.getList();
            String count = data.getStringExtra("count");
            String select = data.getStringExtra("select");
            WmModeData.get(Integer.parseInt(select)).setUnit_num(Integer.parseInt(count));
            yyqAdapter.notifyDataSetChanged();
        }
        if (requestCode==666&&resultCode==888){
            store_id = (String) SPUtils.get(this, "store_id", "");
            mvpPresenter.Warehouse(store_id, page + "");
            qx.setChecked(false);
        }
    }

    public void delectproduct(String store_id,String admin_id,String id){
          mvpPresenter.yqdelect(store_id,admin_id,id);
    }

    public void setcheckbox(boolean setcb){
        qx.setChecked(setcb);
    }
}
