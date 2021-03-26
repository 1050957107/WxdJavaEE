package com.w.xd.mvp.base.observer;


import android.util.Log;

import com.w.xd.mvp.data.response.MvpResponse;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


public abstract class BaseObserver<T> implements Observer<T> {
    public BaseObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(@NonNull T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFail(e.getMessage());
    }

    @Override
    public void onComplete() {
    }

    public abstract void onSuccess(T t);
    public abstract void onFail(String error);
}
