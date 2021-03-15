package com.w.xd.mvp.base.p;



import com.w.xd.mvp.base.v.IBaseSmartView3;
import com.w.xd.mvp.data.request.MvpRequest;

import java.lang.reflect.Type;

public interface IBaseSmartPresenter3<D1,D2,D3,V extends IBaseSmartView3<D1,D2,D3,?>> extends IBaseSmartPresenter2<D1,D2,V> {
    void setType3(Type type);
    void doRequest3(MvpRequest<D3> request);

}
