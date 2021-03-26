package com.w.xd.mvp.base.callback;



import com.w.xd.mvp.data.response.MvpResponse;

import io.reactivex.rxjava3.disposables.Disposable;

public interface IBaseCallBack<T>{
    void onResult(MvpResponse<T> response);
    default void onStart(Disposable disposable){
    }
}
