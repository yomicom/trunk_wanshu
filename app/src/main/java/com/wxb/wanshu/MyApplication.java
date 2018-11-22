package com.wxb.wanshu;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;


import com.umeng.commonsdk.UMConfigure;
import com.wxb.wanshu.component.AppComponent;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.wxb.wanshu.base.Constant;
import com.wxb.wanshu.component.DaggerAppComponent;
import com.wxb.wanshu.module.AppModule;
import com.wxb.wanshu.module.ApiModule;
import com.wxb.wanshu.utils.AppUtils;
import com.wxb.wanshu.utils.LogUtils;
import com.wxb.wanshu.utils.SharedPreferencesUtil;

/**
 * Created by qiming on 2017/11/23.
 */

public class MyApplication extends Application {


    private static MyApplication sInstance;
    private AppComponent appComponent;

//    private RefWatcher refWatcher;
//
//    public static RefWatcher getRefWatcher(Context context) {
//        MyApplication application = (MyApplication) context.getApplicationContext();
//        return application.refWatcher;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
//        refWatcher = LeakCanary.install(this);
        sInstance = this;
        initCompoent();
        AppUtils.init(this);
//        CrashHandler.getInstance().init(this);
        initPrefs();
//        initNightMode();

//        PlatformConfig.setWeixin(Constant.WEXIN_APPID,Constant.WEXIN_APPSECRECT);
//        PlatformConfig.setQQZone(Constant.QQ_APPID,Constant.QQ_APPSECRECT);
//        UMShareAPI.get(this);

        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);
        MobclickAgent.setCatchUncaughtExceptions(true);
    }

    public static MyApplication getsInstance() {
        return sInstance;
    }

    private void initCompoent() {
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * 初始化SharedPreference
     */
    protected void initPrefs() {
        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }

    protected void initNightMode() {
        boolean isNight = SharedPreferencesUtil.getInstance().getBoolean(Constant.ISNIGHT, false);
        LogUtils.d("isNight=" + isNight);
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

}
