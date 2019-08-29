package com.heziz.liyang.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by sws on 2019-06-18.
 * from:
 * describe:
 */

public class ScreenUtils {
    public static int getScreenWidth(Activity mContext){
        DisplayMetrics metric = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay()
                .getMetrics(metric);
        int mScreenWidth = metric.widthPixels; // 屏幕宽度（像素）
        int mScreenHeight = metric.heightPixels; // 屏幕高度（像素）
//        mScreenHeight = mScreenWidth * 3 / 4;
        return mScreenWidth * 3 / 4;
    }

    public static void hiddenKeyBord(Activity mContext) {

        Window window;
        window = mContext.getWindow();
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(window.getDecorView().getWindowToken(), 0);
    }

    public static int height;
    public static int width;
    private static ScreenUtils instance;
    private Context context;

    private ScreenUtils(Context context) {
        this.context = context;
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;
    }

    public static ScreenUtils getInstance(Context context) {
        if (instance == null) {
            instance = new ScreenUtils(context);
        }
        return instance;
    }



    /**
     * 得到手机屏幕的宽度, pix单位
     */

    /**
     * 获得通知栏的高度
     * @return
     */
    public static int getStatusHeight(Context context){
        int resid = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resid > 0){
            return context.getResources().getDimensionPixelSize(resid);
        }
        return -1;
    }

    /**
     * 得到手机屏幕的宽度, pix单位
     */
    public int getScreenWidth1() {
        return width;
    }

    //获取屏幕的宽度
    public static int getScreenWidth1(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }
    //获取屏幕的高度
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }
    //px转dp
    public static int dp2px(Context context,float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
    }

}
