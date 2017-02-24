package com.example.hui.mynews.net;

import com.example.hui.mynews.entity.YuleBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanlongzh on 2017/1/5.
 */

public class JsonManager {
    private static String title;
    private static String date;
    private static String url;
    private static String img_url;

    public static List<YuleBean.ResultBean.DataBean> getJsons(String mydata){
        List<YuleBean.ResultBean.DataBean> mlist = new ArrayList();
        Gson gson = new Gson();
        YuleBean  yuleBean = gson.fromJson(mydata,YuleBean.class);
         mlist = yuleBean.getResult().getData();
        for (int i=0;i<mlist.size();i++){
             title = mlist.get(i).getTitle();
             date = mlist.get(i).getDate();
             url = mlist.get(i).getUrl();
             img_url = mlist.get(i).getThumbnail_pic_s();
        }
        mlist.add(new YuleBean.ResultBean.DataBean(title,date,url,img_url));
        return mlist;
    }
}
