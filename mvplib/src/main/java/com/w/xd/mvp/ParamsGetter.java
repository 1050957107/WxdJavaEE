package com.w.xd.mvp;

import java.util.HashMap;

public interface ParamsGetter {

    HashMap<String,Object> getParams();

    default HashMap<String,Object> getHeaders(){
        return new HashMap<>();
    }
}
