package com.w.xd.mvp.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.w.xd.mvp.base.BaseActivity;
import com.w.xd.mvp.base.view.BaseView;
import com.w.xd.mvp.base.presenter.BasePresenter;

public abstract class MvpBaseActivity<P extends BasePresenter,V extends BaseView> extends BaseActivity{

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.bindView(createView());
        }

    }

    protected abstract P createPresenter();

    protected abstract V createView();



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unBind();
    }
}
