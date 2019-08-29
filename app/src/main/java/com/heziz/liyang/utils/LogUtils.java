package com.heziz.liyang.utils;

import android.util.Log;

/**
 * Created by sws on 2019-04-30.
 * from:
 * describe:
 */

public class LogUtils {
    private static boolean flag=true;
    public static void show(String s){
        if(flag){
            Log.w("main",s);
        }

    }
}
