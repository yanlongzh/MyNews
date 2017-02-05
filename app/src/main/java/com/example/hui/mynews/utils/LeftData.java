package com.example.hui.mynews.utils;

import com.example.hui.mynews.R;

import java.util.ArrayList;

/**
 * Created by yanlongzh on 2016/12/19.
 */

public class LeftData {
    public static ArrayList<LeftItemBean> getItemBean(){
        ArrayList arrayList = new ArrayList<LeftItemBean>();
        arrayList.add(new LeftItemBean("首页", R.drawable.ic_drawer_home_normal));
        arrayList.add(new LeftItemBean("发现",R.drawable.ic_drawer_explore_normal));
        arrayList.add(new LeftItemBean("关注",R.drawable.ic_drawer_follow_normal));
        arrayList.add(new LeftItemBean("收藏",R.drawable.ic_drawer_collect_normal));
        arrayList.add(new LeftItemBean("草稿",R.drawable.ic_drawer_draft_normal));
        arrayList.add(new LeftItemBean("提问",R.drawable.ic_drawer_question_normal));

        return arrayList;
    }
}
