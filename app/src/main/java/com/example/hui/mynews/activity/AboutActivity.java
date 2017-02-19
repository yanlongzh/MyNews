package com.example.hui.mynews.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hui.mynews.R;
import com.example.hui.mynews.utils.GetVersion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanlongzh on 2017/2/5.
 */
public class AboutActivity extends BaseActivity{
    private List<String> lists;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
        initView();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.about_list);
        lists  = new ArrayList<>();
        lists.add("我的应用");
        lists.add("版本号："+ GetVersion.getVersionName(this));
        lists.add("应用源码:https://github.com/yanlongzh");
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lists);
        mListView.setAdapter(adapter);
    }

}
