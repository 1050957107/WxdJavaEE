package com.w.xd.mvp.base.callback;

public interface BaseCallback<T> {
    void onSuccess(T t);

    void onFail(String error);
}
