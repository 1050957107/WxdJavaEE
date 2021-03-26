package com.w.xd.mvp.utils;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.w.xd.mvp.R;


public class IntentUtils {

    public void intent(Activity activity, Class clazz, Bundle bundle) {
        activity.overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
        Intent intent = new Intent(activity, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
    }

    public void intent(Activity activity, Class clazz) {
        activity.overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
    }
}
