package com.example.hui.mynews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hui.mynews.utils.LeftData;
import com.example.hui.mynews.utils.LeftItemBean;
import com.example.hui.mynews.adapter.MyListAdapter;
import com.example.hui.mynews.R;

import java.util.List;

/**
 * Created by yanlongzh on 2016/12/19.
 */

public class LeftFragment extends Fragment {
    private ListView mListView;
    private MyListAdapter mAdapter;
    private View mView;
    private List<LeftItemBean> mItemBeen;

   public interface menuClickListener{
        void clickMenu(int position);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.left_layout,container,false);
        mListView= (ListView) mView.findViewById(R.id.left_listview);
        mItemBeen = LeftData.getItemBean();
        mListView.setAdapter(new MyListAdapter(getActivity()));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(getActivity() instanceof menuClickListener){
                    ((menuClickListener) getActivity()).clickMenu(position);
                }

            }
        });
        return mView;
    }
}
