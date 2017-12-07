package com.base.common.model.user;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.base.common.R;
import com.base.common.base.ToolbarActivity;

/**
 * 登录
 */
public class LoginActivity extends ToolbarActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUI(R.layout.act_login);
    }
}
