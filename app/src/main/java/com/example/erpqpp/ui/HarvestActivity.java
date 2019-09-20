package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airsaid.pickerviewlibrary.TimePickerView;
import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.AddclientMode;
import com.example.erpqpp.mvp.mode.ColorBean;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.mode.Mgbean;
import com.example.erpqpp.mvp.mode.WorkerMode;
import com.example.erpqpp.mvp.presenter.HarvestPresenter;
import com.example.erpqpp.mvp.view.HarvestView;
import com.example.erpqpp.myview.DialogUtils;
import com.example.erpqpp.myview.TitleBarView;
import com.example.erpqpp.utils.MyListToString;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * 收货
 */
public class HarvestActivity extends MvpActivity<HarvestPresenter> implements HarvestView, TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.renyuan)
    TextView renyuan;
    @BindView(R.id.rl_renyuan)
    RelativeLayout rlRenyuan;
    @BindView(R.id.rl_date)
    RelativeLayout rlDate;
    @BindView(R.id.sh_date)
    TextView shDate;
    @BindView(R.id.ly)
    LinearLayout ly;
    private DialogUtils dialogUtils;
    private String title;
    private List<Mgbean> listcount;
    List<String> return_num = new ArrayList<>();
    List<String> postid = new ArrayList<>();
    List<String> colorname = new ArrayList<>();
    List<String> colorid = new ArrayList<>();
    private String returnnum;
    private String spostid;
    private String type;
    private StatusLayoutManager statusLayoutManager;
    List<String> list = new ArrayList<>();
    private List<WorkerMode.DataBean> workdata;
    private int worker_id;
    private List<ColorBean> listcolor;
    private String postcolorname;
    private String postcolorid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harvest);
        ButterKnife.bind(this);
        dialogUtils = new DialogUtils();
        initdata();
    }

    private void initdata() {

        statusLayoutManager = setLayout(ly);
         statusLayoutManager.showLoadingLayout();


        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        type = intent.getStringExtra("type");
        tbvTitlebar.setMainTitle(type);
        tbvTitlebar.setTitleBarListener(this);
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setRightTitleText("确定");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String str = sdf.format(new Date());
        shDate.setText(str);

        rlRenyuan.setOnClickListener(this);
        rlDate.setOnClickListener(this);

        listcount = (List<Mgbean>) intent.getSerializableExtra("listcount");
        if (listcount!=null) {
            for (int i = 0; i < listcount.size(); i++) {
                return_num.add(listcount.get(i).getCount());
                postid.add(listcount.get(i).getId());
            }
        }

        listcolor = (List<ColorBean>) intent.getSerializableExtra("listcolor");
        if (listcolor!=null){
            for (int i = 0; i <listcolor.size() ; i++) {
                colorname.add(listcolor.get(i).getColorname());
                colorid.add(listcolor.get(i).getColorid());
            }
        }

        returnnum = MyListToString.setbanner(return_num);
        spostid = MyListToString.setbanner(postid);
        postcolorname = MyListToString.setbanner(colorname);
        postcolorid = MyListToString.setbanner(colorid);

       mvpPresenter.getmguser(listcount.get(0).getStore_id());

    }

    @Override
    protected HarvestPresenter createPresenter() {
        return new HarvestPresenter(this);
    }

    @Override
    protected void initretry() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_renyuan:
                dialogUtils.setdialog(HarvestActivity.this, list);
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

            case R.id.rl_date:
                TimePickerView timePickerView =
                        new TimePickerView(HarvestActivity.this, TimePickerView.Type.ALL);
                timePickerView.setOnTimeSelectListener(
                        new TimePickerView.OnTimeSelectListener() {
                            @Override
                            public void onTimeSelect(Date date) {
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.CHINA);
                                String dateStr = format.format(date);
                                shDate.setText(dateStr);
                            }
                        });
                timePickerView.show();
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
            worker_id = workdata.get(i).getWorker_id();
        }
        String admin_id = (String) SPUtils.get(HarvestActivity.this, "admin_id", "");

        if (type.equals("木工收货")) {
            mvpPresenter.Harvest(renyuan.getText().toString().trim(), shDate.getText().toString(),
                    listcount.get(0).getStore_id()+"", worker_id+"",returnnum,spostid);
        } else if (type.equals("木工待发货撤回")) {
            mvpPresenter.Harveststayfahuochehui(renyuan.getText().toString().trim(),listcount.get(0).getStore_id()+"",
                    admin_id,returnnum ,spostid);
        } else if (type.equals("木工发货")) {
            LinLog.lLog(returnnum);
            mvpPresenter.Harvestfahuo(renyuan.getText().toString().trim(), shDate.getText().toString(),
                    listcount.get(0).getStore_id()+"", worker_id+"",returnnum,spostid);
        }else if (type.equals("木工撤回")) {
            mvpPresenter.Harvestfahuochehui(renyuan.getText().toString().trim(),listcount.get(0).getStore_id()+"",
           admin_id,returnnum ,spostid);
        }else if (type.equals("打磨收货")){
            mvpPresenter.getreceive(renyuan.getText().toString().trim(),listcount.get(0).getStore_id()+"",
                    worker_id+"",shDate.getText().toString(),returnnum,spostid);
        }else if (type.equals("打磨退回")){
          mvpPresenter.getreceive_revoke(renyuan.getText().toString().trim(),listcount.get(0).getStore_id()+"",
                  spostid,returnnum,admin_id);
        }else if (type.equals("打磨发货")){
          mvpPresenter.DmSend(renyuan.getText().toString().trim(),listcount.get(0).getStore_id()+"",
                  worker_id+"",shDate.getText().toString(),returnnum,spostid);
        }else if (type.equals("打磨待发货撤回")){
            mvpPresenter.dmdsend_revoke(renyuan.getText().toString().trim(),listcount.get(0).getStore_id()+"",
                    spostid,returnnum,admin_id);
        }else if (type.equals("打磨发货撤回")){
            mvpPresenter.dmSend_revoke(renyuan.getText().toString().trim(),listcount.get(0).getStore_id()+"",
                    spostid,returnnum,admin_id);
        }else if (type.equals("上漆收货")){
            mvpPresenter.Sqreceive(renyuan.getText().toString().trim(),listcount.get(0).getStore_id()+"",
                    worker_id+"",shDate.getText().toString(),returnnum,spostid);
        }else if (type.equals("上漆退回")){
            mvpPresenter.Sqreceive_revoke(renyuan.getText().toString().trim(),listcount.get(0).getStore_id()+"",
                    spostid,returnnum,admin_id);
        }else if (type.equals("上漆待发货撤回")){
           mvpPresenter.Sqsend_revoke(renyuan.getText().toString().trim(),listcount.get(0).getStore_id()+"",
                   spostid,returnnum,admin_id );
        }else if (type.equals("上漆发货")){
          mvpPresenter.Sqsend(renyuan.getText().toString().trim(),listcount.get(0).getStore_id(),worker_id+"",
                  shDate.getText().toString() ,returnnum,admin_id,postcolorid,postcolorname);
        }else if (type.equals("上漆发货撤回")){
          mvpPresenter.Sqsendrevoke(renyuan.getText().toString().trim(),listcount.get(0).getStore_id(),spostid,
                  returnnum,admin_id);
        }
    }


    @Override
    public void getDataSuccess(WorkerMode workerMode) {
        statusLayoutManager.showSuccessLayout();
        workdata = workerMode.getData();
        if (workdata.size()>0){
            for (int i = 0; i < workdata.size() ; i++) {
                list.add(workdata.get(i).getWorker_name());
            }

        }


    }

    @Override
    public void getDataFail(String msg) {
        statusLayoutManager.showEmptyLayout();
        LinLog.lLog(msg);
    }

    @Override
    public void getmgDataSuccess(MgMode workerMode) {
        if (workerMode.getCode().equals("1")){
            setResult(888);
            finish();
            toastShow(workerMode.getMsg());
        }else {
            toastShow(workerMode.getMsg());
            LinLog.lLog(workerMode.getMsg());
        }
    }

    @Override
    public void gemgtDataFail(String msg) {
        LinLog.lLog(msg);
    }

    @Override
    public void mytoast(String msg) {
        toastShow(msg);
    }
}
