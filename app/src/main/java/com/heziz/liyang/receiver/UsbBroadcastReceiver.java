package com.heziz.liyang.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.storage.StorageManager;
import android.util.Log;

import com.heziz.liyang.app.MyApplication;
import com.heziz.liyang.utils.Constant;
import com.heziz.liyang.utils.TS;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;

public class UsbBroadcastReceiver extends BroadcastReceiver {

   // private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    private static final String ACTION_USB_PERMISSION = "android.intent.action.MY_BROADCAST";

        private final int mVendorID = 0x17EF ;
    private final int mProductID = 0x3875;
    //黄总银色小U盘
//    private final int mVendorID = 0x58F;
//    private final int mProductID = 0x6387;
    UsbManager usbManager;
    UsbDevice device;
    HashMap<String, UsbDevice> deviceList;
    String root_Path = null;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    //获取USB监听权限 以及USB根目录
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        getUsbpath();

        //获取U盘权限
        if (ACTION_USB_PERMISSION.equals(action)) {
            synchronized (this) {
                //进入U盘模式
                if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                } else {
                }
            }
        }
        //U盘退出
        if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
            device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);

            if (device != null) {
                TS.Show(MyApplication.getAppContext(), "U盘弹出");
            }
        }


    }

    private void getUsbpath() {
        usbManager = (UsbManager) MyApplication.getAppContext().getSystemService(Context.USB_SERVICE);
        deviceList = usbManager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        while (deviceIterator.hasNext()) {
            //获取专用USB
            UsbDevice device = deviceIterator.next();

            if (device.getVendorId() == mVendorID && device.getProductId() == mProductID) {
                int count = 0;
                while (count < 1000) {
                    count++;
                    root_Path = new GetUSBPath().getPath();
                    if (root_Path != null && root_Path.length() >= 0) {
                        TS.Show(MyApplication.getAppContext(), "口令卡识别成功");
                        savePath(root_Path);
                        break;
                    }
                }
            } else {
                // TS.Show(App.mContext,);
                TS.Show(MyApplication.getAppContext(), device.getVendorId() + ";" + device.getProductId());
            }
        }

    }

    //保存USB路径
    private void savePath(String path) {
        if (path != null && path.length() >= 0) {
            sharedPreferences = MyApplication.getAppContext().getSharedPreferences(Constant.SP_NAME, Activity.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putString(Constant.SP_USB_PATH, path);
            editor.commit();

        } else {
            TS.Show(MyApplication.getAppContext(), "路径获取失败");
            return;
        }
    }
    //读取U盘内容类
    class GetUSBPath {
        String usbPath;
        IntentFilter intentFilter;

        public GetUSBPath() {
            //获取权限
            intentFilter = new IntentFilter();
            intentFilter.addAction(Intent.ACTION_MEDIA_SHARED);// 如果SDCard未安装,并通过USB大容量存储共享返回
            intentFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);// 表明sd对象是存在并具有读/写权限
            intentFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);// SDCard已卸掉,如果SDCard是存在但没有被安装
            intentFilter.addAction(Intent.ACTION_MEDIA_CHECKING); // 表明对象正在磁盘检查
            intentFilter.addAction(Intent.ACTION_MEDIA_EJECT); // 物理的拔出 SDCARD
            intentFilter.addAction(Intent.ACTION_MEDIA_REMOVED); // 完全拔出
            intentFilter.addDataScheme("file"); // 必须要有此行，否则无法收到广播
        }

        public String getPath() {
            MyApplication.getAppContext().registerReceiver(receiver, intentFilter);
            StorageManager sm = (StorageManager) MyApplication.getAppContext().getSystemService(Context.STORAGE_SERVICE);
            String[] volumePaths = new String[0];
            try {
                Method method = sm.getClass().getMethod("getVolumePaths");
                if (null != method) {
                    method.setAccessible(true);
                    volumePaths = (String[]) method.invoke(sm);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if ((volumePaths != null) && (volumePaths.length > 0)) {
                for (String sdcardPath : volumePaths) {
                    //按标记获取路径
                    readConfig(sdcardPath);
                }
            }
            TS.Show(MyApplication.getAppContext(), usbPath);
            return usbPath;


        }

        private BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("Android.intent.action.MEDIA_EJECT")
                        || intent.getAction().equals("android.intent.action.MEDIA_UNMOUNTED")) {

                } else if (intent.getAction().equals(Intent.ACTION_MEDIA_MOUNTED)) {
                    String path = intent.getDataString();
                    sharedPreferences = MyApplication.getAppContext().getSharedPreferences(Constant.SP_NAME, Activity.MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.putString("main", path);
                    editor.commit();
                    String pathString = path.split("file://")[1];
                    if (pathString != null && !pathString.equals("")) {
                        usbPath = pathString;
                    }
                }
            }
        };

        private void readConfig(String devicePath) {
            if (devicePath != null && !devicePath.equals("")) {
                String configPath = devicePath + "/info.config";
                File file = new File(configPath);
                if (file.exists()) {
                    try {
                        String configs = FileUtils.readFileToString(file, "UTF-8");
                        if (configs.equals("config")) {
                            usbPath = devicePath;
                            Log.e("TAG", devicePath);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
    }
}


