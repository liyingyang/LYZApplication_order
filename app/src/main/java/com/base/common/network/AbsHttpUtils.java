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


    protected abstract T get();
    protected abstract T post();
    protected abstract T put();

    /**
     * 请求String数据
     * */
    protected void askForString(Call call) {
        Observable.create((Observable.OnSubscribe<T>) subscriber -> {

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
        Observable.create((Observable.OnSubscribe<T>) subscriber -> {

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
     * 请求JSONArray数据
     * */
    protected void askForJSONArray() {
        Observable.create((Observable.OnSubscribe<T>) subscriber -> {

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
     * 请求File数据
     * */
    protected void askForFile() {
        Observable.create((Observable.OnSubscribe<T>) subscriber -> {

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
}
