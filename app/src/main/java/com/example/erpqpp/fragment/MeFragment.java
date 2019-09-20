package com.example.erpqpp.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.erpqpp.R;
import com.example.erpqpp.mvp.mode.MeMode;
import com.example.erpqpp.mvp.presenter.MePresenter;
import com.example.erpqpp.mvp.view.Meview;
import com.example.erpqpp.myview.TitleBarView;
import com.example.erpqpp.ui.FeedbackActivity;
import com.example.erpqpp.ui.LoginActivity;
import com.example.erpqpp.ui.MainActivity;
import com.example.erpqpp.ui.PersonageActivity;
import com.example.erpqpp.ui.PlatformActivity;
import com.example.erpqpp.utils.VersionsUpdata;
import com.lbb.mvplibrary.base.BaseFragment;
import com.lbb.mvplibrary.base.MvpActivity;
import com.lbb.mvplibrary.base.MvpFragment;
import com.lbb.mvplibrary.utils.LinLog;
import com.lbb.mvplibrary.utils.SPUtils;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 个人中心
 */
public class MeFragment extends MvpFragment<MePresenter> implements View.OnClickListener, Meview {
    @BindView(R.id.tbv_titlebar)
    TitleBarView tbvTitlebar;
    @BindView(R.id.touxiang_iv)
    ImageView touxiangIv;
    @BindView(R.id.me_tv)
    TextView meTv;
    @BindView(R.id.kefu)
    RelativeLayout kefu;
    @BindView(R.id.pingtai)
    RelativeLayout pingtai;
    @BindView(R.id.yijian)
    RelativeLayout yijian;
    @BindView(R.id.banben)
    RelativeLayout banben;
    Unbinder unbinder;
    @BindView(R.id.geren)
    RelativeLayout geren;
    private VersionsUpdata versionsUpdata;
    private String uri="http://www.apk.anzhi.com/data3/apk/201703/14/4636d7fce23c9460587d602b9dc20714_88002100.apk";
    List<String> list = new ArrayList<>();
    private String tel;
    MePresenter mePresenter=new MePresenter(this);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_me, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initdata();
        return inflate;
    }

    private void initdata() {
        tbvTitlebar.hideleft();
       tbvTitlebar.setMainTitle("个人中心");
        tbvTitlebar.setMainTitleColor(Color.WHITE);

        versionsUpdata = new VersionsUpdata(getActivity());
        tel = (String) SPUtils.get(getActivity(), "tel", "");

        kefu.setOnClickListener(this);
        pingtai.setOnClickListener(this);
        yijian.setOnClickListener(this);
        banben.setOnClickListener(this);
        geren.setOnClickListener(this);

        mePresenter.getuser(tel);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //客服
            case R.id.kefu:
                new CBDialogBuilder(getActivity())
                    //    .setTouchOutSideCancelable(true)
                        .showCancelButton(true)
                        .setTitle("拨打电话")
                        .setMessage("13924943381")
                        .setConfirmButtonText("呼叫")
                        .setCancelButtonText("取消")
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .setButtonClickListener(true, new CBDialogBuilder.onDialogbtnClickListener() {
                            @Override
                            public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                                switch (whichBtn) {
                                    case BUTTON_CONFIRM:
                                        Intent intent=new Intent("android.intent.action.CALL", Uri.parse("tel:"+"18600218366"));
                                       startActivity(intent);
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
            //平台
            case R.id.pingtai:
                startActivity(PlatformActivity.class);
                break;
            //意见
            case R.id.yijian:
                startActivity(FeedbackActivity.class);
                break;
            //版本
            case R.id.banben:
                String code="2";
                //如果服务器版本大于当前版本
                if (!code.equals(getLocalVersionName(getActivity()))) {
                    //版本更新   是否强制更新
                    versionsUpdata.initdata(list,false,uri);
                }else {
                    toastShow("当前为最新版本");
                }
                break;
            //个人中心
            case R.id.geren:
               startActivity(PersonageActivity.class);
                break;
        }
    }

    /**
     * 获取版本名称
     */
    public String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    @Override
    public void getDataSuccess(MeMode meMode) {
      if (meMode.getMobile()!=null){
          meTv.setText(meMode.getMobile().getAdmin_name()+"  "+meMode.getMobile().getRole_name());
      }
    }

    @Override
    public void getDataFail(String msg) {
        LinLog.lLog(msg);
    }

    @Override
    protected MePresenter createPresenter() {
        return new MePresenter(this);
    }

    @Override
    protected void initretry() {

    }
}
