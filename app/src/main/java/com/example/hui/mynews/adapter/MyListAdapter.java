package com.example.hui.mynews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hui.mynews.R;
import com.example.hui.mynews.utils.LeftData;
import com.example.hui.mynews.utils.LeftItemBean;

import java.util.List;

/**
 * Created by yanlongzh on 2016/12/19.
 */

public class MyListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<LeftItemBean> mItemList;
    private ViewHolder vh;

    public MyListAdapter(Context context) {
        mItemList = LeftData.getItemBean();
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         ViewHolder vh = null;
        if(convertView==null){
            convertView = mInflater.inflate(R.layout.left_item,null);
            vh = new ViewHolder();
            vh.tv = (TextView)convertView.findViewById(R.id.left_text);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv.setText(mItemList.get(position).getText());
        return convertView;
    }

    class ViewHolder{
        TextView tv;
    }
}
