package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.CkMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.Mgbean;
import com.example.erpqpp.mvp.presenter.FallPresenter;
import com.example.erpqpp.mvp.view.FallView;
import com.example.erpqpp.myview.DialogUtils;
import com.example.erpqpp.myview.TitleBarView;
import com.example.erpqpp.utils.MyListToString;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * 调货  发货
 */
public class FallActivity extends MvpActivity<FallPresenter> implements FallView, TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.renyuan)
    TextView renyuan;
    @BindView(R.id.rl_renyuan)
    RelativeLayout rlRenyuan;
    @BindView(R.id.ly)
    LinearLayout ly;
    private DialogUtils dialogUtils;
    private StatusLayoutManager statusLayoutManager;
    private List<Mgbean> listcount;
    private String store_id;
    private List<CkMode.DataBean> data;
    List<String> list = new ArrayList<>();
    List<String> return_num = new ArrayList<>();
    private String returnnum;
    private String spostid;
    private String type;
    private int warehouse_id;
    private String admin_id;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fall);
        ButterKnife.bind(this);

        statusLayoutManager = setLayout(ly);
        statusLayoutManager.showLoadingLayout();

        store_id = (String) SPUtils.get(this, "store_id", "");
        admin_id = (String) SPUtils.get(this, "admin_id", "");

        intent = getIntent();
        listcount = (List<Mgbean>) intent.getSerializableExtra("listcount");
        if (listcount!=null){
            for (int i = 0; i <listcount.size() ; i++) {
                return_num.add(listcount.get(i).getId()+"|"+listcount.get(i).getCount());
            }
        }

        dialogUtils = new DialogUtils();
        initdata();
    }

    private void initdata() {

        type = intent.getStringExtra("type");
        tbvTitlebar.setMainTitle(type);
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setRightTitleText("确定");

        rlRenyuan.setOnClickListener(this);

        returnnum = MyListToString.setbanner(return_num);

    }

    @Override
    protected FallPresenter createPresenter() {
        return new FallPresenter(this);
    }

    @Override
    protected void initretry() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.getWarehousing(store_id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_renyuan:
                dialogUtils.setdialog(FallActivity.this, list);
                dialogUtils.setonclickListener(new DialogUtils.setOnclick() {
                    @Override
                    public void getSeletedItem(String selectname) {
                        renyuan.setText(selectname);
                    }

                    @Override
                    public void getSeletedndex(int selectindex) {
                    }
                });

                break;
        }
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

        int i = list.indexOf(renyuan.getText());
        if (i!=-1){
            warehouse_id = data.get(i).getWarehouse_id();
        }

        if (type.equals("油漆发货")){
            mvpPresenter.YqFh(renyuan.getText().toString().trim(),store_id,admin_id,warehouse_id+"",returnnum);
        }else if (type.equals("油漆退回")){
            mvpPresenter.YqCh(renyuan.getText().toString().trim(),store_id,admin_id,returnnum);
        }else if (type.equals("成品仓库退回")){
            mvpPresenter.CpCh(renyuan.getText().toString().trim(),store_id,admin_id,returnnum);
        }else if (type.equals("成品仓库调货")){
            mvpPresenter.CpDh(renyuan.getText().toString().trim(),store_id,admin_id,warehouse_id+"",returnnum);
        }else if (type.equals("未磨发货")){
           mvpPresenter.WmFh(renyuan.getText().toString().trim(),store_id,admin_id,warehouse_id+"",returnnum);
        }else if (type.equals("未磨退回")){
         mvpPresenter.WmCh(renyuan.getText().toString().trim(),admin_id,warehouse_id+"",returnnum);
        } else if (type.equals("已磨发货")){
            LinLog.lLog(store_id+"---"+admin_id+"---"+warehouse_id+"---"+returnnum);
            mvpPresenter.YmFh(renyuan.getText().toString().trim(),store_id,admin_id,warehouse_id+"",returnnum);
        }else if (type.equals("已磨退回")){
            mvpPresenter.YmCh(renyuan.getText().toString().trim(),admin_id,warehouse_id+"",returnnum);
        }
    }


    @Override
    public void getDataSuccess(CkMode ckMode) {
        statusLayoutManager.showSuccessLayout();
        data = ckMode.getData();
         if (data.size()>0){
             for (int i = 0; i < data.size(); i++) {
                 list.add(data.get(i).getWarehousename());
             }
         }
    }

    @Override
    public void getDataFail(String msg) {
        statusLayoutManager.showEmptyLayout();
        LinLog.lLog(msg);
    }

    @Override
    public void mytoast(String msg) {
        toastShow(msg);
    }

    @Override
    public void getfhDataSuccess(MgMode mgModeMode) {
        if (mgModeMode.getCode().equals("1")){
            toastShow(mgModeMode.getMsg());
            setResult(888);
            finish();
        }else {
            toastShow(mgModeMode.getMsg());
        }
    }

    @Override
    public void getfhDataFail(String msg) {
        LinLog.lLog(msg);
    }
}
