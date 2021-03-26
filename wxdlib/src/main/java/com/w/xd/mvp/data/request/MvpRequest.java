package com.w.xd.mvp.data.request;




import com.w.xd.mvp.manager.MvpManager;

import java.lang.reflect.Type;
import java.util.HashMap;

public class MvpRequest<T> {

    protected String url;
    protected RequestType requestType = RequestType.FIRST;  // 第一次请求 0，刷新1 加载更多2
    protected RequestMethod requestMethod;
    protected HashMap<String, Object> params;
    protected HashMap<String, Object> headers;
    private Type type;

    protected boolean isEnableCancel;


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public MvpRequest(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public HashMap<String, Object> getParams() {
        return params == null ? new HashMap<>() : params;
    }

    public MvpRequest<T> addParams(String key,Object value){
        getParams().put(key,value);
        return this;
    }

    public void setParams(HashMap<String, Object> params) {
        if (this.params != null) {
            this.params.putAll(params);
        } else {
            this.params = params;
        }
    }

    public MvpRequest<T> putParams(String key, Object value) {
        if (params == null) {
            params = new HashMap<>();
        }

        params.put(key, value);
        return this;
    }


    public HashMap<String, Object> getHeaders() {
        return headers == null ? new HashMap<>() : headers;
    }

    public void setHeaders(HashMap<String, Object> headers) {
        this.headers = headers;
    }

    public boolean isEnableCancel() {
        return isEnableCancel;
    }

    public void setEnableCancel(boolean enableCancel) {
        isEnableCancel = enableCancel;
    }
}
