package com.example.hui.mynews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hui.mynews.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by yanlongzh on 2016/12/20.
 */

public class PhoneFragment extends Fragment implements View.OnClickListener {
    private Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_del, btn_query;
    private EditText et;
    private ImageView phone_iv;
    private TextView phone_tv;
    private String str_number;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.attentionlayout,null);
       findView(view);
        return view;
    }

    private void findView(View view) {
        phone_tv = (TextView) view.findViewById(R.id.tv_result);
        phone_iv = (ImageView) view.findViewById(R.id.iv_company);
        et = (EditText) view.findViewById(R.id.phone_edit);

        btn_0 = (Button)view.findViewById(R.id.btn_0);
        btn_0.setOnClickListener(this);
        btn_1 = (Button)view.findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        btn_2 = (Button)view.findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        btn_3 = (Button)view.findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
        btn_4 = (Button)view.findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);
        btn_5 = (Button)view.findViewById(R.id.btn_5);
        btn_5.setOnClickListener(this);
        btn_6 = (Button)view.findViewById(R.id.btn_6);
        btn_6.setOnClickListener(this);
        btn_7 = (Button)view.findViewById(R.id.btn_7);
        btn_7.setOnClickListener(this);
        btn_8 = (Button)view.findViewById(R.id.btn_8);
        btn_8.setOnClickListener(this);
        btn_9 = (Button)view.findViewById(R.id.btn_9);
        btn_9.setOnClickListener(this);
        btn_del = (Button)view.findViewById(R.id.btn_del);
        btn_del.setOnClickListener(this);
        btn_query = (Button)view.findViewById(R.id.btn_query);
        btn_query.setOnClickListener(this);

        btn_del.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                et.setText("");
                return false;
            }
        });
    }
    @Override
    public void onClick(View v) {
        str_number =et.getText().toString();
        switch (v.getId()){
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
                et.setText(str_number+((Button)v).getText());
                et.setSelection(str_number.length()+1);
                break;
            case R.id.btn_del:
                if(!TextUtils.isEmpty(et.getText())){
                    et.setText(str_number.substring(0,str_number.length()-1));
                    et.setSelection(str_number.length()-1);
                    break;
                }
            case R.id.btn_query:
                String uri = "http://apis.juhe.cn/mobile/get?phone="+et.getText()+"&key=73f62a180560dee400f940109f769489" ;
                OkHttpUtils.get().url(uri).build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        if(!TextUtils.isEmpty(et.getText())){
                            queryPhone(response);
                        }
                    }
                });
                break;
        }
    }

    private void queryPhone(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject result = jsonObject.getJSONObject("result");
            String province = (String) result.get("province");
            String city = (String) result.get("city");
            String zip = (String) result.get("zip");
            String company = (String) result.get("company");
            String card = (String) result.get("card");
            phone_tv.setText("归属地:" + province + city + "\n"
                     + "邮编:" + zip + "\n"
                    + "运营商:" + company + "\n"
                    + "类型:" + card);
            switch (company){
                case "移动":
                    phone_iv.setBackgroundResource(R.drawable.china_mobile);
                    break;
                case "联通":
                    phone_iv.setBackgroundResource(R.drawable.china_unicom);
                    break;
                case "电信":
                    phone_iv.setBackgroundResource(R.drawable.china_telecom);
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
