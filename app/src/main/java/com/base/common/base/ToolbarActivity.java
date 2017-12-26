package com.base.common.base;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.common.R;
import com.base.common.util.StatusBarHelper;
import com.base.common.util.SystemUtils;

public class ToolbarActivity extends BaseActivity {

    public Toolbar toolbar;
    public AppBarLayout appBarLayout;
    protected RelativeLayout contentRoot;
    private View toolbar_shadow_compat;
    private AppBarLayout app_bar_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateBefor(savedInstanceState);
        setContentView(R.layout.base_ui);

        appBarLayout = findViewById(R.id.app_bar_layout);
        toolbar_shadow_compat = findViewById(R.id.toolbar_shadow_compat);
        app_bar_layout = findViewById(R.id.app_bar_layout);

        //适配Toolbar阴影
        if (!SystemUtils.v21()) {
            toolbar_shadow_compat.setVisibility(View.VISIBLE);
        } else {
            toolbar_shadow_compat.setVisibility(View.GONE);
            appBarLayout.setStateListAnimator(AnimatorInflater.loadStateListAnimator(this,
                    R.animator.appbar_elevation));
        }
        //
        toolbar = findViewById(R.id.toolbar);
        contentRoot = findViewById(R.id.ui_layout);
        refreshToolbar();
        showBack(true);

    }


    protected void onCreateBefor(@Nullable Bundle savedInstanceState) {

    }


    public void showToolbar(boolean isShow) {
        if (isShow) {
            app_bar_layout.setVisibility(View.VISIBLE);
        } else {
            app_bar_layout.setVisibility(View.GONE);
        }

        if (!SystemUtils.v21()) {
            if (isShow) {
                toolbar_shadow_compat.setVisibility(View.VISIBLE);
            } else {
                toolbar_shadow_compat.setVisibility(View.GONE);
            }
        }
    }

    public void onBackClick() {
        onBackPressed();
    }


    public void refreshToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackClick());
    }

    public void showBack(boolean isShow) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(isShow);
    }

    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
        refreshToolbar();
    }

    public void setToolbarTitle(int strId) {
        toolbar.setTitle(getString(strId));
        refreshToolbar();
    }

    public void setToolbarTileCenter(String title) {
        toolbar.setTitle("");
        TextView txv_title = findViewById(R.id.txv_title);
        txv_title.setText(title);
        refreshToolbar();
    }

    protected void loadUI(int layoutId) {
        View vMainCont = getLayoutInflater().inflate(layoutId, null);
        vMainCont.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        contentRoot.addView(vMainCont);
    }

    protected void hideActionMenuView() {
        if (toolbar == null) {
            return;
        }
        toolbar.post(() -> {
            final int len = toolbar.getChildCount();
            for (int i = 0; i < len; i++) {
                View view = toolbar.getChildAt(i);
                if (view instanceof ActionMenuView) {
                    view.setVisibility(View.GONE);
                }
            }
        });

    }

    /**
     * 显示菜单按钮
     */
    protected void showActionMenuView() {
        if (toolbar == null) {
            return;
        }
        toolbar.post(() -> {
            final int len = toolbar.getChildCount();
            for (int i = 0; i < len; i++) {
                View view = toolbar.getChildAt(i);
                if (view instanceof ActionMenuView) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    /**
     * 动画改变View的高度
     *
     * @param view
     * @param originalHeight
     * @param targetHeight
     */
    protected void setViewHeight(final View view, int originalHeight, int targetHeight, Animator.AnimatorListener listener) {
        ObjectAnimator anim = ObjectAnimator//
                .ofInt(view, "setViewHeight", originalHeight, targetHeight)
                .setDuration(300);//

        final ViewGroup.LayoutParams params = view.getLayoutParams();

        anim.addUpdateListener((animation) ->
        {
            int cVal = (int) animation.getAnimatedValue();
            params.height = cVal;
            view.setLayoutParams(params);
        });
        if (listener != null) {
            anim.addListener(listener);
        }

        anim.start();

    }


    /**
     * 支持Android 4.4以上的状态栏
     *
     * @param isCompat
     */
    protected void compatStatusBar(boolean isCompat) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        View statusBar = findViewById(R.id.status_bar_compat);
        if (isCompat) {
            statusBar.setVisibility(View.VISIBLE);
        } else {
            statusBar.setVisibility(View.GONE);
        }


        if (StatusBarHelper.setMiuiStatusBarDarkMode(this, true)
                || StatusBarHelper.setMeizuStatusBarDarkIcon(this, true)) {
            statusBar.setBackgroundColor(Color.WHITE);
        } else {
            statusBar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        ViewGroup.LayoutParams params = statusBar.getLayoutParams();
        if (params != null) {
            params.height = getStatusHeight();
            statusBar.setLayoutParams(params);
        }
    }

    /**
     * 撑开头部布局，使内容布局移到状态栏
     */
    protected void strutToolBarLayout(boolean isStrut) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) contentRoot.getLayoutParams();
        if (params == null) {
            return;
        }
        if (isStrut) {
            params.addRule(RelativeLayout.BELOW);
        } else {
            params.addRule(RelativeLayout.BELOW, R.id.app_bar_layout);
        }

    }


    protected void setToolbarBackMenuColor(int color) {
        Drawable upArrow = toolbar.getNavigationIcon();
        if (upArrow != null) {
            upArrow.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
        }
    }

    protected void setAppBarLayoutElevation(float elevation) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StateListAnimator stateListAnimator = new StateListAnimator();
            stateListAnimator.addState(new int[0], ObjectAnimator.ofFloat(appBarLayout, "elevation", elevation));
            appBarLayout.setStateListAnimator(stateListAnimator);
        }
    }


    protected void hideAppbarLayoutShadow() {
        //去掉Appbar阴影
        setAppBarLayoutElevation(0);
        //隐藏掉兼容阴影
        toolbar_shadow_compat.setVisibility(View.GONE);
    }
}
