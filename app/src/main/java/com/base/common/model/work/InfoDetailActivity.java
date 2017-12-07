package com.base.common.model.work;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.base.common.R;
import com.base.common.base.ToolbarActivity;

/**
 *
 * 查看资料详情
 */
public class InfoDetailActivity extends ToolbarActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUI(R.layout.act_info_detail);
    }
}
