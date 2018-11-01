package com.wxb.wanshu.ui.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseFragment;
import com.wxb.wanshu.bean.BookDetails;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.ui.activity.BookDetailsActivity;
import com.wxb.wanshu.ui.adapter.easyadpater.RVHorizontal1Adapter;
import com.wxb.wanshu.ui.adapter.easyadpater.RVRecommandAdapter;
import com.wxb.wanshu.utils.ViewToolUtils;
import com.wxb.wanshu.view.recycleview.decoration.DividerDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 主编推荐
 * Created by qiming on 2017/11/21.
 */

public class HorizontalType1Fragment extends BaseFragment implements OnRvItemClickListener {
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.rv_horizontal_1)
    RecyclerView rvHorizontal1;
    Unbinder unbinder;

    RecyclerView.LayoutManager layoutManager;
    @BindView(R.id.divide)
    View divide;
    private int type;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_horizontal01;
    }

    public static HorizontalType1Fragment newInstance(HomeData.DataBeanX dataBeanX) {
        HorizontalType1Fragment fragment = new HorizontalType1Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", dataBeanX);
        bundle.putInt("tag", 1);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static HorizontalType1Fragment newInstance2(BookDetails.DataBean dataBeanX) {
        HorizontalType1Fragment fragment = new HorizontalType1Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", dataBeanX);
        bundle.putInt("tag", 2);
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
    }

    @Override
    public void configViews() {
        Bundle bundle = getArguments();
        type = bundle.getInt("tag");
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvHorizontal1.addItemDecoration(new DividerDecoration(ContextCompat.getColor(getActivity(), R.color.white), 20));
        rvHorizontal1.setLayoutManager(layoutManager);

        if (type == 1) {//首页主编推荐
            HomeData.DataBeanX data = (HomeData.DataBeanX) bundle.getSerializable("data");
            RVHorizontal1Adapter adapter = new RVHorizontal1Adapter(getActivity(), data.getData(), this);
            rvHorizontal1.setAdapter(adapter);

            tvTag.setText(data.getName());
        } else {//书籍详情猜你喜欢
            gone(divide);
            tvTag.setText("猜你喜欢");

            BookDetails.DataBean data = (BookDetails.DataBean) bundle.getSerializable("data");
            RVRecommandAdapter adapter = new RVRecommandAdapter(getActivity(), data.getRecommend(), this);
            rvHorizontal1.setAdapter(adapter);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
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
        if(type == 1) {
            BookDetailsActivity.startActivity(getActivity(), ((HomeData.DataBeanX.DataBean) data).getNovel_id());
        }else {
            BookDetailsActivity.startActivity(getActivity(), ((BookDetails.DataBean.RecommendBean) data).getNovel_id());
        }
    }
}
