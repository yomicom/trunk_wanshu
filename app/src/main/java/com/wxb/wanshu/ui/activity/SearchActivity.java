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
import android.widget.TextView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseRVActivity;
import com.wxb.wanshu.bean.BookList;
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class SearchActivity extends BaseRVActivity<BookList.DataBean> implements SearchContract.View{

    int MAN_TYPE = 10;
    int WOMAN_TYPE = 20;
    public static String SEX_TYPE = "sex_type";
    int sex_type;

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
    //    @BindView(R.id.recyclerview_history)
//    RecyclerView recyclerviewHistory;
    @BindView(R.id.recyclerview_hot)
    RecyclerView recyclerviewHot;
    @BindView(R.id.ll_other)
    LinearLayout llOther;
    @BindView(R.id.rootLayout)
    LinearLayout rootLayout;
    @BindView(R.id.tag_group)
    TagGroup mTagGroup;

    //    private NovelCategoryAdapter mHisAdapter;
    private List<String> mHisList = new ArrayList<>();

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, SearchActivity.class));
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
        initSearchHistory();
    }

    @Override
    public void configViews() {

        mPresenter.attachView(this);
        mPresenter.getHotNovelList();

        initAdapter(SelectBooksAdapter.class, false, true);
        mRecyclerView.removeAllItemDecoration();

        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2, LinearLayoutManager.VERTICAL, false);
        recyclerviewHot.setLayoutManager(layoutManager);

        setView();
    }

    private void setView() {
        mTagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
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
                if ("".equals(etSearch.getText().toString()))
                    ivClean.setVisibility(View.GONE);
                else
                    ivClean.setVisibility(View.VISIBLE);
            }
        });
        ivClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSearch.setText("");
                etSearch.requestFocus();

                visible(llOther);
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
            mPresenter.getBookList(sex_type, "", "", page, key);
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
//        mAdapter.clear();
    }

    @Override
    public void onLoadMore() {
        page++;
        mPresenter.getBookList(sex_type,  "", "",page, keyword);
    }

    @Override
    public void onItemClick(int position) {
        BookDetailsActivity.startActivity(mContext, mAdapter.getAllData().get(position).getNovel_id());
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
        HotNovelAdapter hotNovelAdapter = new HotNovelAdapter(mContext, data.getData(), new OnRvItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Object data) {
                BookDetailsActivity.startActivity(mContext, ((HotNovelList.DataBean) data).getNovel_id());
            }
        });
        recyclerviewHot.setAdapter(hotNovelAdapter);
    }

    @OnClick({R.id.tv_cancle_search, R.id.iv_del})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancle_search:
                finish();
                break;
            case R.id.iv_del:
                CacheManager.getInstance().saveSearchHistory(null);
                initSearchHistory();
                break;
        }
    }

    /**
     * 保存搜索记录.不重复，最多保存20条
     *
     * @param query
     */
    private void saveSearchHistory(String query) {
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
        if (size > 20) { // 最多保存20条
            for (int i = size - 1; i >= 20; i--) {
                list.remove(i);
            }
        }
        CacheManager.getInstance().saveSearchHistory(list);
        initSearchHistory();
    }

    private void initSearchHistory() {
        List<String> list = CacheManager.getInstance().getSearchHistory();
//        mHisAdapter.clear();
        if (list != null && list.size() > 0) {
            mTagGroup.setTags(list);
            visible(ivClean, mTagGroup);
        } else {
            gone(ivClean, mTagGroup);
        }
//        mHisAdapter.notifyDataSetChanged();
    }

    private void hideKeyboard(Context context) {
        InputMethodManager im = (InputMethodManager) getSystemService(context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
