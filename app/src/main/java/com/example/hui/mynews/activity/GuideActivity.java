package com.example.hui.mynews.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.hui.mynews.utils.ConfigStatic;
import com.example.hui.mynews.adapter.FragmentAdapter;
import com.example.hui.mynews.R;
import com.example.hui.mynews.fragment.Image_One_Fragment;
import com.example.hui.mynews.fragment.Image_Three_Fragment;
import com.example.hui.mynews.fragment.Image_Two_Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanlongzh on 2016/12/20.
 */

public class GuideActivity extends FragmentActivity {
    private List<Fragment> mlist;
    private ViewPager vp;
    private ImageView mimage1;
    private ImageView mimage2;
    private ImageView mimage3;
    private int currentIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_point_layout);
        fristSplash();
        initFragment();
        initView();

    }

    private void fristSplash() {
        SharedPreferences spf = getSharedPreferences(ConfigStatic.SHAREDREFERENCE_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putBoolean("isFirst",false);
        editor.apply();
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.viewpager);
        mimage1 = (ImageView) findViewById(R.id.point_1);
        mimage2 = (ImageView) findViewById(R.id.point_2);
        mimage3 = (ImageView) findViewById(R.id.point_3);
        vp.setAdapter(new FragmentAdapter(getSupportFragmentManager(),mlist));
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mimage1.setImageResource(R.drawable.guide_point_normal);
                        mimage2.setImageResource(R.drawable.guide_point_pressed);
                        mimage3.setImageResource(R.drawable.guide_point_pressed);
                        break;
                    case 1:
                        mimage1.setImageResource(R.drawable.guide_point_pressed);
                        mimage2.setImageResource(R.drawable.guide_point_normal);
                        mimage3.setImageResource(R.drawable.guide_point_pressed);
                        break;
                    case 2:
                        mimage1.setImageResource(R.drawable.guide_point_pressed);
                        mimage2.setImageResource(R.drawable.guide_point_pressed);
                        mimage3.setImageResource(R.drawable.guide_point_normal);
                        break;
                }
                currentIndex=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setCurrentItem(currentIndex);
    }

    private void initFragment() {
        mlist = new ArrayList<Fragment>();
        mlist.add(new Image_One_Fragment());
        mlist.add(new Image_Two_Fragment());
        mlist.add(new Image_Three_Fragment());
    }
}
