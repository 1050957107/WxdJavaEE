package com.w.xd.mvp.base.v;


import com.w.xd.mvp.base.p.IBaseSmartPresenter1;
import com.w.xd.mvp.data.response.MvpResponse;

public interface IBaseSmartView1<D,P extends IBaseSmartPresenter1> extends IBaseView<P> {
    void onResult1(MvpResponse<D> response);
}
