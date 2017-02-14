package com.example.hui.mynews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hui.mynews.R;
import com.example.hui.mynews.entity.SpeakBean;

import java.util.List;

/**
 * Created by yanlongzh on 2017/2/13.
 */

public class SpeakAdapter extends BaseAdapter {
    private Context mContext;
    private List<SpeakBean> lists;
    private LayoutInflater mInflater;
    public static final int VALUE_LEFT = 1;
    public static final int VALUE_Right = 2;

    public SpeakAdapter(Context mContext, List<SpeakBean> lists) {
        super();
        this.mContext = mContext;
        this.lists = lists;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LeftViewHolder leftViewHolder = null;
        RightViewHolder rightViewHolder = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case VALUE_LEFT:
                    leftViewHolder = new LeftViewHolder();
                    convertView = mInflater.inflate(R.layout.left_speak_item, null);
                    leftViewHolder.left_text = (TextView) convertView.findViewById(R.id.speak_left_tv);
                    convertView.setTag(leftViewHolder);
                    break;
                case VALUE_Right:
                    rightViewHolder = new RightViewHolder();
                    convertView = mInflater.inflate(R.layout.right_speak_item, null);
                    rightViewHolder.right_text = (TextView) convertView.findViewById(R.id.speak_right_tv);
                    convertView.setTag(rightViewHolder);
                    break;
            }
        }else {
            switch (type){
                case VALUE_LEFT:
                    leftViewHolder = (LeftViewHolder) convertView.getTag();
                    break;
                case VALUE_Right:
                    rightViewHolder = (RightViewHolder) convertView.getTag();
                    break;
            }
            switch (type){
                case VALUE_LEFT:
                    leftViewHolder.left_text.setText(lists.get(position).getText());
                    break;
                case VALUE_Right:
                    rightViewHolder.right_text.setText(lists.get(position).getText());
                    break;
            }
        }

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        SpeakBean speakBean = lists.get(position);
        int type = speakBean.getType();
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    class LeftViewHolder {
        TextView left_text;
    }

    class RightViewHolder {
        TextView right_text;
    }
}
