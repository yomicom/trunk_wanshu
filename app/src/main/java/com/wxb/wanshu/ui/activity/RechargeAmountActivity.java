package com.wxb.wanshu.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.bean.RechargeAmount;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerAccountComponent;
import com.wxb.wanshu.component.DaggerBookComponent;
import com.wxb.wanshu.manager.ThemeManager;
import com.wxb.wanshu.ui.adapter.easyadpater.ReadThemeAdapter;
import com.wxb.wanshu.ui.adapter.easyadpater.RechatgeAmountAdapter;
import com.wxb.wanshu.ui.contract.RechargeAmountContract;
import com.wxb.wanshu.ui.presenter.RechargeAmountPresenter;
import com.wxb.wanshu.view.dialog.BuyBookPopupWindow;
import com.wxb.wanshu.view.dialog.PayMoneyPopWindow;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.wxb.wanshu.R.id.gvTheme;

public class RechargeAmountActivity extends BaseActivity implements RechargeAmountContract.View {
    @BindView(R.id.tv_tips)
    TextView tips;
    @BindView(R.id.tv_title0)
    TextView tvTitle0;
    @BindView(R.id.gridview)
    GridView gridview;

    @Inject
    RechargeAmountPresenter mPresenter;

    PayMoneyPopWindow popupWindow;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, RechargeAmountActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recharge_amount;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerAccountComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("充值中心");
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        showDialog();
        mPresenter.attachView(this);
        mPresenter.getRechargeAmount();
    }

    @Override
    public void configViews() {
        popupWindow = new PayMoneyPopWindow((Activity) mContext);
//        tips.setText(Html.fromHtml("1. 苹果政策规定。在iOS上的书币，在非iOS终端不能使用<br/>2. 如充值后账户余额长时间没有变化，请联系客服 400-998-1236"));
    }

    @Override
    public void showError() {
        hideDialog();
    }

    @Override
    public void complete() {

    }

    @Override
    public void showRechargeAmount(RechargeAmount data) {
        hideDialog();
        tips.setText(Html.fromHtml(data.getTips()));
        tvTitle0.setText("温馨提示");
        List<RechargeAmount.DataBean> rechargeLst = data.getData();

        RechatgeAmountAdapter gvAdapter = new RechatgeAmountAdapter(this, rechargeLst);
        gridview.setAdapter(gvAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RechargeAmount.DataBean dataBean = rechargeLst.get(position);

                popupWindow.setPay(dataBean.getAmount());
                popupWindow.showAtLocation(findViewById(R.id.base), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
