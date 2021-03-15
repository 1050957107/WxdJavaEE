package com.w.xd.mvp.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;
import com.w.xd.mvp.utils.Logger;
import com.w.xd.mvp.utils.StatusBar;
import com.w.xd.mvp.utils.TimeUtils;

import java.math.BigDecimal;


public abstract class BaseActivity extends RxAppCompatActivity {
    protected Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean haveNavigationBar = StatusBar.isHaveNavigationBar(this);
        if (haveNavigationBar){
            StatusBar.transportStatus(this);
        }
        if (null == bundle) {
            bundle = new Bundle();
        }
        if (getLayoutId() > 0) {
            View view = getLayoutInflater().inflate(getLayoutId(), findViewById(android.R.id.content), false);
            setContentView(view);
            bindingView(view);
            initView();
            initListener();
        }
    }

    protected void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(@StringRes int id) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    }

    protected void bindingView(View view) {

    }

    protected abstract int getLayoutId();


    protected abstract void initView();

    protected void initListener(){

    }

    public void showfitsSystemWindows() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    //判断当前线程
    public boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

}
