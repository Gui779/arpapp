package com.example.erpqpp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.ManagementListMode;
import com.example.erpqpp.mvp.presenter.ManagementDetailsPresenter;
import com.example.erpqpp.mvp.view.ManagementDetailsView;
import com.example.erpqpp.myview.Item;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 客户管理详情
 **/
public class ManagementDetailsActivity extends MvpActivity<ManagementDetailsPresenter> implements ManagementDetailsView, TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.name)
    Item name;
    @BindView(R.id.number)
    Item number;
    @BindView(R.id.card)
    Item card;
    @BindView(R.id.date)
    Item date;
    @BindView(R.id.tel)
    Item tel;
    @BindView(R.id.addres)
    Item addres;
    @BindView(R.id.tel_bei)
    Item telBei;
    @BindView(R.id.region)
    Item region;
    @BindView(R.id.mendain_date)
    Item mendainDate;
    @BindView(R.id.type)
    Item type;
    @BindView(R.id.beizhu)
    TextView beizhu;
    @BindView(R.id.birthday)
    Item birthday;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_details);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        tbvTitlebar.setMainTitle("客户管理");
        tbvTitlebar.setTitleBarListener(this);
        Intent intent = getIntent();
        user_id = intent.getStringExtra("type");
        mvpPresenter.getuselist(user_id);

    }

    @Override
    protected ManagementDetailsPresenter createPresenter() {
        return new ManagementDetailsPresenter(this);
    }

    @Override
    protected void initretry() {
        mvpPresenter.getuselist(user_id);
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


    @Override
    public void getDataSuccess(ManagementListMode managementListMode) {
        List<ManagementListMode.DataBean> data = managementListMode.getData();
        if (data.size() > 0) {
            name.setContentText(data.get(0).getUser_name());
            number.setContentText(data.get(0).getNumber());
            card.setContentText(data.get(0).getIdcard());
            tel.setContentText(data.get(0).getTel());
            addres.setContentText(data.get(0).getAddress());
            telBei.setContentText(data.get(0).getPhone());
            region.setContentText(data.get(0).getRegion());
            mendainDate.setContentText(data.get(0).getCreate_time());
            beizhu.setText(data.get(0).getNote());
            birthday.setContentText(data.get(0).getBirthday());
            String stringtype = data.get(0).getType();
            switch (stringtype) {
                case "1":
                    type.setContentText("零售");
                    break;
                case "2":
                    type.setContentText("批发");
                    break;
                case "3":
                    type.setContentText("加盟商");
                    break;
            }
        }
    }

    @Override
    public void getDataFail(String msg) {
        LinLog.lLog(msg);
    }
}
