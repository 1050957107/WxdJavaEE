package com.w.xd.mvp.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;


public abstract class BaseActivity extends RxAppCompatActivity {
    protected Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() > 0 && null == bundle) {
            bundle = new Bundle();
            View view = getLayoutInflater().inflate(getLayoutId(), findViewById(android.R.id.content), false);
            setContentView(view);
            bindingView(view);
            initView();
            initListener();
            loadData();
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

    protected void initListener() {
    }
    protected void loadData(){
    }
    public void showFitsSystemWindows() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
