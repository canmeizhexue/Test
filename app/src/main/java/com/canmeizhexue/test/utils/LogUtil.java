package com.canmeizhexue.test.utils;

import android.util.Log;

/**
 * Created by silence on 2015-11-7.
 */
public class LogUtil {
    private LogUtil(){}
    public static final void d(String tag, String msg){
        Log.d(tag,msg);
    }
    public static final void e(String tag, String msg){
        Log.e(tag, msg);
    }
    public static final void v(String tag, String msg){
        Log.v(tag, msg);
    }
    public static final void w(String tag, String msg){
        Log.w(tag, msg);
    }
    public static final void i(String tag, String msg){
        Log.i(tag,msg);

    }
}
