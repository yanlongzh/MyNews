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
import com.example.hui.mynews.utils.SharedUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
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
    private SpeechSynthesizer mTts;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.robot_layout,container,false);
        findView(view);
        return view;
    }

    private void findView(View view) {
        //1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传InitListener
         mTts= SpeechSynthesizer.createSynthesizer(getActivity(), null);
        //2.合成参数设置，详见《科大讯飞MSC API手册(Android)》SpeechSynthesizer 类
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端

        lists = new ArrayList<SpeakBean>();
        speak_bt = (Button) view.findViewById(R.id.speak_bt);
        speak_et = (EditText) view.findViewById(R.id.speak_et);
        speak_list = (ListView) view.findViewById(R.id.speak_list);
        sAdapter = new SpeakAdapter(getActivity(),lists);
        speak_bt.setOnClickListener(this);
        speak_list.setAdapter(sAdapter);
        addToLeft("我是你的好友");
    }

    public void speak(String text) {
        boolean isSpeak = SharedUtils.getBoolean(getActivity(),"isSpeak",false);
        if(isSpeak) {
            mTts.startSpeaking(text, mSynListener);
        }


    }
    //合成监听器
    private SynthesizerListener mSynListener = new SynthesizerListener() {
        //会话结束回调接口，没有错误时，error为null
        public void onCompleted(SpeechError error) {
        }

        //缓冲进度回调
        //percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在文本中结束位置，info为附加信息。
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
        }

        //开始播放
        public void onSpeakBegin() {
        }

        //暂停播放
        public void onSpeakPaused() {
        }

        //播放进度回调
        //percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
        }

        //恢复播放回调接口
        public void onSpeakResumed() {
        }

        //会话事件回调接口
        public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
        }
    };

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
        speak(text);
        SpeakBean sb = new SpeakBean();
        sb.setType(SpeakAdapter.VALUE_LEFT);
        sb.setText(text);
        lists.add(sb);
        sAdapter.notifyDataSetChanged();
        speak_list.setSelection(speak_list.getBottom());
    }
}
