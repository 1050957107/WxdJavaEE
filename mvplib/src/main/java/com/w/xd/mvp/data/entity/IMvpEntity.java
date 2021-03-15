package com.w.xd.mvp.data.entity;


public interface IMvpEntity<T> {

    boolean isOk(); //
    String getMessage();
    T getData();
    int getCode(); //


}
