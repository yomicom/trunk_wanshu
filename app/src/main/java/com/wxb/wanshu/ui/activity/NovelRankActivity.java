package com.wxb.wanshu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.base.BaseRVActivity;
import com.wxb.wanshu.bean.BookDetails;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.bean.NovelRank;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerBookComponent;
import com.wxb.wanshu.ui.activity.ListActivity.SelectBooksActivity;
import com.wxb.wanshu.ui.adapter.easyadpater.NovelRankAdapter;
import com.wxb.wanshu.ui.adapter.easyadpater.SelectBooksAdapter;
import com.wxb.wanshu.ui.contract.NovelRankContract;
import com.wxb.wanshu.ui.presenter.NovelRankPresenter;
import com.wxb.wanshu.utils.ViewToolUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NovelRankActivity extends BaseRVActivity<NovelRank.DataBean> implements NovelRankContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;
    @BindView(R.id.tv_click_sort)
    TextView tvClickSort;
    @BindView(R.id.view_click)
    View viewClick;
    @BindView(R.id.item_click_sort)
    RelativeLayout itemClickSort;
    @BindView(R.id.tv_sell_sort)
    TextView tvSellSort;
    @BindView(R.id.view_sell)
    View viewSell;
    @BindView(R.id.item_sell_sort)
    RelativeLayout itemSellSort;
    @BindView(R.id.tv_reward_sort)
    TextView tvRewardSort;
    @BindView(R.id.view_reward)
    View viewReward;
    @BindView(R.id.item_reward_sort)
    RelativeLayout itemRewardSort;

    public static String SEX_TYPE = "sex_type";
    int sex_type;
    int type = 1;

    int TYPE_1 = 1;
    int TYPE_2 = 2;
    int TYPE_3 = 3;

    @Inject
    NovelRankPresenter mPresenter;

    public static void startActivity(Context context, int sex_type, String title) {
        context.startActivity(new Intent(context, NovelRankActivity.class).putExtra(SEX_TYPE, sex_type).putExtra("title", title));
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
        mCommonToolbar.setTitle(getIntent().getStringExtra("title"));
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        mPresenter.attachView(this);
        mPresenter.getNovelRank(type, sex_type, page);
    }

    @Override
    public void configViews() {
        initAdapter(NovelRankAdapter.class, false, true);
        mRecyclerView.removeAllItemDecoration();
    }

    @OnClick({R.id.item_click_sort, R.id.item_sell_sort, R.id.item_reward_sort})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_click_sort:
                if (type != 1) {
                    changeStatus(tvClickSort, viewClick);
                    type = 1;
                    page = START_PAGE;
                    mPresenter.getNovelRank(type, sex_type, page);
                }
                break;
            case R.id.item_sell_sort:
                if (type != 2) {
                    changeStatus(tvSellSort, viewSell);
                    type = 2;
                    page = START_PAGE;
                    mPresenter.getNovelRank(type, sex_type, page);
                }
                break;
            case R.id.item_reward_sort:
                if (type != 3) {
                    changeStatus(tvRewardSort, viewReward);
                    type = 3;
                    page = START_PAGE;
                    mPresenter.getNovelRank(type, sex_type, page);
                }
                break;
        }
    }

    private void changeStatus(TextView textView, View view) {
        ViewToolUtils.getResourceColor(mContext, tvClickSort, R.color.text_color_1);
        ViewToolUtils.getResourceColor(mContext, tvSellSort, R.color.text_color_1);
        ViewToolUtils.getResourceColor(mContext, tvRewardSort, R.color.text_color_1);

        ViewToolUtils.getResourceColor(mContext, textView, R.color.gobal_color);

        gone(viewClick, viewReward, viewSell);
        visible(view);
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        page++;
        mPresenter.getNovelRank(type, sex_type, page);
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showNovelRank(NovelRank data) {
        if (page == START_PAGE) {
            mAdapter.clear();
        }
        mAdapter.addAll(data.getData());
    }

    @Override
    public void onItemClick(int position) {
        BookDetailsActivity.startActivity(mContext, mAdapter.getItem(position).getNovel_id());
    }
}
