package com.wxb.wanshu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wxb.wanshu.MyApplication;
import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.BookDetails;
import com.wxb.wanshu.bean.BookRewardData;
import com.wxb.wanshu.bean.RewardType;
import com.wxb.wanshu.bean.SimpleEventBus;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerBookComponent;
import com.wxb.wanshu.ui.activity.ListActivity.MenuActivity;
import com.wxb.wanshu.ui.adapter.easyadpater.BookRewardAdapter;
import com.wxb.wanshu.ui.contract.BookDetailsContract;
import com.wxb.wanshu.ui.fragment.HomeRecommendFragment;
import com.wxb.wanshu.ui.presenter.BookDetailsPresenter;
import com.wxb.wanshu.utils.ImageUtils;
import com.wxb.wanshu.utils.SharedPreferencesUtil;
import com.wxb.wanshu.utils.ToastUtils;
import com.wxb.wanshu.utils.ViewToolUtils;
import com.wxb.wanshu.view.dialog.RewardGiftDialog;
import com.wxb.wanshu.view.dialog.ShareBookDialog;
import com.wxb.wanshu.view.recycleview.NoScrollLayoutManager;
import com.wxb.wanshu.view.recycleview.decoration.DividerDecoration;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 书籍详情页
 */
public class BookDetailsActivity extends BaseActivity implements BookDetailsContract.View, OnRvItemClickListener {

    public static String INTENT_BOOK_ID = "bookId";
    @BindView(R.id.iv_book)
    ImageView ivBook;
    @BindView(R.id.iv_is_free)
    ImageView ivIsFree;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_read)
    TextView tvRead;
    @BindView(R.id.author)
    TextView author;
    @BindView(R.id.rl_image)
    RelativeLayout rlImage;
    @BindView(R.id.fl_content_recommand)
    FrameLayout flContentRecommand;
    @BindView(R.id.tv_dashang)
    TextView tvDashang;
    @BindView(R.id.tv_dashang_record)
    TextView tvDashangRecord;
    @BindView(R.id.tv_to_dashang)
    TextView tvToDashang;
    @BindView(R.id.rv_dashang)
    RecyclerView rvDashang;
    @BindView(R.id.tv_load_more)
    TextView tvLoadMore;
    @BindView(R.id.tv_add_book)
    TextView tvAddBook;
    @BindView(R.id.tv_read_book)
    TextView tvReadBook;
    @BindView(R.id.tv_account_intro)
    TextView tvAccountIntro;
    @BindView(R.id.iv_show_text)
    ImageView ivShowText;
    @BindView(R.id.description_layout)
    View descriptionLayout;
    @BindView(R.id.ll_content)
    LinearLayout llContent;

    String novel_id;
    @BindView(R.id.last_chapter)
    TextView lastChapter;
    @BindView(R.id.last_chapter_time)
    TextView lastChapterTime;
    @BindView(R.id.book_chapter_num)
    TextView bookChapterNum;
    private BookDetails.DataBean bookDetails;
    private BookRewardAdapter adapter;
    private int rewardPage = 1;
    private ShareBookDialog shareBookDialog;

    public static void startActivity(Context context, String novel_id) {
        context.startActivity(new Intent(context, BookDetailsActivity.class)
                .putExtra(INTENT_BOOK_ID, "2"));
    }

    @Inject
    BookDetailsPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_book_details;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerBookComponent.builder().appComponent(MyApplication.getsInstance().getAppComponent()).build().inject(this);
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle(R.string.details_books);
    }

    @Override
    public void initDatas() {

        EventBus.getDefault().register(this);
        novel_id = getIntent().getStringExtra(INTENT_BOOK_ID);
        int client_id = SharedPreferencesUtil.getInstance().getInt(SharedPreferencesUtil.CLIENT_ID, 1);

        mPresenter.attachView(this);
        showDialog();
        mPresenter.getBookDetails(novel_id, client_id, 0);
//        mPresenter.getBookReward(novel_id, rewardPage);
    }

    @Override
    public void configViews() {

        NoScrollLayoutManager layoutManager = new NoScrollLayoutManager(this);
        layoutManager.setScrollEnabled(false);
        rvDashang.setLayoutManager(layoutManager);
        rvDashang.addItemDecoration(new DividerDecoration(ContextCompat.getColor(this, R.color.white), 20));

        adapter = new BookRewardAdapter(this, new ArrayList<BookRewardData.DataBean>(), this);
        rvDashang.setAdapter(adapter);
    }

    @Override
    public void showError() {
        hideDialog();
    }

    @Override
    public void complete() {

    }

    @Override
    public void showBookDetails(BookDetails.DataBean data) {
        hideDialog();
        visible(llContent);

        bookDetails = data;
        showBookData(data);
        showRecommandData(data);

    }

    private void showBookData(BookDetails.DataBean data) {
        ImageUtils.displayImage(this, ivBook, data.getCover(), R.mipmap.defalt_book_cover, R.mipmap.defalt_book_cover);
        tvTitle.setText(data.getName());
        author.setText(data.getAuthor());
        tvRead.setText(data.getCategory_name() + " • " + ("0".equals(data.getComplete_status()) ? "连载" : "完结") + " • " + data.getWord_num() + "字");
        if (bookDetails.on_shelf) {
            tvAddBook.setText("已加入书架");
            ViewToolUtils.getResourceColor(mContext, tvAddBook, R.color.text_color_2);
        } else {
            tvAddBook.setText("加入书架");
            ViewToolUtils.getResourceColor(mContext, tvAddBook, R.color.text_color_1);
        }

        lastChapter.setText("最新：" + data.getLatest_chapter().name);
        lastChapterTime.setText("更新时间：" + data.latest_chapter.publish_time);
        bookChapterNum.setText("共" + data.getChapter_num() + "章");
        ViewToolUtils.setShowMoreContent(4, data.getDescription(), tvAccountIntro, ivShowText, descriptionLayout);
    }

    /**
     * 显示推荐书籍
     *
     * @param data
     */
    private void showRecommandData(BookDetails.DataBean data) {
        if (data.getRecommend().size() > 0) {
            visible(flContentRecommand);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            HomeRecommendFragment type1Fragment = HomeRecommendFragment.newInstance(data, 2);
            transaction.replace(R.id.fl_content_recommand, type1Fragment);
            transaction.commit();
        } else {
            gone(flContentRecommand);
        }
    }

    @Override
    public void showBookReward(BookRewardData data) {
        if (data != null) {
            if (rewardPage == 1) {
                adapter.clear();
            }
            adapter.addAll(data.getData());
        }
    }

    @Override
    public void showRewardType(RewardType data) {
        hideDialog();
        if (data != null && bookDetails != null) {
            RewardGiftDialog.shareView(this, R.id.ll_content, data, bookDetails.getName(), novel_id);
        }
    }

    @Override
    public void addBookResult(Base result) {
        if (result.getErrcode() == 0) {
            ToastUtils.showLongToast("添加书架成功");
            tvAddBook.setText("已加入书架");
            ViewToolUtils.getResourceColor(mContext, tvAddBook, R.color.text_color_2);
            bookDetails.on_shelf = true;
        }
    }

    @OnClick({R.id.tv_to_dashang, R.id.tv_load_more, R.id.tv_add_book, R.id.tv_read_book, R.id.book_menu, R.id.item_last_chapter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_to_dashang:
                showDialog();
                mPresenter.getRewardType();
                break;
            case R.id.tv_load_more:
                rewardPage++;
                mPresenter.getBookReward(novel_id, rewardPage);
                break;
            case R.id.tv_add_book:
                if (!bookDetails.on_shelf) {
                    mPresenter.addBookShelf(novel_id);
                }else {

                }
                break;
            case R.id.tv_read_book:
                ReadActivity.startActivity(this, bookDetails.getId());
                break;
            case R.id.book_menu:
                MenuActivity.startActivity(this, bookDetails.getId(), 0, false);
                break;
            case R.id.item_last_chapter:
                ReadActivity.startActivity(this, bookDetails.getId(), bookDetails.latest_chapter.sort, false);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 10, 0, "").setIcon(R.mipmap.menu_share).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 10) {
            shareBookDialog = new ShareBookDialog(this, novel_id);
            shareBookDialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Subscriber
    public void onEventMainThread(SimpleEventBus event) {
        rewardPage = 1;
        mPresenter.getBookReward(novel_id, rewardPage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        EventBus.getDefault().unregister(this);
        if (shareBookDialog != null) {
            shareBookDialog.dismiss();
        }
    }

    @Override
    public void onItemClick(View view, int position, Object data) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
