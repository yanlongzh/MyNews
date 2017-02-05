package com.example.hui.mynews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yanlongzh on 2016/12/20.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList;

    public FragmentAdapter(FragmentManager fm,List<Fragment> mlist) {
        super(fm);
        this.mList = mlist;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

}
