package com.example.hui.mynews.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hui.mynews.R;
import com.example.hui.mynews.fragment.AttentionFragment;
import com.example.hui.mynews.fragment.CollectionFragment;
import com.example.hui.mynews.fragment.DraftFragment;
import com.example.hui.mynews.fragment.FindFragment;
import com.example.hui.mynews.fragment.HomePageFragment;
import com.example.hui.mynews.fragment.LeftFragment;
import com.example.hui.mynews.utils.ConfigStatic;
import com.example.hui.mynews.utils.LeftData;
import com.example.hui.mynews.utils.LeftItemBean;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LeftFragment.menuClickListener{
    private boolean isFristIn= false;
    private Toolbar mToolbar;
    private LayoutInflater infla;
    private DrawerLayout mdraw;
    private ActionBarDrawerToggle mabdt;
    private List<LeftItemBean> mlist;

    private HomePageFragment hfragment;
    private FindFragment ffragment;
    private CollectionFragment cfragment;
    private DraftFragment dfragment;
    private AttentionFragment afragment;

    private Fragment isFragment;
    private boolean isShowShuffle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSplash();
        initToolBar();
        initFragment(savedInstanceState);
    }



    private void initSplash() {
        SharedPreferences sp = getSharedPreferences(ConfigStatic.SHAREDREFERENCE_NAME,MODE_PRIVATE);
        isFristIn = sp.getBoolean("isFirst",true);
        if(isFristIn){
            Intent mintent = new Intent();
            mintent.setClass(this, GuideActivity.class);
            startActivity(mintent);
        }
    }

    private void initFragment(Bundle savedInstanceState) {
        if(savedInstanceState==null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            hfragment = new HomePageFragment();
            isFragment = hfragment;
            ft.add(R.id.main_fragment,hfragment).commit();
        }
    }

    private void initToolBar() {
        infla = LayoutInflater.from(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mdraw = (DrawerLayout) findViewById(R.id.drwa_layout);
        mabdt = new ActionBarDrawerToggle(this,mdraw,mToolbar,R.string.draw_open,R.string.draw_close);
        mabdt.syncState();
        mdraw.addDrawerListener(mabdt);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.toolbar_out:
                        finish();
                        break;
                    case R.id.toolbar_about:
                        startActivity(new Intent(MainActivity.this,AboutActivity.class));
                }

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
    }

    @Override
    public void clickMenu(int position) {
        mlist = LeftData.getItemBean();
        String menuname = mlist.get(position).getText();
        getSupportActionBar().setTitle(menuname);
        switch (menuname){
            case "首页":
               if(isFragment!=null){
                    hfragment= new HomePageFragment();
               }
                switchFragment(isFragment,hfragment);
                isShowShuffle = false;
                break;
            case "发现":
                if(isFragment!=null){
                    ffragment = new FindFragment();
                }
                switchFragment(isFragment,ffragment);
                break;
            case "关注":
                if(isFragment!=null){
                    afragment=new AttentionFragment();
                }
                switchFragment(isFragment,afragment);
                break;
            case "收藏":
                if(isFragment!=null){
                    cfragment= new CollectionFragment();
                }
                switchFragment(isFragment,cfragment);
                break;
            case "草稿":
                if(isFragment!=null){
                    dfragment = new DraftFragment();
                }
                switchFragment(isFragment,dfragment);
                break;
            case "提问":

        }
//        刷新oncreatoptionmenu()方法的
        invalidateOptionsMenu();
        mdraw.closeDrawers();
    }

//    该方法只在menu创建的时候创建一次，后来如果想要更改menu的item，则需要invalidateOptionsMenu();
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(isShowShuffle) {
            menu.findItem(R.id.toolbar_shuffle).setVisible(true);
        }else{
            menu.findItem(R.id.toolbar_shuffle).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public void switchFragment(Fragment from, Fragment to){

        if(isFragment!=to) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            isFragment = to;
            if (!to.isAdded()) {
                ft.hide(from).add(R.id.main_fragment,to).commit();
            }else{
                ft.hide(from).show(to).commit();
            }
        }
    }
}
