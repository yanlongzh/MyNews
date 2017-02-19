package com.example.hui.mynews.Application;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.tencent.bugly.crashreport.CrashReport;

import cn.bmob.v3.Bmob;

/**
 * Created by yanlongzh on 2017/2/6.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        初始化bugly
        CrashReport.initCrashReport(getApplicationContext(), "64d4206ad3", false);
//        bmob初始化
        Bmob.initialize(this, "8b30f2395296ae890ac3ad699dad809b");
//        baiduMap
        SDKInitializer.initialize(getApplicationContext());
    }
}
