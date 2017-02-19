package com.example.hui.mynews.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.hui.mynews.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

/**
 * Created by yanlongzh on 2017/2/19.
 */
public class UpdateActivity extends BaseActivity{
    private String path ;
    private TextView update_tv;
    private String url;
    private NumberProgressBar progerssBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_layout);
        initView();
    }

    private void initView() {
        update_tv = (TextView) findViewById(R.id.update_tv_size);
        progerssBar = (NumberProgressBar) findViewById(R.id.update_bar);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/app-debug.apk";
        OkHttpUtils.get().url(url).build().execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(),
               "app-debug.apk") {
            @Override
            public void inProgress(float progress, long total, int id) {
                int now = (int) (total*(progress*0.01));
                update_tv.setText(now+"/"+(int) total);
                progerssBar.setProgress((int)(100* progress));
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                update_tv.setText("下载失败");
            }

            @Override
            public void onResponse(File response, int id) {
                update_tv.setText("下载成功");
                startInstallApk();
            }
        });
    }

    private void startInstallApk() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(path)),"application/vnd.android.package-archive");
        startActivity(intent);
        finish();
    }
}
