package com.wxb.wanshu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.ui.activity.ListActivity.OrderListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.switch_setting_push)
    SwitchCompat switchSettingPush;
    @BindView(R.id.tv_clean)
    TextView tvClean;
    @BindView(R.id.tv_advice)
    TextView tvAdvice;
    @BindView(R.id.tv_about_us)
    TextView tvAboutUs;
    @BindView(R.id.out_login)
    Button outLogin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("设置");
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, SettingActivity.class));
    }

    @OnClick({R.id.switch_setting_push, R.id.tv_clean, R.id.tv_advice, R.id.tv_about_us, R.id.out_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.switch_setting_push:
                break;
            case R.id.tv_clean:
                break;
            case R.id.tv_advice:
                CommentActivity.startActivity(mContext);
                break;
            case R.id.tv_about_us:
                AboutUsActivity.startActivity(mContext);
                break;
            case R.id.out_login:
                break;
        }
    }
}
