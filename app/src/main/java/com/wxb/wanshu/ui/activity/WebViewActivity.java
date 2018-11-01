package com.wxb.wanshu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.component.AppComponent;

import butterknife.BindView;

/**
 * Created by qiming on 2017/12/29.
 */

public class WebViewActivity extends BaseActivity {
    public static String INTENT_WEBVIEW_URL = "url";

    public static void startActivity(Context context, String url) {
        context.startActivity(new Intent(context, WebViewActivity.class).putExtra(INTENT_WEBVIEW_URL, url));
    }

    @BindView(R.id.webView)
    WebView webView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("年度VIP会员");
    }

    @Override
    public void initDatas() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);


        if (getIntent().hasExtra(INTENT_WEBVIEW_URL)) {
            String url = getIntent().getStringExtra(INTENT_WEBVIEW_URL);
            webView.loadUrl(url);
        }
    }

    @Override
    public void configViews() {

    }
}
