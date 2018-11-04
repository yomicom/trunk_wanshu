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

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMMin;
import com.wxb.wanshu.R;
import com.wxb.wanshu.ReaderApplication;
import com.wxb.wanshu.bean.HomeBookData;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.common.MyShareListener;
import com.wxb.wanshu.component.DaggerBookComponent;
import com.wxb.wanshu.ui.contract.HomeContract;
import com.wxb.wanshu.ui.fragment.BannerFragment;
import com.wxb.wanshu.ui.fragment.HomeBookListFragment;
import com.wxb.wanshu.ui.fragment.HomePopularityFragment;
import com.wxb.wanshu.ui.fragment.HomeRecommendFragment;
import com.wxb.wanshu.ui.fragment.VerticalType2Fragment;
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

    int frameId[] = {R.id.fl_banner, R.id.fl_content1, R.id.fl_content2, R.id.fl_content3, R.id.fl_content4,R.id.fl_content5};

    @Inject
    HomeBookPresenter mPresenter;

    String TYPE_CAROUSEL = "carousel";//广告banner
    String TYPE_GRID = "grid";//推荐
    String TYPE_LIST = "list";//小说精选
    String TYPE_RANK = "rank";//排行榜
    String TYPE_FREE = "free";//限时免费
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

    int list_type = 20;

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
//        dialog = CustomDialog.instance(this);
//        dialog.setCancelable(true);
//        dialog.show();

    }

    private void showData(List<HomeData.DataBeanX> data) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //轮播图
        bannerFragment = BannerFragment.newInstance(data.get(0));
        transaction.replace(frameId[0], bannerFragment);

        //主编力荐
//        HomeRecommendFragment recommendFragment = HomeRecommendFragment.newInstance();
//        transaction.replace(frameId[1], recommendFragment);

//        HomeBookListFragment homeBookListFragment = HomeBookListFragment.newInstance(list_type);
//        transaction.replace(frameId[2], homeBookListFragment);

        //人气佳作
//        HomePopularityFragment homePopularityFragment = HomePopularityFragment.newInstance();
//        transaction.replace(frameId[3], homePopularityFragment);
//
//        homeBookListFragment = HomeBookListFragment.newInstance(list_type);
//        transaction.replace(frameId[4], homeBookListFragment);
//
//        homeBookListFragment = HomeBookListFragment.newInstance(list_type);
//        transaction.replace(frameId[5], homeBookListFragment);

        transaction.commit();
    }

    @OnClick({R.id.iv_to_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_to_top://gh_8348fbc38b91 掌读宝
                UMImage image = new UMImage(this, "https://open.weixin.qq.com/cgi-bin/openproxy?url=http%3A%2F%2Fwx.qlogo.cn%2Fmmhead%2FQ3auHgzwzM5RaVqFHiboLO91ItfVH5UG4QKdb6EMqSgfvQNicevjnLhw%2F0");
                UMMin umMin = new UMMin("http://www.qq.com");
                umMin.setThumb(image);
                umMin.setTitle("掌读宝");
                umMin.setDescription("各种激情小说，海量资源，应有尽有！");
//                umMin.setPath("pages/page10007/xxxxxx");
                umMin.setUserName("gh_8348fbc38b91");
                new ShareAction(this)
                        .withMedia(umMin)
                        .setPlatform(SHARE_MEDIA.WEIXIN)
                        .setCallback(new MyShareListener())
                        .share();

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
