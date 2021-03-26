package com.w.xd.mvp.base.view;

public interface BaseView<T> {
    void onSuccess(T t);
    void onFail(String error);
}
