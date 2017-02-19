package com.example.hui.mynews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hui.mynews.R;
import com.example.hui.mynews.utils.YuleBean;

import java.util.List;

/**
 * Created by yanlongzh on 2017/1/5.
 */

public class RecyGsonAdapter extends RecyclerView.Adapter<RecyGsonAdapter.MyViewHolder> {
    private List<YuleBean.ResultBean.DataBean> mList;
    private LayoutInflater mInflater;
    private Context mContext;


    public RecyGsonAdapter(Context context,List<YuleBean.ResultBean.DataBean> mlist) {
        mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mList = mlist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.star_layout,parent,false);
        MyViewHolder mhd = new MyViewHolder(view);
        return mhd;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(mList.get(position).getTitle());
        holder.tvw.setText(mList.get(position).getDate());
       Glide.with(mContext).load(mList.get(position).getThumbnail_pic_s()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        ImageView iv;
        TextView tvw;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.json_text1);
            tvw = (TextView) itemView.findViewById(R.id.json_text2);
            iv = (ImageView) itemView.findViewById(R.id.json_img);
        }
    }
}
