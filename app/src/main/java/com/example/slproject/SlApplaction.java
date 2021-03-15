package com.example.slproject;

import androidx.multidex.MultiDexApplication;

public class SlApplaction extends MultiDexApplication {

    private static SlApplaction instance;


    public static SlApplaction getInstance(){
        if (instance==null){
            synchronized (SlApplaction.class){
                instance=new SlApplaction();
            }
        }
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
