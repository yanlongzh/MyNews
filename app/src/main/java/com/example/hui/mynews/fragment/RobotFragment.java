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
import android.widget.ListView;

import com.example.hui.mynews.R;
import com.example.hui.mynews.adapter.SpeakAdapter;
import com.example.hui.mynews.entity.SpeakBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by yanlongzh on 2016/12/20.
 */

public class RobotFragment extends Fragment implements View.OnClickListener {
    private List<SpeakBean> lists;
    private ListView speak_list;
    private Button speak_bt;
    private EditText speak_et;
    private SpeakAdapter sAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.robot_layout,container,false);
        findView(view);
        return view;
    }

    private void findView(View view) {
        lists = new ArrayList<SpeakBean>();
        speak_bt = (Button) view.findViewById(R.id.speak_bt);
        speak_et = (EditText) view.findViewById(R.id.speak_et);
        speak_list = (ListView) view.findViewById(R.id.speak_list);
        sAdapter = new SpeakAdapter(getActivity(),lists);
        speak_bt.setOnClickListener(this);
        speak_list.setAdapter(sAdapter);
        addToLeft("我是你的好友");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.speak_bt:
                String text = speak_et.getText().toString();
                if(!TextUtils.isEmpty(text)){
                    addToRight(text);
                    speak_et.setText("");
                    String uri = "http://op.juhe.cn/robot/index?info=" + text
                            + "&key=" +"b1682e9909aea5036fea7de6ce967db8";
                    OkHttpUtils.get().url(uri).build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                        }
                        @Override
                        public void onResponse(String response, int id) {
                            ParseJson(response);
                        }
                    });
                }
                break;
        }
    }

    private void ParseJson(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject result = jsonObject.getJSONObject("result");
            String text = (String) result.get("text");
            addToLeft(text);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void addToRight(String text) {
        SpeakBean sb = new SpeakBean();
        sb.setType(SpeakAdapter.VALUE_Right);
        sb.setText(text);
        lists.add(sb);
        sAdapter.notifyDataSetChanged();
        speak_list.setSelection(speak_list.getBottom());
    }

    private void addToLeft(String text) {
        SpeakBean sb = new SpeakBean();
        sb.setType(SpeakAdapter.VALUE_LEFT);
        sb.setText(text);
        lists.add(sb);
        sAdapter.notifyDataSetChanged();
        speak_list.setSelection(speak_list.getBottom());
    }
}
