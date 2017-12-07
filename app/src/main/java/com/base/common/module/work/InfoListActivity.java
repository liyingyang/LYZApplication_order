package com.base.common.module.work;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.base.common.R;
import com.base.common.base.ToolbarActivity;

/**
 * 查看上传信息列表
 */
public class InfoListActivity extends ToolbarActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUI(R.layout.act_info_list);
    }
}
