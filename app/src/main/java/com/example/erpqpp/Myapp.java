package com.example.erpqpp;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;

import com.billy.android.swipe.SmartSwipeBack;
import com.example.erpqpp.retrofit.ApiStores;
import com.lbb.mvplibrary.retrofit.ApiManager;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by 胡涛.
 */
public class Myapp extends Application {
    public static Context context;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate() {
        super.onCreate();
        ApiManager.init(ApiStores.API_SERVER_URL);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder(); StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        context=getApplicationContext();
        SmartSwipeBack.activityBezierBack(this, null,0);
        Context context = getApplicationContext();
        CrashReport.initCrashReport(context, "3e7739b9bd", true);
    }
}
