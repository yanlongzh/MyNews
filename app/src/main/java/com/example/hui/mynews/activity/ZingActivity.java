package com.example.hui.mynews.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.hui.mynews.R;
import com.xys.libzxing.zxing.encoding.EncodingUtils;


/**
 * Created by yanlongzh on 2017/2/20.
 */
public class ZingActivity extends  BaseActivity {
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zing_layout);
        iv = (ImageView)findViewById(R.id.zing_img);
        int width = getResources().getDisplayMetrics().widthPixels;
        Bitmap qrCodeBitmap = EncodingUtils.createQRCode("我的应用", width/2, width/2,
                        BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
       iv.setImageBitmap(qrCodeBitmap);
    }
}
