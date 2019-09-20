package com.example.erpqpp.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.LogOutMode;
import com.example.erpqpp.mvp.mode.MeMode;
import com.example.erpqpp.mvp.presenter.PersonagePresenter;
import com.example.erpqpp.mvp.view.ManagementView;
import com.example.erpqpp.mvp.view.PersonageView;
import com.example.erpqpp.myview.Item;
import com.example.erpqpp.myview.TitleBarView;
import com.example.erpqpp.utils.AvatarStudio;
import com.lbb.mvplibrary.app.AppManager;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhl.cbdialog.CBDialogBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 个人主页
 */
public class PersonageActivity extends MvpActivity<PersonagePresenter> implements PersonageView, TitleBarView.BtnClickListener {

    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.updata)
    RelativeLayout updata;
    @BindView(R.id.name)
    Item name;
    @BindView(R.id.sex)
    Item sex;
    @BindView(R.id.bt)
    Button bt;
    @BindView(R.id.header)
    ImageView contactHeader;
    private AvatarStudio.Builder builder;
    private String tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personage);
        ButterKnife.bind(this);
        initcamer();
        initdata();
    }

    private void initdata() {

        builder = new AvatarStudio.Builder(PersonageActivity.this).needCrop(false)
                //第三个参数为包名.fileprovider  必填
                .setText("打开相机", "从相册中选取", "取消", "com.example.erpqpp.fileprovider");

        tbvTitlebar.setMainTitle("个人主页");
        tbvTitlebar.setRightTitleColor(Color.WHITE);
        tbvTitlebar.setTitleBarListener(this);
        bt.setOnClickListener(this);
        updata.setOnClickListener(this);

        tel = (String) SPUtils.get(this, "tel", "");
        mvpPresenter.getuser(tel);

    }

    @Override
    protected PersonagePresenter createPresenter() {
        return new PersonagePresenter(this);
    }

    @Override
    protected void initretry() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt:
                new CBDialogBuilder(PersonageActivity.this)
                        .setTouchOutSideCancelable(true)
                        .showCancelButton(true)
                        .setTitle("确认退出")
                        .setMessage("确认退出登录状态，将返回登录页面,请确认!")
                        .setConfirmButtonText("确定")
                        .setCancelButtonText("取消")
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .setButtonClickListener(true, new CBDialogBuilder.onDialogbtnClickListener() {
                            @Override
                            public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                                switch (whichBtn) {
                                    case BUTTON_CONFIRM:
                                        String admin_id = (String) SPUtils.get(PersonageActivity.this, "admin_id", "");
                                        mvpPresenter.Logout(admin_id);
                                        break;
                                    case BUTTON_CANCEL:
                                        // Toast.makeText(context, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                                        break;
                                    default:
                                        break;
                                }
                            }
                        })
                        .create().show();
                break;
            case R.id.updata:
              /*  builder.show(new AvatarStudio.CallBack() {
                    @Override
                    public void callback(String uri) {
                        contactHeader.setImageURI(Uri.parse(uri));
                    }
                });*/
                break;
        }
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }



    private void initcamer() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            RxPermissions permissions = new RxPermissions(this);
            permissions.setLogging(true);
            Disposable subscribe = permissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA
                    , Manifest.permission.RECORD_AUDIO, Manifest.permission.WAKE_LOCK,
                    Manifest.permission.MODIFY_AUDIO_SETTINGS, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
                    Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE
            )
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                            if (aBoolean) {
                            }
                        }
                    });
        }
    }

    @Override
    public void getDataSuccess(LogOutMode logOutMode) {
        if (logOutMode.getCode().equals("1")){
            SPUtils.clear(PersonageActivity.this);
            startActivity(LoginActivity.class);
            AppManager.getInstance().killAllActivity();
        }
    }

    @Override
    public void getDataFail(String msg) {
        LinLog.lLog(msg);
    }

    @Override
    public void getuserDataSuccess(MeMode meMode) {
        MeMode.MobileBean mobile = meMode.getMobile();
        if (mobile!=null){
            name.setContentText(mobile.getAdmin_name()+" "+mobile.getRole_name());
            if (mobile.getSex()==1){
                sex.setContentText("男");
            }else {
                sex.setContentText("女");
            }
        }
    }

    @Override
    public void getuserDataFail(String msg) {
        LinLog.lLog(msg);
    }
}
