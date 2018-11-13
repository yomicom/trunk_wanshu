package com.wxb.wanshu.ui.activity.ListActivity;

import android.content.Context;
import android.content.Intent;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseRVActivity;
import com.wxb.wanshu.base.Constant;
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

    public static String TYPE = "type";
    String type;

    public static void startActivity(Context context, String type, String title) {
        context.startActivity(new Intent(context, SelectBooksActivity.class).putExtra(TYPE, type).putExtra("title", title));
    }
    public static void startActivity(Context context, String type,String category_id, String title) {
        context.startActivity(new Intent(context, SelectBooksActivity.class).putExtra(TYPE, type).putExtra("title", title).putExtra("category_id", category_id));
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

        type = getIntent().getStringExtra(TYPE);
        getData();
    }

    @Override
    public void configViews() {

        initAdapter(SelectBooksAdapter.class, false, false);
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
    public void showNovelCategory(NovelCategory category) {

    }

    @Override
    public void onLoadMore() {
        page++;
        getData();
    }

    private void getData() {
        switch (type) {
            case "classify":
                String category_id = getIntent().getStringExtra("category_id");
                mPresenter.getBookList( category_id,  page);
                break;
            case Constant.BookType.Boutique_All://精选
                mPresenter.getBoutiqueList(0, page);
                break;
            case Constant.BookType.Boutique_Publishing:
                mPresenter.getBoutiqueList(1, page);
                break;
            case Constant.BookType.Boutique_Finished:
                mPresenter.getBoutiqueList(2, page);
                break;
            case Constant.BookType.Short_AncientRomance://短文
                mPresenter.getShortStoryList(1, page);
                break;
            case Constant.BookType.Short_ModernRomance:
                mPresenter.getShortStoryList(2, page);
                break;
            case Constant.BookType.Short_All:
                mPresenter.getShortStoryList(0, page);
                break;
            case Constant.BookType.Finished_All://完结
                mPresenter.getFinishList("read", 1, page);
                break;
            case Constant.BookType.Finished_Popular:
                mPresenter.getFinishList("read", 1, page);
                break;
            case Constant.BookType.Finished_Latest:
                mPresenter.getFinishList("time", 1, page);
                break;

            case Constant.BookType.PUBLISHING://首页-火爆连载
                mPresenter.getFinishList("time", 0, page);
                break;
            case Constant.BookType.FINISH://首页-完本精选
                mPresenter.getFinishList("time", 1, page);
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        BookDetailsActivity.startActivity(this, mAdapter.getAllData().get(position).getId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
