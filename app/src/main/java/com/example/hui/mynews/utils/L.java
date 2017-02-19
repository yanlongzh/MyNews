package com.example.hui.mynews.utils;

import android.util.Log;

/**
 * Created by yanlongzh on 2017/2/18.
 */

public class L {
    public static final  boolean DEBUG = true;
    public static final String TAG = "MyNews";
    public static void d(String text){
        if(DEBUG){
            Log.d(TAG,text);
        }
    }

    public static void i(String text){
        if(DEBUG){
            Log.i(TAG,text);
        }
    }

    public static void w(String text){
        if(DEBUG){
            Log.w(TAG,text);
        }
    }

    public static void e(String text){
        if(DEBUG){
            Log.e(TAG,text);
        }
    }
}
