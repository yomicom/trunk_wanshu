package com.wxb.wanshu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseRVActivity;
import com.wxb.wanshu.bean.BookList;;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerBookComponent;
import com.wxb.wanshu.ui.adapter.easyadpater.NovelRankAdapter;
import com.wxb.wanshu.ui.contract.NovelRankContract;
import com.wxb.wanshu.ui.presenter.NovelRankPresenter;
import com.wxb.wanshu.utils.ViewToolUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NovelRankActivity extends BaseRVActivity<BookList.DataBean> implements NovelRankContract.View {

    String type = "read";

    String TYPE_1 = "read";
    String TYPE_2 = "search";
    String TYPE_3 = "click";
    String TYPE_4 = "store";

    @Inject
    NovelRankPresenter mPresenter;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;
    @BindView(R.id.read)
    TextView read;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.click)
    TextView click;
    @BindView(R.id.store)
    TextView store;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, NovelRankActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_novel_rank;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerBookComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("排行榜");
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        mPresenter.attachView(this);
        mPresenter.getNovelRank(type, page);
    }

    @Override
    public void configViews() {
        initAdapter(NovelRankAdapter.class, false, false);
        mRecyclerView.removeAllItemDecoration();
    }

    @OnClick({R.id.read, R.id.search, R.id.click, R.id.store})
    public void onViewClicked(View view) {
        showDialog();
        switch (view.getId()) {
            case R.id.read:
                if (!type.equals(TYPE_1)) {
                    changeStatus(read);
                    type = TYPE_1;
                    page = START_PAGE;
                    mPresenter.getNovelRank(type, page);
                }
                break;
            case R.id.search:
                if (!type.equals(TYPE_2)) {
                    changeStatus(search);
                    type = TYPE_2;
                    page = START_PAGE;
                    mPresenter.getNovelRank(type, page);
                }
                break;
            case R.id.click:
                if (!type.equals(TYPE_3)) {
                    changeStatus(click);
                    type = TYPE_3;
                    page = START_PAGE;
                    mPresenter.getNovelRank(type, page);
                }
                break;
            case R.id.store:
                if (!type.equals(TYPE_4)) {
                    changeStatus(store);
                    type = TYPE_4;
                    page = START_PAGE;
                    mPresenter.getNovelRank(type, page);
                }
                break;
        }
    }

    private void changeStatus(TextView textView) {
        ViewToolUtils.getResourceColor(mContext, read, R.color.text_color_1);
        ViewToolUtils.getResourceColor(mContext, search, R.color.text_color_1);
        ViewToolUtils.getResourceColor(mContext, click, R.color.text_color_1);
        ViewToolUtils.getResourceColor(mContext, store, R.color.text_color_1);

        ViewToolUtils.getResourceColor(mContext, textView, R.color.gobal_color);

        ViewToolUtils.setBackgroundResourceColor(mContext, read, R.color.common_bg);
        ViewToolUtils.setBackgroundResourceColor(mContext, search, R.color.common_bg);
        ViewToolUtils.setBackgroundResourceColor(mContext, click, R.color.common_bg);
        ViewToolUtils.setBackgroundResourceColor(mContext, store, R.color.common_bg);

        ViewToolUtils.setBackgroundResourceColor(mContext, textView, R.color.white);
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        page++;
        mPresenter.getNovelRank(type, page);
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
    public void showNovelRank(BookList data) {
        if (page == START_PAGE) {
            hideDialog();
            mAdapter.clear();
        }
        mAdapter.addAll(data.getData());
    }

    @Override
    public void onItemClick(int position) {
        BookDetailsActivity.startActivity(mContext, mAdapter.getItem(position).getId());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
