package com.example.hui.mynews.utils;

/**
 * Created by yanlongzh on 2016/12/19.
 */

public class LeftItemBean {
    public String text;
    public int icon;

    public LeftItemBean(String text, int icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public int getIcon() {
        return icon;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
