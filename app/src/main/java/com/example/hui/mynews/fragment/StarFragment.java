package com.example.hui.mynews.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hui.mynews.R;
import com.example.hui.mynews.adapter.RecyGsonAdapter;
import com.example.hui.mynews.net.JsonManager;
import com.example.hui.mynews.utils.GsonUrl;
import com.example.hui.mynews.entity.YuleBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by yanlongzh on 2016/12/27.
 */

public class StarFragment extends Fragment {
    private View view;
    private List<YuleBean.ResultBean.DataBean> mlist;
    private RecyclerView rv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.recy_json_layout,null);
        rv = (RecyclerView) view.findViewById(R.id.Json_recy);
        initData();
        return view;
    }

    private void initData() {
        OkHttpUtils.get().url(GsonUrl.GSON_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                            Log.d("cuowu",e.getMessage());
                    }

                    @Override
                    public void onResponse(final String response, int id) {
                        Handler mhandler = new Handler(Looper.getMainLooper());
                        mhandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mlist = JsonManager.getJsons(response);
                                initAdapter();
                            }
                        });

                    }
                });
    }

    private void initAdapter() {
        RecyGsonAdapter radapter = new RecyGsonAdapter(getActivity(),mlist);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(radapter);
    }

}
