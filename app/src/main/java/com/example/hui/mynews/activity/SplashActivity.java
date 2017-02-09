package com.example.hui.mynews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.hui.mynews.R;

/**
 * Created by yanlongzh on 2017/2/8.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                finish();
            }
        },2000);
    }
}
