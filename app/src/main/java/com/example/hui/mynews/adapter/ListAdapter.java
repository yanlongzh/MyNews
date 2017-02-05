package com.example.hui.mynews.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hui.mynews.utils.HomeNewBean;
import com.example.hui.mynews.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by yanlongzh on 2016/12/27.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private List<HomeNewBean> mlist;
    private Context mContext;
    private LayoutInflater mInflater;
    private View mView;
    private Uri uri;
    private MyItemClickListener mListener;

    public ListAdapter(Context mContext, List<HomeNewBean> mlist) {
        this.mContext = mContext;
        this.mlist = mlist;
        mInflater= LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = mInflater.inflate(R.layout.homenews_item_layout,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.itemView.setTag(position);
        if(mListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClickListener(v,holder.getLayoutPosition());
                }
            });
        }
        holder.title.setText(mlist.get(position).getDatatext());
        holder.time.setText(mlist.get(position).getDatatime());
        holder.Newsfrom.setText(mlist.get(position).getnewsfrom());
            OkHttpUtils.get().url(mlist.get(15).getImgurl()).build()
                    .execute(new BitmapCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.d("OnError", "" + e.getMessage());
                        }

                        @Override
                        public void onResponse(Bitmap response, int id) {
                            holder.imgurl.setImageBitmap(response);
                        }
                    });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setOnItemClickListener(MyItemClickListener mListener){
        this.mListener = mListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView time;
        TextView Newsfrom;
        ImageView imgurl;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.News_title);
            time = (TextView) itemView.findViewById(R.id.News_time);
            Newsfrom = (TextView) itemView.findViewById(R.id.News_from);
            imgurl = (ImageView) itemView.findViewById(R.id.img_url);
        }
    }

     public  interface MyItemClickListener{
        void onItemClickListener(View view,int position);
     }
}
