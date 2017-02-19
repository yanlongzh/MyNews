package com.example.hui.mynews.fragment;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.telephony.SmsMessage;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.hui.mynews.R;
import com.example.hui.mynews.utils.L;
import com.example.hui.mynews.view.CustomLinearLayout;


/**
 * Created by yanlongzh on 2017/2/18.
 */
public class SmsServices extends Service implements View.OnClickListener {
    public static final String SMS_ACTION ="android.provider.Telephony.SMS_RECEIVED";
    private BroadCast broadCast;
    private String smsPhone;
    private String smsText;
    private CustomLinearLayout mView;
    private TextView tv_phone;
    private TextView tv_text;
    private Button bt_sms;
    private WindowManager wm;
    private HomeWatchReceiver mHomeWatchReceiver;
    public static final String SYSTEM_DIALOGS_RESON_KEY = "reason";
    public static final String SYSTEM_DIALOGS_HOME_KEY = "homekey";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        broadCast = new BroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(SMS_ACTION);
        filter.setPriority(Integer.MAX_VALUE);
        registerReceiver(broadCast, filter);

        mHomeWatchReceiver = new HomeWatchReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(mHomeWatchReceiver, intentFilter);
        L.i("chushihuachenggong");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadCast);
        unregisterReceiver(mHomeWatchReceiver);
        L.i("jiechu");
    }


    class BroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
         L.i("duanxin");
             String action = intent.getAction();
            if (action.equals(SMS_ACTION)) {
                Bundle bundle = intent.getExtras();
                Object[] pdus = (Object[]) bundle.get("pdus");
                for (Object object : pdus) {
                    SmsMessage sms = SmsMessage.createFromPdu((byte[]) object);
                    smsPhone = sms.getOriginatingAddress();
                    smsText = sms.getMessageBody();
                    showWindow();
                }
            }
        }
    }

    private void showWindow() {
        if(Build.VERSION.SDK_INT >= 23){
            if(!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return;
            }else {
                wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
                WindowManager.LayoutParams wl = new WindowManager.LayoutParams();
                wl.width = WindowManager.LayoutParams.MATCH_PARENT;
                wl.height = WindowManager.LayoutParams.MATCH_PARENT;
                wl.format = PixelFormat.TRANSLUCENT;
                wl.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
                wl.type = WindowManager.LayoutParams.TYPE_PHONE;
                mView = (CustomLinearLayout) View.inflate(getApplicationContext(), R.layout.sms_layout, null);
                tv_phone = (TextView) mView.findViewById(R.id.tv_phone);
                tv_text = (TextView) mView.findViewById(R.id.tv_text);
                bt_sms = (Button) mView.findViewById(R.id.sms_button);
                tv_phone.setText("发件人:" + smsPhone);
                L.i("短信内容：" + smsText);
                tv_text.setText(smsText);
                bt_sms.setOnClickListener(this);
                wm.addView(mView, wl);
                mView.setOutsideListener(listener);
            }
        }

    }

    @Override
    public void onClick(View v) {
        Uri uri = Uri.parse("smsto:" + smsPhone);
        Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("sms_body", "");
        startActivity(intent);
        if (mView.getParent() != null) {
            wm.removeView(mView);
        }
    }
    CustomLinearLayout.OnTouchOutsideListener listener = new CustomLinearLayout.OnTouchOutsideListener() {

        @Override
        public boolean disPatchOutside(KeyEvent event) {
            if(event.getKeyCode()==KeyEvent.KEYCODE_BACK){
                if(mView.getParent()!=null){
                    wm.removeView(mView);
                }
            }
            return false;
        }
    };

    class HomeWatchReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_DIALOGS_RESON_KEY);
                if (SYSTEM_DIALOGS_HOME_KEY.equals(reason)) {
                    if (mView.getParent() != null) {
                        wm.removeView(mView);
                    }
                }
            }
        }
    }
}
