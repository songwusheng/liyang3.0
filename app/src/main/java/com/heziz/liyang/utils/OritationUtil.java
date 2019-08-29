package com.heziz.liyang.utils;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by sws on 2018/5/22.
 * from:
 * describe:
 */

public class OritationUtil {
    public static boolean getOritation(Context context){
        Configuration mConfiguration = context.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向
        if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
           return true;
        } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
            //竖屏
           return false;
        }
        return true;
    }
}
