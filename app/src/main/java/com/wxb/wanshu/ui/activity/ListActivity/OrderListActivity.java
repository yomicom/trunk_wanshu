package com.wxb.wanshu.ui.activity.ListActivity;

import android.content.Context;
import android.content.Intent;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseRVActivity;
import com.wxb.wanshu.bean.UserOrder;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerAccountComponent;
import com.wxb.wanshu.ui.adapter.easyadpater.OrderListAdapter;
import com.wxb.wanshu.ui.contract.OrderListContract;
import com.wxb.wanshu.ui.presenter.OrderListPresenter;
import com.wxb.wanshu.view.recycleview.EasyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 精选书籍列表页
 */
public class OrderListActivity extends BaseRVActivity<UserOrder.DataBean> implements OrderListContract.View {

    int page;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, OrderListActivity.class));
    }

    @Inject
    OrderListPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_recycleview;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerAccountComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("充值记录");
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        mPresenter.attachView(this);
        onRefresh();

    }

    @Override
    public void configViews() {
        initAdapter(OrderListAdapter.class, false, true);
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showOrderList(UserOrder data) {
        mAdapter.addAll(data.getData());
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.getOrderList(page);
    }

    @Override
    public void onLoadMore() {
        page ++;
        mPresenter.getOrderList(page);
    }

    @Override
    public void onItemClick(int position) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
