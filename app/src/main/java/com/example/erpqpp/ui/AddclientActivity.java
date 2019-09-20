package com.example.erpqpp.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.airsaid.pickerviewlibrary.TimePickerView;
import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.AddclientMode;
import com.example.erpqpp.mvp.presenter.AddclientPresenter;
import com.example.erpqpp.mvp.view.AddclientView;
import com.example.erpqpp.myview.DialogUtils;
import com.example.erpqpp.myview.Item;
import com.example.erpqpp.myview.TitleBarView;
import com.example.erpqpp.utils.IDCardsUtils;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;
import com.lbb.mvplibrary.utils.Validator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 添加客户
 */
public class AddclientActivity extends MvpActivity<AddclientPresenter> implements AddclientView, TitleBarView.BtnClickListener {


    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.add_tv)
    TextView addTv;
    @BindView(R.id.add_name)
    Item addName;
    @BindView(R.id.tv_item_name)
    TextView tvItemName;
    @BindView(R.id.input_card)
    EditText inputCard;
    @BindView(R.id.add_date)
    Item addDate;
    @BindView(R.id.tel_tv)
    TextView telTv;
    @BindView(R.id.add_tel)
    Item addTel;
    @BindView(R.id.addres_tv)
    TextView addresTv;
    @BindView(R.id.add_addres)
    Item addAddres;
    @BindView(R.id.add_tel_bz)
    Item addTelBz;
    @BindView(R.id.region_tv)
    TextView regionTv;
    @BindView(R.id.add_region)
    Item addRegion;
    @BindView(R.id.add_shopdate)
    Item addShopdate;
    @BindView(R.id.type_tv)
    TextView typeTv;
    @BindView(R.id.add_type)
    Item addType;
    @BindView(R.id.add_remark)
    Item addRemark;
    @BindView(R.id.add_bt)
    Button addBt;
    private DialogUtils dialogUtils;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addclient);
        ButterKnife.bind(this);
        dialogUtils = new DialogUtils();
        initdata();
    }

    private void initdata() {
        tbvTitlebar.setMainTitle("添加客户");
        tbvTitlebar.setTitleBarListener(this);

        addBt.setOnClickListener(this);


        inputCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 18) {
                    if (!IDCardsUtils.isIDNumber(s.toString().trim())) {
                        toastShow("身份证号码输入不正确");
                    }else {
                        Map<String, Object> carInfo = IDCardsUtils.getCarInfo(s.toString().trim());
                        String birthday = (String) carInfo.get("birthday");
                        addDate.setContentText(birthday);
                    }
                }
            }
        });


       /* addDate.setonclickListener(new Item.setOnclick() {
            @Override
            public void onclick(View view) {
                TimePickerView timePickerView =
                        new TimePickerView(AddclientActivity.this, TimePickerView.Type.YEAR_MONTH_DAY);
                timePickerView.setOnTimeSelectListener(
                        new TimePickerView.OnTimeSelectListener() {
                            @Override
                            public void onTimeSelect(Date date) {
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                                String dateStr = format.format(date);
                                addDate.setContentText(dateStr);
                            }
                        });
                timePickerView.show();
            }
        });*/

        list = new ArrayList<>();
        list.add("零售");
        list.add("批发");
        list.add("加盟商");

        addType.setonclickListener(view -> {
            dialogUtils.setdialog(AddclientActivity.this, list);
            dialogUtils.setonclickListener(new DialogUtils.setOnclick() {
                @Override
                public void getSeletedItem(String selectname) {
                    addType.setContentText(selectname);
                }

                @Override
                public void getSeletedndex(int selectindex) {

                }
            });
        });

        addShopdate.setonclickListener(view -> {
            TimePickerView timePickerView =
                    new TimePickerView(AddclientActivity.this, TimePickerView.Type.YEAR_MONTH_DAY);
            timePickerView.setOnTimeSelectListener(
                    new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                            String dateStr = format.format(date);
                            addShopdate.setContentText(dateStr);
                        }
                    });
            timePickerView.show();
        });


    }


    @Override
    protected AddclientPresenter createPresenter() {
        return new AddclientPresenter(this);
    }

    @Override
    protected void initretry() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_bt:
                String store_id = (String) SPUtils.get(this, "store_id", "");
                mvpPresenter.Addclient(store_id, addName.getText().toString().trim(),
                        inputCard.getText().toString().trim(),
                        addDate.getContentText().toString().trim(),
                        addTel.getText().toString().trim(),
                        addAddres.getText().toString().trim(),
                        addTelBz.getText().toString().trim(),
                        addRegion.getText().toString().trim(),
                        addShopdate.getContentText().toString().trim(),
                        addType.getContentText().toString().trim(),
                        addRemark.getText().toString().trim()
                );
                break;

        }
    }

    @Override
    public void getDataSuccess(AddclientMode addclientMode) {
        if (addclientMode.getStatus() == 1) {
            finish();
            toastShow(addclientMode.getMsg());
        } else {
            toastShow(addclientMode.getMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        LinLog.lLog(msg);
    }

    @Override
    public void mytoast(String msg) {
        toastShow(msg);
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }


}
