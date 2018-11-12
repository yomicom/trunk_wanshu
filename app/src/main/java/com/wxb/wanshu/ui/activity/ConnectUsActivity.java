package com.wxb.wanshu.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_call, R.id.tv_qq, R.id.tv_cooperate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_call:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                            10);
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + KEFU_CALL));
                    startActivity(intent);
                }
                break;
            case R.id.tv_qq:
                Utils.joinQQ(this, KEFU_QQ);
                break;
            case R.id.tv_cooperate:
                Utils.joinQQ(this, HEZUO_QQ);
                break;
        }
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 10) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 授权成功，继续打电话
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + KEFU_CALL));
                startActivity(intent);
            } else {
                // 授权失败！
            }
        }
    }
}
