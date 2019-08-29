package com.heziz.liyang.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.heziz.liyang.app.MyApplication;

public class USBReceiver extends BroadcastReceiver{
    public static final String ACTION_VOLUME_STATE_CHANGED =
            "android.os.storage.action.VOLUME_STATE_CHANGED";
    public static final String EXTRA_VOLUME_ID =
            "android.os.storage.extra.VOLUME_ID";
    public static final String EXTRA_VOLUME_STATE =
            "android.os.storage.extra.VOLUME_STATE";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        if ("android.intent.action.MEDIA_UNMOUNTED".equals(intent.getAction())) {
            Toast.makeText(context, "拔出", Toast.LENGTH_LONG).show();
            MyApplication.getInstance().setOtgString("");
        }
        if ("android.intent.action.MEDIA_MOUNTED".equals(intent.getAction())) {
                String s=intent.getData().getPath();
                MyApplication.getInstance().setOtgString(s);
                Toast.makeText(context, "插入"+s, Toast.LENGTH_LONG).show();

        }
        if("android.os.storage.action.VOLUME_STATE_CHANGED".equals(intent.getAction())){
            Toast.makeText(context, "1111111", Toast.LENGTH_LONG).show();
        }
    }
}