package com.wxb.wanshu.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseFragment;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.ui.activity.KindNovelActivity;
import com.wxb.wanshu.ui.activity.NovelRankActivity;
import com.wxb.wanshu.ui.activity.SearchActivity;
import com.wxb.wanshu.view.CustomerBanner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by qiming on 2017/11/21.
 */

public class BannerFragment extends BaseFragment {
    String url;
    @BindView(R.id.vp_banner)
    CustomerBanner banner;
    @BindView(R.id.pointGroup)
    LinearLayout pointGroup;
    @BindView(R.id.rl_bg)
    RelativeLayout relativeLayoutBg;
    Unbinder unbinder;
    private HomeData.DataBeanX data_search;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_banner;
    }

    public static BannerFragment newInstance(HomeData.DataBeanX dataBeanX, HomeData.DataBeanX beanX) {
        BannerFragment fragment = new BannerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", dataBeanX);
        bundle.putSerializable("data_search", beanX);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void attachView() {

    }

    @Override
    public void initDatas() {
        Bundle bundle = getArguments();
        HomeData.DataBeanX data = (HomeData.DataBeanX) bundle.getSerializable("data");
        data_search = (HomeData.DataBeanX) bundle.getSerializable("data_search");
        banner.setData(data);
    }

    @Override
    public void configViews() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.item_rank, R.id.item_best, R.id.item_short, R.id.item_finish, R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_rank:
                NovelRankActivity.startActivity(mContext);
                break;
            case R.id.item_best:
                KindNovelActivity.startActivity(mContext,0);
                break;
            case R.id.item_short:
                KindNovelActivity.startActivity(mContext,1);
                break;
            case R.id.item_finish:
                KindNovelActivity.startActivity(mContext,2);
                break;
            case R.id.search:
                SearchActivity.startActivity(getActivity(),data_search);
                break;
        }
    }
}
