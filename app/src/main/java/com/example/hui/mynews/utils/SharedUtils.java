package com.example.hui.mynews.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yanlongzh on 2017/2/7.
 */

public class SharedUtils {
    public static final String SHARED_NAME = "config";
    public static void putString(Context mcontext,String key,String value){
        SharedPreferences mSharedPreferences = mcontext.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }
    public static void putInt(Context mcontext,String key,int value){
        SharedPreferences mSharedPreferences = mcontext.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key,value);
        editor.apply();
    }
    public static void putBoolean(Context mcontext,String key,boolean value){
        SharedPreferences mSharedPreferences = mcontext.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }
    public static Boolean getBoolean(Context mcontext,String key,boolean defvalue){
        SharedPreferences mSharedPreferences = mcontext.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);
       return mSharedPreferences.getBoolean(key,defvalue);

    }
    public static int getInteger(Context mcontext,String key,int defvalue){
        SharedPreferences mSharedPreferences = mcontext.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(key,defvalue);

    }
    public static String getString(Context mcontext,String key,String defvalue){
        SharedPreferences mSharedPreferences = mcontext.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);
        return mSharedPreferences.getString(key,defvalue);
    }
    public static void delValue(Context mcontext,String key){
        SharedPreferences mSharedPreferences = mcontext.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove(key).apply();
    }
    public static void delAll(Context mcontext){
        SharedPreferences mSharedPreferences = mcontext.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear().apply();
    }


}
