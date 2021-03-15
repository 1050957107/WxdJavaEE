package com.w.xd.mvp.base.p;


import com.w.xd.mvp.base.IBaseCallBack;
import com.w.xd.mvp.base.m.IBaseMode;
import com.w.xd.mvp.base.v.IBaseSmartView2;
import com.w.xd.mvp.data.request.MvpRequest;
import com.w.xd.mvp.data.response.MvpResponse;

import java.lang.reflect.Type;

import io.reactivex.rxjava3.disposables.Disposable;

public class BaseSmartPresenter2<D1,D2,V extends IBaseSmartView2<D1,D2,?>> extends BaseSmartPresenter1<D1,V> implements IBaseSmartPresenter2<D1,D2,V> {

    protected Type mType2;

    public BaseSmartPresenter2() {
    }

    public BaseSmartPresenter2(IBaseMode baseMode) {
        super(baseMode);
    }

    @Override
    public void setType2(Type type) {
        mType2 = type;
    }

    @Override
    public void doRequest2(MvpRequest<D2> request) {
        mMode.doRequest(getLifecycleProvider(),request, new IBaseCallBack<D2>() {
            @Override
            public void onStart(Disposable disposable) {
               handStart(disposable);
            }
            @Override
            public void onResult(MvpResponse<D2> response) {
                if(mView != null){
                    mView.onResult2(response);
                }
            }
        });
    }

}
