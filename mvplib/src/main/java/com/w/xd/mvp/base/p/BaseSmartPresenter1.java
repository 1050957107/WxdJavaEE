package com.w.xd.mvp.base.p;



import com.w.xd.mvp.base.IBaseCallBack;
import com.w.xd.mvp.base.m.IBaseMode;
import com.w.xd.mvp.base.v.IBaseSmartView1;
import com.w.xd.mvp.data.BaseRepository;
import com.w.xd.mvp.data.request.MvpRequest;
import com.w.xd.mvp.data.response.MvpResponse;

import java.lang.reflect.Type;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class BaseSmartPresenter1<D,V extends IBaseSmartView1<D,?>> extends BasePresenter<V> implements IBaseSmartPresenter1<D,V> {

    protected IBaseMode mMode;
    protected CompositeDisposable mCompositeDisposable;
    protected Type mType;

    @Override
    public void setType(Type type) {
        mType = type;
    }

    public BaseSmartPresenter1() {
        mMode = new BaseRepository();
    }

    public BaseSmartPresenter1(IBaseMode baseMode) {
        mMode = baseMode;
    }

    public void doRequest(MvpRequest<D> request) {

        request.setType(mType);

        mMode.doRequest(getLifecycleProvider(),request, new IBaseCallBack<D>() {
            @Override
            public void onStart(Disposable disposable) {
               handStart(disposable);
            }

            @Override
            public void onResult(MvpResponse<D> response) {
                if(mView != null){
                    mView.onResult1(response);
                }
            }
        });
    }

    @Override
    public boolean cancelRequest() {
        if(mCompositeDisposable != null){
            mCompositeDisposable.dispose();
            return true;
        }

        return false;
    }

    protected  void handStart(Disposable disposable){
        if(mCompositeDisposable == null){
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

}
