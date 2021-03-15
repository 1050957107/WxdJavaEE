package com.w.xd.mvp.base.v;

import android.content.Context;

import com.w.xd.mvp.base.p.IBasePresenter;


public interface IBaseView<P extends IBasePresenter>  {
    P createPresenter();
    Context getMvpContent();

}
