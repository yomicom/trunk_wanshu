package com.wxb.wanshu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.bean.UserInfo;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerAccountComponent;
import com.wxb.wanshu.ui.activity.ListActivity.ReadHistoryActivity;
import com.wxb.wanshu.ui.contract.MeContract;
import com.wxb.wanshu.ui.presenter.MePresenter;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

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

    }

    @Override
    public void complete() {

    }

    @Override
    public void showUserData(UserInfo data) {
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
                break;
            case R.id.item_score:
                break;
            case R.id.item_update:
                break;
            case R.id.about_us:
                AboutUsActivity.startActivity(this);
                break;
        }
    }
}
