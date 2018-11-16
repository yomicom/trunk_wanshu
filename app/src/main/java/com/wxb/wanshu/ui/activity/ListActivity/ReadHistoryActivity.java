package com.wxb.wanshu.ui.activity.ListActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseRVActivity;
import com.wxb.wanshu.base.Constant;
import com.wxb.wanshu.bean.AddShlef;
import com.wxb.wanshu.bean.ReadHistoryList;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerAccountComponent;
import com.wxb.wanshu.ui.activity.ReadActivity;
import com.wxb.wanshu.ui.activity.ReadOtherStatusActivity;
import com.wxb.wanshu.ui.adapter.easyadpater.ReadHistoryAdapter;
import com.wxb.wanshu.ui.contract.ReadHistoryContract;
import com.wxb.wanshu.ui.presenter.ReadHistoryPresenter;
import com.wxb.wanshu.utils.ToastUtils;
import com.wxb.wanshu.utils.ViewToolUtils;
import com.wxb.wanshu.view.dialog.ConfirmDialog;
import com.wxb.wanshu.view.recycleview.adapter.RecyclerArrayAdapter;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 阅读历史页
 */
public class ReadHistoryActivity extends BaseRVActivity<ReadHistoryList.DataBean> implements ReadHistoryContract.View, RecyclerArrayAdapter.OnItemLongClickListener {

    @BindView(R.id.finish)
    TextView finish;
    @BindView(R.id.manage)
    TextView manage;
    @BindView(R.id.tool_bar)
    RelativeLayout toolBar;
    @BindView(R.id.tvDelete)
    TextView tvDelete;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.main_title)
    TextView main_title;
    @BindView(R.id.llBatchManagement)
    LinearLayout llBatchManagement;
    @BindView(R.id.item)
    View item;

    private boolean isSelectAll = false;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ReadHistoryActivity.class));
    }

    @Inject
    ReadHistoryPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bookshelf;
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
//        mCommonToolbar.setTitle("阅读历史");
//        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        mPresenter.attachView(this);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    public void configViews() {
        gone(item, main_title);
        visible(title);
        title.setText("阅读历史");
        ViewToolUtils.setEmptyView(mRecyclerView, R.mipmap.no_read_history, R.string.no_history);
        mRecyclerView.getEmptyView().findViewById(R.id.tv_take).setVisibility(View.GONE);

        initAdapter(ReadHistoryAdapter.class, false, true);
        mRecyclerView.removeAllItemDecoration();
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showReadHistoryList(ReadHistoryList data) {
        if (data != null && data.data.size() > 0) {
            visible(manage);
        }
        if (page == START_PAGE)
            mAdapter.clear();
        mAdapter.addAll(data.getData());
    }

    @Override
    public void addBookResult(String novel_ids) {
        List<ReadHistoryList.DataBean> allData = mAdapter.getAllData();
        for (int i = 0; i < allData.size(); i++) {
            ReadHistoryList.DataBean bean = allData.get(i);
//            ReadHistoryList.DataBean.NovelBean novel = bean.novel;
            if (novel_ids.equals(bean.novel.id)) {
                bean.on_shelf = true;
                mAdapter.notifyItemChanged(i, bean);
                break;
            }
        }
    }

    @Override
    public void delHistoryBookResult(String novel_ids) {
        String[] ids = novel_ids.split(",");
        for (ReadHistoryList.DataBean bean : mAdapter.getAllData()) {
            for (int i = 0; i < ids.length; i++) {
                if (ids[i].equals(bean.log.id)) {
                    mAdapter.remove(bean);
                }
            }
        }
        if (mAdapter.getAllData().size() == 0) gone(manage);
        if (isVisible(llBatchManagement)) {
            //批量管理完成后，隐藏批量管理布局并刷新页面
            goneBatchManagementAndRefreshUI();
        }
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
        if (isVisible(finish)) { //批量管理时，点击选中某项
            ReadHistoryList.DataBean item = mAdapter.getItem(position);
            if (item.isSeleted) {
                item.isSeleted = false;
            } else {
                item.isSeleted = true;
            }
            mAdapter.notifyDataSetChanged();


            List<ReadHistoryList.DataBean> removeList = new ArrayList<>();
            for (ReadHistoryList.DataBean bean : mAdapter.getAllData()) {
                if (bean.isSeleted) removeList.add(bean);
            }
        } else {
            if (!ReadOtherStatusActivity.startActivity(this, mAdapter.getItem(position).novel.is_onsale))
                ReadActivity.startActivity(this, mAdapter.getItem(position).novel.id);
        }

    }

    @Override
    public boolean onItemLongClick(int position) {
        //批量管理时，屏蔽长按事件
        if (isVisible(llBatchManagement)) return false;
        showBatchManagementLayout(position);
        return false;
    }

    @OnClick({R.id.tvDelete, R.id.finish, R.id.manage, R.id.title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvDelete:
                List<ReadHistoryList.DataBean> removeList = new ArrayList<>();
                for (ReadHistoryList.DataBean bean : mAdapter.getAllData()) {
                    if (bean.isSeleted) removeList.add(bean);
                }
                if (removeList.isEmpty()) {
//                    mRecyclerView.showTipViewAndDelayClose(mContext.getString(R.string.has_not_selected_delete_book));
                    ToastUtils.showToast(mContext.getString(R.string.has_not_selected_delete_book));
                } else {
                    showDeleteDialog(removeList);
                }
                break;
            case R.id.finish://完成操作
                goneBatchManagementAndRefreshUI();
                break;
            case R.id.manage:
                if (mAdapter.getAllData() != null)
                    if (isVisible(llBatchManagement)) {//全选操作
                        isSelectAll = !isSelectAll;
                        manage.setText(isSelectAll ? mContext.getString(R.string.cancel_selected_all) : mContext.getString(R.string.selected_all));
                        for (ReadHistoryList.DataBean bean : mAdapter.getAllData()) {
                            bean.isSeleted = isSelectAll;
                        }
                        mAdapter.notifyDataSetChanged();
                    } else {//管理操作
                        showBatchManagementLayout(-1);
                    }
                break;
            case R.id.title:
                finish();
                break;
        }
    }

    private void showDeleteDialog(List<ReadHistoryList.DataBean> removeList) {
        ConfirmDialog.showNotice(mContext, "提示", "确认删除吗？", () -> {
            StringBuilder novelIds = new StringBuilder();
            for (ReadHistoryList.DataBean item : removeList) {
                novelIds.append(item.log.id + ",");
            }
            if (novelIds.length() > 0) {
                mPresenter.delHistory(novelIds.substring(0, novelIds.length() - 1));
            }
        });
    }


    /**
     * 隐藏批量管理布局并刷新页面
     */
    public void goneBatchManagementAndRefreshUI() {
        if (mAdapter == null) return;
        gone(finish, llBatchManagement);
        visible(title);
        manage.setText("管理");
        manage.setTextColor(getResources().getColor(R.color.text_color_2));
        for (ReadHistoryList.DataBean bean : mAdapter.getAllData()) {
            bean.showCheckBox = false;
            bean.isSeleted = false;
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 显示批量管理布局
     *
     * @param position
     */
    private void showBatchManagementLayout(int position) {
        for (ReadHistoryList.DataBean bean : mAdapter.getAllData()) {
            bean.showCheckBox = true;
        }
        if (position > 0) {
            mAdapter.getItem(position).isSeleted = true;
        }
        mAdapter.notifyDataSetChanged();

        visible(finish, llBatchManagement);
        gone(title);
        manage.setText("全选");
        manage.setTextColor(getResources().getColor(R.color.gobal_color));
    }


    @Subscriber
    public void onEventMainThread(AddShlef data) {
        if (data.item != null) {
            mPresenter.addBookShelf(data.item.novel.id);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
