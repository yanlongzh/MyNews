package com.example.hui.mynews.utils;

import java.util.ArrayList;

/**
 * Created by yanlongzh on 2016/12/19.
 */

public class LeftData {

    public static ArrayList<LeftItemBean> getItemBean(){
        ArrayList arrayList = new ArrayList<LeftItemBean>();
        arrayList.add(new LeftItemBean("首页"));
        arrayList.add(new LeftItemBean("快递"));
        arrayList.add(new LeftItemBean("归属地"));
        arrayList.add(new LeftItemBean("机器人"));
        arrayList.add(new LeftItemBean("设置"));

        return arrayList;
    }
}
