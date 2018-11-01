package com.wxb.wanshu.ui.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseFragment;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.view.CustomerBanner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.wxb.wanshu.ui.activity.HomeBookActivity.WOMAN_TYPE;

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
    @BindView(R.id.rl_banner)
    CardView rlBanner;
    private int sex_type;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_banner;
    }

    public void setBannerColor(boolean isWoman){
        if(isWoman){
            relativeLayoutBg.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.bg_woman_banner));
        }else {
            relativeLayoutBg.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.bg_man_banner));
        }
    }

    public static BannerFragment newInstance(HomeData.DataBeanX dataBeanX, int sex_type) {
        BannerFragment fragment = new BannerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", dataBeanX);
        bundle.putSerializable("sex", sex_type);
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
        sex_type = bundle.getInt("sex");
        banner.setData(data);
    }

    @Override
    public void configViews() {
        if(sex_type == WOMAN_TYPE){
            relativeLayoutBg.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.bg_woman_banner));
        }else {
            relativeLayoutBg.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.bg_man_banner));
        }
    }
}
