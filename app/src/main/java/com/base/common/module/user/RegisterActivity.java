package com.base.common.module.user;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.base.common.R;
import com.base.common.base.ToolbarActivity;

/**
 * 注册
 */
public class RegisterActivity extends ToolbarActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUI(R.layout.act_register);
    }
}
