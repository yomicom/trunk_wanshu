package com.wxb.wanshu.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMMin;
import com.wxb.wanshu.R;
import com.wxb.wanshu.ReaderApplication;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.common.MyShareListener;
import com.wxb.wanshu.component.DaggerBookComponent;
import com.wxb.wanshu.ui.contract.HomeContract;
import com.wxb.wanshu.ui.fragment.BannerFragment;
import com.wxb.wanshu.ui.fragment.GridViewType1Fragment;
import com.wxb.wanshu.ui.fragment.HorizontalType1Fragment;
import com.wxb.wanshu.ui.fragment.VerticalType1Fragment;
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

    int frameId[] = {R.id.fl_banner, R.id.fl_content1, R.id.fl_content2, R.id.fl_content3, R.id.fl_content4};
    @BindView(R.id.tv_selc_woman)
    TextView tvSelcWoman;
    @BindView(R.id.tv_selc_man)
    TextView tvSelcMan;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
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

    @BindView(R.id.v_selc_woman)
    View vSelcWoman;
    @BindView(R.id.v_selc_man)
    View vSelcMan;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.iv_to_top)
    ImageView ivToTop;

    @Inject
    HomeBookPresenter mPresenter;

    String TYPE_CAROUSEL = "carousel";//广告banner
    String TYPE_GRID = "grid";//推荐
    String TYPE_LIST = "list";//小说精选
    String TYPE_RANK = "rank";//排行榜
    String TYPE_FREE = "free";//限时免费
    private FragmentTransaction transaction;
    private BannerFragment bannerFragment;
    private CustomDialog dialog;

    public static int MAN_TYPE = 10;
    public static int WOMAN_TYPE = 20;
    int sex_type = 20;

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
        mPresenter.getHomeData(sex_type);

        dialog = CustomDialog.instance(this);
        dialog.setCancelable(true);
        dialog.show();
    }

    @OnClick({R.id.ll_woman, R.id.ll_man, R.id.iv_search, R.id.iv_to_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_woman:
                if (sex_type != WOMAN_TYPE) {
                    sex_type = WOMAN_TYPE;
                    mPresenter.getHomeData(sex_type);
                    tvSelcWoman.setTextColor(ContextCompat.getColor(this, R.color.gobal_color));
                    vSelcWoman.setVisibility(View.VISIBLE);

                    tvSelcMan.setTextColor(ContextCompat.getColor(this, R.color.text_color_1));
                    vSelcMan.setVisibility(View.INVISIBLE);
                    if (bannerFragment != null) {
                        bannerFragment.setBannerColor(true);
                    }
                }
                break;
            case R.id.ll_man:
                if (sex_type != MAN_TYPE) {
                    sex_type = MAN_TYPE;
                    mPresenter.getHomeData(sex_type);
                    tvSelcMan.setTextColor(ContextCompat.getColor(this, R.color.gobal_color));
                    vSelcMan.setVisibility(View.VISIBLE);

                    tvSelcWoman.setTextColor(ContextCompat.getColor(this, R.color.text_color_1));
                    vSelcWoman.setVisibility(View.INVISIBLE);
                    if (bannerFragment != null) {
                        bannerFragment.setBannerColor(false);
                    }
                }
                break;
            case R.id.iv_search:
                SearchActivity.startActivity(this);
                break;
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
            if (dialog != null)
                dialog.hide();
            FragmentManager fragmentManager = getSupportFragmentManager();
            transaction = fragmentManager.beginTransaction();

            List<HomeData.DataBeanX> list = data.getData();
            for (int i = 0; i < list.size(); i++) {
                HomeData.DataBeanX dataBeanX = list.get(i);
                String type = dataBeanX.getType();

                if (TYPE_CAROUSEL.equals(type)) {
                    bannerFragment = BannerFragment.newInstance(dataBeanX, sex_type);
                    transaction.replace(frameId[0], bannerFragment);
                }
                if (TYPE_GRID.equals(type)) {
                    HorizontalType1Fragment type1Fragment = HorizontalType1Fragment.newInstance(dataBeanX);
                    transaction.replace(frameId[1], type1Fragment);
                }
                if (TYPE_LIST.equals(type)) {
                    VerticalType1Fragment verticalType1Fragment = VerticalType1Fragment.newInstance(dataBeanX, sex_type);
                    transaction.replace(frameId[2], verticalType1Fragment);
                }
                if (TYPE_RANK.equals(type)) {
                    GridViewType1Fragment gridViewType1Fragment = GridViewType1Fragment.newInstance(dataBeanX,sex_type);
                    transaction.replace(frameId[3], gridViewType1Fragment);
                }
                if (TYPE_FREE.equals(type)) {
                    VerticalType2Fragment verticalType2Fragment = VerticalType2Fragment.newInstance(dataBeanX);
                    transaction.replace(frameId[4], verticalType2Fragment);
                }
            }

            transaction.commit();
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
