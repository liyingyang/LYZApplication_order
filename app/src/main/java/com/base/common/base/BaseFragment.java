package com.base.common.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

public abstract class BaseFragment extends Fragment {
    public int pageSize = 20;
    public int pageNum = 1;
    private static final String LOG_TAG = "Error";
    protected View contentView;

    public String tag = "";
    private static Toast toast;
    private boolean isInit;


    protected abstract int getLayoutId();

    protected abstract void start(Bundle savedInstanceState);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isInit) {
            return;
        }
        startBefor(savedInstanceState);
        start(savedInstanceState);
        startAfter(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!TextUtils.isEmpty(tag)) {
            outState.putString("baseTag", tag);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (contentView == null) {
            contentView = inflater.inflate(getLayoutId(), null);
        }

        return contentView;
    }

    protected void startBefor(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("baseTag")) {
            tag = savedInstanceState.getString("baseTag", "");
        }
    }

    protected void startAfter(Bundle savedInstanceState) {
        isInit = true;
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

    /**
     * @return 屏幕宽度 （px）
     */
    protected int getScreenWidth() {
        return this.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * @return 屏幕高度 （px）
     */
    public int getScreenHeight() {
        return this.getResources().getDisplayMetrics().heightPixels;
    }


    public void NSLog(String msg) {
        Log.v(LOG_TAG, msg);
    }

    public Dialog simpleLoading(String loadingMessage) {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("正在加载，请稍后...");
        if (!TextUtils.isEmpty(loadingMessage)) {
            progressDialog.setMessage(loadingMessage);
        }
        return progressDialog;
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

        List<Fragment> fragments = getChildFragmentManager().getFragments();
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

            } catch (java.lang.InstantiationException e) {
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
}
