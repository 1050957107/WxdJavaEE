package com.w.xd.mvp.base.p;



import com.w.xd.mvp.base.v.IBaseSmartView1;
import com.w.xd.mvp.data.request.MvpRequest;

import java.lang.reflect.Type;

public interface IBaseSmartPresenter1<D,V extends IBaseSmartView1<D,?>> extends IBasePresenter<V>{
    void setType(Type type);
    void doRequest(MvpRequest<D> request);



}
