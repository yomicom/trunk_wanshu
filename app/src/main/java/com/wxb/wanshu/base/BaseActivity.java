package com.wxb.wanshu.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ViewUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrInterface;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.R;
import com.wxb.wanshu.MyApplication;
import com.wxb.wanshu.ui.activity.ReadActivity;
import com.wxb.wanshu.utils.SharedPreferencesUtil;
import com.wxb.wanshu.utils.StatusBarUtils;
import com.wxb.wanshu.utils.Utils;
import com.wxb.wanshu.utils.ViewToolUtils;
import com.wxb.wanshu.view.loadding.CustomDialog;

import butterknife.ButterKnife;

/**
 * Created by qiming on 2017/11/20.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public Toolbar mCommonToolbar;

    protected Context mContext;
    protected int statusBarColor = 0;
    protected View statusBarView = null;
    private boolean mNowMode;
    private CustomDialog dialog;//进度条
    protected SlidrInterface slidrInterface;

    boolean canSlide = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mContext = this;

//        if (statusBarColor == 0) {
//            statusBarView = StatusBarCompat.compat(this, ContextCompat.getColor(this, R.color.gobal_color));
//        } else if (statusBarColor != -1) {
//            statusBarView = StatusBarCompat.compat(this, statusBarColor);
//        }
//        transparent19and20();

        if (mContext instanceof ReadActivity) {
        }else {
            setStatusBarView();
//        StatusBarUtils.setStatusBarLightMode((Activity) mContext, R.color.white);
        }

        ButterKnife.bind(this);
        setupActivityComponent(MyApplication.getsInstance().getAppComponent());
        mCommonToolbar = findViewById(R.id.common_toolbar);
        if (mCommonToolbar != null) {
            //设置默认Toolbar样式
            mCommonToolbar.setTitleTextAppearance(mContext, R.style.ToolbarTitle);
            mCommonToolbar.setSubtitleTextAppearance(mContext, R.style.ToolbarSubTitle);
            mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
            initToolBar();
            setSupportActionBar(mCommonToolbar);
        }
        initDatas();
        configViews();
        mNowMode = SharedPreferencesUtil.getInstance().getBoolean(Constant.ISNIGHT);
        initSlidable();
    }

    private void setStatusBarView() {
        //标题栏与状态栏颜色一致 xml中配置
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup systemContent = findViewById(android.R.id.content);
            View statusBarView = new View(this);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewToolUtils.getStatusBarHeight());
            statusBarView.setBackgroundColor(getResources().getColor(R.color.white));
            systemContent.getChildAt(0).setFitsSystemWindows(true);
            systemContent.addView(statusBarView, 0, lp);
        }

        //设置状态栏文字的颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    protected void setCanSlide(boolean canSlide) {
        this.canSlide = canSlide;
    }

    protected void transparent19and20() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void toolbarSetElevation(float elevation) {//去掉阴影
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            mCommonToolbar.setElevation(elevation);
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (SharedPreferencesUtil.getInstance().getBoolean(Constant.ISNIGHT, false) != mNowMode) {
//            if (SharedPreferencesUtil.getInstance().getBoolean(Constant.ISNIGHT, false)) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            } else {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            }
//            recreate();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissDialog();
    }

//    public static void startActivity(Context context, String intentStr, Bundle bundle){
//
//    }

    public abstract int getLayoutId();

    protected abstract void setupActivityComponent(AppComponent appComponent);

    public abstract void initToolBar();

    public abstract void initDatas();

    /**
     * 对各种控件进行设置、适配、填充数据
     */
    public abstract void configViews();

    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    // dialog
    public CustomDialog getDialog() {
        if (dialog == null) {
            dialog = CustomDialog.instance(this);
            dialog.setCancelable(true);
        }
        return dialog;
    }

    public void hideDialog() {
        if (dialog != null)
            dialog.hide();
    }

    public void showDialog() {
        getDialog().show();
    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 隐藏状态栏
     */
    protected void hideStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if (statusBarView != null) {
            statusBarView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    /**
     * 显示状态栏
     */
    protected void showStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if (statusBarView != null) {
            statusBarView.setBackgroundColor(statusBarColor);
        }
    }


    /**
     * 初始化滑动返回
     */
    protected void initSlidable() {
        if (canSlide) {
            SlidrConfig config = new SlidrConfig.Builder()
                    .build();
            slidrInterface = Slidr.attach(this, config);
            canSlide = true;
        }
    }
}
