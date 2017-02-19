package com.example.hui.mynews.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hui.mynews.R;
import com.example.hui.mynews.adapter.GirlAdapter;
import com.example.hui.mynews.entity.GirlBean;
import com.example.hui.mynews.utils.CustomDiglog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by yanlongzh on 2016/12/27.
 */

public class GirlFragment extends Fragment {
    private GridView mGridView;
    private List<GirlBean> lists;
    private GirlAdapter adapter;
    private CustomDiglog dialog;
    private ImageView iv;
    private PhotoViewAttacher mAttacher;
    private String uri = "http://gank.io/api/search/query/listview/category/福利/count/50/page/1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.beautiful_girl_layout,null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        dialog = new CustomDiglog(getActivity(), WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,R.style.CustomDialog,R.layout.girl_dialog,
                Gravity.CENTER,R.style.pop_anim_style);
        iv = (ImageView) dialog.findViewById(R.id.dialog_girl);
        mGridView = (GridView) view.findViewById(R.id.girl_list);
        lists = new ArrayList<GirlBean>();
        OkHttpUtils.get().url(uri).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }
            @Override
            public void onResponse(String response, int id) {
                parseJson(response);
            }
        });

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Glide.with(getActivity()).load(lists.get(position).getUri()).into(iv);
                mAttacher = new PhotoViewAttacher(iv);
                mAttacher.update();
                dialog.show();
            }
        });
    }



    private void parseJson(String response) {
        try {
            JSONObject object = new JSONObject(response);
            JSONArray result = object.getJSONArray("results");
            for(int i=0;i<result.length();i++){
                JSONObject obj = result.getJSONObject(i);
                String url = (String) obj.get("url");
                GirlBean gb = new GirlBean();
                gb.setUri(url);
                lists.add(gb);
            }
            adapter = new GirlAdapter(getActivity(),lists);
            mGridView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
