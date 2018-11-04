package com.wxb.wanshu.ui.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.wxb.wanshu.view.recycleview.decoration.DividerDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 主编力荐
 * Created by qiming on 2017/11/21.
 */

public class HomeRecommendFragment extends BaseFragment implements OnRvItemClickListener {
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.rv_horizontal_1)
    RecyclerView rvHorizontal1;
    Unbinder unbinder;

    RecyclerView.LayoutManager layoutManager;
    @BindView(R.id.divide)
    View divide;
    @BindView(R.id.iv_article_pic)
    ImageView ivArticlePic;
    @BindView(R.id.iv_free)
    ImageView ivFree;
    @BindView(R.id.iv_sort)
    ImageView ivSort;
    @BindView(R.id.rl_article_pic)
    RelativeLayout rlArticlePic;
    @BindView(R.id.article_title)
    TextView articleTitle;
    @BindView(R.id.tv_title)
    LinearLayout tvTitle;
    @BindView(R.id.tv_article_intro)
    TextView tvArticleIntro;
    @BindView(R.id.rl_time)
    RelativeLayout rlTime;
    @BindView(R.id.read_times)
    TextView readTimes;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_category)
    TextView tvCategory;
    @BindView(R.id.rl_material_item)
    RelativeLayout rlMaterialItem;
    private int type;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_horizontal01;
    }

    public static HomeRecommendFragment newInstance() {
        HomeRecommendFragment fragment = new HomeRecommendFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("tag", 1);
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

//        HomeData.DataBeanX data = (HomeData.DataBeanX) bundle.getSerializable("data");
//        RVHorizontal1Adapter adapter = new RVHorizontal1Adapter(getActivity(), data.getData(), this);
//        rvHorizontal1.setAdapter(adapter);

//        tvTag.setText(data.getName());
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
