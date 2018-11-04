package com.wxb.wanshu.ui.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import com.wxb.wanshu.ui.activity.NovelRankActivity;
import com.wxb.wanshu.ui.adapter.easyadpater.RVGridView1Adapter;
import com.wxb.wanshu.view.recycleview.decoration.DividerDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 人气佳作
 * Created by qiming on 2017/11/30.
 */

public class HomePopularityFragment extends BaseFragment implements OnRvItemClickListener {
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.rv_horizontal_1)
    RecyclerView rvHorizontal1;
    Unbinder unbinder;
    int sex_type = 10;

    private HomeData.DataBeanX data;

    @Override
    public int getLayoutId() {
        return R.layout.vertical_type1;
    }

    public static HomePopularityFragment newInstance() {
        HomePopularityFragment fragment = new HomePopularityFragment();
        Bundle bundle = new Bundle();
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
        sex_type = bundle.getInt("sex");

//        tvTag.setText(data.getName());
    }

    @Override
    public void configViews() {
        if (data != null) {
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
            rvHorizontal1.setLayoutManager(layoutManager);
            rvHorizontal1.addItemDecoration(new DividerDecoration(ContextCompat.getColor(getActivity(), R.color.white), 30));

            RVGridView1Adapter adapter = new RVGridView1Adapter(getActivity(), data.getData(), this);
            rvHorizontal1.setAdapter(adapter);
        }

        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NovelRankActivity.startActivity(mContext,sex_type,data.getName());
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
        BookDetailsActivity.startActivity(getActivity(), ((HomeData.DataBeanX.DataBean) data).getNovel_id());
    }

}
