package com.base.common;

import android.os.Bundle;
import android.util.Log;

import com.base.common.base.ToolbarActivity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUI(R.layout.activity_main);
        setToolbarTitle("首页");
        /*try {
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
                    Log.v("sssdeeee", response.body().toString());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }*/

        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            if (!subscriber.isUnsubscribed()) {
                subscriber.onNext("hello");
                subscriber.onNext("world");
                subscriber.onNext(downLoadJosn());
                subscriber.onCompleted();
            }
        })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.i("adu", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("adu", "onError");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("adu", "onNext" + s);
                    }
                });

        Integer[] integer = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Observable.from(integer).subscribe(integer1 -> Log.i("adu", integer1.toString()));

        Observable.interval(1, 1, TimeUnit.SECONDS).subscribe(aLong -> Log.i("adu", aLong.toString()));

        Observable.just(1,2,3,4,5,6,7,8).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer < 5;
            }
        }).observeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i("adu","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("adu","onError");
            }

            @Override
            public void onNext(Integer integer) {
                Log.i("adu","inNext==》》"+integer);
            }
        });
    }

    private String downLoadJosn() {
        return "Json down";
    }
}
