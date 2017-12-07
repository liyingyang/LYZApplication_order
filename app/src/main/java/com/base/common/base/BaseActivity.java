package com.base.common.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.base.common.R;
import com.base.common.util.StatusBarHelper;
import com.base.common.util.SystemUtils;

import java.util.List;

public class BaseActivity extends AppCompatActivity {
    public int pageSize = 20;
    public int pageNum = 1;
    private static final String LOG_TAG = "Error";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (SystemUtils.v21()) {
            if (StatusBarHelper.setMiuiStatusBarDarkMode(this, true)
                    || StatusBarHelper.setMeizuStatusBarDarkIcon(this, true)) {
                getWindow().setStatusBarColor(Color.WHITE);

            }

        }
        //必须在代码里面设置状态栏颜色，否则在乐视手机上回出现状态栏变黑问题
        setStatusBarColor(R.color.colorPrimaryDark);
    }

    protected void setStatusBarColor(@ColorRes int color) {
        if (SystemUtils.v21()) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, color));
        }

    }

    public void NSLog(String msg) {
        Log.v(LOG_TAG, msg);
    }

    /**
     * 通过这个方法可以避免重复实例化Fragment
     *
     * @param <T>
     * @param clazz
     * @param tag
     * @return
     */
    public <T extends BaseFragment> T getFragment(Class<?> clazz, String tag) {

        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        BaseFragment fragment = null;
        if (fragments != null && fragments.size() > 0) {
            for (Fragment fragment1 : fragments) {

                if (fragment1 != null && fragment1 instanceof BaseFragment) {
                    BaseFragment baseFragment = (BaseFragment) fragment1;
                    if (baseFragment.getClass().getName().equals(clazz.getName())) {
                        if (!TextUtils.isEmpty(tag)) {
                            if (baseFragment.tag.equals(tag)) {
                                fragment = baseFragment;
                            }
                        } else {
                            fragment = baseFragment;
                        }
                    }

                }
            }
        }


        if (fragment == null) {
            try {
                fragment = (BaseFragment) clazz.newInstance();

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (tag != null) {
            fragment.tag = tag;
        }

        return (T) fragment;
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public int getStatusHeight() {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = this.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
}
