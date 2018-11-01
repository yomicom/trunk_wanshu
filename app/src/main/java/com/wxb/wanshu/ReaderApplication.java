package com.wxb.wanshu;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;


import com.wxb.wanshu.component.AppComponent;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.wxb.wanshu.base.Constant;
import com.wxb.wanshu.component.DaggerAppComponent;
import com.wxb.wanshu.module.AppModule;
import com.wxb.wanshu.module.BookApiModule;
import com.wxb.wanshu.utils.AppUtils;
import com.wxb.wanshu.utils.LogUtils;
import com.wxb.wanshu.utils.SharedPreferencesUtil;

import static com.wxb.wanshu.base.Constant.WEXIN_APPSECRECT;

/**
 * Created by qiming on 2017/11/23.
 */

public class ReaderApplication extends Application {


    private static ReaderApplication sInstance;
    private AppComponent appComponent;

//    private RefWatcher refWatcher;
//
//    public static RefWatcher getRefWatcher(Context context) {
//        ReaderApplication application = (ReaderApplication) context.getApplicationContext();
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
        initNightMode();
        //initHciCloud();


        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);

        PlatformConfig.setWeixin(Constant.WEXIN_APPID,Constant.WEXIN_APPSECRECT);
        PlatformConfig.setQQZone(Constant.QQ_APPID,Constant.QQ_APPSECRECT);

        UMShareAPI.get(this);

        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.setCatchUncaughtExceptions(true);
    }

    public static ReaderApplication getsInstance() {
        return sInstance;
    }

    private void initCompoent() {
        appComponent = DaggerAppComponent.builder()
                .bookApiModule(new BookApiModule())
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
