package com.wxb.wanshu.ui.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseFragment;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.ui.activity.BookDetailsActivity;
import com.wxb.wanshu.ui.adapter.easyadpater.RVVertical2Adapter;
import com.wxb.wanshu.view.RushBuyCountDownTimerView;
import com.wxb.wanshu.view.recycleview.NoScrollLayoutManager;
import com.wxb.wanshu.view.recycleview.decoration.DividerDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 限时免费
 * Created by qiming on 2017/11/30.
 */

public class VerticalType2Fragment extends BaseFragment implements OnRvItemClickListener {
//    @BindView(R.id.timerView)
//    RushBuyCountDownTimerView timerView;
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.rv_horizontal_1)
    RecyclerView rvHorizontal1;
    Unbinder unbinder;

    private HomeData.DataBeanX data;

    @Override
    public int getLayoutId() {
        return R.layout.vertical_type1;
    }

    public static VerticalType2Fragment newInstance(HomeData.DataBeanX dataBeanX) {
        VerticalType2Fragment fragment = new VerticalType2Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", dataBeanX);
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
        data = (HomeData.DataBeanX) bundle.getSerializable("data");

//        tvTag.setText(data.getName());

//        int time = data.getTtl();
//        visible(timerView);
        gone(tvMore);

//        timerView.addTime(time);
//        timerView.start();
    }

    @Override
    public void configViews() {
        if (data != null) {
            NoScrollLayoutManager layoutManager = new NoScrollLayoutManager(getActivity());
            layoutManager.setScrollEnabled(false);
            rvHorizontal1.setLayoutManager(layoutManager);
            rvHorizontal1.addItemDecoration(new DividerDecoration(ContextCompat.getColor(getActivity(), R.color.white), 30));

            RVVertical2Adapter adapter = new RVVertical2Adapter(getActivity(), data.getData(), this);
            rvHorizontal1.setAdapter(adapter);
        }

        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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

    @Override
    public void onItemClick(View view, int position, Object data) {
        BookDetailsActivity.startActivity(getActivity(), ((HomeData.DataBeanX.DataBean) data).getId(), ((HomeData.DataBeanX.DataBean) data).is_onsale);
    }

}
