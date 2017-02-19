package com.example.hui.mynews.net;


import com.example.hui.mynews.utils.HomeNewBean;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yanlongzh on 2016/12/27.
 */

public class HomeNewManager {

    public HomeNewManager() {
    }

    public  static List<HomeNewBean> getHomeNewBean(Document document){
        List homenewbeans = new ArrayList<HomeNewBean>();
        Elements elements = document.select("div.item").first().select("div.Q-tpList");
        for (Element element:elements){
            Elements pic_element= element.select("img");
            String imgurl = pic_element.attr("_src");
            Element texteElement= element.select("em.f14").first();
            String datetext= texteElement.select("a").text();
            String href= texteElement.select("a").attr("href");
            Element NewsElement= element.select("div.info").first();
            String Newstext= NewsElement.select("span.from").text();
            String Newstime= NewsElement.select("span.pub_time").text();
            homenewbeans.add(new HomeNewBean(datetext,Newstime,href,imgurl,Newstext));
        }
        return homenewbeans;
    }

    public  static List<String> getImgurl(Document document){
        List imgUrl = new ArrayList<String>();
        Elements elements = document.select("div.item").first().select("div.Q-tpList");
        for (Element element:elements){
            Elements pic_element= element.select("img");
            String _imgurl = pic_element.attr("src");
            imgUrl.add(_imgurl);
        }
        return imgUrl;
    }
}
