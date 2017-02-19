package com.example.hui.mynews.utils;

/**
 * Created by yanlongzh on 2016/12/27.
 */

public class HomeNewBean {

    private String newsfrom;
    private String imgurl;
    private String datatime;
    private String datatext;
    private String herf;


    public HomeNewBean(String datatext, String datatime, String herf, String imgurl, String newsfrom) {
        this.datatext = datatext;
        this.datatime = datatime;
        this.herf = herf;
        this.imgurl = imgurl;
        this.newsfrom = newsfrom;
    }

    public String getHerf() {
        return herf;
    }

    public void setHerf(String herf) {
        this.herf = herf;
    }

    public String getnewsfrom() {
        return newsfrom;
    }

    public void setnewsfrom(String newsfrom) {
        this.newsfrom = newsfrom;
    }

    public String getDatatext() {
        return datatext;
    }

    public void setDatatext(String datatext) {
        this.datatext = datatext;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    @Override
    public String toString() {
        return "HomeNewBean{" +
                "datatext='" + datatext + '\'' +
                ", newsfrom='" + newsfrom + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", datatime='" + datatime + '\'' +
                ", herf='" + herf + '\'' +
                '}';
    }

}
