package com.wxb.wanshu.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import com.wxb.wanshu.MyApplication;

import java.util.UUID;

public class Utils {


    public static <T> T checkNotNull(T t, String message) {
        if (t == null) {
            throw new NullPointerException(message);
        }
        return t;
    }


    public static String getUUID() {
        try {
            final TelephonyManager tm = (TelephonyManager) MyApplication.getsInstance().getSystemService(Context.TELEPHONY_SERVICE);
            final String tmDevice, tmSerial, tmPhone, androidId;
            if (ActivityCompat.checkSelfPermission(MyApplication.getsInstance(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return getCustomUUID();
            }
            tmDevice = "" + tm.getDeviceId();
            tmSerial = "" + tm.getSimSerialNumber();
            androidId = "" + android.provider.Settings.Secure.getString(MyApplication.getsInstance().getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
            String uniqueId = deviceUuid.toString();
            return "ARD_" + uniqueId;
        } catch (Exception e) {
            e.printStackTrace();
            return getCustomUUID();
        }
    }

    private static String getCustomUUID() {
        if (SharedPreferencesUtil.getInstance().contain(SharedPreferencesUtil.GET_CUSTOME_UUID)) {
            return SharedPreferencesUtil.getInstance().getString(SharedPreferencesUtil.GET_CUSTOME_UUID);
        } else {
            String defautlUUID = "ARD_00000000-0d89-f9dd-ffff-" + String.valueOf(System.currentTimeMillis() / 10);
            SharedPreferencesUtil.getInstance().putString(SharedPreferencesUtil.GET_CUSTOME_UUID, defautlUUID);
            return defautlUUID;
        }
    }

    /**
     * 获取APP版本
     *
     * @return
     */
    public static String getVersionName() {
        try {
            return MyApplication.getsInstance().getPackageManager().getPackageInfo(MyApplication.getsInstance().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取操作系统信息
     *
     * @return
     */
    public static String getOsMessage() {
        return "model:" + Build.MODEL + "," + "SDK:" + Build.VERSION.SDK_INT;
    }

//    "[Android当前应用软件版本:" + getPackageManager().getPackageInfo(getPackageName(), 0).versionName + "]"
//            + "[手机型号：" + Build.MODEL + "]"
//            + "[当前系统版本号：" + Build.VERSION.SDK_INT + "]"
}
