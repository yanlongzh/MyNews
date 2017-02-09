package com.example.hui.mynews.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yanlongzh on 2017/2/7.
 * activity的基类
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if( getSupportActionBar()!=null){
            //设置actiongbar无Z轴的阴影效果
            getSupportActionBar().setElevation(0);
            //显示返回键
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }
}
