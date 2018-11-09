package com.wxb.wanshu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.utils.Utils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConnectUsActivity extends BaseActivity {


    public String KEFU_QQ = "3374397930";
    public String KEFU_CALL = "400-9981236";
    public String HEZUO_QQ = "2499931904";
    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ConnectUsActivity.class));
    }

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_call, R.id.tv_qq, R.id.tv_cooperate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_call:
                break;
            case R.id.tv_qq:
                Utils.joinQQ(this,KEFU_QQ);
                break;
            case R.id.tv_cooperate:
                break;
        }
    }
}
