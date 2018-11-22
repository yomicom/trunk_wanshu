package com.wxb.wanshu.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wxb.wanshu.ImageActivity;
import com.wxb.wanshu.MyApplication;
import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.base.Constant;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerBookComponent;
import com.wxb.wanshu.ui.contract.HomeContract;
import com.wxb.wanshu.ui.fragment.BannerFragment;
import com.wxb.wanshu.ui.fragment.HomeBookListFragment;
import com.wxb.wanshu.ui.fragment.HomePopularityFragment;
import com.wxb.wanshu.ui.fragment.HomeRecommendFragment;
import com.wxb.wanshu.ui.presenter.HomeBookPresenter;
import com.wxb.wanshu.utils.ImageUtils;
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
public class HomeBookActivity extends BaseActivity implements HomeContract.View {

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
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.iv_book)
    ImageView iv_book;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.vp_banner)
    BGABanner banner;
    @BindView(R.id.bg_search)
    RelativeLayout bgSearch;
    @BindView(R.id.et_article_search)
    TextView etArticleSearch;
    @BindView(R.id.rank)
    LinearLayout rank;
    @BindView(R.id.search)
    LinearLayout search;
    @BindView(R.id.divide)
    View divide;
    @BindView(R.id.rl_empty_view)
    RelativeLayout netErrorView;
    private BannerFragment bannerFragment;
    private CustomDialog dialog;

    private HomeData homeData;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setCanSlide(false);
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home_book;
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
        mPresenter.attachView(this);
    }

    @Override
    public void configViews() {
        int[] colors = {R.color.gobal_color, R.color.light_red};
        swipeRefresh.setColorSchemeResources(colors);
        swipeRefresh.setOnRefreshListener(() -> {
            mPresenter.getHomeData("");
        });

        swipeRefresh.setRefreshing(true);
        mPresenter.getHomeData("");

        scrollView.setTitleAndHead(bgSearch, search, etArticleSearch, iv_search, banner);
    }


    @Override
    public void showHome(HomeData data) {
        swipeRefresh.setRefreshing(false);
        if (data != null) {
            showData(data);
        }
        if (dialog != null)
            dialog.hide();
    }


    private void showData(HomeData homeData) {
        visible(swipeRefresh, bgSearch, divide, rank);
        gone(netErrorView);

        this.homeData = homeData;
        List<HomeData.DataBeanX> data = homeData.getData();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (int i = 0; i < data.size(); i++) {
            HomeData.DataBeanX item = data.get(i);
            switch (item.type) {
                case Constant.BookType.Banner://轮播图
                    setBanner(item);
                    break;

                case Constant.BookType.EDITOR://主编力荐
                    if (item.data.size() > 0) {
                        flContent1.setVisibility(View.VISIBLE);
                        HomeRecommendFragment recommendFragment = HomeRecommendFragment.newInstance(item, HOME_RECOMMEND_TYPE);
                        transaction.replace(frameId[0], recommendFragment);
                    }
                    break;

                case Constant.BookType.FRESH:
                    if (item.data.size() > 0) {
                        flContent2.setVisibility(View.VISIBLE);
                        HomeBookListFragment homeBookListFragment = HomeBookListFragment.newInstance(item);
                        transaction.replace(frameId[1], homeBookListFragment);
                    }
                    break;

                case Constant.BookType.POPULAR://人气佳作
                    if (item.data.size() > 0) {
                        flContent3.setVisibility(View.VISIBLE);
                        HomePopularityFragment homePopularityFragment = HomePopularityFragment.newInstance(item);
                        transaction.replace(frameId[2], homePopularityFragment);
                    }
                    break;

                case Constant.BookType.MID_BANNER://设置首页图片
                    if (item.data.size() > 0) {
                        HomeData.DataBeanX.DataBean midData = item.data.get(0);
                        iv_book.setVisibility(View.VISIBLE);
                        ImageUtils.displayImage(mContext, iv_book, midData.cover, R.mipmap.mid_image, R.mipmap.mid_image);

                        iv_book.setOnClickListener(v -> {
                            if ("page".equals(midData.type)) {
                                WebViewActivity.startActivity(mContext, "详情", midData.url);
                            } else {
                                BookDetailsActivity.startActivity(mContext, midData.novel_id, 1);
                            }
                        });
                    }
                    break;

                case Constant.BookType.PUBLISHING://火热连载
                    if (item.data.size() > 0) {
                        flContent4.setVisibility(View.VISIBLE);
                        HomeRecommendFragment recommendFragment = HomeRecommendFragment.newInstance(item, HOME_HOT_TYPE);
                        transaction.replace(frameId[3], recommendFragment);
                    }
                    break;

                case Constant.BookType.FINISH:
                    if (item.data.size() > 0) {
                        flContent5.setVisibility(View.VISIBLE);
                        HomeBookListFragment homeBookListFragment = HomeBookListFragment.newInstance(item);
                        transaction.replace(frameId[4], homeBookListFragment);
                    }
                    break;

                case Constant.BookType.SEARCH_HOT://热门推荐
                    if (item.getData().size() > 0) {
                        if (!"".equals(item.getData().get(0).name)) {
                            etArticleSearch.setHint(item.getData().get(0).name);
                        } else {
                            etArticleSearch.setHint(getResources().getString(R.string.hint_search));
                        }
                    }
                    break;
            }
        }

        transaction.commit();
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
                ImageUtils.displayImage(mContext, itemView, model, R.mipmap.default_banner, R.mipmap.default_banner));

        banner.setData(imgs, tips);
        banner.setDelegate((BGABanner.Delegate<ImageView, String>) (banner, itemView, model, position) -> {
                    HomeData.DataBeanX.DataBean bean = list.get(position);
                    if ("page".equals(bean.type)) {
                        WebViewActivity.startActivity(mContext, "详情", bean.url);
                    } else {
                        BookDetailsActivity.startActivity(mContext, bean.novel_id, 1);
                    }
                }
        );
    }

    Context mContext = this;

    @OnClick({R.id.iv_to_top, R.id.search, R.id.item_rank, R.id.item_best, R.id.item_short, R.id.item_finish,R.id.get})
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
                    SearchActivity.startActivity(this, getItemData(data, Constant.BookType.SEARCH_HOT));
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
            case R.id.get:
                mPresenter.getHomeData("");
                break;
        }
    }

    //获取当前的Item数据
    private HomeData.DataBeanX getItemData(List<HomeData.DataBeanX> data, String type) {
        HomeData.DataBeanX bean = new HomeData.DataBeanX();
        for (int i = 0; i < data.size(); i++) {
            HomeData.DataBeanX dataBeanX = data.get(i);
            if (dataBeanX.type.equals(type)) {
                return dataBeanX;
            }
        }
        return bean;
    }

    @Override
    public void netError() {
        gone(swipeRefresh);
        visible(netErrorView);
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

    int mAnimationTime = 1000;

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

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
