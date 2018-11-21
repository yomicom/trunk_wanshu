package com.wxb.wanshu.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseRVActivity;
import com.wxb.wanshu.bean.BookList;
import com.wxb.wanshu.bean.NovelCategory;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerBookComponent;
import com.wxb.wanshu.ui.activity.ListActivity.SelectBooksActivity;
import com.wxb.wanshu.ui.adapter.easyadpater.BookClassifyAdapter;
import com.wxb.wanshu.ui.contract.SelectBooksContract;
import com.wxb.wanshu.ui.presenter.SelectBookPresenter;
import com.wxb.wanshu.view.recycleview.decoration.GridSpacingItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 分类
 */
public class ClassifyActivity extends BaseRVActivity<NovelCategory.DataBean> implements SelectBooksContract.View {

    @Inject
    SelectBookPresenter mPresenter;
    @BindView(R.id.iv_search)
    ImageView ivSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setCanSlide(false);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_classify;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerBookComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initToolBar() {
    }

    @Override
    public void initDatas() {
        mPresenter.attachView(this);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.getNovelCategory();
    }

    @Override
    public void configViews() {
        initAdapter(BookClassifyAdapter.class, false, false);
        mRecyclerView.removeAllItemDecoration();

        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 35, false));

        onRefresh();
    }

    @Override
    public void showError() {
        hideDialog();
        mRecyclerView.showError();
        View errorView = mRecyclerView.getErrorView();
        errorView.findViewById(R.id.get).setOnClickListener(v -> onRefresh());
    }

    @Override
    public void complete() {
        hideDialog();
    }

    @Override
    public void showBookList(BookList data) {
    }

    @Override
    public void showNovelCategory(NovelCategory categoryData) {
        List<NovelCategory.DataBean> data = categoryData.getData();
        if (data.size() > 0) {
            mAdapter.addAll(categoryData.getData());
        }
    }

    @Override
    public void onLoadMore() {
    }

    @Override
    public void onItemClick(int position) {
        NovelCategory.DataBean dataBean = mAdapter.getItem(position);
        SelectBooksActivity.startActivity(mContext, "classify", dataBean.getId(), dataBean.getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
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
