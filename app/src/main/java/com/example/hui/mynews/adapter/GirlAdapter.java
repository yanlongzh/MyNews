package com.example.hui.mynews.adapter;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hui.mynews.R;
import com.example.hui.mynews.entity.GirlBean;

import java.util.List;

/**
 * Created by yanlongzh on 2017/2/13.
 */

public class GirlAdapter extends BaseAdapter {
    private List<GirlBean> mGrils;
    private Context mContext;
    private LayoutInflater mInflater;
    private WindowManager wm;
    private  int width;
    private int height;

    public GirlAdapter(Context mContext, List<GirlBean> mGrils) {
        this.mContext = mContext;
        this.mGrils = mGrils;
        mInflater = LayoutInflater.from(mContext);
        wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
    }

    @Override
    public int getCount() {
        return mGrils.size();
    }

    @Override
    public Object getItem(int position) {
        return mGrils.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.girl_layout,null);
            viewHolder.girl_iv = (ImageView) convertView.findViewById(R.id.girl_pic);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mContext).load(mGrils.get(position).getUri()).
                override(width/2, (int) (height/2.5)).centerCrop().into( viewHolder.girl_iv);

        return convertView;
    }

    class ViewHolder{
        ImageView girl_iv;
    }
}
