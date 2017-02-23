package com.example.hui.mynews.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.hui.mynews.R;
import com.example.hui.mynews.activity.AboutActivity;
import com.example.hui.mynews.activity.MapActivity;
import com.example.hui.mynews.activity.UpdateActivity;
import com.example.hui.mynews.activity.ZingActivity;
import com.example.hui.mynews.utils.GetVersion;
import com.example.hui.mynews.utils.L;
import com.example.hui.mynews.utils.SharedUtils;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

import static android.app.Activity.RESULT_OK;
import static com.example.hui.mynews.R.id.set_scan_zing;
import static com.example.hui.mynews.R.id.set_zing;
import static com.example.hui.mynews.utils.SharedUtils.getBoolean;

/**
 * Created by yanlongzh on 2016/12/20.
 */

public class SettingFragment extends Fragment implements View.OnClickListener {
    private String apk;
    private static final String APK_URI = "http://192.168.1.107:8080/long/apkfig.json";
    private View view;
    private Switch mRobot;
    private Switch sms;
    private LinearLayout mUpdate;
    private LinearLayout mScan;
    private LinearLayout mZing;
    private LinearLayout mLocation;
    private LinearLayout mAbout;
    private boolean isSmsSwitch;
    private TextView version,set_scan_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.setting_layout, container, false);
        findView(view);
        return view;
    }

    private void findView(View view) {
        mRobot = (Switch) view.findViewById(R.id.robot_switch);
        sms = (Switch) view.findViewById(R.id.sms_switch);
        mUpdate = (LinearLayout) view.findViewById(R.id.set_update);
        mScan = (LinearLayout) view.findViewById(R.id.set_scan);
        mZing = (LinearLayout) view.findViewById(set_zing);
        mLocation = (LinearLayout) view.findViewById(R.id.set_location);
        mAbout = (LinearLayout) view.findViewById(R.id.set_about);
        version = (TextView) view.findViewById(R.id.set_version);
        version.setText("检测版本"+ GetVersion.getVersionName(getActivity()));
        set_scan_tv = (TextView) view.findViewById(set_scan_zing);
        mRobot.setOnClickListener(this);
        sms.setOnClickListener(this);
        mUpdate.setOnClickListener(this);
        mScan.setOnClickListener(this);
        mZing.setOnClickListener(this);
        mLocation.setOnClickListener(this);
        mAbout.setOnClickListener(this);
        mRobot.setOnClickListener(this);
        isSmsSwitch = getBoolean(getActivity(),"smsselect",false);
        sms.setChecked(isSmsSwitch);
        boolean isSpeak = SharedUtils.getBoolean(getActivity(),"isSpeak",false);
        mRobot.setChecked(isSpeak);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.robot_switch:
                mRobot.setSelected(!mRobot.isSelected());
                SharedUtils.putBoolean(getActivity(),"isSpeak",mRobot.isChecked());
                break;
            case R.id.sms_switch:
                sms.setSelected(!sms.isSelected());
                SharedUtils.putBoolean(getActivity(),"smsselect",sms.isChecked());
                if(sms.isChecked()){
                    getActivity().startService(new Intent(getActivity(), SmsServices.class));
                }else {
                    getActivity().stopService(new Intent(getActivity(), SmsServices.class));
                }
                break;
            case R.id.set_update:

                OkHttpUtils.get().url(APK_URI).build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        L.i("请求错误"+e.toString());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        parseJson(response);

                    }
                });
                break;
            case R.id.set_scan:
                Intent openCameraIntent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
                break;
            case set_zing:
                startActivity(new Intent(getActivity(),ZingActivity.class));
                break;
            case R.id.set_location:
                startActivity(new Intent(getActivity(),MapActivity.class));
                break;
            case R.id.set_about:
               startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
        }
    }

    private void parseJson(String response) {
        try {
            JSONObject obj = new JSONObject(response);
            int versionCode = obj.getInt("versionCode");
            String content = obj.getString("content");
            apk = obj.getString("apk");
            if(versionCode>GetVersion.getVersionCode(getActivity())){
                showDialog(content);
                L.i("zai");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showDialog(String content) {
        new AlertDialog.Builder(getActivity()).setTitle("有新版本，请更新").setMessage(content).
                setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(),UpdateActivity.class);
                        intent.putExtra("url",apk);
                        startActivity(intent);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                L.i("取消");
            }
        }).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            set_scan_tv.setText(scanResult);
        }
    }
}
