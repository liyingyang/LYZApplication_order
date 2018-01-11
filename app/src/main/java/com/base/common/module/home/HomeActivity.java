package com.base.common.module.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.base.common.R;
import com.base.common.base.BaseFragmentPagerAdapter;
import com.base.common.base.ToolbarActivity;
import com.base.common.module.home.fragment.HomeFragment;
import com.base.common.module.home.fragment.UserFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class HomeActivity extends ToolbarActivity {

    private ViewPager viewPager;
    private List<Fragment> fragments;
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUI(R.layout.activity_main);
        setToolbarTitle("");
        showBack(false);
        initView();
    }

    private void initView() {
        viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(2);

        //首页
        HomeFragment homeFragment = getFragment(HomeFragment.class, null);
        //我的
        UserFragment userFragment = getFragment(UserFragment.class, null);

        fragments = new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(userFragment);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        navigationView.setSelectedItemId(R.id.nav_home);
                        setToolbarTitle("");
                        break;
                    case 1:
                        navigationView.setSelectedItemId(R.id.nav_user);
                        setToolbarTitle("");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new BaseFragmentPagerAdapter(getSupportFragmentManager(), fragments));

        navigationView = findViewById(R.id.nav_view);
        navigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.nav_user:
                    viewPager.setCurrentItem(1);
                    break;
                default:
                    break;
            }
            return true;
        });

    }


    @Override
    public void onBackPressed() {
        exitApp();
    }
}
