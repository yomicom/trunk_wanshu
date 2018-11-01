package com.wxb.wanshu;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.igexin.sdk.PushManager;
import com.umeng.analytics.MobclickAgent;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.BookShelfStatus;
import com.wxb.wanshu.ui.activity.BookshelfActivity;
import com.wxb.wanshu.ui.activity.ClassifyActivity;
import com.wxb.wanshu.ui.activity.HomeActivity;
import com.wxb.wanshu.ui.activity.HomeBookActivity;
import com.wxb.wanshu.ui.activity.MeActivity;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

public class MainActivity extends ActivityGroup implements View.OnClickListener{

    TabHost tabHost;
    RelativeLayout ral_tab;
    ImageView tabData, tabFunc, tabArticle, tabMe;
    TextView txtData, txtFunc, txtArticle, txtMe;
    String TAB_DATA = "data", TAB_FUNC = "func", TAB_ARC = "article", TAB_ME = "me";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        setTabHost();

        EventBus.getDefault().register(this);
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoIntentService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);
        String clientid = PushManager.getInstance().getClientid(this);
        if("".equals(clientid)){}
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
                .setContent(new Intent(this, HomeActivity.class)));
        tabHost.addTab(tabHost.newTabSpec(TAB_FUNC).setIndicator(TAB_FUNC)
                .setContent(new Intent(this, BookshelfActivity.class)));
        tabHost.addTab(tabHost.newTabSpec(TAB_ARC).setIndicator(TAB_ARC)
                .setContent(new Intent(this, ClassifyActivity.class)));
        tabHost.addTab(tabHost.newTabSpec(TAB_ME).setIndicator(TAB_ME)
                .setContent(new Intent(this, MeActivity.class)));

        tabData.setImageResource(R.mipmap.ic_data_select);
        txtData.setTextColor(getResources().getColor(R.color.gobal_color));
        tabHost.setCurrentTabByTag(TAB_DATA);
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
        }else if (id == R.id.home_tab_art) {
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
    public void onEventMainThread(BookShelfStatus data){
        if(data.editStatus){
            ral_tab.setVisibility(View.VISIBLE);
        }else {
            ral_tab.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
