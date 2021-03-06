package com.wxb.wanshu.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.bean.AppVersion;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.UserInfo;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerAccountComponent;
import com.wxb.wanshu.manager.CacheManager;
import com.wxb.wanshu.ui.activity.ListActivity.ReadHistoryActivity;
import com.wxb.wanshu.ui.contract.MeContract;
import com.wxb.wanshu.ui.presenter.MePresenter;
import com.wxb.wanshu.utils.MarketUtils;
import com.wxb.wanshu.utils.ToastUtils;
import com.wxb.wanshu.utils.Utils;
import com.wxb.wanshu.view.dialog.ConfirmDialog;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MeActivity extends BaseActivity implements MeContract.View {

    @Inject
    MePresenter mPresenter;
    @BindView(R.id.clean)
    TextView clean;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.update)
    TextView update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setCanSlide(false);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.me_activity;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerAccountComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initDatas() {
        mPresenter.attachView(this);
//        mPresenter.getUserData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getLastVersion(false);
        clean.setText(CacheManager.getInstance().getCacheSize());
    }

    @Override
    public void configViews() {
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
        }
    }

    @Override
    public void showError() {
        hideDialog();
    }

    @Override
    public void complete() {
        hideDialog();
    }

    @Override
    public void showUserData(UserInfo data) {
    }

    @Override
    public void showLastVersion(AppVersion data, boolean isClick) {
        hideDialog();
        AppVersion.DataBean bean = data.data;
        int old_version = Utils.getVersionNumber();
        int new_version = bean.version_code;

        if (old_version == new_version) {
            if (isClick) {
                ToastUtils.showToast("已是最新版本");
            }
            update.setText("");
        } else {
            if (old_version < new_version) {//有新版本
                update.setText("点击更新V" + bean.version + "版本");
                if (isClick) {//弹框去更新
                    ConfirmDialog.showNotice(mContext, "提醒", bean.msg, () -> {
                        Uri content_url = Uri.parse(bean.package_path);
                        Intent intent = new Intent(Intent.ACTION_VIEW, content_url);
                        intent.setData(content_url);
                        startActivity(intent);
                    });
                }
            }
        }
    }

    @Subscriber


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.history, R.id.comment, R.id.item_read_screen_time, R.id.item_clean, R.id.item_score, R.id.item_update, R.id.about_us})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.history:
                ReadHistoryActivity.startActivity(this);
                break;
            case R.id.comment:
                ConnectUsActivity.startActivity(this);
                break;
            case R.id.item_read_screen_time:
                break;
            case R.id.item_clean:
                showDialog();
                CacheManager.getInstance().clearCache(true);
                new Handler().postDelayed(() -> {
                    clean.setText("0M");
                    hideDialog();
                    ToastUtils.showToast("清除缓存成功");
                }, 1000);
                break;
            case R.id.item_score:
                MarketUtils.yingyongbao("com.wxb.wanshu", "com.tencent.android.qqdownloader", this);
                break;
            case R.id.item_update:
                showDialog();
                mPresenter.getLastVersion(true);
                break;
            case R.id.about_us:
                AboutUsActivity.startActivity(this);
                break;
        }
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
