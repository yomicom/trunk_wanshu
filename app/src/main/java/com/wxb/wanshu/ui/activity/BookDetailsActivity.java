package com.wxb.wanshu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wxb.wanshu.MyApplication;
import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.base.Constant;
import com.wxb.wanshu.bean.AddShlef;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.BookDetails;
import com.wxb.wanshu.bean.BookRewardData;
import com.wxb.wanshu.bean.RewardType;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerBookComponent;
import com.wxb.wanshu.ui.activity.ListActivity.MenuActivity;
import com.wxb.wanshu.ui.adapter.easyadpater.BookRewardAdapter;
import com.wxb.wanshu.ui.contract.BookDetailsContract;
import com.wxb.wanshu.ui.fragment.HomeRecommendFragment;
import com.wxb.wanshu.ui.presenter.BookDetailsPresenter;
import com.wxb.wanshu.utils.FormatUtils;
import com.wxb.wanshu.utils.ImageUtils;
import com.wxb.wanshu.utils.ToastUtils;
import com.wxb.wanshu.utils.ViewToolUtils;
import com.wxb.wanshu.view.dialog.RewardGiftDialog;
import com.wxb.wanshu.view.dialog.ShareBookDialog;
import com.wxb.wanshu.view.recycleview.NoScrollLayoutManager;
import com.wxb.wanshu.view.recycleview.decoration.DividerDecoration;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.wxb.wanshu.ui.fragment.HomeRecommendFragment.BOOK_DETAILS_TYPE;

/**
 * 书籍详情页
 */
public class BookDetailsActivity extends BaseActivity implements BookDetailsContract.View, OnRvItemClickListener {

    public static String INTENT_BOOK_ID = "bookId";
    //    @BindView(R.id.background)
//    ImageView background;
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
    @BindView(R.id.recommend)
    LinearLayout recommend;

    String novel_id;
    @BindView(R.id.last_chapter)
    TextView lastChapter;
    @BindView(R.id.last_chapter_time)
    TextView lastChapterTime;
    @BindView(R.id.book_chapter_num)
    TextView bookChapterNum;
    @BindView(R.id.iv_add_book)
    ImageView ivAddBook;
    private BookDetails.DataBean bookDetails;
    private BookRewardAdapter adapter;
    private int rewardPage = 1;
    private ShareBookDialog shareBookDialog;

    public static void startActivity(Context context, String novel_id) {
        context.startActivity(new Intent(context, BookDetailsActivity.class)
                .putExtra(INTENT_BOOK_ID, novel_id));
    }

    public static void startActivity(Context context, String novel_id, int is_onsale) {
        if (!ReadOtherStatusActivity.startActivity(context, is_onsale)) {
            context.startActivity(new Intent(context, BookDetailsActivity.class)
                    .putExtra(INTENT_BOOK_ID, novel_id));
        }
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
        mCommonToolbar.setTitle("书籍详情");
//        mCommonToolbar.setTitle(R.string.details_books);
    }

    @Override
    public void initDatas() {

        EventBus.getDefault().register(this);
        novel_id = getIntent().getStringExtra(INTENT_BOOK_ID);

        mPresenter.attachView(this);
        showDialog();
//        mPresenter.getBookReward(novel_id, rewardPage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getBookDetails(novel_id, 0);
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
        hideDialog();
    }

    @Override
    public void showBookDetails(BookDetails data) {
        hideDialog();
        if (data.errcode == 0) {
            visible(llContent);

            bookDetails = data.getData();
            showBookData(bookDetails);
            showRecommandData(bookDetails);
        } else if (data.errcode == Constant.READ_DOWN_CODE) {//书籍已下架
            finish();
            ReadOtherStatusActivity.startActivity(mContext, 0);
        }
    }

    private void showBookData(BookDetails.DataBean data) {
//        try {
//            Glide.with(mContext).asBitmap().load("https://www.google.com.hk/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwia8Jmsw87eAhVTNrwKHfi6CgAQjRx6BAgBEAU&url=http%3A%2F%2Fwww.epochtimes.com%2Fgb%2F17%2F6%2F12%2Fn9254057.htm&psig=AOvVaw2BlL1H9ImQ92sKxgerG_T7&ust=1542100791090688").into(new SimpleTarget<Bitmap>() {
//                @Override
//                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
//                    ViewToolUtils.blurBitmap(getBitMBitmap(data.cover), 10, mContext);
//                }
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        if (bookDetails != null) {
            ImageUtils.displayImage(this, ivBook, data.getCover(), R.drawable.defalt_book_cover, R.drawable.defalt_book_cover);
            tvTitle.setText(data.getName());
            author.setText(data.getAuthor());
            tvRead.setText(data.getCategory_name() + " • " + ("0".equals(data.getComplete_status()) ? "连载" : "完结") + " • " + FormatUtils.formatWordCount(data.word_num));
            if (bookDetails.on_shelf) {
                hasAddBookshlef();
            } else {
                tvAddBook.setText("加入书架");
                ViewToolUtils.getResourceColor(mContext, tvAddBook, R.color.text_color_1);
            }

            lastChapter.setText(data.getLatest_chapter().name);
            lastChapterTime.setText(data.latest_chapter.publish_time);
            bookChapterNum.setText("共" + data.getChapter_num() + "章");
            ViewToolUtils.setShowMoreContent(3, data.getDescription(), tvAccountIntro, ivShowText, descriptionLayout);
        }
    }

    private void hasAddBookshlef() {
        if (bookDetails != null) {
            bookDetails.on_shelf = true;
            tvAddBook.setText("已加入书架");
            ViewToolUtils.getResourceColor(mContext, tvAddBook, R.color.text_color_2);
            visible(ivAddBook);
        }
    }

    public static Bitmap getBitMBitmap(String urlpath) {
        Bitmap map = null;
        try {
            URL url = new URL(urlpath);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream in;
            in = conn.getInputStream();
            map = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 显示推荐书籍
     *
     * @param data
     */
    private void showRecommandData(BookDetails.DataBean data) {
        if (data.getRecommend().size() > 0) {
            visible(recommend);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            HomeRecommendFragment type1Fragment = HomeRecommendFragment.newInstance(data, BOOK_DETAILS_TYPE);
            transaction.replace(R.id.fl_content_recommand, type1Fragment);
            transaction.commit();
        } else {
            gone(recommend);
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
            hasAddBookshlef();
        }
    }

    @OnClick({R.id.tv_to_dashang, R.id.tv_load_more, R.id.ll_add_book, R.id.tv_read_book, R.id.book_menu, R.id.item_last_chapter})
    public void onViewClicked(View view) {
        int is_onsale = 1;
        if (bookDetails != null) {
            is_onsale = bookDetails.is_onsale;
            switch (view.getId()) {
                case R.id.tv_to_dashang:
                    showDialog();
                    mPresenter.getRewardType();
                    break;
                case R.id.tv_load_more:
                    rewardPage++;
                    mPresenter.getBookReward(novel_id, rewardPage);
                    break;
                case R.id.ll_add_book:
                    if (!bookDetails.on_shelf) {
                        showDialog();
                        mPresenter.addBookShelf(novel_id);
                    } else {

                    }
                    break;
                case R.id.tv_read_book://开始阅读
                    if (!ReadOtherStatusActivity.startActivity(this, is_onsale))//书已下架
                        ReadActivity.startActivity(this, bookDetails.getId(), bookDetails.on_shelf);
                    break;
                case R.id.book_menu:
                    if (!ReadOtherStatusActivity.startActivity(this, is_onsale))
                        MenuActivity.startActivity(this, bookDetails.getId(), 0, false);
                    break;
                case R.id.item_last_chapter://查看最新章节
                    if (!ReadOtherStatusActivity.startActivity(this, is_onsale))
                        ReadActivity.startActivity(this, bookDetails.getId(), bookDetails.on_shelf, bookDetails.latest_chapter.sort, true);
                    break;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(0, 10, 0, "").setIcon(R.mipmap.menu_share).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
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
    public void onEventMainThread(AddShlef event) {//阅读页加入书架成功
        if (event.add == 1) {
            hasAddBookshlef();
        }
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
        ButterKnife.bind(this);
    }
}
