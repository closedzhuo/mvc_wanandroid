package com.bonnidee.wanandroiddemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.bonnidee.wanandroiddemo.base.BaseActivity;
import com.bonnidee.wanandroiddemo.home.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.toobar)
    Toolbar toobar;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    @BindView(R.id.bnv_main_bottom)
    BottomNavigationView bnvMainBottom;
    @Override
    protected void initData() {
    }

    @Override
    protected void bindView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content,HomeFragment.instance()).commit();

        bnvMainBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, HomeFragment.instance()).commit();
                        return true;
                    case R.id.bottom_mine:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, HomeFragment.instance()).commit();
                        return true;

                }

                return false;
            }
        });


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


}
