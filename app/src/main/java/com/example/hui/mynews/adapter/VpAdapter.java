package com.example.hui.mynews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.hui.mynews.entity.TabBean;

import java.util.List;

/**
 * Created by yanlongzh on 2016/12/27.
 */

public class VpAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;
    private List<TabBean> mtb;

    public VpAdapter(FragmentManager fm, List<Fragment> mlist,List<TabBean> mtb) {
        super(fm);
        this.mList = mlist;
        this.mtb = mtb;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mtb.get(position).getTitle();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = null;
        fragment = (Fragment) super.instantiateItem(container,position);
        return  fragment;
    }
}
