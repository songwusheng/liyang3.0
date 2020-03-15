package com.heziz.liyang.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.heziz.liyang.bean.JPushCommBean;
import com.heziz.liyang.bean.UserInfor;
import com.pgyersdk.crash.PgyCrashManager;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by sws on 2018/home_bg_icon3/28.
 * from:
 * describe:
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    private UserInfor userInfor;
    private static Context mContext;
    private String otgString;
    private JPushCommBean jPushCommBean;

    public JPushCommBean getjPushCommBean() {
        return jPushCommBean;
    }

    public void setjPushCommBean(JPushCommBean jPushCommBean) {
        this.jPushCommBean = jPushCommBean;
    }
    public String getOtgString() {
        return otgString;
    }

    public void setOtgString(String otgString) {
        this.otgString = otgString;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        mContext=this;
//        new PgyUpdateManager.Builder()
//                .register();
        PgyCrashManager.register();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        SDKInitializer.initialize(this);
        loadLibrary();
        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {
                Log.e("apptbs", " onViewInitFinished is " + b);
            }
        };

        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                Log.d("apptbs", "onDownloadFinish");
            }

            @Override
            public void onInstallFinish(int i) {
                Log.d("apptbs", "onInstallFinish");
            }

            @Override
            public void onDownloadProgress(int i) {
                Log.d("apptbs", "onDownloadProgress:" + i);
            }
        });

        //初始化X5内核
        QbSdk.initX5Environment(getApplicationContext(), cb);

    }
    private void loadLibrary(){
        System.loadLibrary("gnustl_shared");
        System.loadLibrary("dsl");
        System.loadLibrary("dslalien");
        System.loadLibrary("DPRTSPSDK");
        System.loadLibrary("PlatformRestSDK");
        System.loadLibrary("PlatformSDK");
        System.loadLibrary("netsdk");
        System.loadLibrary("CommonSDK");
    }
    public static MyApplication getInstance(){
        return instance;
    }

    public UserInfor getUserInfor(){
        return userInfor;
    }

    public void setUserInfor(UserInfor userInfor){
        if(userInfor.getPosition()!=null){

        }else{
            userInfor.setPosition("1");
        }
        this.userInfor=userInfor;
    }

    @Override
    public String getPackageName() {
        return super.getPackageName();
    }
    public static Context getAppContext() {
        return mContext;
    }

    public String getVersion(){
        PackageInfo pkg;
        String versionName="";
        try {
            pkg = getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
            String appName = pkg.applicationInfo.loadLabel(getPackageManager()).toString();
            versionName = pkg.versionName;
            System.out.println("appName:" + appName);
            System.out.println("versionName:" + versionName);

        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  versionName;
    }
}
