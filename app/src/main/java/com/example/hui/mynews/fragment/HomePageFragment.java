package com.example.hui.mynews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hui.mynews.entity.TabBean;
import com.example.hui.mynews.adapter.VpAdapter;
import com.example.hui.mynews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanlongzh on 2016/12/20.
 */

public class HomePageFragment extends Fragment {
    private List<Fragment> mList;
    private NewFragment nfragment;
    private GirlFragment sfragment;
    private StarFragment cfragment;
    private List<TabBean> mtbs;
    private VpAdapter mvpadapter;
    private TabLayout mtablayout;
    private ViewPager mvp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_frame_layout,null);
        mtablayout = (TabLayout) view.findViewById(R.id.tab_main);
        mtablayout.setTabMode(TabLayout.MODE_FIXED);
        mvp = (ViewPager) view.findViewById(R.id.tab_vp);
        initFragment();
        return view;
    }


    private void initFragment() {
        mtbs = new ArrayList<TabBean>();
        mtbs.add(new TabBean("最新新闻"));
        mtbs.add(new TabBean("明星娱乐"));
        mtbs.add(new TabBean("美女图片"));
        mList= new ArrayList<Fragment>();
        mList.add(new NewFragment());
        mList.add(new StarFragment());
        mList.add(new GirlFragment());
        mvpadapter = new VpAdapter(getChildFragmentManager(),mList,mtbs);
        mvp.setAdapter(mvpadapter);
        mtablayout.setupWithViewPager(mvp);


    }
}
