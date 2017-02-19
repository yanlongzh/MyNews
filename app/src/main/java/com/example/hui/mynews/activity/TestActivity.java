package com.example.hui.mynews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.hui.mynews.R;
import com.example.hui.mynews.fragment.SmsServices;
import com.example.hui.mynews.utils.L;

/**
 * Created by yanlongzh on 2017/2/19.
 */

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        Button button  = (Button) findViewById(R.id.testbutton);
        startService(new Intent(this, SmsServices.class));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filter = new Intent();
                filter.putExtra("号码","123456");
                filter.putExtra("内容","你好吗");
                filter.setAction("com.example.hui.mynews");
                sendBroadcast(filter);
                L.i("fasong");
            }
        });
    }
}
