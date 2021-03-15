package com.w.xd.mvp.base.v;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.w.xd.mvp.base.BaseFragment;
import com.w.xd.mvp.base.BaseView;
import com.w.xd.mvp.base.p.IBasePresenter;
import com.w.xd.mvp.widgets.MvpLoadingView;


public abstract class MvpBaseFragment<P extends IBasePresenter> extends BaseFragment implements IBaseView<P> , BaseView {

    protected P mPresenter;

    protected MvpLoadingView mLoadingView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mPresenter != null){
            mPresenter.bindView(this);
        }
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }



    @Override
    public Context getMvpContent() {
        return getContext();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPresenter != null){
            mPresenter.unBind();
        }
    }

    @Override
    public IBasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void setLoadView(MvpLoadingView loadView) {
        mLoadingView = loadView;
    }

    @Override
    public MvpLoadingView getLoadingView() {
        return mLoadingView;
    }



}
