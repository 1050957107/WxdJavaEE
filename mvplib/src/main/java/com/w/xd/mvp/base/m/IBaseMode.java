package com.w.xd.mvp.base.m;


import com.trello.rxlifecycle4.LifecycleProvider;
import com.w.xd.mvp.base.IBaseCallBack;
import com.w.xd.mvp.data.request.MvpRequest;


public interface IBaseMode {

    <T> void doRequest(LifecycleProvider lifecycleProvider, MvpRequest<T> request, IBaseCallBack<T> callBack);
}
