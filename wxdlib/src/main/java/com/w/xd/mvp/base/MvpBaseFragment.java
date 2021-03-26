package com.w.xd.mvp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.w.xd.mvp.base.BaseFragment;
import com.w.xd.mvp.base.view.BaseView;
import com.w.xd.mvp.base.presenter.BasePresenter;


public abstract class MvpBaseFragment<P extends BasePresenter,V extends BaseView> extends BaseFragment {

    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mPresenter != null){
            mPresenter.bindView(createView());
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    protected abstract P createPresenter();
    protected abstract V createView();
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPresenter != null){
            mPresenter.unBind();
        }
    }
}
