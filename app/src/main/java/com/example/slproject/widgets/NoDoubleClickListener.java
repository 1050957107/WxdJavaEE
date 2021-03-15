package com.example.slproject.widgets;

import android.util.SparseArray;
import android.view.View;

public abstract class NoDoubleClickListener implements View.OnClickListener {

    public static final int MIN_CLICK_DELAY_TIME = 400;
    // 记录所有绑定该监听器View的最后一次点击时间
    private SparseArray<Long> lastClickViewArray = new SparseArray<>();
    @Override
    public void onClick(View v) {
        long currentTime = System.currentTimeMillis();
        long lastClickTime = lastClickViewArray.get(v.getId(), -1L);// 获取该view最后一次的点击时间,默认为-1
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {// 两次点击的时间间隔大于最小限制时间，则触发点击事件
            lastClickViewArray.put(v.getId(), currentTime);
            onNoDoubleClick(v);
        }
    }

    protected abstract void onNoDoubleClick(View v);
}
