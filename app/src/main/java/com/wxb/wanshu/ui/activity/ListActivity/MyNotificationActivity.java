package com.wxb.wanshu.ui.activity.ListActivity;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseRVActivity;
import com.wxb.wanshu.bean.NotificationList;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerAccountComponent;
import com.wxb.wanshu.ui.adapter.easyadpater.MyNotificationAdapter;
import com.wxb.wanshu.ui.contract.MyNotificationContract;
import com.wxb.wanshu.ui.presenter.MyNotificationPresenter;

import javax.inject.Inject;

/**
 * 精选书籍列表页
 */
public class MyNotificationActivity extends BaseRVActivity<NotificationList.DataBean> implements MyNotificationContract.View {

    int page;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MyNotificationActivity.class));
    }

    @Inject
    MyNotificationPresenter mPresenter;

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
        mCommonToolbar.setTitle("通知");
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        mPresenter.attachView(this);
        onRefresh();
    }

    @Override
    public void configViews() {
        initAdapter(MyNotificationAdapter.class, false, true);
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showNotificationList(NotificationList data) {
        mAdapter.addAll(data.getData());
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.getNotificationList(page);
    }

    @Override
    public void onLoadMore() {
        page ++;
        mPresenter.getNotificationList(page);
    }

    @Override
    public void onItemClick(int position) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 10, 0, "清空").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 10) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
