package com.example.hui.mynews.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by yanlongzh on 2017/2/19.
 */

public class GetVersion {
    public static String getVersionName(Context mContext){
        PackageManager pm = mContext.getPackageManager();
        String version = null;
        try {
            PackageInfo info = pm.getPackageInfo(mContext.getPackageName(),0);
            version = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static int getVersionCode(Context mContext){
        PackageManager pm = mContext.getPackageManager();
       int version = 0;
        try {
            PackageInfo info = pm.getPackageInfo(mContext.getPackageName(),0);
            version = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }
}
