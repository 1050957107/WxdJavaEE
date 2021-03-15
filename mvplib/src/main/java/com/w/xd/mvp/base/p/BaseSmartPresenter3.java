package com.w.xd.mvp.base.p;



import com.w.xd.mvp.base.IBaseCallBack;
import com.w.xd.mvp.base.m.IBaseMode;
import com.w.xd.mvp.base.v.IBaseSmartView3;
import com.w.xd.mvp.data.request.MvpRequest;
import com.w.xd.mvp.data.response.MvpResponse;

import java.lang.reflect.Type;

import io.reactivex.rxjava3.disposables.Disposable;

public class BaseSmartPresenter3<D1,D2,D3,V extends IBaseSmartView3<D1,D2,D3,?>> extends BaseSmartPresenter2<D1,D2,V> implements IBaseSmartPresenter3<D1,D2,D3,V> {

    protected Type mType3;


    public BaseSmartPresenter3() {

    }

    public BaseSmartPresenter3(IBaseMode baseMode) {
        super(baseMode);
    }

    @Override
    public void setType3(Type type) {
        mType3 = type;
    }

    @Override
    public void doRequest3(MvpRequest<D3> request) {
        mMode.doRequest(getLifecycleProvider(),request, new IBaseCallBack<D3>() {
            @Override
            public void onStart(Disposable disposable) {
                handStart(disposable);
            }

            @Override
            public void onResult(MvpResponse<D3> response) {
                if(mView != null){
                    mView.onResult3(response);
                }
            }
        });
    }
}
