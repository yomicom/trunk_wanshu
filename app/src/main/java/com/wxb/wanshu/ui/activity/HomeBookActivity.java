package com.wxb.wanshu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wxb.wanshu.ImageActivity;
import com.wxb.wanshu.MainActivity;
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
import com.wxb.wanshu.utils.ImageUtils;
import com.wxb.wanshu.view.CustomerBanner;
import com.wxb.wanshu.view.loadding.CustomDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

import static com.wxb.wanshu.ui.fragment.HomeRecommendFragment.HOME_HOT_TYPE;
import static com.wxb.wanshu.ui.fragment.HomeRecommendFragment.HOME_RECOMMEND_TYPE;

/**
 * 书城首页
 */
public class HomeBookActivity extends FragmentActivity implements HomeContract.View {

    int frameId[] = {R.id.fl_content1, R.id.fl_content2, R.id.fl_content3, R.id.fl_content4, R.id.fl_content5};

    @Inject
    HomeBookPresenter mPresenter;

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
    @BindView(R.id.vp_banner)
    BGABanner banner;
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

        int[] colors = {R.color.gobal_color, R.color.light_red};
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
//            bannerFragment = BannerFragment.newInstance(data.get(0), data.get(data.size() - 1));
//            transaction.replace(frameId[0], bannerFragment);
            setBanner(data.get(0));
            //主编力荐
            HomeRecommendFragment recommendFragment = HomeRecommendFragment.newInstance(data.get(1), HOME_RECOMMEND_TYPE);
            transaction.replace(frameId[0], recommendFragment);

            HomeBookListFragment homeBookListFragment = HomeBookListFragment.newInstance(data.get(2));
            transaction.replace(frameId[1], homeBookListFragment);

            //人气佳作
            HomePopularityFragment homePopularityFragment = HomePopularityFragment.newInstance(data.get(3));
            transaction.replace(frameId[2], homePopularityFragment);

            //火热连载
            recommendFragment = HomeRecommendFragment.newInstance(data.get(4), HOME_HOT_TYPE);
            transaction.replace(frameId[3], recommendFragment);

            homeBookListFragment = HomeBookListFragment.newInstance(data.get(5));
            transaction.replace(frameId[4], homeBookListFragment);

            transaction.commit();
        }
    }

    /**
     * 设置Banner
     *
     * @param data
     */
    private void setBanner(HomeData.DataBeanX data) {
        List<HomeData.DataBeanX.DataBean> list = data.getData();
        List<String> imgs = new ArrayList<>();
        List<String> tips = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            imgs.add(list.get(i).cover);
            tips.add("");
        }
        banner.setAdapter((BGABanner.Adapter<ImageView, String>) (banner, itemView, model, position) ->
                ImageUtils.displayImage(mContext, itemView, model));

        banner.setData(imgs, tips);
        banner.setDelegate((BGABanner.Delegate<ImageView, String>) (banner, itemView, model, position) -> {
                    HomeData.DataBeanX.DataBean bean = list.get(position);
                    if ("page".equals(bean.type)) {
                        WebViewActivity.startActivity(mContext, "", bean.url);
                    } else {
                        BookDetailsActivity.startActivity(mContext, bean.getId());
                    }
                }
        );
    }

    Context mContext = this;

    @OnClick({R.id.iv_to_top, R.id.search, R.id.item_rank, R.id.item_best, R.id.item_short, R.id.item_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_to_top://gh_8348fbc38b91 掌读宝
                startActivity(new Intent(this, ImageActivity.class));
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
            case R.id.item_rank:
                NovelRankActivity.startActivity(mContext);
                break;
            case R.id.item_best:
                KindNovelActivity.startActivity(mContext, 0);
                break;
            case R.id.item_short:
                KindNovelActivity.startActivity(mContext, 1);
                break;
            case R.id.item_finish:
                KindNovelActivity.startActivity(mContext, 2);
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
