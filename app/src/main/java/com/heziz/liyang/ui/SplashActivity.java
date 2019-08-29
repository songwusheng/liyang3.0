package com.heziz.liyang.ui;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.heziz.liyang.R;
import com.heziz.liyang.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    private TextView version;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_splash);

        initViews();
    }


    private void initViews() {
        version = (TextView) findViewById(R.id.mversion);
        version.setText("当前版本为:V"+getVersion());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMyActivity(LoginActivity.class);
                finish();
//                startMyActivity(WeatherActivity.class);
//                finish();

            }
        },2000);
    }

    private String getVersion(){
        PackageInfo pkg;
        String versionName="";
        try {
            pkg = getPackageManager().getPackageInfo(getApplication().getPackageName(), 0);
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
