package com.wxb.wanshu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.bean.UserInfo;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerAccountComponent;
import com.wxb.wanshu.ui.activity.ListActivity.AmountRecordActivity;
import com.wxb.wanshu.ui.activity.ListActivity.MyNotificationActivity;
import com.wxb.wanshu.ui.activity.ListActivity.OrderListActivity;
import com.wxb.wanshu.ui.activity.ListActivity.ReadHistoryActivity;
import com.wxb.wanshu.ui.contract.MeContract;
import com.wxb.wanshu.ui.presenter.MePresenter;
import com.wxb.wanshu.utils.ImageUtils;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wxb.wanshu.R.id.tv;

public class MeActivity extends BaseActivity implements MeContract.View {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_vip)
    ImageView ivVip;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.iv_user)
    ImageView ivUser;
    @BindView(R.id.tv_over_time)
    TextView tvOverTime;
    @BindView(R.id.tv_account_coin)
    TextView tvAccountCoin;
    @BindView(R.id.tv_add_money)
    TextView tvAddMoney;
    @BindView(R.id.tv_book_coin)
    TextView tvBookCoin;
    @BindView(R.id.tv_chongzhi_record)
    TextView tvChongzhiRecord;
    @BindView(R.id.tv_read_history)
    TextView tvReadHistory;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.ll_vip)
    LinearLayout llVip;

    @Inject
    MePresenter mPresenter;

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
        mPresenter.getUserData();
    }

    @Override
    public void configViews() {
        EventBus.getDefault().register(this);
    }

    @OnClick({R.id.tv_login, R.id.ll_vip, R.id.tv_account_coin, R.id.tv_add_money, R.id.tv_book_coin, R.id.tv_chongzhi_record, R.id.tv_read_history, R.id.tv_message, R.id.tv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                WanshuLoginActivity.startActivityFor(this, 10);
                break;
            case R.id.ll_vip:
                WebViewActivity.startActivity(mContext, "");
                break;
            case R.id.tv_account_coin:
                break;
            case R.id.tv_add_money:
                RechargeAmountActivity.startActivity(this);
                break;
            case R.id.tv_book_coin:
                AmountRecordActivity.startActivity(this);
                break;
            case R.id.tv_chongzhi_record:
                OrderListActivity.startActivity(this);
                break;
            case R.id.tv_read_history:
                ReadHistoryActivity.startActivity(this);
                break;
            case R.id.tv_message:
                MyNotificationActivity.startActivity(this);
                break;
            case R.id.tv_setting:
                SettingActivity.startActivity(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 10) {
                tvLogin.setVisibility(View.GONE);
                tvName.setText(data.getStringExtra("name"));

                ImageUtils.displayCircleImage(this, ivUser, data.getStringExtra("head"));
            }
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
        if (data != null) {
//            tvLogin.setVisibility(View.GONE);
//            UserInfo.DataBean userInfo = data.getData();
//            tvName.setText(userInfo.getNickname());
//            tvId.setText("账号ID：" + userInfo.getId());
//            ImageUtils.displayImage(mContext, ivUser, userInfo.getAvatar(), R.mipmap.default_circle, R.mipmap.default_circle);
//            tvAccountCoin.setText(userInfo.getAmount() + "书币");
//            tvAccountCoin.setVisibility(View.VISIBLE);
//            if (userInfo.getIs_vip() == 1) {
//                ivVip.setVisibility(View.VISIBLE);
//                tvOverTime.setText("还有" + userInfo.getVip_end_time() + "天到期");
//            } else {
//                ivVip.setVisibility(View.GONE);
//                tvOverTime.setText("");
//            }
        } else {
            tvLogin.setVisibility(View.VISIBLE);
            tvName.setText("");
            ivVip.setVisibility(View.GONE);
            ivUser.setImageResource(R.mipmap.default_circle);
            tvAccountCoin.setVisibility(View.GONE);
            tvOverTime.setText("");
        }
    }

    @Subscriber


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        EventBus.getDefault().unregister(this);
    }
}
