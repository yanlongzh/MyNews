package com.example.hui.mynews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hui.mynews.utils.HomeNewBean;
import com.example.hui.mynews.net.HomeNewManager;
import com.example.hui.mynews.adapter.ListAdapter;
import com.example.hui.mynews.R;
import com.example.hui.mynews.activity.WebACtivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yanlongzh on 2016/12/27.
 */

public class NewFragment extends Fragment{
    private View view;
    private RecyclerView rv;
    private List<HomeNewBean> mhomenews;
    private LinearLayoutManager mManager;
    private Document document;
    private ListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            view=inflater.inflate(R.layout.homenew_list_layout,null);
        rv = (RecyclerView) view.findViewById(R.id.Recycler);
        initData();

        return view;
    }


    private void initData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://news.qq.com/").build();
        Call call =client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("qq.com","首页新闻数据加载失败...");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Handler handler = new Handler(Looper.getMainLooper());
                document = Jsoup.parse(result,"http://news.qq.com/");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mhomenews = HomeNewManager.getHomeNewBean(document);
                        bindAdapter();
                    }
                });
            }
        });
    }
    public void  bindAdapter() {
        mAdapter = new ListAdapter(getActivity(), mhomenews);
        mManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(mManager);
        rv.setAdapter(mAdapter);
        rv.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        mAdapter.setOnItemClickListener(new ListAdapter.MyItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Intent mintent = new Intent(getActivity(),WebACtivity.class);
                mintent.putExtra("url",mhomenews.get(position).getHerf());
                startActivity(mintent);
            }
        });
    }
}
