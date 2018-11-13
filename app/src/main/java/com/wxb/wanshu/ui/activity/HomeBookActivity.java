package com.wxb.wanshu.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wxb.wanshu.ImageActivity;
import com.wxb.wanshu.MyApplication;
import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.component.DaggerBookComponent;
import com.wxb.wanshu.ui.contract.HomeContract;
import com.wxb.wanshu.ui.fragment.BannerFragment;
import com.wxb.wanshu.ui.fragment.HomeBookListFragment;
import com.wxb.wanshu.ui.fragment.HomePopularityFragment;
import com.wxb.wanshu.ui.fragment.HomeRecommendFragment;
import com.wxb.wanshu.ui.presenter.HomeBookPresenter;
import com.wxb.wanshu.utils.ImageUtils;
import com.wxb.wanshu.view.AdvanceSwipeRefreshLayout;
import com.wxb.wanshu.view.AlphaTitleScrollView;
import com.wxb.wanshu.view.loadding.CustomDialog;

import java.util.ArrayList;
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
    AlphaTitleScrollView scrollView;
    @BindView(R.id.iv_to_top)
    ImageView ivToTop;
    @BindView(R.id.swipeRefresh)
    AdvanceSwipeRefreshLayout swipeRefresh;
    @BindView(R.id.vp_banner)
    BGABanner banner;
    @BindView(R.id.bg_search)
    RelativeLayout bgSearch;
    private BannerFragment bannerFragment;
    private CustomDialog dialog;

    private HomeData homeData;

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.M)
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
//        swipeRefresh.setRefreshing(true);
        dialog = CustomDialog.instance(this);
        dialog.setCancelable(true);
        dialog.show();

        scrollView.setTitleAndHead(bgSearch, banner);
//        bgSearch.setBackgroundColor(ContextCompat.getColor(mContext, R.color.no_gobal_color));   //初始化搜索栏背景颜色


//        swipeRefresh.setOnPreInterceptTouchEventDelegate(ev -> false);
//        scrollView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                float initTouchY = 0;
//
//                switch (event.getAction()) {
//                    //放下监听
//                    case MotionEvent.ACTION_DOWN:
//                        initTouchY = event.getY();
//                        break;
//                    //手指抬起监听
//                    case MotionEvent.ACTION_UP:
////                        mSlidingPlayView.setLayoutParams(new RelativeLayout.LayoutParams(diaplayWidth, AbViewUtil.dip2px(DetailA.this, imagHeight)));
//
//                        break;
//                    //移动监听
//                    case MotionEvent.ACTION_MOVE:
//                        int upY = v.getScrollY();
//                        float touchY = event.getY();
//                        //对图片放大的处理
//                        if (upY == 0) {  //scrollview在顶部
//                            float deltaY = touchY - initTouchY;// 滑动距离
//                            if (deltaY < 80) {
//                                crossfadeToContentView(bgSearch);
//                            }
//                        }
//                        break;
//                    default:
//                        break;
//                }
//                return false;
//            }
//        });

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
//        crossfadeToProgressView(bgSearch);
        swipeRefresh.setRefreshing(false);
        if (data != null) {
            homeData = data;
            showData(data.getData());
        }
        if (dialog != null)
            dialog.hide();
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

    int mAnimationTime = 1000;

    private void crossfadeToContentView(View progressView) {
        // 加载progressView开始动画逐渐变为0%的不透明度，
        // 动画结束后，设置可见性为GONE（消失）作为一个优化步骤
        // （它将不再参与布局的传递等过程）
        progressView.animate().alpha(0f).setDuration(mAnimationTime)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        progressView.setVisibility(View.GONE);
                    }
                });
    }

    private void crossfadeToProgressView(View progressView) {

        // 设置progressView为0%的不透明度，但是状态为“可见”，
        // 因此在动画过程中是一直可见的（但是为全透明）。
        progressView.setAlpha(0f);
        progressView.setVisibility(View.VISIBLE);

        // 开始动画progressView到100%的不透明度，然后清除所有设置在View上的动画监听器。
        progressView.animate().alpha(1f).setDuration(mAnimationTime)
                .setListener(null);
    }
}
