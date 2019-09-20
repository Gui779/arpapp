package com.example.erpqpp.ui;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpqpp.R;
import com.example.erpqpp.fragment.FunctionFragment;
import com.example.erpqpp.fragment.GranarymanageFragment;
import com.example.erpqpp.fragment.MeFragment;
import com.example.erpqpp.fragment.NewsFragment;
import com.lbb.mvplibrary.base.BaseActivity;
import com.lbb.mvplibrary.utils.LinLog;
import com.next.easynavigation.view.EasyNavigationBar;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class MainActivity extends BaseActivity {


    @BindView(R.id.navigationBar)
    EasyNavigationBar navigationBar;

    private String[] tabText = {"常用功能", "消息", "个人中心"};
    //未选中icon
    private int[] normalIcon = {R.drawable.gongneng, R.drawable.msg, R.drawable.me};

    //选中时icon
    private int[] selectIcon = {R.drawable.select_gongnneg, R.drawable.select_msg, R.drawable.select_me};

    private List<Fragment> fragments = new ArrayList<>();
    private long exitTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initcamer();

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        //销售
        if (type.equals("4")){
            fragments.add(new FunctionFragment());
            fragments.add(new NewsFragment());
            fragments.add(new MeFragment());
        //仓管
        }else if (type.equals("5")||type.equals("6")){
            fragments.add(new GranarymanageFragment());
            fragments.add(new NewsFragment());
            fragments.add(new MeFragment());
        }

        navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .selectTextColor(Color.parseColor("#42a1f7"))
                .normalTextColor(Color.parseColor("#666666"))
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .canScroll(true)
                .build();

    }


    @Override
    public void onClick(View v) {

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
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
    protected void onDestroy() {
        super.onDestroy();
        LinLog.lLog("我走了");
    }
}
