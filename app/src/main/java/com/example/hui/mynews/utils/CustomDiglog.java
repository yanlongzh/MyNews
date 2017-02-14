package com.example.hui.mynews.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.hui.mynews.R;

/**
 * Created by yanlongzh on 2017/2/14.
 */

public class CustomDiglog extends Dialog {

    public CustomDiglog(Context context,int width,int height,int style,int layout,int anim, int gravity) {
        super(context, style);
        setContentView(layout);
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = WindowManager.LayoutParams.MATCH_PARENT;
        wl.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wl.gravity = Gravity.CENTER;
        window.setAttributes(wl);
        window.setWindowAnimations(anim);
    }

    public CustomDiglog(Context context,int width,int height,int style,int layout, int gravity){
        this(context,width,height,style,layout,R.style.pop_anim_style,gravity);
    }

    public CustomDiglog(Context context,int layout,int style){
        this(context,WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,style,layout,Gravity.CENTER);
    }

}
