package com.example.hui.mynews.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.hui.mynews.R;
import com.example.hui.mynews.adapter.ExpressAdapter;
import com.example.hui.mynews.entity.ExpressBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;

/**
 * Created by yanlongzh on 2016/12/20.
 */

public class ExpressFragment extends Fragment implements View.OnClickListener {
    private ListView expressList;
    private Button express_bt;
    private EditText express_number,express_company;
    private List<ExpressBean> lists;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.express_layout,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        lists = new ArrayList<ExpressBean>();
        express_bt = (Button) view.findViewById(R.id.express_bt);
        express_number= (EditText) view.findViewById(R.id.express_number);
        express_company= (EditText) view.findViewById(R.id.express_company);
        express_bt.setOnClickListener(this);
        expressList = (ListView) view.findViewById(R.id.express_listview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.express_bt:
                String number = express_number.getText().toString().trim();
                String ex_company = express_company.getText().toString().trim();
                String uri = "http://v.juhe.cn/exp/index?key=2d3c64cf4f1fb285cac3564fe1a8a1ba&com="+
                        ex_company+"&no="+number;
                if(!TextUtils.isEmpty(number)&!TextUtils.isEmpty(ex_company)){
                    OkHttpUtils.get().url(uri).build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.d("wuliu","加载失败...");
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            parseJson(response);
                        }
                    });
                }
        }
    }

    private void parseJson(String response) {
        JSONObject jsonobject = null;
        try {
            jsonobject = new JSONObject(response);
            JSONObject jsonobj = jsonobject.getJSONObject("result");
            JSONArray jsonarray = jsonobj.getJSONArray("list");
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject object = (JSONObject) jsonarray.get(i);
                ExpressBean express = new ExpressBean();
                express.setDataTime(object.getString("datetime"));
                express.setReMark(object.getString("remark"));
                lists.add(express);
            }
            Collections.reverse(lists);
            ExpressAdapter adapter = new ExpressAdapter(getActivity(),lists);
            expressList.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
