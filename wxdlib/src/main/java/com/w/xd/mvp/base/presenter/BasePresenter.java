package com.w.xd.mvp.base.presenter;

import com.w.xd.mvp.base.view.BaseView;

public abstract class BasePresenter<V extends BaseView> {

    protected V mView;

    public BasePresenter() {
        createModel();
    }
    public void bindView(V mView) {
        this.mView = mView;
    }
    public void unBind() {
        mView = null;
    }
    protected abstract void createModel();
}
