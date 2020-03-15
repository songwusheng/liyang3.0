package com.heziz.liyang.receiver;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.bean.JPushCommBean;
import com.heziz.liyang.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * Created by sws on 2019-11-15.
 * from:
 * describe:
 */

public class MyJPushReceiver extends JPushMessageReceiver {

    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        //super.onAliasOperatorResult(context, jPushMessage);
//        ToastUtil.showToast(jPushMessage.getAlias());
    }

    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        //super.onMessage(context, customMessage);
        //ToastUtil.showToast(customMessage.title);
    }

    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage notificationMessage) {
        //super.onNotifyMessageArrived(context, notificationMessage);
        //ToastUtil.showToast(notificationMessage.notificationTitle + "==" + notificationMessage.notificationExtras);
        Log.w("main", notificationMessage.notificationExtras);

        String extraStr = notificationMessage.notificationExtras;
        Gson gson=new Gson();
        JPushCommBean jPushCommBean=gson.fromJson(extraStr,JPushCommBean.class);
        MyApplication.getInstance().setjPushCommBean(jPushCommBean);
        Intent intent = new Intent();
        intent.setAction("jpush_refresh");
        intent.putExtra("jPushCommBean", jPushCommBean);
        context.sendBroadcast(intent);

        try {
            JSONObject object = new JSONObject(extraStr);
            int num = object.getInt("badge");
            Bundle extra = new Bundle();
            extra.putString("package", "com.heziz.liyang");
            extra.putString("class", "com.heziz.liyang.ui.SplashActivity");
            extra.putInt("badgenumber", num);
            context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, extra);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
