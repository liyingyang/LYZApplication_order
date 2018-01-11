package com.base.common.widget.loadpage;

import android.view.View;

public interface ILoadPage {
    void showLoading(String message);

    void showLoading();

    void showError(String message, View.OnClickListener listener);

    void showError(String message);

    void showEmpty(View.OnClickListener listener);

    void showEmpty(String message);

    void hide();

    void gotoLogin(String message, View.OnClickListener listener);

    void gotoLogin(String message);

    void showNetworkError(View.OnClickListener listener);
}
