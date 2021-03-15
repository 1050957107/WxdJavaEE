package com.w.xd.mvp.base.p;


import com.w.xd.mvp.base.v.IBaseSmartView2;
import com.w.xd.mvp.data.request.MvpRequest;

import java.lang.reflect.Type;

public interface IBaseSmartPresenter2<D1,D2,V extends IBaseSmartView2<D1,D2,?>> extends IBaseSmartPresenter1<D1,V> {
    void setType2(Type type);
    void doRequest2(MvpRequest<D2> request);

}
