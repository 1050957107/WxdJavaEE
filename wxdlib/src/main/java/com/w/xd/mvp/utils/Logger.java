package com.w.xd.mvp.utils;

import android.text.TextUtils;
import android.util.Log;


import com.w.xd.mvp.BuildConfig;
import com.w.xd.mvp.manager.MvpManager;

import java.util.Collection;
import java.util.Locale;


/**
 * adb shell setprop log.tag.{@link #PRIVATE_TAG} LOG_LEVEL
 * eg: adb shell setprop log.tag.Test d
 * Created by Cherry on 2018/06/07.
 * adb shell log.tag.Test V
 * <p>
 * <p>
 * eg: Logger.v("%s %s %d + %d  = %d,","TAG","Hello: ",5,6,11)
 * will print "TAG Hello: 5 + 6 = 11"
 */

/**
 * 1. 能够控制日志的输出与否
 * 2. 输出这行日志是从哪个类文件的什么方法里面的某一行输出的。
 * 3.不用手动打开日志开关。更具打包的类型自动设置是否输出日志。
 * 4.能够对上线的apk 把log 日志开关再次打开
 */

public class Logger {
    /**
     * 控制是否 需要开启 日志打印输出的开关
     */

    private static boolean isOpen = true;

    public static void e(String msg) {
        if (isOpen) {
            Log.e("TAG", "<--------打印的日志信息-------->" + msg);
        }
    }

    public static void d(String msg) {
        if (isOpen) {
            Log.d("TAG", "<--------打印的日志信息-------->" + msg);
        }
    }
    public static void i(String msg) {
        if (isOpen) {
            Log.i("TAG", "<--------打印的日志信息-------->" + msg);
        }
    }
}
