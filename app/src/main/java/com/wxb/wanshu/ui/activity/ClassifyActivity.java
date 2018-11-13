package com.wxb.wanshu.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseRVActivity;
import com.wxb.wanshu.bean.BookList;
import com.wxb.wanshu.bean.NovelCategory;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerBookComponent;
import com.wxb.wanshu.ui.activity.ListActivity.SelectBooksActivity;
import com.wxb.wanshu.ui.adapter.easyadpater.BookClassifyAdapter;
import com.wxb.wanshu.ui.adapter.easyadpater.SelectBooksAdapter;
import com.wxb.wanshu.ui.contract.SelectBooksContract;
import com.wxb.wanshu.ui.presenter.SelectBookPresenter;
import com.wxb.wanshu.utils.ViewToolUtils;
import com.wxb.wanshu.view.recycleview.decoration.GridSpacingItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 分类
 */
public class ClassifyActivity extends BaseRVActivity<BookList.DataBean> implements SelectBooksContract.View, OnRvItemClickListener {

    int MAN_TYPE = 10;
    int WOMAN_TYPE = 20;
    int sex_type = 20;

    @Inject
    SelectBookPresenter mPresenter;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;

//    private HeaderViewHolder headerViewHolder;
//    String category_id = "";
//    String complete_status = "";

    @Override
    public void onItemClick(View view, int position, Object data) {
        NovelCategory.DataBean dataBean = (NovelCategory.DataBean) data;
        SelectBooksActivity.startActivity(mContext, "classify", dataBean.getId(), dataBean.getName());
    }


//    static class HeaderViewHolder {
//
//        private TextView tvComplete;
//        private TextView tvUpdating;
//        private TextView tvAllStatus;
//        private GridView gridview;
//        private TextView tvAllCategory;
//        private LinearLayout llChoose;
//        private TextView closeChoose;
//        private TextView tvMan;
//        private TextView tvWoman;
//
//        public HeaderViewHolder(View view) {
//            initView(view);
//        }
//
//        private void initView(View view) {
//            tvWoman = (TextView) view.findViewById(R.id.tv_woman);
//            tvMan = (TextView) view.findViewById(R.id.tv_man);
//            closeChoose = (TextView) view.findViewById(R.id.close_choose);
//            llChoose = (LinearLayout) view.findViewById(R.id.ll_choose);
//            tvAllCategory = (TextView) view.findViewById(R.id.tv_all_category);
//            gridview = (GridView) view.findViewById(R.id.gridview);
//            tvAllStatus = (TextView) view.findViewById(R.id.tv_all_status);
//            tvUpdating = (TextView) view.findViewById(R.id.tv_updating);
//            tvComplete = (TextView) view.findViewById(R.id.tv_complete);
//        }
//    }

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

        onRefresh();
//        refreshData();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.getNovelCategory();
    }

    //    private void refreshData() {
//        page = START_PAGE;
//        mPresenter.getBookList(sex_type, category_id, complete_status, page, "");
//    }

    @Override
    public void configViews() {

        initAdapter(SelectBooksAdapter.class, false, true);
//        mRecyclerView.removeAllItemDecoration();

//        mAdapter.addHeader(new RecyclerArrayAdapter.ItemView() {
//
//            @Override
//            public View onCreateView(ViewGroup parent) {
//                View headerView = LayoutInflater.from(mContext).inflate(R.layout.header_view_classify, parent, false);
//                return headerView;
//            }
//
//            @Override
//            public void onBindView(View headerView) {
//                headerViewHolder = new HeaderViewHolder(headerView);
//                setView();
//            }
//
//        });
    }

//    private void setView() {
//        headerViewHolder.tvAllCategory.setOnClickListener(this);
//        headerViewHolder.tvAllStatus.setOnClickListener(this);
//        headerViewHolder.tvComplete.setOnClickListener(this);
//        headerViewHolder.tvUpdating.setOnClickListener(this);
//        headerViewHolder.closeChoose.setOnClickListener(this);
//        headerViewHolder.tvMan.setOnClickListener(this);
//        headerViewHolder.tvWoman.setOnClickListener(this);
//    }

    @Override
    public void showError() {
        hideDialog();
    }

    @Override
    public void complete() {
        hideDialog();
    }

    @Override
    public void showBookList(BookList data) {
//        if (page == START_PAGE) {
//            mAdapter.clear();
//        }
//        mAdapter.addAll(data.getData());
    }

    @Override
    public void showNovelCategory(NovelCategory categoryData) {

        List<NovelCategory.DataBean> data = categoryData.getData();
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3, LinearLayoutManager.VERTICAL, false);
        recycleview.setLayoutManager(layoutManager);
        recycleview.addItemDecoration(new GridSpacingItemDecoration(3, 30, false));

        BookClassifyAdapter adapter = new BookClassifyAdapter(mContext, data, this);
        recycleview.setAdapter(adapter);

    }

    @Override
    public void onLoadMore() {
//        page++;
//        mPresenter.getBookList(sex_type, category_id, complete_status, page, "");
    }

    @Override
    public void onItemClick(int position) {
//        BookDetailsActivity.startActivity(this, mAdapter.getAllData().get(position).getId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_all_category:
//                if (!"".equals(category_id)) {
//                    category_id = "";
//                    refreshData();
//                    ViewToolUtils.getResourceColor(mContext, headerViewHolder.tvAllCategory, R.color.gobal_color);
//                }
//                break;
//            case R.id.tv_all_status:
//                if (!"".equals(complete_status)) {
//                    complete_status = "";
//                    refreshData();
//                    ViewToolUtils.getResourceColor(mContext, headerViewHolder.tvAllStatus, R.color.gobal_color);
//                    ViewToolUtils.getResourceColor(mContext, headerViewHolder.tvUpdating, R.color.text_color_2);
//                    ViewToolUtils.getResourceColor(mContext, headerViewHolder.tvComplete, R.color.text_color_2);
//                }
//                break;
//            case R.id.tv_updating:
//                if (!"0".equals(complete_status)) {
//                    complete_status = "0";
//                    refreshData();
//                    ViewToolUtils.getResourceColor(mContext, headerViewHolder.tvAllStatus, R.color.text_color_2);
//                    ViewToolUtils.getResourceColor(mContext, headerViewHolder.tvUpdating, R.color.gobal_color);
//                    ViewToolUtils.getResourceColor(mContext, headerViewHolder.tvComplete, R.color.text_color_2);
//                }
//                break;
//            case R.id.tv_complete:
//                if (!"1".equals(complete_status)) {
//                    complete_status = "1";
//                    refreshData();
//                    ViewToolUtils.getResourceColor(mContext, headerViewHolder.tvAllStatus, R.color.text_color_2);
//                    ViewToolUtils.getResourceColor(mContext, headerViewHolder.tvUpdating, R.color.text_color_2);
//                    ViewToolUtils.getResourceColor(mContext, headerViewHolder.tvComplete, R.color.gobal_color);
//                }
//                break;
//            case R.id.close_choose:
//                if (isVisible(headerViewHolder.llChoose)) {
//                    gone(headerViewHolder.llChoose);
//                    headerViewHolder.closeChoose.setText("展开");
//                } else {
//                    visible(headerViewHolder.llChoose);
//                    headerViewHolder.closeChoose.setText("收起");
//                }
//                break;
//            case R.id.tv_woman:
//                if (sex_type != WOMAN_TYPE) {
//                    sex_type = WOMAN_TYPE;
//                    refreshData();
//                    ViewToolUtils.getResourceColor(mContext, headerViewHolder.tvWoman, R.color.gobal_color);
//                    ViewToolUtils.getResourceColor(mContext, headerViewHolder.tvMan, R.color.text_color_1);
//                }
//                break;
//            case R.id.tv_man:
//                if (sex_type != MAN_TYPE) {
//                    sex_type = MAN_TYPE;
//                    refreshData();
//                    ViewToolUtils.getResourceColor(mContext, headerViewHolder.tvMan, R.color.gobal_color);
//                    ViewToolUtils.getResourceColor(mContext, headerViewHolder.tvWoman, R.color.text_color_1);
//                }
//                break;
//        }
//    }

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
