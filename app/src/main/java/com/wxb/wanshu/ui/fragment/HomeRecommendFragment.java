package com.wxb.wanshu.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import com.wxb.wanshu.base.Constant;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.ui.activity.BookDetailsActivity;
import com.wxb.wanshu.ui.activity.ListActivity.SelectBooksActivity;
import com.wxb.wanshu.ui.adapter.easyadpater.HomeHotAdapter;
import com.wxb.wanshu.ui.adapter.easyadpater.RVHorizontal1Adapter;
import com.wxb.wanshu.utils.ImageUtils;
import com.wxb.wanshu.view.recycleview.decoration.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 主编力荐/火热连载
 * Created by qiming on 2017/11/21.
 */

public class HomeRecommendFragment extends BaseFragment implements OnRvItemClickListener {
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.rv_horizontal_1)
    RecyclerView rvHorizontal1;
    Unbinder unbinder;

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
    @BindView(R.id.tv_category)
    TextView tvCategory;
    @BindView(R.id.rl_material_item)
    RelativeLayout rlMaterialItem;
    @BindView(R.id.author)
    TextView author;
    @BindView(R.id.tv_word_nums)
    TextView tvWordNums;
    @BindView(R.id.tv_more)
    TextView tvMore;
    private int type;
    private HomeData.DataBeanX data;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_horizontal01;
    }

    public static HomeRecommendFragment newInstance(HomeData.DataBeanX dataBeanX, int type) {
        HomeRecommendFragment fragment = new HomeRecommendFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", dataBeanX);
        bundle.putInt("type", type);
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
        type = bundle.getInt("type");

        data = (HomeData.DataBeanX) bundle.getSerializable("data");
        List<HomeData.DataBeanX.DataBean> list0 = data.getData();
        if (list0.size() > 0) {
            setOneBookData(list0.get(0));
            if (list0.size() > 1) {
                List list = new ArrayList();
                for (int i = 1; i < list0.size(); i++) {
                    list.add(list0.get(i));
                }

                if (type == 0) {//主编力荐
//                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//                    rvHorizontal1.addItemDecoration(new DividerDecoration(ContextCompat.getColor(getActivity(), R.color.white), 20));
                    GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 4);
                    rvHorizontal1.setLayoutManager(layoutManager);
                    rvHorizontal1.addItemDecoration(new GridSpacingItemDecoration(4, 20, false));
                    RVHorizontal1Adapter adapter = new RVHorizontal1Adapter(getActivity(), list, this);
                    rvHorizontal1.setAdapter(adapter);
                } else {//火热连载
                    GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
                    rvHorizontal1.setLayoutManager(layoutManager);
                    rvHorizontal1.addItemDecoration(new GridSpacingItemDecoration(2, 50, false));
                    HomeHotAdapter adapter = new HomeHotAdapter(getActivity(), list, this);
                    rvHorizontal1.setAdapter(adapter);
                }
            }
        }

        tvTag.setText(data.getName());

        setMoreView();
    }

    private void setMoreView() {
        String type = data.getType();
        String name = data.getName();
        if (type.equals(Constant.BookType.EDITOR)) {
            tvMore.setVisibility(View.GONE);
        }
        tvMore.setOnClickListener(v ->
                SelectBooksActivity.startActivity(mContext, type, name));
    }

    private void setOneBookData(HomeData.DataBeanX.DataBean item) {
        ImageUtils.displayImage(getActivity(), ivArticlePic, item.getCover(), R.mipmap.defalt_book_cover, R.mipmap.defalt_book_cover);
        articleTitle.setText(item.getName());
        tvArticleIntro.setText(item.getDescription());
        author.setText(item.getAuthor());
        tvWordNums.setText(item.getWord_num() + "字");
        tvCategory.setText(item.getCategory_name());

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
        BookDetailsActivity.startActivity(getActivity(), ((HomeData.DataBeanX.DataBean) data).getId());
    }
}
