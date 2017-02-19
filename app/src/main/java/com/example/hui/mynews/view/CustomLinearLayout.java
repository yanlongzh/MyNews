package com.example.hui.mynews.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.LinearLayout;

/**
 * Created by yanlongzh on 2017/2/18.
 */

public class CustomLinearLayout extends LinearLayout {
    private OnTouchOutsideListener mOutsideListener;

    public CustomLinearLayout(Context context) {
        super(context);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public OnTouchOutsideListener getOutsideListener() {
        return mOutsideListener;
    }

    public void setOutsideListener(OnTouchOutsideListener outsideListener) {
        mOutsideListener = outsideListener;
    }

    public static interface OnTouchOutsideListener{
       boolean disPatchOutside(KeyEvent event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(mOutsideListener!=null){
            mOutsideListener.disPatchOutside(event);
        }
        return super.dispatchKeyEvent(event);
    }
}
