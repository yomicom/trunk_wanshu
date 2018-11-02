package com.wxb.wanshu.ui.fragment;

import android.os.Bundle;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeNewBookFragment extends BaseFragment implements OnRvItemClickListener {
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.rv_horizontal_1)
    RecyclerView rvHorizontal1;
    @BindView(R.id.rv_vertical)
    RecyclerView rvVertical;
    @BindView(R.id.divide)
    View divide;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.layout_home_new_book;
    }

    public static HomeNewBookFragment newInstance(HomeData.DataBeanX dataBeanX) {
        HomeNewBookFragment fragment = new HomeNewBookFragment();
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
//        data = (HomeData.DataBeanX) bundle.getSerializable("data");

//        tvTag.setText(data.getName());
    }

    @Override
    public void configViews() {

    }

    @Override
    public void onItemClick(View view, int position, Object data) {

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

    @OnClick(R.id.tv_more)
    public void onViewClicked() {
    }
}
