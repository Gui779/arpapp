package com.example.erpqpp.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.erpqpp.R;
import com.lbb.mvplibrary.app.AppManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 胡涛.
 */
public class VersionsUpdata {

    private Activity mainActivity;
    private String loaduri;
    public VersionsUpdata(Activity  mainActivity) {
        this.mainActivity = mainActivity;
    }

    /**
     * 更新的内容  是否强制更新
     * */
    public void initdata(List<String> versionList, final boolean isflag,String uri) {
        loaduri=uri;
        View view = View.inflate(mainActivity, R.layout.banbengengxin, null);
       final MyDialog dialog = new MyDialog(mainActivity, 0, 0, view, R.style.dialog);
       if (isflag) {
           dialog.setCancelable(false);   //返回键不可关闭
       }
        dialog.show();

        Button gengxin  = view.findViewById(R.id.gengxin);
        Button quxiao = view.findViewById(R.id.quxiao);
        final NumberProgressBar number_progress_bar = view.findViewById(R.id.number_progress_bar);
        //版本更新内容
        RecyclerView tv_remarks = view.findViewById(R.id.remarks);
        tv_remarks.setAdapter(new UpdataversionAdapter(mainActivity, versionList));
        tv_remarks.setLayoutManager(new LinearLayoutManager(mainActivity));

        gengxin.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                int flag = ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (flag != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(mainActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 111);
                }else
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    boolean hasInstallPermission =
                            mainActivity.getApplicationContext().getPackageManager().canRequestPackageInstalls();
                    if (!hasInstallPermission) {
                        startInstallPermissionSettingActivity();
                        return;
                    }
                }


                //下载apk
                downloadApk(number_progress_bar,dialog);
            }
        });


        //取消退出app
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (isflag){
                    dialog.dismiss();
                    AppManager.getInstance().AppExit(mainActivity);
                }else {
                    dialog.dismiss();
                }

            }
        });

    }

    /**
     * 下载apk
     */
    private void downloadApk(NumberProgressBar number_progress_bar,MyDialog myDialog) {
        //显示下载进度
        number_progress_bar.setVisibility(View.VISIBLE);
        //访问网络下载apk
        new Thread(new DownloadApk(number_progress_bar,myDialog)).start();
    }



    /**
     * 访问网络下载apk
     */
    private class DownloadApk implements Runnable {
        private NumberProgressBar dialog;
        private MyDialog myDialog;
        InputStream is;
        FileOutputStream fos;

        private DownloadApk(NumberProgressBar dialog,MyDialog myDialog) {
            this.dialog = dialog;
            this.myDialog = myDialog;
        }

        @Override
        public void run() {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().get().url(loaduri).build();
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    //获取内容总长度
                    final long contentLength = response.body().contentLength();
                    //保存到sd卡
                    File apkFile = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".apk");
                    fos = new FileOutputStream(apkFile);
                    //获得输入流
                    is = response.body().byteStream();
                    //定义缓冲区大小
                    byte[] bys = new byte[1024];
                    int sum  = 0;
                    int len = -1;
                    while ((len = is.read(bys)) != -1) {
                        try {
                            Thread.sleep(1);
                            fos.write(bys, 0, len);
                            fos.flush();
                            sum += len;
                            final int progress = (int) (sum * 1.0f / contentLength * 100);


                            //设置进度
                           mainActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.setMax(100);
                                    dialog.setProgress(progress);
                                }
                            });
                        } catch (InterruptedException e) {
                            Log.e("erroy",e.toString());
                        }
                    }
                    //下载完成,提示用户安装
                    installApk(apkFile);
                    myDialog.dismiss();
                }
            } catch (IOException e) {
                Log.e("erroy",e.toString());
            } finally {
                //关闭io流
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    is = null;
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    fos = null;
                }
            }

            mainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dialog.setVisibility(View.GONE);
                }
            });
        }
    }



    /**
     * 下载完成,提示用户安装
     */
    private void installApk(File file) {
        //调用系统安装程序
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        mainActivity.startActivityForResult(intent, 1);
    }


    /**
     * 跳转到设置-允许安装未知来源-页面
     */
    @RequiresApi(api = Build.VERSION_CODES.O) private void startInstallPermissionSettingActivity() {
        //注意这个是8.0新API
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mainActivity.getApplicationContext().startActivity(intent);
    }

}
