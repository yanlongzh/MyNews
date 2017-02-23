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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hui.mynews.R;
import com.example.hui.mynews.adapter.GirlAdapter;
import com.example.hui.mynews.entity.GirlBean;
import com.example.hui.mynews.view.CustomDiglog;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
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
    private PullToRefreshGridView mPullView;
    private GridView mGridView;
    private List<GirlBean> lists;
    private GirlAdapter adapter;
    private CustomDiglog dialog;
    private ImageView iv;
    private PhotoViewAttacher mAttacher;
    private int pageNum = 1;
    private String uri = "http://gank.io/api/data/福利/10"+"/"+pageNum;
    private boolean isRefresh = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.beautiful_girl_layout,null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        lists = new ArrayList<GirlBean>();
        dialog = new CustomDiglog(getActivity(), WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,R.style.CustomDialog,R.layout.girl_dialog,
                Gravity.CENTER,R.style.pop_anim_style);
        iv = (ImageView) dialog.findViewById(R.id.dialog_girl);
        mPullView = (PullToRefreshGridView) view.findViewById(R.id.girl_list);

        if (mPullView!=null){
            mGridView  = mPullView.getRefreshableView();
            TextView tv = new TextView(getActivity());
            tv.setGravity(Gravity.CENTER);
            tv.setText("加载中......");
            mPullView.setEmptyView(tv);
            loadImage(uri);
        }

        mPullView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                Toast.makeText(getActivity(), "内容无更新", Toast.LENGTH_SHORT).show();
                mPullView.onRefreshComplete();
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                pageNum++;
                if(pageNum<=5){
                    loadImage("http://gank.io/api/data/福利/10"+"/"+pageNum);
                    mPullView.onRefreshComplete();
                }else{
                    mPullView.onRefreshComplete();
                    isRefresh = false;
                    Toast.makeText(getActivity(), "没有更多了", Toast.LENGTH_SHORT).show();
                }
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

    private void loadImage(String uri) {
        OkHttpUtils.get().url(uri).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }
            @Override
            public void onResponse(String response, int id) {
                parseJson(response);
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
            //判定刷新时候，不是每次都重新setadapter；
           if(!isRefresh){
               adapter = new GirlAdapter(getActivity(),lists);
               mGridView.setAdapter(adapter);
               isRefresh = true;
           }else {
               adapter.notifyDataSetChanged();
           }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
