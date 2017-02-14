package com.example.hui.mynews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hui.mynews.R;
import com.example.hui.mynews.entity.ExpressBean;

import java.util.List;

/**
 * Created by yanlongzh on 2017/2/12.
 */

public class ExpressAdapter extends BaseAdapter {
    private List<ExpressBean> mExpressBeanList;
    private Context mContext;
    private LayoutInflater mInflater;

    public ExpressAdapter(Context mContext,List<ExpressBean> mExpressBeanList) {
        this.mContext = mContext;
        this.mExpressBeanList = mExpressBeanList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mExpressBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mExpressBeanList.get(position);
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
            convertView = mInflater.inflate(R.layout.express_item,null);
            viewHolder.dataTime = (TextView) convertView.findViewById(R.id.express_datetime);
            viewHolder.remarkTime = (TextView) convertView.findViewById(R.id.express_remark);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.remarkTime.setText(mExpressBeanList.get(position).getReMark());
        viewHolder.dataTime.setText(mExpressBeanList.get(position).getDataTime());
        return convertView;
    }

    class ViewHolder{
        TextView dataTime;
        TextView remarkTime;

    }
}
