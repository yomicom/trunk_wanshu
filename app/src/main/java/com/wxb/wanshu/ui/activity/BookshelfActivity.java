package com.wxb.wanshu.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.hubert.guide.NewbieGuide;
import com.app.hubert.guide.core.Controller;
import com.app.hubert.guide.listener.OnLayoutInflatedListener;
import com.app.hubert.guide.model.GuidePage;
import com.app.hubert.guide.model.HighLight;
import com.wxb.wanshu.MyApplication;
import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseRVActivity;
import com.wxb.wanshu.base.Constant;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.BookShelfStatus;
import com.wxb.wanshu.bean.BookselfList;
import com.wxb.wanshu.bean.ReadHistoryList;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerBookComponent;
import com.wxb.wanshu.manager.CacheManager;
import com.wxb.wanshu.manager.SettingManager;
import com.wxb.wanshu.ui.adapter.easyadpater.BookshelfAdapter;
import com.wxb.wanshu.ui.contract.BookselfContract;
import com.wxb.wanshu.ui.presenter.BookselfPresenter;
import com.wxb.wanshu.utils.FileUtils;
import com.wxb.wanshu.utils.ToastUtils;
import com.wxb.wanshu.view.EmptyView;
import com.wxb.wanshu.view.dialog.ConfirmDialog;
import com.wxb.wanshu.view.recycleview.adapter.RecyclerArrayAdapter;

import org.simple.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class BookshelfActivity extends BaseRVActivity<BookselfList.DataBean> implements BookselfContract.View, RecyclerArrayAdapter.OnItemLongClickListener {

    @BindView(R.id.finish)
    TextView finish;
    @BindView(R.id.manage)
    TextView manage;
    @BindView(R.id.tvDelete)
    TextView tvDelete;
    @BindView(R.id.llBatchManagement)
    LinearLayout llBatchManagement;
    @BindView(R.id.item)
    View item;

    private boolean isSelectAll = false;
    @Inject
    BookselfPresenter mPresenter;

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
                .appComponent(MyApplication.getsInstance().getAppComponent())
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
//        mRecyclerView.getEmptyView().findViewById(R.id.tv_take).setVisibility(View.VISIBLE);
        mRecyclerView.getEmptyView().findViewById(R.id.tv_take).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去书城
                EventBus.getDefault().post(new BookShelfStatus(0));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
        if (SettingManager.getInstance().getFirstEnterBookshelf()) {//第一次进入查看书城
            SettingManager.getInstance().saveFirstEnterBookshelf();
            visible(item);
        }
    }

    @Override
    public void showError() {
        gone(manage, finish, tvDelete);
        View errorView = mRecyclerView.getErrorView();
        errorView.findViewById(R.id.get).setOnClickListener(v -> onRefresh());
        mRecyclerView.showError();
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
        if (data0.getData().size() > 0) {
            mAdapter.addAll(data0.getData());
            visible(manage);

            if (SettingManager.getInstance().showGuide1()) {//第一次显示管理引导
                SettingManager.getInstance().saveGuide1();
                EventBus.getDefault().post(new BookShelfStatus(2));
                NewbieGuide.with(this)
                        .setLabel("Guide_Books")
                        .addGuidePage(GuidePage.newInstance().addHighLight(manage, HighLight.Shape.RECTANGLE, 4)
                                .setLayoutRes(R.layout.view_guide_show)
                                .setOnLayoutInflatedListener((view, controller) -> {
                                    view.findViewById(R.id.guide).setOnClickListener(v -> {
                                        controller.remove();
                                        EventBus.getDefault().post(new BookShelfStatus(1));
                                    });
                                }))
                        .show();
            }

        } else {
            gone(manage, finish);
        }
    }

    @Override
    public void showDelSuccess(String novel_ids) {
        String[] ids = novel_ids.split(",");
        for (BookselfList.DataBean bean : mAdapter.getAllData()) {
            for (int i = 0; i < ids.length; i++) {
                if (ids[i].equals(bean.id)) {
                    mAdapter.remove(bean);
                    FileUtils.deleteBookFiles(bean.id, 0);
                    break;
                }
            }
        }
        if (mAdapter.getAllData().size() == 0)
            gone(manage);
        if (isVisible(llBatchManagement)) {
            //批量管理完成后，隐藏批量管理布局并刷新页面
            goneBatchManagementAndRefreshUI();
        }
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.getData();
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
    }

    @Override
    public void onItemClick(int position) {
        if (isVisible(finish)) { //批量管理时，点击选中某项
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
//            tvSelectedBooks.setText("已选择" + removeList.size() + "");
        } else {
            if (!ReadOtherStatusActivity.startActivity(this, mAdapter.getItem(position).is_onsale)) {
                ReadActivity.startActivity(this, mAdapter.getItem(position).getId());
            }
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
        gone(finish, llBatchManagement);
        manage.setText("管理");
        manage.setTextColor(getResources().getColor(R.color.text_color_2));
        for (BookselfList.DataBean bean :
                mAdapter.getAllData()) {
            bean.showCheckBox = false;
            bean.isSeleted = false;
        }
        mAdapter.notifyDataSetChanged();

        EventBus.getDefault().post(new BookShelfStatus(1));
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
        if (position > 0) {
            mAdapter.getItem(position).isSeleted = true;
        }
        mAdapter.notifyDataSetChanged();

        visible(finish, llBatchManagement);
        manage.setText("全选");
        manage.setTextColor(getResources().getColor(R.color.gobal_color));

        EventBus.getDefault().post(new BookShelfStatus(2));
    }


    @OnClick({R.id.tvDelete, R.id.finish, R.id.manage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvDelete:

                List<BookselfList.DataBean> removeList = new ArrayList<>();
                for (BookselfList.DataBean bean : mAdapter.getAllData()) {
                    if (bean.isSeleted) removeList.add(bean);
                }
                if (removeList.isEmpty()) {
                    ToastUtils.showToast(mContext.getString(R.string.has_not_selected_delete_book));
                } else {
                    showDeleteCacheDialog(removeList);
                }
                break;
            case R.id.finish://完成操作
                goneBatchManagementAndRefreshUI();
                break;
            case R.id.manage:
                if (isVisible(llBatchManagement)) {//全选操作
                    isSelectAll = !isSelectAll;
                    manage.setText(isSelectAll ? mContext.getString(R.string.cancel_selected_all) : mContext.getString(R.string.selected_all));
                    for (BookselfList.DataBean bean : mAdapter.getAllData()) {
                        bean.isSeleted = isSelectAll;
                    }
                    mAdapter.notifyDataSetChanged();
                } else {//管理操作
                    showBatchManagementLayout(-1);
                }
                break;
        }
    }


    /**
     * 显示删除本地缓存对话框
     *
     * @param removeList
     */
    private void showDeleteCacheDialog(final List<BookselfList.DataBean> removeList) {
        ConfirmDialog.showNotice(mContext, "提示", "确认要移出书架吗？", () -> {
            StringBuilder novelIds = new StringBuilder();
            for (BookselfList.DataBean item : removeList) {
                novelIds.append(item.id + ",");
                CacheManager.clearBookCache(item.id);//删除书籍缓存
            }
            if (novelIds.length() > 0) {
                mPresenter.delBooks(novelIds.substring(0, novelIds.length() - 1));
            }
        });
    }

    private long exitTime = 0;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (isVisible(llBatchManagement)) {
                goneBatchManagementAndRefreshUI();
            } else {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Toast.makeText(getApplicationContext(), "再按一次退出",
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
