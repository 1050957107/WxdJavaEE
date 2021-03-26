package com.w.xd.mvp.data.request;

public class PostRequest<T> extends MvpRequest<T> {
    public PostRequest(String url) {
        super(url);
    }

    @Override
    public RequestMethod getRequestMethod() {
        return RequestMethod.POST;
    }
}
