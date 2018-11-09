package com.wxb.wanshu.ui.activity.ListActivity;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseRVActivity;
import com.wxb.wanshu.bean.AddShlef;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.BookShelfStatus;
import com.wxb.wanshu.bean.ReadHistoryList;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerAccountComponent;
import com.wxb.wanshu.ui.adapter.easyadpater.ReadHistoryAdapter;
import com.wxb.wanshu.ui.contract.ReadHistoryContract;
import com.wxb.wanshu.ui.presenter.ReadHistoryPresenter;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import javax.inject.Inject;

/**
 * 阅读历史页
 */
public class ReadHistoryActivity extends BaseRVActivity<ReadHistoryList.DataBean> implements ReadHistoryContract.View {

    int page;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ReadHistoryActivity.class));
    }

    @Inject
    ReadHistoryPresenter mPresenter;

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
        mCommonToolbar.setTitle("阅读历史");
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        mPresenter.attachView(this);
        onRefresh();

        EventBus.getDefault().register(this);
    }

    @Override
    public void configViews() {
        initAdapter(ReadHistoryAdapter.class, false, true);
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showReadHistoryList(ReadHistoryList data) {
        mAdapter.addAll(data.getData());
    }

    @Override
    public void addBookResult(Base result) {

    }

    @Override
    public void delHistoryBookResult(Base result) {

    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.getReadHistoryList(page);
    }

    @Override
    public void onLoadMore() {
        page++;
        mPresenter.getReadHistoryList(page);
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

    @Subscriber
    public void onEventMainThread(AddShlef data) {
        if (data.add >= 0) {
            mAdapter.notifyItemChanged(data.add);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        EventBus.getDefault().unregister(this);
    }
}
