package com.example.erpqpp.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.CzMode;
import com.example.erpqpp.mvp.mode.MgMode;
import com.example.erpqpp.mvp.presenter.FeedbackPresenter;
import com.example.erpqpp.mvp.view.FeedbackView;
import com.example.erpqpp.myview.TitleBarView;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 意见反馈
 */
public class FeedbackActivity extends MvpActivity<FeedbackPresenter> implements FeedbackView, TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.feedback_content)
    EditText feedbackContent;
    @BindView(R.id.bt_send)
    Button btSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        tbvTitlebar.setMainTitle("意见反馈");
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setTitleBarListener(this);

        btSend.setOnClickListener(this);
    }

    @Override
    protected FeedbackPresenter createPresenter() {
        return new FeedbackPresenter(this);
    }

    @Override
    protected void initretry() {

    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.bt_send:
            String tel = (String) SPUtils.get(FeedbackActivity.this, "tel", "");
            mvpPresenter.Feedback(feedbackContent.getText().toString().trim(),tel);
            break;
    }
    }

    @Override
    public void getDataSuccess(CzMode mgMode) {
        if (mgMode.getCode().equals("1")){
            finish();
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
