package com.base.common.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.base.common.module.home.HomeActivity;
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
        setToolbarTileCenter("登录");
        showBack(false);
        initWithUI();
    }

    private void initWithUI() {
        findViewById(R.id.btn_login).setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.txv_register).setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.txv_forget).setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, ForgetPassWordActivity.class);
            startActivity(intent);
        });
    }
}
