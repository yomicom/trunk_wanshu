package com.wxb.wanshu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.component.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiming on 2017/12/29.
 */

public class WebViewActivity extends BaseActivity {
    public static String INTENT_WEBVIEW_URL = "url";
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    public static void startActivity(Context context, String title, String url) {
        context.startActivity(new Intent(context, WebViewActivity.class).putExtra(INTENT_WEBVIEW_URL, url).putExtra("title", title));
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
        mCommonToolbar.setTitle(getIntent().getStringExtra("title"));
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        String url = getIntent().getStringExtra(INTENT_WEBVIEW_URL);
        setWebView(url);
        showDialog();
    }

    private void setWebView(String url) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webView.loadUrl(url);


        // 设置可以支持缩放
        webSettings.setSupportZoom(true);
        // 设置出现缩放工具
        webSettings.setBuiltInZoomControls(true);
        //扩大比例的缩放
        webSettings.setUseWideViewPort(true);
        //自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //页面下载完毕,却不代表页面渲染完毕显示出来
                //WebChromeClient中progress==100时也是一样
                if (webView.getContentHeight() != 0) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                        }
                    }, 500);

                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if (newProgress > 90) {
                    hideDialog();
                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        webView.reload();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                webView.destroy();
                webView.setVisibility(View.GONE);
            }
        }, ZOOM_CONTROLS_TIMEOUT);
    }

    private static final long ZOOM_CONTROLS_TIMEOUT =
            ViewConfiguration.getZoomControlsTimeout();

    @Override
    public void configViews() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();// 返回前一个页面
                return true;
            } else {
                finish();
            }
        }
        return false;
    }
}
