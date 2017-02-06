package com.example.hui.mynews.Application;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by yanlongzh on 2017/2/6.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "64d4206ad3", false);
    }
}
