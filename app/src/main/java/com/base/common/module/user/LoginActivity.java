package com.base.common.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.base.common.MainActivity;
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
        setToolbarTitle("登录");
        initWithUI();
    }

    private void initWithUI() {
        findViewById(R.id.btn_login).setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_register).setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
