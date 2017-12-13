package com.base.common.network;


import okhttp3.Call;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 */
public abstract class AbsHttpUtils<T> {

    /**
     * 请求String数据
     * */
    protected void askForString(Call call) {
        Observable.create(new Observable.OnSubscribe<T>() {

            @Override
            public void call(Subscriber<? super T> subscriber) {

            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<T>() {

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(T t) {

                    }
                });
    }

    /**
     * 请求JSONObject数据
     * */
    protected void askForJSONObject() {

    }

    /**
     * 请求JSONArray数据
     * */
    protected void askForJSONArray() {

    }

    /**
     * 请求File数据
     * */
    protected void askForFile() {

    }
}
