package com.w.xd.mvp.base.p;

import android.content.Context;

import com.w.xd.mvp.base.v.IBaseView;


public interface IBasePresenter<V extends IBaseView> {
    void bindView(V view);
    void unBind();
    Context getMvpContent();
    boolean cancelRequest();



}
