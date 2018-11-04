package com.wxb.wanshu.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.component.AppComponent;

public class ConnectUsActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_connect_us;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initToolBar() {

        mCommonToolbar.setTitle("关于");
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {

    }
}
