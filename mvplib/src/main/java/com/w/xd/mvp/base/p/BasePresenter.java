package com.w.xd.mvp.base.p;

import android.content.Context;


import com.trello.rxlifecycle4.LifecycleProvider;
import com.w.xd.mvp.base.v.IBaseView;

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V>{

    protected V mView;


    @Override
    public void bindView(V view) {
        mView = view;
    }

    @Override
    public void unBind() {
        mView = null;
    }

    @Override
    public Context getMvpContent() {
        if(mView != null){
            return mView.getMvpContent();
        }
       return null;
    }

    public LifecycleProvider getLifecycleProvider(){
        return (LifecycleProvider) mView;
    }





}
