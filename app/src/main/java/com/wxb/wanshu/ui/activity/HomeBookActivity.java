package com.wxb.wanshu.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.ReaderApplication;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.component.DaggerBookComponent;
import com.wxb.wanshu.ui.contract.HomeContract;
import com.wxb.wanshu.ui.fragment.BannerFragment;
import com.wxb.wanshu.ui.fragment.HomeBookListFragment;
import com.wxb.wanshu.ui.fragment.HomePopularityFragment;
import com.wxb.wanshu.ui.fragment.HomeRecommendFragment;
import com.wxb.wanshu.ui.presenter.HomeBookPresenter;
import com.wxb.wanshu.view.loadding.CustomDialog;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private BannerFragment bannerFragment;
    private CustomDialog dialog;

    final static int POPULAR_LIST = 0;
    final static int HOT_LIST = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home_book);
        ButterKnife.bind(this);

        DaggerBookComponent.builder()
                .appComponent(ReaderApplication.getsInstance().getAppComponent())
                .build()
                .inject(this);

        mPresenter.attachView(this);
        mPresenter.getHomeData("");
        dialog = CustomDialog.instance(this);
        dialog.setCancelable(true);
        dialog.show();
    }

    private void showData(List<HomeData.DataBeanX> data) {
        if (data.size() > 5) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            //轮播图
            bannerFragment = BannerFragment.newInstance(data.get(0));
            transaction.replace(frameId[0], bannerFragment);

            //主编力荐
            HomeRecommendFragment recommendFragment = HomeRecommendFragment.newInstance(data.get(1), POPULAR_LIST);
            transaction.replace(frameId[1], recommendFragment);

            HomeBookListFragment homeBookListFragment = HomeBookListFragment.newInstance(data.get(2));
            transaction.replace(frameId[2], homeBookListFragment);

            //人气佳作
            HomePopularityFragment homePopularityFragment = HomePopularityFragment.newInstance(data.get(3));
            transaction.replace(frameId[3], homePopularityFragment);

            //火热连载
            recommendFragment = HomeRecommendFragment.newInstance(data.get(4), HOT_LIST);
            transaction.replace(frameId[4], recommendFragment);

            homeBookListFragment = HomeBookListFragment.newInstance(data.get(5));
            transaction.replace(frameId[5], homeBookListFragment);

            transaction.commit();
        }
    }

    @OnClick({R.id.iv_to_top})
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
        }
    }

    @Override
    public void showHome(HomeData data) {
        if (data != null) {
            showData(data.getData());
        }
    }


    @Override
    public void netError() {
        if (dialog != null)
            dialog.hide();
    }

    @Override
    public void showError() {
        if (dialog != null)
            dialog.hide();
    }

    @Override
    public void complete() {
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
