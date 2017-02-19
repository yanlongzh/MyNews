package com.example.hui.mynews;

import android.content.Intent;
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

import com.example.hui.mynews.activity.AboutActivity;
import com.example.hui.mynews.fragment.PhoneFragment;
import com.example.hui.mynews.fragment.RobotFragment;
import com.example.hui.mynews.fragment.SettingFragment;
import com.example.hui.mynews.fragment.ExpressFragment;
import com.example.hui.mynews.fragment.HomePageFragment;
import com.example.hui.mynews.fragment.LeftFragment;
import com.example.hui.mynews.utils.LeftData;
import com.example.hui.mynews.utils.LeftItemBean;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LeftFragment.menuClickListener{

    private Toolbar mToolbar;
    private LayoutInflater infla;
    private DrawerLayout mdraw;
    private ActionBarDrawerToggle mabdt;
    private List<LeftItemBean> mlist;

    private HomePageFragment mHomePageFragment;
    private ExpressFragment mExpressFragment;
    private RobotFragment mRobotFragment;
    private SettingFragment mSettingFragment;
    private PhoneFragment mPhoneFragment;

    private Fragment isFragment;
    private boolean isShowShuffle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        initFragment(savedInstanceState);
    }

    private void initFragment(Bundle savedInstanceState) {
        if(savedInstanceState==null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mHomePageFragment = new HomePageFragment();
            isFragment = mHomePageFragment;
            ft.add(R.id.main_fragment,mHomePageFragment).commit();
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
                   mHomePageFragment= new HomePageFragment();
               }
                switchFragment(isFragment,mHomePageFragment);
                isShowShuffle = false;
                break;
            case "快递":
                if(isFragment!=null){
                    mExpressFragment = new ExpressFragment();
                }
                switchFragment(isFragment,mExpressFragment);
                break;
            case "归属地":
                if(isFragment!=null){
                    mPhoneFragment=new PhoneFragment();
                }
                switchFragment(isFragment,mPhoneFragment);
                break;
            case "机器人":
                if(isFragment!=null){
                    mRobotFragment= new RobotFragment();
                }
                switchFragment(isFragment,mRobotFragment);
                break;
            case "设置":
                if(isFragment!=null){
                    mSettingFragment = new SettingFragment();
                }
                switchFragment(isFragment,mSettingFragment);
                break;
        }
        // 刷新oncreatoptionmenu()方法的
        invalidateOptionsMenu();
        mdraw.closeDrawers();
    }

   //该方法只在menu创建的时候创建一次，后来如果想要更改menu的item，则需要invalidateOptionsMenu();
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

        if(!isFragment.equals(to)) {
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
