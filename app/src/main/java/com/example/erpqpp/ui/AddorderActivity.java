package com.example.erpqpp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.View;

import com.airsaid.pickerviewlibrary.TimePickerView;
import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.AddMode;
import com.example.erpqpp.mvp.mode.MdMode;
import com.example.erpqpp.mvp.mode.SelectNameMode;
import com.example.erpqpp.mvp.mode.XsListMode;
import com.example.erpqpp.mvp.mode.XsproductListMode;
import com.example.erpqpp.mvp.presenter.AddorderPresenter;
import com.example.erpqpp.mvp.view.AddorderView;
import com.example.erpqpp.myview.DialogUtils;
import com.example.erpqpp.myview.Item;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 添加订单
 */
public class AddorderActivity extends MvpActivity<AddorderPresenter> implements AddorderView, TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.add_name)
    Item addName;
    @BindView(R.id.order_date)
    Item orderDate;
    @BindView(R.id.price)
    Item price;
    @BindView(R.id.xiaoshou)
    Item xiaoshou;
    @BindView(R.id.mendain)
    Item mendain;
    @BindView(R.id.delivery_date)
    Item deliveryDate;
    @BindView(R.id.order_id)
    Item orderId;
    @BindView(R.id.addres)
    Item addres;
    @BindView(R.id.tel)
    Item tel;
    @BindView(R.id.peijian)
    Item peijian;
    @BindView(R.id.zuofa)
    Item zuofa;
    @BindView(R.id.remark)
    Item remark;
    @BindView(R.id.item1)
    Item item1;
    @BindView(R.id.fukaun)
    Item fukaun;
    private DialogUtils dialogUtils;
    List<String> mendianlist = new ArrayList<>();
    List<String> fukaunlist = new ArrayList<>();
    List<String> xiaoshoulist = new ArrayList<>();
    private List<XsListMode.OrdersaleBean> ordersale;
    private int xs_id;
    private List<MdMode.StoreBean> store;
    private int mdid;
    private SelectNameMode.DataBean.ListBean user;
    private List<XsproductListMode.DataBean.ListBean> ddlist;
    private String store_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addorder);
        ButterKnife.bind(this);
        initdata();
        dialogUtils = new DialogUtils();

    }

    private void initdata() {
        store_id = (String) SPUtils.get(this, "store_id", "");
        mvpPresenter.xsList(store_id);
        mvpPresenter.mdList(store_id);

        tbvTitlebar.setMainTitle("添加订单");
        tbvTitlebar.setRightTitleText("确定");
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setTitleBarListener(this);


        fukaunlist.add("预付款");
        fukaunlist.add("全额付款");


        //选择日期
        orderDate.setonclickListener(view -> {
            TimePickerView timePickerView =
                    new TimePickerView(AddorderActivity.this, TimePickerView.Type.YEAR_MONTH_DAY);
            timePickerView.setOnTimeSelectListener(
                    date -> {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                        String dateStr = format.format(date);
                        orderDate.setContentText(dateStr);
                    });
            timePickerView.show();
        });

        //选择门店
        mendain.setonclickListener(view -> {
            dialogUtils.setdialog(AddorderActivity.this, mendianlist);
            dialogUtils.setonclickListener(new DialogUtils.setOnclick() {
                @Override
                public void getSeletedItem(String selectname) {
                    mendain.setContentText(selectname);
                }

                @Override
                public void getSeletedndex(int selectindex) {
                    mdid = store.get(selectindex).getId();
                }
            });
        });

        //选择交货日期
        deliveryDate.setonclickListener(view -> {
            TimePickerView timePickerView =
                    new TimePickerView(AddorderActivity.this, TimePickerView.Type.YEAR_MONTH_DAY);
            timePickerView.setOnTimeSelectListener(
                    date -> {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                        String dateStr = format.format(date);
                        deliveryDate.setContentText(dateStr);
                    });
            timePickerView.show();
        });

        //添加产品
        item1.setonclickListener(view -> {
            Intent intent=new Intent(AddorderActivity.this,XsAddproductActivity.class);
            intent.putExtra("ddlist", (Serializable) ddlist);
            startActivityForResult(intent,1002);
        });

        //客户名称
        addName.setonclickListener(view -> {
            Intent intent = new Intent(AddorderActivity.this,SelectNameActivity.class);
            startActivityForResult(intent,1020);
        });

        //付款方式
        fukaun.setonclickListener(view -> {
            dialogUtils.setdialog(AddorderActivity.this, fukaunlist);
            dialogUtils.setonclickListener(new DialogUtils.setOnclick() {
                @Override
                public void getSeletedItem(String selectname) {
                    fukaun.setContentText(selectname);
                    if (selectname.equals("全额付款")) {
                        price.setVisibility(View.GONE);
                    }else {
                        price.setVisibility(View.VISIBLE);
                    }
                }
                @Override
                public void getSeletedndex(int selectindex) {

                }
            });
        });

        //销售列表
        xiaoshou.setonclickListener(view -> {
            dialogUtils.setdialog(AddorderActivity.this, xiaoshoulist);
            dialogUtils.setonclickListener(new DialogUtils.setOnclick() {
                @Override
                public void getSeletedItem(String selectname) {
                    xiaoshou.setContentText(selectname);
                }

                @Override
                public void getSeletedndex(int selectindex) {
                    xs_id = ordersale.get(selectindex).getAdmin_id();
                }
            });
        });
    }

    @Override
    protected AddorderPresenter createPresenter() {
        return new AddorderPresenter(this);
    }

    @Override
    protected void initretry() {

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
        String user_id;
        if(user==null){
            user_id="";
        }else {
            user_id = user.getUser_id()+"";
        }
        mvpPresenter.Addorder(store_id,addName.getContentText().toString().trim(),
                orderDate.getContentText().toString().trim(),
                price.getText().toString().trim(),
                xiaoshou.getContentText().toString().trim(),
                mendain.getContentText().toString().trim(),
                deliveryDate.getContentText().toString().trim(),
                orderId.getText().toString().trim(),
                addres.getText().toString().trim(),
                tel.getText().toString().trim(),
                peijian.getText().toString().trim(),
                zuofa.getText().toString().trim(),
                remark.getText().toString().trim(),price
                ,user_id,
                xs_id+"",
                mdid+"",
                ddlist);

    }


    @Override
    public void getXsDataSuccess(XsListMode xsListMode) {
        ordersale = xsListMode.getOrdersale();
        for (int i = 0; i < ordersale.size(); i++) {
            xiaoshoulist.add(ordersale.get(i).getAdmin_name());
        }

    }

    @Override
    public void getMdDataFail(String msg) {
        LinLog.lLog(msg);

    }

    @Override
    public void addDataSuccess(AddMode addMode) {
        if (addMode.getCode()==1){
            finish();
        }
        toastShow(addMode.getMsg());
    }

    @Override
    public void addDataFail(String msg) {
            LinLog.lLog(msg);
    }

    @Override
    public void getMdDataSuccess(MdMode xsListMode) {
        store = xsListMode.getStore();
        for (int i = 0; i < store.size(); i++) {
            mendianlist.add(store.get(i).getStore_name());
        }
    }

    @Override
    public void getXsDataFail(String msg) {
        LinLog.lLog(msg);
    }

    @Override
    public void mytost(String msg) {
        toastShow(msg);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1002&&resultCode==002) {
            ddlist = (List<XsproductListMode.DataBean.ListBean>) data.getSerializableExtra("list");

        }

        if (requestCode==1020&&resultCode==112){
            user = (SelectNameMode.DataBean.ListBean) data.getSerializableExtra("user");
            tel.setText(user.getTel());
            addName.setContentText(user.getUser_name());
            addres.setText(user.getAddress());
        }
    }
}
