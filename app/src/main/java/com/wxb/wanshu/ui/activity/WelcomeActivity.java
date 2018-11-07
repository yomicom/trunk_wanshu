package com.wxb.wanshu.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.ui.presenter.PermissionUtils;

public class WelcomeActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                    10);
        } else {

        }
//        if(PermissionUtils.CheckPermission(PermissionUtils.READ_EXTERNAL_STORAGE,mContext)&&PermissionUtils.CheckPermission(PermissionUtils.WRITE_EXTERNAL_STORAGE,mContext)){
//        }else{
//            PermissionUtils.verifyStoragePermissions(mContext);
//        }
    }
}
