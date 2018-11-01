package com.wxb.wanshu.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wxb.wanshu.R;
import com.wxb.wanshu.ReaderApplication;
import com.wxb.wanshu.base.BaseRVActivity;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.BookShelfStatus;
import com.wxb.wanshu.bean.BookselfList;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerBookComponent;
import com.wxb.wanshu.ui.adapter.easyadpater.BookshelfAdapter;
import com.wxb.wanshu.ui.contract.BookselfContract;
import com.wxb.wanshu.ui.presenter.BookselfPresenter;
import com.wxb.wanshu.view.EmptyView;
import com.wxb.wanshu.view.recycleview.adapter.RecyclerArrayAdapter;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.string.no;

public class BookshelfActivity extends BaseRVActivity<BookselfList.DataBean> implements BookselfContract.View, RecyclerArrayAdapter.OnItemLongClickListener {

    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.emptyView)
    EmptyView emptyView;
    @BindView(R.id.rl_no_content)
    RelativeLayout rlNoContent;

    @BindView(R.id.tool_bar)
    RelativeLayout toolBar;
    @BindView(R.id.tv_select_all)
    TextView tvSelectAll;
    @BindView(R.id.tv_selected_books)
    TextView tvSelectedBooks;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.ll_edit)
    RelativeLayout llEdit;
    @BindView(R.id.tvDelete)
    TextView tvDelete;
    @BindView(R.id.llBatchManagement)
    LinearLayout llBatchManagement;

    private boolean isSelectAll = false;
    @Inject
    BookselfPresenter mPresenter;
    private List<BookselfList.DataBean> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setCanSlide(false);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bookshelf;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerBookComponent.builder()
                .appComponent(ReaderApplication.getsInstance().getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initDatas() {
        EventBus.getDefault().register(this);
        mPresenter.attachView(this);
    }

    @Override
    public void configViews() {
        initAdapter(BookshelfAdapter.class, false, false);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.removeAllItemDecoration();

        mAdapter.setOnItemLongClickListener(this);
        mRecyclerView.getEmptyView().findViewById(R.id.tv_take).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        onRefresh();
    }

    @Override
    public void showError() {
        emptyView.setVisibility(View.VISIBLE);
        emptyView.setEnterText(R.string.to_get_web);
        emptyView.setWnToast(R.string.no_web);
        emptyView.setWnImage(R.mipmap.icon_no_web);
        emptyView.setEnterListener(new EmptyView.Callback() {
            @Override
            public void exec() {
                page = START_PAGE;
                mPresenter.getData(page, pageSize);
            }
        });
    }

    @Override
    public void complete() {
        mRecyclerView.setRefreshing(false);
    }

    @Override
    public void showData(BookselfList data0) {
        if (page == START_PAGE) {
            mAdapter.clear();
        }
        mAdapter.addAll(data0.getData());

    }

    @Override
    public void showDelSuccess(Base base) {

    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        page = START_PAGE;
        mPresenter.getData(page, pageSize);
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        page++;
        mPresenter.getData(page, pageSize);
    }

    @Override
    public void onItemClick(int position) {
        if (isVisible(llBatchManagement)) { //批量管理时，点击选中某项
            BookselfList.DataBean item = mAdapter.getItem(position);
            if (item.isSeleted) {
                item.isSeleted = false;
            } else {
                item.isSeleted = true;
            }
            mAdapter.notifyDataSetChanged();


            List<BookselfList.DataBean> removeList = new ArrayList<>();
            for (BookselfList.DataBean bean : mAdapter.getAllData()) {
                if (bean.isSeleted) removeList.add(bean);
            }
            tvSelectedBooks.setText("已选择" + removeList.size() + "");
        } else {
            ReadActivity.startActivity(this, mAdapter.getItem(position).getNovel_id());
        }
    }

    @Override
    public boolean onItemLongClick(int position) {
        //批量管理时，屏蔽长按事件
        if (isVisible(llBatchManagement)) return false;
        showBatchManagementLayout(position);
        return false;
    }


    /**
     * 隐藏批量管理布局并刷新页面
     */
    public void goneBatchManagementAndRefreshUI() {
        if (mAdapter == null) return;
        gone(llBatchManagement, llEdit);
        visible(toolBar);
        for (BookselfList.DataBean bean :
                mAdapter.getAllData()) {
            bean.showCheckBox = false;
            bean.isSeleted = false;
        }
        mAdapter.notifyDataSetChanged();

        EventBus.getDefault().post(new BookShelfStatus(true));
    }

    /**
     * 显示批量管理布局
     *
     * @param position
     */
    private void showBatchManagementLayout(int position) {
        for (BookselfList.DataBean bean : mAdapter.getAllData()) {
            bean.showCheckBox = true;
        }
        mAdapter.getItem(position).isSeleted = true;
        mAdapter.notifyDataSetChanged();

        visible(llBatchManagement, llEdit);
        gone(toolBar);

        EventBus.getDefault().post(new BookShelfStatus(false));
    }


    @OnClick({R.id.tv_select_all, R.id.tv_finish, R.id.tvDelete, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_select_all:
                isSelectAll = !isSelectAll;
                tvSelectAll.setText(isSelectAll ? mContext.getString(R.string.cancel_selected_all) : mContext.getString(R.string.selected_all));
                for (BookselfList.DataBean bean : mAdapter.getAllData()) {
                    bean.isSeleted = isSelectAll;
                }
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_finish:
                goneBatchManagementAndRefreshUI();
                break;
            case R.id.tvDelete:

                List<BookselfList.DataBean> removeList = new ArrayList<>();
                for (BookselfList.DataBean bean : mAdapter.getAllData()) {
                    if (bean.isSeleted) removeList.add(bean);
                }
                if (removeList.isEmpty()) {
                    mRecyclerView.showTipViewAndDelayClose(mContext.getString(R.string.has_not_selected_delete_book));
                } else {
                    showDeleteCacheDialog(mContext, removeList);
                }
                break;
            case R.id.iv_search:
                SearchActivity.startActivity(mContext);
                break;
        }
    }


    /**
     * 显示删除本地缓存对话框
     *
     * @param removeList
     */
    private void showDeleteCacheDialog(Context activity, final List<BookselfList.DataBean> removeList) {
        final boolean selected[] = {true};
        new AlertDialog.Builder(activity)
                .setTitle(activity.getString(R.string.remove_selected_book))
//                .setMultiChoiceItems(new String[]{activity.getString(R.string.delete_local_cache)}, selected,
//                        new DialogInterface.OnMultiChoiceClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                                selected[0] = isChecked;
//                            }
//                        })
                .setPositiveButton(activity.getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
//                        new AsyncTask<String, String, String>() {
//                            @Override
//                            protected void onPreExecute() {
//                                super.onPreExecute();
//                                showDialog();
//                            }
//
//                            @Override
//                            protected String doInBackground(String... params) {//后台删除本地缓存
////                                CollectionsManager.getInstance().removeSome(removeList, selected[0]);
//                                return null;
//                            }
//
//                            @Override
//                            protected void onPostExecute(String s) {
//                                super.onPostExecute(s);
//                                mRecyclerView.showTipViewAndDelayClose("成功移除书籍");
//                                for (BookselfList.DataBean bean : removeList) {
//                                    mAdapter.remove(bean);
//                                }
//                                if (isVisible(llBatchManagement)) {
//                                    //批量管理完成后，隐藏批量管理布局并刷新页面
//                                    goneBatchManagementAndRefreshUI();
//                                }
//                                hideDialog();
//                            }
//                        }.execute();

                        StringBuilder novelIds = new StringBuilder();
                        for (BookselfList.DataBean item : removeList) {
                            novelIds.append(item.getNovel_id());
                        }
                        if (novelIds.length() > 0)
                            mPresenter.delBooks(novelIds.substring(0, novelIds.length() - 1));

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mRecyclerView.showTipViewAndDelayClose("成功移除书籍");
                                for (BookselfList.DataBean bean : removeList) {
                                    mAdapter.remove(bean);
                                }
                                if (isVisible(llBatchManagement)) {
                                    //批量管理完成后，隐藏批量管理布局并刷新页面
                                    goneBatchManagementAndRefreshUI();
                                }
                                hideDialog();
                            }
                        }, 800);
                    }
                })
                .setNegativeButton(activity.getString(R.string.cancel), null)
                .create().show();
    }

    private long exitTime = 0;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (isVisible(llBatchManagement)) {
                goneBatchManagementAndRefreshUI();
            } else {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Toast.makeText(getApplicationContext(), "再按一次退出万书",
                            Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    // 退出代码
                    finish();
                    System.exit(0);
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mPresenter.detachView();
    }

}
