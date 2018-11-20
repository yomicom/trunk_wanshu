package com.wxb.wanshu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.component.AppComponent;

import butterknife.BindView;

import static com.wxb.wanshu.ui.activity.WanshuLoginActivity.INTENT_COME_ID;

public class AboutUsActivity extends BaseActivity {

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, AboutUsActivity.class));
    }
    @BindView(R.id.wxb_version)
    TextView tvVersion;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("关于我们");
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {
        try {
            tvVersion.setText("v" + getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
