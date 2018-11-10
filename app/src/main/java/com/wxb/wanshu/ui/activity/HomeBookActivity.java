package com.wxb.wanshu.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.MyApplication;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.component.DaggerBookComponent;
import com.wxb.wanshu.ui.contract.HomeContract;
import com.wxb.wanshu.ui.fragment.BannerFragment;
import com.wxb.wanshu.ui.fragment.HomeBookListFragment;
import com.wxb.wanshu.ui.fragment.HomePopularityFragment;
import com.wxb.wanshu.ui.fragment.HomeRecommendFragment;
import com.wxb.wanshu.ui.presenter.HomeBookPresenter;
import com.wxb.wanshu.view.loadding.CustomDialog;
import com.wxb.wanshu.view.recycleview.swipe.OnRefreshListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.wxb.wanshu.ui.fragment.HomeRecommendFragment.HOME_HOT_TYPE;
import static com.wxb.wanshu.ui.fragment.HomeRecommendFragment.HOME_RECOMMEND_TYPE;

/**
 * 书城首页
 */
public class HomeBookActivity extends FragmentActivity implements HomeContract.View {

    int frameId[] = {R.id.fl_banner, R.id.fl_content1, R.id.fl_content2, R.id.fl_content3, R.id.fl_content4, R.id.fl_content5};

    @Inject
    HomeBookPresenter mPresenter;

    @BindView(R.id.ll_to_top)
    LinearLayout llToTop;
    @BindView(R.id.fl_banner)
    FrameLayout flBanner;
    @BindView(R.id.fl_content1)
    FrameLayout flContent1;
    @BindView(R.id.fl_content2)
    FrameLayout flContent2;
    @BindView(R.id.fl_content3)
    FrameLayout flContent3;
    @BindView(R.id.fl_content4)
    FrameLayout flContent4;
    @BindView(R.id.fl_content5)
    FrameLayout flContent5;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.iv_to_top)
    ImageView ivToTop;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    private BannerFragment bannerFragment;
    private CustomDialog dialog;

    private HomeData homeData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home_book);
        ButterKnife.bind(this);

        DaggerBookComponent.builder()
                .appComponent(MyApplication.getsInstance().getAppComponent())
                .build()
                .inject(this);

        int[] colors = {R.color.gobal_color,R.color.light_red};
        swipeRefresh.setColorSchemeResources(colors);
//        swipeRefresh.setProgressBackgroundColorSchemeResource(R.color.common_bg);
        swipeRefresh.setOnRefreshListener(() -> {
            if (homeData != null) {
                mPresenter.getHomeData("");
            }
        });

        mPresenter.attachView(this);
        mPresenter.getHomeData("");
        swipeRefresh.setRefreshing(true);
//        dialog = CustomDialog.instance(this);
//        dialog.setCancelable(true);
//        dialog.show();
    }

    private void showData(List<HomeData.DataBeanX> data) {
        if (data.size() > 5) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            //轮播图
            bannerFragment = BannerFragment.newInstance(data.get(0), data.get(data.size() - 1));
            transaction.replace(frameId[0], bannerFragment);

            //主编力荐
            HomeRecommendFragment recommendFragment = HomeRecommendFragment.newInstance(data.get(1), HOME_RECOMMEND_TYPE);
            transaction.replace(frameId[1], recommendFragment);

            HomeBookListFragment homeBookListFragment = HomeBookListFragment.newInstance(data.get(2));
            transaction.replace(frameId[2], homeBookListFragment);

            //人气佳作
            HomePopularityFragment homePopularityFragment = HomePopularityFragment.newInstance(data.get(3));
            transaction.replace(frameId[3], homePopularityFragment);

            //火热连载
            recommendFragment = HomeRecommendFragment.newInstance(data.get(4), HOME_HOT_TYPE);
            transaction.replace(frameId[4], recommendFragment);

            homeBookListFragment = HomeBookListFragment.newInstance(data.get(5));
            transaction.replace(frameId[5], homeBookListFragment);

            transaction.commit();
        }
    }

    @OnClick({R.id.iv_to_top, R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_to_top://gh_8348fbc38b91 掌读宝

                mPresenter.getHomeData("");
//                scrollView.post(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        scrollView.fullScroll(ScrollView.FOCUS_UP);
//                    }
//                });
                break;
            case R.id.search:
                if (homeData != null) {
                    List<HomeData.DataBeanX> data = homeData.getData();
                    SearchActivity.startActivity(this, data.get(data.size() - 1));
                }
                break;
        }
    }

    @Override
    public void showHome(HomeData data) {
        swipeRefresh.setRefreshing(false);
        if (data != null) {
            homeData = data;
            showData(data.getData());
        }
    }


    @Override
    public void netError() {
        swipeRefresh.setRefreshing(false);
        if (dialog != null)
            dialog.hide();
    }

    @Override
    public void showError() {
        swipeRefresh.setRefreshing(false);
        if (dialog != null)
            dialog.hide();
    }

    @Override
    public void complete() {
        swipeRefresh.setRefreshing(false);
        if (dialog != null)
            dialog.hide();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
