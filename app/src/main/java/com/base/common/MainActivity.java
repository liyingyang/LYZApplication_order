package com.base.common;

import android.os.Bundle;
import android.util.Log;

import com.base.common.base.ToolbarActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUI(R.layout.activity_main);

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://nanningdebug.ujuz.cn/api/UAgent/GetAgentAll")
                    .addHeader("Index", "1")
                    .addHeader("Limit", "20")
                    .addHeader("Key", "1")
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.v("ssss", e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.v("sssd", response.body().toString());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
