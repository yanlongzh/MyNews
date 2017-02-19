package com.example.hui.mynews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hui.mynews.R;
import com.example.hui.mynews.activity.PersonalActivity;
import com.example.hui.mynews.adapter.MyListAdapter;
import com.example.hui.mynews.utils.LeftData;
import com.example.hui.mynews.utils.LeftItemBean;

import java.util.List;

/**
 * Created by yanlongzh on 2016/12/19.
 */

public class LeftFragment extends Fragment implements View.OnClickListener {
    private ListView mListView;
    private MyListAdapter mAdapter;
    private View mView;
    private List<LeftItemBean> mItemBeen;
    private ImageView title_icon;
    private TextView title_tv;
    private Button person_bt;

    /*
    * 主页接口 回调设置页面
    * */
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
        initView();
        return mView;
    }

    private void initView() {
        person_bt = (Button) mView.findViewById(R.id.left_person_bt);
        title_icon = (ImageView) mView.findViewById(R.id.title_icon);
        title_tv = (TextView) mView.findViewById(R.id.title_tv);
        title_icon.setOnClickListener(this);
        title_tv.setOnClickListener(this);
        person_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_person_bt:
            case R.id.title_icon:
            case R.id.title_tv:
                startActivity(new Intent(getActivity(),PersonalActivity.class));
                break;
        }
    }
}
