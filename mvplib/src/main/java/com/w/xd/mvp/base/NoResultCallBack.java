package com.w.xd.mvp.base;



import com.w.xd.mvp.data.response.MvpResponse;

import io.reactivex.rxjava3.disposables.Disposable;

public class NoResultCallBack<T> implements IBaseCallBack<T> {
    @Override
    public void onResult(MvpResponse<T> response) {

    }

    @Override
    public void onStart(Disposable disposable) {

    }



}
