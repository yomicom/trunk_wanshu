package com.wxb.wanshu.ui.activity.ListActivity;

import android.content.Context;
import android.content.Intent;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseRVActivity;
import com.wxb.wanshu.bean.BookList;
import com.wxb.wanshu.bean.NovelCategory;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerBookComponent;
import com.wxb.wanshu.ui.activity.BookDetailsActivity;
import com.wxb.wanshu.ui.adapter.easyadpater.SelectBooksAdapter;
import com.wxb.wanshu.ui.contract.SelectBooksContract;
import com.wxb.wanshu.ui.presenter.SelectBookPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * 精选书籍列表页
 */
public class SelectBooksActivity extends BaseRVActivity<BookList.DataBean> implements SelectBooksContract.View {

    int MAN_TYPE = 10;
    int WOMAN_TYPE = 20;
    public static String SEX_TYPE = "sex_type";
    int sex_type;

    public static void startActivity(Context context, int sex_type, String title) {
        context.startActivity(new Intent(context, SelectBooksActivity.class).putExtra(SEX_TYPE, sex_type).putExtra("title", title));
    }

    @Inject
    SelectBookPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_recycleview;
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
        mCommonToolbar.setTitle(getIntent().getStringExtra("title"));
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        mPresenter.attachView(this);
        mPresenter.getBookList(sex_type, "", "", page, "");
    }

    @Override
    public void configViews() {
        sex_type = getIntent().getIntExtra(SEX_TYPE, MAN_TYPE);

        initAdapter(SelectBooksAdapter.class, false, true);
        mRecyclerView.removeAllItemDecoration();
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showBookList(BookList data) {
        mAdapter.addAll(data.getData());
//        mAdapter.clear();
    }

    @Override
    public void showNovelCategory(List<NovelCategory.DataBean> category) {

    }

    @Override
    public void onLoadMore() {
        page++;
        mPresenter.getBookList(sex_type, "", "", page, "");
    }

    @Override
    public void onItemClick(int position) {
        BookDetailsActivity.startActivity(this, mAdapter.getAllData().get(position).getNovel_id());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
