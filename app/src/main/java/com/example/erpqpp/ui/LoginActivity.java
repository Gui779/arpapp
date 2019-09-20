package com.example.erpqpp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.LoginMode;
import com.example.erpqpp.mvp.presenter.LoginPresenter;
import com.example.erpqpp.mvp.view.LoginView;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.tv_login_type)
    TextView tvLoginType;
    @BindView(R.id.et_login_code)
    EditText etLoginCode;
    @BindView(R.id.tv_login_code)
    TextView tvLoginCode;
    @BindView(R.id.tv_login_change)
    TextView tvLoginChange;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.ly)
    LinearLayout ly;
    private boolean isPwd = false;
    private StatusLayoutManager statusLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        ButterKnife.bind(this);
        initdata();
        String tel = (String) SPUtils.get(LoginActivity.this, "tel", "");
        String pwd = (String) SPUtils.get(LoginActivity.this, "pwd", "");
        etLoginPhone.setText(tel);
        etLoginCode.setText(pwd);

    }

    private void initdata() {
        statusLayoutManager = setLayout(ly);

        tvLoginChange.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        tvLoginCode.setOnClickListener(this);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initretry() {

    }


    @Override
    public void getDataSuccess(LoginMode loginMode) {
        statusLayoutManager.showSuccessLayout();
        SPUtils.put(LoginActivity.this, "tel", etLoginPhone.getText().toString().trim());
        SPUtils.put(LoginActivity.this, "pwd", etLoginCode.getText().toString().trim());

        if (loginMode.getCode().equals("1")) {
            Bundle bundle = new Bundle();
            bundle.putString("type", loginMode.getData().getRole_id()+"");
            SPUtils.put(LoginActivity.this, "store_id", loginMode.getData().getStore_id()+"");
            SPUtils.put(LoginActivity.this, "admin_id", loginMode.getData().getAdmin_id()+"");
            startActivity(MainActivity.class, bundle);
            finish();
        }else {
            toastShow(loginMode.getMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        statusLayoutManager.showSuccessLayout();
        LinLog.lLog(msg);
    }

    @Override
    public void mytoast(String msg) {
        toastShow(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_change:
                isPwd = !isPwd;
                if (isPwd) {
                    tvLoginType.setText("密码登录");
                    tvLoginChange.setText("验证码登录");
                    etLoginCode.setHint("请输入密码");
                    tvLoginCode.setVisibility(View.GONE);
                } else {
                    tvLoginType.setText("验证码登录");
                    tvLoginChange.setText("密码登录");
                    etLoginCode.setHint("请输入验证码");
                    tvLoginCode.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.bt_login:
                mvpPresenter.login(etLoginPhone.getText().toString().trim(),
                        etLoginCode.getText().toString().trim(),
                        true);
                break;
            case R.id.tv_login_code:
                mvpPresenter.getcode(etLoginPhone.getText().toString().trim(),
                        tvLoginCode);
                break;
        }
    }
}
