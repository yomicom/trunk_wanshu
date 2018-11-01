package com.wxb.wanshu.ui.activity.ListActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseRVActivity;
import com.wxb.wanshu.bean.AmountRecordList;
import com.wxb.wanshu.bean.ReadHistoryList;
import com.wxb.wanshu.bean.UserOrder;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerAccountComponent;
import com.wxb.wanshu.ui.adapter.easyadpater.AmountRecordAdapter;
import com.wxb.wanshu.ui.adapter.easyadpater.AmountTypeAdapter;
import com.wxb.wanshu.ui.adapter.easyadpater.MyNotificationAdapter;
import com.wxb.wanshu.ui.adapter.easyadpater.OrderListAdapter;
import com.wxb.wanshu.ui.contract.AmountRecordContract;
import com.wxb.wanshu.ui.contract.OrderListContract;
import com.wxb.wanshu.ui.presenter.AmountRecordPresenter;
import com.wxb.wanshu.ui.presenter.OrderListPresenter;
import com.wxb.wanshu.view.dialog.ShareBookDialog;
import com.wxb.wanshu.view.recycleview.EasyRecyclerView;
import com.wxb.wanshu.view.recycleview.decoration.DividerDecoration;
import com.wxb.wanshu.view.recycleview.decoration.SpaceDecoration;

import java.util.List;

import javax.inject.Inject;

import static com.wxb.wanshu.R.id.item;

/**
 * 精选书籍列表页
 */
public class AmountRecordActivity extends BaseRVActivity<AmountRecordList.DataBean> implements AmountRecordContract.View {

    int page;
    PopupWindow pop;
    private List<AmountRecordList.TypeNameBean> type_name;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, AmountRecordActivity.class));
    }

    @Inject
    AmountRecordPresenter mPresenter;

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
        mCommonToolbar.setTitle("书币明细");
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        mPresenter.attachView(this);
        onRefresh();

    }

    @Override
    public void configViews() {
        initAdapter(AmountRecordAdapter.class, false, true);
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showAmountRecordList(AmountRecordList data) {
        mAdapter.addAll(data.getData());
        if (type_name == null || type_name.size() == 0) {
            type_name = data.getType_name();
        }
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.getAmountRecordList(page);
    }

    @Override
    public void onLoadMore() {
        page++;
        mPresenter.getAmountRecordList(page);
    }

    @Override
    public void onItemClick(int position) {
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 10, 0, "").setIcon(R.mipmap.menu_amount_record).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 10) {
            if (type_name != null && type_name.size() > 0) {
                initWindow(findViewById(R.id.base));
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initWindow(View view) {
        View conentView = null;
        pop = new PopupWindow();
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.simple_recycleview, null);
        int h = this.getWindowManager().getDefaultDisplay().getHeight();
        int w = this.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        pop.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//        pop.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        // 刷新状态

        pop.setBackgroundDrawable(new ColorDrawable());
        //设置背景变暗
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.8f;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getWindow().setAttributes(params);
            }
        }, 200);

        //popWindow消失监听方法
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getWindow().setAttributes(params);
                    }
                }, 200);
            }
        });
        RecyclerView rv = conentView.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
//        rv.addItemDecoration(new SpaceDecoration(1));
        rv.setLayoutManager(layoutManager);

        AmountTypeAdapter typeAdapter = new AmountTypeAdapter(mContext, type_name, new OnRvItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Object data) {
                for (AmountRecordList.TypeNameBean select : type_name) {
                    select.isSelected = false;
                }
                type_name.get(position).isSelected = true;

                int type = ((AmountRecordList.TypeNameBean) data).getType();
                page = START_PAGE;
                mAdapter.clear();
                mPresenter.getAmountRecordList(page);
                pop.dismiss();
            }
        });
        rv.setAdapter(typeAdapter);

        pop.showAsDropDown(view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
