package com.wxb.wanshu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.MyApplication;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerBookComponent;
import com.wxb.wanshu.ui.contract.HomeContract;
import com.wxb.wanshu.ui.fragment.HomeBookListFragment;
import com.wxb.wanshu.ui.fragment.HomePopularityFragment;
import com.wxb.wanshu.ui.fragment.HomeRecommendFragment;
import com.wxb.wanshu.ui.presenter.HomeBookPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wxb.wanshu.ui.fragment.HomeRecommendFragment.HOME_RECOMMEND_TYPE;


public class KindNovelActivity extends BaseActivity implements HomeContract.View {

    String key = "";
    @BindView(R.id.fl_content0)
    FrameLayout flContent0;
    @BindView(R.id.fl_content1)
    FrameLayout flContent1;
    @BindView(R.id.fl_content2)
    FrameLayout flContent2;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    int frameId[] = {R.id.fl_content0, R.id.fl_content1, R.id.fl_content2};

    @Inject
    HomeBookPresenter mPresenter;

    public static void startActivity(Context context, int type) {
        Intent intent = new Intent(context, KindNovelActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_kind_novel;
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
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
        int type = getIntent().getIntExtra("type", 0);
        switch (type) {
            case 0://精选
                mCommonToolbar.setTitle("精品馆频道");
                key = "boutique:all,boutique:publishing,finished:all";
                break;
            case 1://短篇
                mCommonToolbar.setTitle("短篇频道");
                key = "short:modernRomance,short:ancientRomance,short:all";
                break;
            case 2://完本
                mCommonToolbar.setTitle("完本频道");
                key = "finished:all,finished:popular,finished:latest";
                break;
        }
    }

    @Override
    public void initDatas() {
        mPresenter.attachView(this);
        mPresenter.getHomeData(key);
        showDialog();
    }

    @Override
    public void configViews() {
    }

    @Override
    public void showHome(HomeData data) {
        if (data != null) {
            showData(data.getData());
        }
    }


    private void showData(List<HomeData.DataBeanX> data) {
        if (data.size() > 2) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            HomeRecommendFragment recommendFragment = HomeRecommendFragment.newInstance(data.get(0), HOME_RECOMMEND_TYPE);
            transaction.replace(frameId[0], recommendFragment);

            HomePopularityFragment homePopularityFragment = HomePopularityFragment.newInstance(data.get(1));
            transaction.replace(frameId[1], homePopularityFragment);

            HomeBookListFragment homeBookListFragment = HomeBookListFragment.newInstance(data.get(2));
            transaction.replace(frameId[2], homeBookListFragment);

            transaction.commit();
        }
    }

    @Override
    public void netError() {
        hideDialog();
    }

    @Override
    public void showError() {
        hideDialog();
    }

    @Override
    public void complete() {
        hideDialog();
    }
}
