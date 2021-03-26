package com.w.xd.mvp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;


import com.w.xd.mvp.manager.MvpManager;

import java.util.ArrayList;
import java.util.List;

public class MvpInitializer implements Initializer<Void> {


    @NonNull
    @Override
    public Void create(@NonNull Context context) {
         MvpManager.init(context);
         return null;
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return new ArrayList<>();
    }
}
