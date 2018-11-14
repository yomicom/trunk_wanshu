package com.wxb.wanshu;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.igexin.sdk.PushManager;
import com.umeng.analytics.MobclickAgent;
import com.wxb.wanshu.api.Api;
import com.wxb.wanshu.bean.BookShelfStatus;
import com.wxb.wanshu.bean.ClientData;
import com.wxb.wanshu.ui.activity.BookshelfActivity;
import com.wxb.wanshu.ui.activity.ClassifyActivity;
import com.wxb.wanshu.ui.activity.HomeBookActivity;
import com.wxb.wanshu.ui.activity.MeActivity;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends ActivityGroup implements View.OnClickListener {

    TabHost tabHost;
    RelativeLayout ral_tab;
    ImageView tabData, tabFunc, tabArticle, tabMe;
    TextView txtData, txtFunc, txtArticle, txtMe;
    String TAB_DATA = "data", TAB_FUNC = "func", TAB_ARC = "article", TAB_ME = "me";

    private Api api;
    protected CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        setTabHost();
        setBannerStatus();

        EventBus.getDefault().register(this);
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoIntentService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);
        String clientid = PushManager.getInstance().getClientid(this);
        if ("".equals(clientid)) {
        }

//        clientLaunch();
    }

    //全屏保留状态栏文字(页面上部有Banner图)
    private void setBannerStatus() {
        Window window = getWindow();
        //默认API 最低19
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup contentView = window.getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
            contentView.getChildAt(0).setFitsSystemWindows(false);
        }
    }

    /**
     * 上报设备数据
     */
    private void clientLaunch() {
        api = MyApplication.getsInstance().getAppComponent().getReaderApi();
        //MyApplication.getMyContext().getPackageManager().getPackageInfo(MyApplication.getMyContext().getPackageName()
        Subscription subscribe = api.clientLaunch("", 1, "", "android").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClientData>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ClientData data) {
                        int client_id = data.getData().getClient_id();
                    }
                });
        addSubscrebe(subscribe);
    }


    private void initView() {
        tabHost = (TabHost) findViewById(R.id.tabhost);
        ral_tab = (RelativeLayout) findViewById(R.id.ral_tab);

        tabData = (ImageView) findViewById(R.id.home_tab_dataImg);
        tabFunc = (ImageView) findViewById(R.id.home_tab_funcImg);
        tabArticle = (ImageView) findViewById(R.id.home_tab_artImg);
        tabMe = (ImageView) findViewById(R.id.home_tab_meImg);

        txtData = (TextView) findViewById(R.id.home_tab_dataText);
        txtFunc = (TextView) findViewById(R.id.home_tab_funcText);
        txtArticle = (TextView) findViewById(R.id.home_tab_artText);
        txtMe = (TextView) findViewById(R.id.home_tab_meText);

        findViewById(R.id.home_tab_data).setOnClickListener(this);
        findViewById(R.id.home_tab_func).setOnClickListener(this);
        findViewById(R.id.home_tab_art).setOnClickListener(this);
        findViewById(R.id.home_tab_me).setOnClickListener(this);
    }

    private void setTabHost() {
        tabHost.setup();
        tabHost.setup(this.getLocalActivityManager());

        tabHost.addTab(tabHost.newTabSpec(TAB_DATA).setIndicator(TAB_DATA)
                .setContent(new Intent(this, HomeBookActivity.class)));
        tabHost.addTab(tabHost.newTabSpec(TAB_FUNC).setIndicator(TAB_FUNC)
                .setContent(new Intent(this, BookshelfActivity.class)));
        tabHost.addTab(tabHost.newTabSpec(TAB_ARC).setIndicator(TAB_ARC)
                .setContent(new Intent(this, ClassifyActivity.class)));
        tabHost.addTab(tabHost.newTabSpec(TAB_ME).setIndicator(TAB_ME)
                .setContent(new Intent(this, MeActivity.class)));

        tabFunc.setImageResource(R.mipmap.ic_function_select);
        txtFunc.setTextColor(getResources().getColor(R.color.gobal_color));
        tabHost.setCurrentTabByTag(TAB_FUNC);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        setTab(id);
    }

    public void setTab(int id) {
        if (id == R.id.home_tab_data) {
            MobclickAgent.onEvent(this, "DataTab");
            resetState();
            tabData.setImageResource(R.mipmap.ic_data_select);
            txtData.setTextColor(getResources().getColor(R.color.gobal_color));
            tabHost.setCurrentTabByTag(TAB_DATA);
        } else if (id == R.id.home_tab_func) {
            MobclickAgent.onEvent(this, "FunctionTab");
            resetState();
            tabFunc.setImageResource(R.mipmap.ic_function_select);
            txtFunc.setTextColor(getResources().getColor(R.color.gobal_color));
            tabHost.setCurrentTabByTag(TAB_FUNC);
        } else if (id == R.id.home_tab_art) {
            MobclickAgent.onEvent(this, "ArticleTab");
            resetState();
            tabArticle.setImageResource(R.mipmap.ic_materical_select);
            txtArticle.setTextColor(getResources().getColor(R.color.gobal_color));
            tabHost.setCurrentTabByTag(TAB_ARC);
        } else if (id == R.id.home_tab_me) {
            MobclickAgent.onEvent(this, "AccountTab");
            resetState();
            tabMe.setImageResource(R.mipmap.ic_my_select);
            txtMe.setTextColor(getResources().getColor(R.color.gobal_color));
            tabHost.setCurrentTabByTag(TAB_ME);
        }
    }

    // 重置按钮状态
    private void resetState() {
        tabData.setImageResource(R.mipmap.ic_data);
        tabFunc.setImageResource(R.mipmap.ic_function);
        tabArticle.setImageResource(R.mipmap.ic_materical);
        tabMe.setImageResource(R.mipmap.ic_my);

        txtData.setTextColor(getResources().getColor(R.color.text_color_2));
        txtFunc.setTextColor(getResources().getColor(R.color.text_color_2));
        txtArticle.setTextColor(getResources().getColor(R.color.text_color_2));
        txtMe.setTextColor(getResources().getColor(R.color.text_color_2));
    }

    @Subscriber
    public void onEventMainThread(BookShelfStatus data) {
        if (data.editStatus == 0) {//去书城
            setTab(R.id.home_tab_data);
        } else if (data.editStatus == 1) {//显示Tab
            ral_tab.setVisibility(View.VISIBLE);
        } else {//隐藏Tab
            ral_tab.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }


    private long exitTime = 0;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                // 退出代码
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
