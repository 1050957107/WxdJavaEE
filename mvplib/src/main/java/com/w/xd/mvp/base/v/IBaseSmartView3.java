package com.w.xd.mvp.base.v;


import com.w.xd.mvp.base.p.IBaseSmartPresenter3;
import com.w.xd.mvp.data.response.MvpResponse;

public interface IBaseSmartView3<D1,D2,D3,P extends IBaseSmartPresenter3<D1,D2,D3,?>> extends IBaseSmartView2<D1,D2,P> {
    void onResult3(MvpResponse<D3> response);
}
