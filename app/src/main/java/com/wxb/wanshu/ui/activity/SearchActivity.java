package com.wxb.wanshu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anarchy.classify.util.L;
import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseRVActivity;
import com.wxb.wanshu.bean.BookList;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.bean.HotNovelList;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerBookComponent;
import com.wxb.wanshu.manager.CacheManager;
import com.wxb.wanshu.ui.adapter.easyadpater.HotNovelAdapter;
import com.wxb.wanshu.ui.adapter.easyadpater.SelectBooksAdapter;
import com.wxb.wanshu.ui.contract.SearchContract;
import com.wxb.wanshu.ui.presenter.SearchPresenter;
import com.wxb.wanshu.view.TagGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class SearchActivity extends BaseRVActivity<BookList.DataBean> implements SearchContract.View {

    String keyword = "";
    @BindView(R.id.et_article_search)
    EditText etSearch;
    @BindView(R.id.iv_clean_search)
    ImageView ivClean;
    @BindView(R.id.tv_cancle_search)
    TextView tvCancleSearch;
    @BindView(R.id.rl_search_cancle)
    LinearLayout rlSearchCancle;
    @BindView(R.id.iv_del)
    ImageView ivDel;
    @BindView(R.id.hot_group)
    TagGroup HotGroup;
    @BindView(R.id.ll_other)
    LinearLayout llOther;
    @BindView(R.id.rootLayout)
    LinearLayout rootLayout;
    @BindView(R.id.history)
    RelativeLayout history;
    @BindView(R.id.tag_group)
    TagGroup RecordGroup;


    public static void startActivity(Context context, HomeData.DataBeanX beanX) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra("data", beanX);
        context.startActivity(intent);
    }

    @Inject
    SearchPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerBookComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initToolBar() {
    }

    @Override
    public void initDatas() {
        //设置热门标签
        initAdapter(SelectBooksAdapter.class, false, true);
        mRecyclerView.removeAllItemDecoration();

        HomeData.DataBeanX data = (HomeData.DataBeanX) getIntent().getSerializableExtra("data");
        List<String> list = new ArrayList<>();
        List<HomeData.DataBeanX.DataBean> HotBookData = data.getData();
        for (int i = 0; i < HotBookData.size(); i++) {
            list.add(HotBookData.get(i).getName());
        }
        HotGroup.setTags(list);
        HotGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                for (int i = 0; i < HotBookData.size(); i++) {
                    if (tag.equals(HotBookData.get(i).getName())) {
                        BookDetailsActivity.startActivity(mContext, HotBookData.get(i).getId());
                        break;
                    }
                }
            }
        });

        initSearchHistory();
    }

    @Override
    public void configViews() {

        mPresenter.attachView(this);

        setView();
    }

    private void setView() {
        RecordGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                if (tag.length() > 10 && tag.endsWith("...")) {
                    tag = tag.replace("...", "");
                }
                etSearch.setText(tag);
                search(tag);
            }
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if ("".equals(etSearch.getText().toString())) {
                    ivClean.setVisibility(View.GONE);
                } else {
                    ivClean.setVisibility(View.VISIBLE);
                }
            }
        });
        ivClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSearch.setText("");
                etSearch.requestFocus();

                visible(llOther, history);
                gone(mRecyclerView);
            }
        });
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search(etSearch.getText().toString().trim());
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 查询小说
     *
     * @param key
     */
    private void search(String key) {
        if (!TextUtils.isEmpty(key)) {
            keyword = key;
            visible(mRecyclerView);
            gone(llOther);

            page = START_PAGE;
            mPresenter.getBookList(key, page);
            hideKeyboard(mContext);
            saveSearchHistory(key);
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showBookList(BookList data) {
        if (page == 0) {
            mAdapter.clear();
        }
        mAdapter.addAll(data.getData());
    }

    @Override
    public void onLoadMore() {
        page++;
        mPresenter.getBookList(keyword, page);
    }

    @Override
    public void onItemClick(int position) {
        BookDetailsActivity.startActivity(mContext, mAdapter.getAllData().get(position).getId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showHotNovelList(HotNovelList data) {
    }

    @OnClick({R.id.tv_cancle_search, R.id.iv_del})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancle_search:
                finish();
//                search(etSearch.getText().toString().trim());
                break;
            case R.id.iv_del:
                CacheManager.getInstance().saveSearchHistory(null);
                initSearchHistory();
                gone(history);
                break;
        }
    }

    /**
     * 保存搜索记录.不重复，最多保存10条
     *
     * @param query
     */
    private void saveSearchHistory(String query) {
        if (query.length() > 10) {//搜索关键词超过10个字符省略号
            query = query.substring(0, 10) + "...";
        }
        List<String> list = CacheManager.getInstance().getSearchHistory();
        if (list == null) {
            list = new ArrayList<>();
            list.add(query);
        } else {
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String item = iterator.next();
                if (TextUtils.equals(query, item)) {
                    iterator.remove();
                }
            }
            list.add(0, query);
        }
        int size = list.size();
        if (size > 10) { // 最多保存20条
            for (int i = size - 1; i >= 10; i--) {
                list.remove(i);
            }
        }
        CacheManager.getInstance().saveSearchHistory(list);
        initSearchHistory();
    }

    private void initSearchHistory() {
        List<String> list = CacheManager.getInstance().getSearchHistory();
        if (list != null && list.size() > 0) {
            RecordGroup.setTags(list);
            visible(history, RecordGroup);
        } else {
            gone(history, RecordGroup);
        }
    }

    private void hideKeyboard(Context context) {
        InputMethodManager im = (InputMethodManager) getSystemService(context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
