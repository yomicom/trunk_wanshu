package com.wxb.wanshu.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseFragment;
import com.wxb.wanshu.base.ChapterRead;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.ui.activity.BookDetailsActivity;
import com.wxb.wanshu.ui.adapter.easyadpater.ReadRecommendAdapter;
import com.wxb.wanshu.view.recycleview.decoration.GridSpacingItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 人气佳作
 * Created by qiming on 2017/11/30.
 */

public class ReadRecommendFragment extends BaseFragment implements OnRvItemClickListener {
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.rv_horizontal_1)
    RecyclerView rvHorizontal1;
    Unbinder unbinder;

    private ChapterRead.DataBean data;

    @Override
    public int getLayoutId() {
        return R.layout.frag_home_popular;
    }

    public static ReadRecommendFragment newInstance(ChapterRead.DataBean dataBeanX) {
        ReadRecommendFragment fragment = new ReadRecommendFragment();
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
        data = (ChapterRead.DataBean) bundle.getSerializable("data");


        tvTag.setText("猜你喜欢");
    }

    @Override
    public void configViews() {
        if (data != null) {
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 4, LinearLayoutManager.VERTICAL, false);
            rvHorizontal1.setLayoutManager(layoutManager);
            rvHorizontal1.addItemDecoration(new GridSpacingItemDecoration(4, 30, false));

            ReadRecommendAdapter adapter = new ReadRecommendAdapter(getActivity(), data.getRecommend_list(), this);
            rvHorizontal1.setAdapter(adapter);
        }

        setMoreView();
    }

    private void setMoreView() {
        gone(tvMore);
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
        BookDetailsActivity.startActivity(getActivity(), ((ChapterRead.DataBean.NovelBean) data).id);
    }

}
