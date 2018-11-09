package com.wxb.wanshu.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.base.ChapterRead;
import com.wxb.wanshu.base.Constant;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.BookMenu;
import com.wxb.wanshu.bean.ReadTheme;
import com.wxb.wanshu.bean.RewardType;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerBookComponent;
import com.wxb.wanshu.manager.CacheManager;
import com.wxb.wanshu.manager.EventManager;
import com.wxb.wanshu.manager.SettingManager;
import com.wxb.wanshu.manager.ThemeManager;
import com.wxb.wanshu.ui.activity.ListActivity.MenuActivity;
import com.wxb.wanshu.ui.adapter.easyadpater.ReadThemeAdapter;
import com.wxb.wanshu.ui.contract.BookReadContract;
import com.wxb.wanshu.ui.presenter.BookReadPresenter;
import com.wxb.wanshu.utils.AppUtils;
import com.wxb.wanshu.utils.FileUtils;
import com.wxb.wanshu.utils.LogUtils;
import com.wxb.wanshu.utils.ScreenUtils;
import com.wxb.wanshu.utils.SharedPreferencesUtil;
import com.wxb.wanshu.utils.ToastUtils;
import com.wxb.wanshu.utils.ViewToolUtils;
import com.wxb.wanshu.view.dialog.ConfirmDialog;
import com.wxb.wanshu.view.dialog.ShareBookDialog;
import com.wxb.wanshu.view.readview.BaseReadView;
import com.wxb.wanshu.view.readview.OnReadStateChangeListener;
import com.wxb.wanshu.view.readview.OverlappedWidget;

import org.simple.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wxb.wanshu.ui.activity.ListActivity.MenuActivity.INTENT_CHAPTER;
import static com.wxb.wanshu.ui.activity.ListActivity.MenuActivity.INTENT_ON_SHELF;

/**
 * 阅读页
 * Created by qiming on 2017/11/23.
 */

public class ReadActivity extends BaseActivity implements BookReadContract.View {

    @Inject
    BookReadPresenter mPresenter;

    @BindView(R.id.ivBack)
    ImageView mIvBack;
    @BindView(R.id.tvBookReadIntroduce)
    TextView tvAddBook;

    @BindView(R.id.flReadWidget)
    FrameLayout flReadWidget;

    @BindView(R.id.llBookReadTop)
    LinearLayout llBookReadTop;
    @BindView(R.id.tvBookReadTocTitle)
    TextView mTvBookReadTocTitle;
    //    @BindView(R.id.tvBookReadDownload)
//    TextView mTvBookReadDownload;
    @BindView(R.id.llBookReadBottom)
    LinearLayout llBookReadBottom;
    @BindView(R.id.rlBookReadRoot)
    RelativeLayout mRlBookReadRoot;
    @BindView(R.id.tvDownloadProgress)
    TextView mTvDownloadProgress;

    @BindView(R.id.rlReadAaSet)
    LinearLayout rlReadAaSet;
    @BindView(R.id.ivBrightnessMinus)
    ImageView ivBrightnessMinus;
    @BindView(R.id.seekbarLightness)
    SeekBar seekbarLightness;
    @BindView(R.id.ivBrightnessPlus)
    ImageView ivBrightnessPlus;
    @BindView(R.id.tvFontsizeMinus)
    ImageView tvFontsizeMinus;
    @BindView(R.id.tv_fontSize)
    TextView tvFontSize;
    @BindView(R.id.seekbarFontSize)
    SeekBar seekbarFontSize;
    @BindView(R.id.tvFontsizePlus)
    ImageView tvFontsizePlus;

    @BindView(R.id.rlReadMark)
    LinearLayout rlReadMark;
    @BindView(R.id.tvAddMark)
    TextView tvAddMark;
    @BindView(R.id.lvMark)
    ListView lvMark;

    @BindView(R.id.cbVolume)
    CheckBox cbVolume;
    @BindView(R.id.cbAutoBrightness)
    CheckBox cbAutoBrightness;
    @BindView(R.id.gvTheme)
    GridView gvTheme;
    @BindView(R.id.iv_book_mode)
    ImageView ivBookMode;
    @BindView(R.id.tv_book_mode)
    TextView tvBookMode;
    @BindView(R.id.seekbarChapter)
    SeekBar seekbarChapter;
    @BindView(R.id.item_chapter)
    LinearLayout itemChapter;

    private View decodeView;

    boolean isOnShelf = false;//是否被添加书架
    int fontChangeSize = 5;//字体调节度

    private List<BookMenu.DataBean.ChaptersBean> mChapterList = new ArrayList<>();

    private int currentChapter = 1;

    /**
     * 是否开始阅读章节
     **/
    private boolean startRead = false;

    private BaseReadView mPageWidget;
    private int curTheme = -1;
    private List<ReadTheme> themes;
    private ReadThemeAdapter gvAdapter;
    private Receiver receiver = new Receiver();
    private IntentFilter intentFilter = new IntentFilter();
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    public static final String INTENT_BEAN = "recommendBooksBean";
    public static final String INTENT_SD = "isFromSD";
    public static final String INTENT_MENU = "isFromMenu";

    private String novel_id;

    private boolean isAutoLightness = false; // 记录其他页面是否自动调整亮度
    private boolean isFromSD = false;
    //    private BuyBookPopupWindow buyBookPopupWindow;
    private boolean isFromMenu = false;
    private ShareBookDialog shareBookDialog;
    private int chapter_num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setCanSlide(false);
        super.onCreate(savedInstanceState);
    }

    //添加收藏需要，所以跳转的时候传递整个实体类
    public static void startActivity(Context context, String bookId) {
        startActivity(context, bookId, true);
    }

    public static void startActivity(Context context, String bookId, int currentChapter, boolean isFromMenu, boolean on_self) {
        context.startActivity(new Intent(context, ReadActivity.class)
                .putExtra(INTENT_BEAN, bookId)
                .putExtra(INTENT_CHAPTER, currentChapter)
                .putExtra(INTENT_ON_SHELF, on_self)
                .putExtra(INTENT_MENU, isFromMenu));
    }

    public static void startActivity(Context context, String bookId, boolean on_self) {
        context.startActivity(new Intent(context, ReadActivity.class)
                .putExtra(INTENT_BEAN, bookId)
                .putExtra(INTENT_ON_SHELF, on_self));
    }

    @Override
    public int getLayoutId() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        statusBarColor = ContextCompat.getColor(this, R.color.reader_menu_bg_color);
        return R.layout.activity_read;
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
        novel_id = getIntent().getStringExtra(INTENT_BEAN);
        currentChapter = getIntent().getIntExtra(INTENT_CHAPTER, 1);
        isOnShelf = getIntent().getBooleanExtra(INTENT_ON_SHELF, false);
        isFromMenu = getIntent().getBooleanExtra(INTENT_MENU, false);

        if (Intent.ACTION_VIEW.equals(getIntent().getAction())) {
            String filePath = Uri.decode(getIntent().getDataString().replace("file://", ""));
            String fileName;
            if (filePath.lastIndexOf(".") > filePath.lastIndexOf("/")) {
                fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.lastIndexOf("."));
            } else {
                fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            }

            // 转存
            File desc = FileUtils.createWifiTranfesFile(fileName);
            FileUtils.fileChannelCopy(new File(filePath), desc);
            // 建立
//            bookDetails = new BookDetails.DataBean();
//            bookDetails.isFromSD = true;
//            bookDetails._id = fileName;
//            bookDetails.title = fileName;

            isFromSD = true;
        }
        EventBus.getDefault().register(this);
        showDialog();

//        mTvBookReadTocTitle.setText(bookDetails.getTitle());

        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_TIME_TICK);

//        Observable.timer(1000, TimeUnit.MILLISECONDS)
//                .subscribe(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//                        //延迟1秒刷新书架
//                        EventManager.refreshCollectionList();
//                    }
//                });
    }

    @Override
    public void configViews() {
        hideStatusBar();
        decodeView = getWindow().getDecorView();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) llBookReadTop.getLayoutParams();
        params.topMargin = ScreenUtils.getStatusBarHeight(this) - 2;
        llBookReadTop.setLayoutParams(params);

        initAASet();

        initPagerWidget();

        mPresenter.attachView(this);
        // 本地收藏  直接打开
        if (isFromSD) {
            BookMenu.DataBean.ChaptersBean chapters = new BookMenu.DataBean.ChaptersBean();
//            chapters.name = bookDetails.getTitle();
            mChapterList.add(chapters);
            showChapterRead(null);
            //本地书籍隐藏社区、简介、缓存按钮
            gone(tvAddBook);
            return;
        }
        mPresenter.getBookMixAToc(novel_id);

        //设置屏幕常亮时间
        int screenLight = SettingManager.getInstance().getScreenLight();
        if (screenLight != 0) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            //取消屏幕常亮
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
//        buyBookPopupWindow = new BuyBookPopupWindow((Activity) mContext, novel_id, currentChapter);
    }

    /**
     * 时刻监听系统亮度改变事件
     */
    private ContentObserver Brightness = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
//            LogUtils.d("BrightnessOnChange:" + ScreenUtils.getScreenBrightnessInt255());
            if (!ScreenUtils.isAutoBrightness(ReadActivity.this)) {
                seekbarLightness.setProgress(ScreenUtils.getScreenBrightness());
            }
        }
    };

    /**
     * 设置阅读背景主题
     */
    private void initAASet() {
        curTheme = SettingManager.getInstance().getReadTheme();
        ThemeManager.setReaderTheme(curTheme, mRlBookReadRoot);

//        seekbarFontSize.setMax(10);
        //int fontSizePx = SettingManager.getInstance().getReadFontSize(novel_id);
        int fontSizePx = SettingManager.getInstance().getReadFontSize();
//        int progress = (int) ((ScreenUtils.pxToDpInt(fontSizePx) - 12) / 1.7f);
//        seekbarFontSize.setProgress(progress);
//        seekbarFontSize.setOnSeekBarChangeListener(new SeekBarChangeListener());
        tvFontSize.setText(fontSizePx + "");

        seekbarLightness.setMax(100);
        seekbarLightness.setOnSeekBarChangeListener(new SeekBarChangeListener());
        seekbarLightness.setProgress(ScreenUtils.getScreenBrightness());
        isAutoLightness = ScreenUtils.isAutoBrightness(this);

        seekbarChapter.setOnSeekBarChangeListener(new SeekBarChangeListener());

        this.getContentResolver().registerContentObserver(Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS), true, Brightness);

        if (SettingManager.getInstance().isAutoBrightness()) {
            startAutoLightness();
        } else {
            stopAutoLightness();
        }

        cbVolume.setChecked(SettingManager.getInstance().isVolumeFlipEnable());
        cbVolume.setOnCheckedChangeListener(new ChechBoxChangeListener());

        cbAutoBrightness.setChecked(SettingManager.getInstance().isAutoBrightness());
        cbAutoBrightness.setOnCheckedChangeListener(new ChechBoxChangeListener());

        gvAdapter = new ReadThemeAdapter(this, (themes = ThemeManager.getReaderThemeData()), curTheme);
        gvTheme.setAdapter(gvAdapter);
        gvTheme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changedMode(false, position);
            }
        });
    }

    /**
     * 设置阅读模式、字体
     */
    private void initPagerWidget() {
//        switch (SharedPreferencesUtil.getInstance().getInt(Constant.FLIP_STYLE, 0)) {
//            case 0:
//                mPageWidget = new PageWidget(this, novel_id + "", mChapterList, new ReadListener());
//                break;
//            case 1:
        mPageWidget = new OverlappedWidget(this, novel_id, mChapterList, new ReadListener());
//                break;
//            case 2:
//                mPageWidget = new NoAimWidget(this, novel_id + "", mChapterList, new ReadListener());
//        }

        registerReceiver(receiver, intentFilter);
        if (SharedPreferencesUtil.getInstance().getBoolean(Constant.ISNIGHT, false)) {
            mPageWidget.setTextColor(ContextCompat.getColor(this, R.color.read_content_night),
                    ContextCompat.getColor(this, R.color.read_title_night));
        }
        flReadWidget.removeAllViews();
        flReadWidget.addView(mPageWidget);
    }

    /**
     * 加载章节列表
     *
     * @param dataBean
     */
    @Override
    public void showBookToc(BookMenu.DataBean dataBean) {
        List<BookMenu.DataBean.ChaptersBean> list = dataBean.getChapters();
        mChapterList.clear();
        mChapterList.addAll(list);

        //总章节数
        chapter_num = dataBean.novel.chapter_num;
        seekbarChapter.setMax(chapter_num);
        readCurrentChapter(currentChapter);
    }

    /**
     * 读书并设置章节进度
     *
     * @param chapter
     */
    private void setChapterProgress(int chapter) {
        seekbarChapter.setProgress(chapter);
        currentChapter = chapter;
    }

    /**
     * 获取当前章节。章节文件存在则直接阅读，不存在则请求
     */
    public void readCurrentChapter(int chapter) {
        if (CacheManager.getInstance().getChapterFile(novel_id, chapter) != null) {
            showChapterRead(null);
        } else {
            showDialog();
            mPresenter.getChapterRead(novel_id, chapter, 0);
        }
    }

    @Override
    public synchronized void showChapterRead(ChapterRead.DataBean data) { // 加载章节内容
        if (data != null) {
            hideDialog();
            switch (data.code) {
                case Constant.READ_DOWN_CODE:
                    finish();
                    ReadOtherStatusActivity.startActivity(mContext, Constant.READ_DOWN_CODE, "", data);
                    break;
                case Constant.READ_ING_CODE:
//                    finish();
                    ReadOtherStatusActivity.startActivity(mContext, Constant.READ_ING_CODE, "", data);
                    break;
                case Constant.READ_FINISH_CODE:
//                    finish();
                    ReadOtherStatusActivity.startActivity(mContext, Constant.READ_FINISH_CODE, "", data);
                    break;
                case 0:
                    CacheManager.getInstance().saveChapterFile(data);
                    isOnShelf = data.on_shelf;
                    break;
            }
        }

        if (!startRead) {
            startRead = true;
            if (isFromMenu) {//小说详情的目录进入，先初始化再进入对应章节
                isFromMenu = false;
                mPageWidget.jumpInitChapter(curTheme, currentChapter);
            } else {
                if (!mPageWidget.isPrepared) {//点击开始阅读进入
                    mPageWidget.init(curTheme);
                } else {//点击阅读页的目录进入
                    mPageWidget.jumpToChapter(currentChapter);
                }
            }
            hideDialog();
        }
    }

    @Override
    public void netError(int chapter) {
        hideDialog();//防止因为网络问题而出现dialog不消失
        if (Math.abs(chapter - currentChapter) <= 1) {
            ToastUtils.showToast(R.string.last_chapter);
        }
    }

    @Override
    public void showRewardType(RewardType data) {
        if (data != null) {
//            hideDialog();
//            RewardGiftDialog.shareView(this, R.id.rlBookReadRoot, data, "", novel_id);
        }
    }

    @Override
    public void addBookResult(Base result) {
        if (result.getErrcode() == 0) {
            ToastUtils.showLongToast("添加书架成功");
            tvAddBook.setText("已加入书架");
            ViewToolUtils.getResourceColor(mContext, tvAddBook, R.color.text_color_2);
            isOnShelf = true;
        }
    }

    @Override
    public void showError() {
        hideDialog();
    }

    @Override
    public void complete() {
        hideDialog();
    }

    private synchronized void hideReadBar() {
        gone(mTvDownloadProgress, llBookReadBottom, llBookReadTop, rlReadAaSet, rlReadMark, itemChapter);
        hideStatusBar();
        decodeView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
    }

    private synchronized void showReadBar() { // 显示工具栏
        visible(llBookReadBottom, llBookReadTop, itemChapter);
        showStatusBar();
        decodeView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    private synchronized void toggleReadBar() { // 切换工具栏 隐藏/显示 状态
        if (isVisible(llBookReadTop)) {
            hideReadBar();
        } else {
            showReadBar();
        }
    }

    /***************Title Bar*****************/

    @OnClick(R.id.ivBack)
    public void onClickBack() {
        finish();
    }

    /**
     * 购买书籍
     */
    @OnClick(R.id.tvBookReadReading)
    public void buyBook() {
        gone(rlReadAaSet, rlReadMark);
//        buyBookPopupWindow.setPay(0);
//        buyBookPopupWindow.showAtLocation(findViewById(R.id.rlBookReadRoot), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    /**
     * 添加书架
     */
    @OnClick(R.id.tvBookReadIntroduce)
    public void addBook() {
        gone(rlReadAaSet, rlReadMark);
        visible(itemChapter);
        mPresenter.addBookShelf(novel_id);
    }

    /**
     * 打赏
     */
    @OnClick(R.id.tvBookReadCommunity)
    public void RewardBook() {
        gone(rlReadAaSet, rlReadMark);
        showDialog();
        mPresenter.getRewardType();
    }

    /***************Bottom Bar*****************/

    @OnClick(R.id.item_book_mode)
    public void onClickChangeMode() { // 日/夜间模式切换
        gone(rlReadAaSet, rlReadMark);
        visible(itemChapter);

        boolean isNight = !SharedPreferencesUtil.getInstance().getBoolean(Constant.ISNIGHT, false);
        changedMode(isNight, -1);
    }

    //切换阅读模式
    private void changedMode(boolean isNight, int position) {
        SharedPreferencesUtil.getInstance().putBoolean(Constant.ISNIGHT, isNight);
        AppCompatDelegate.setDefaultNightMode(isNight ? AppCompatDelegate.MODE_NIGHT_YES
                : AppCompatDelegate.MODE_NIGHT_NO);

        if (position >= 0) {
            curTheme = position;
        } else {
            curTheme = SettingManager.getInstance().getReadTheme();
        }
        gvAdapter.select(curTheme);

        mPageWidget.setTheme(isNight ? ThemeManager.NIGHT : curTheme);

        if (!isNight) {
            mPageWidget.setTextColor(ContextCompat.getColor(mContext, ThemeManager.THEME_CONTENT_COLOR[curTheme]),
                    ContextCompat.getColor(mContext, ThemeManager.THEME_TITLE_COLOR[curTheme]));
            ivBookMode.setImageResource(R.mipmap.ic_menu_mode_night_normal);
            tvBookMode.setText("夜间");
        } else {
            mPageWidget.setTextColor(ContextCompat.getColor(mContext, ThemeManager.THEME_CONTENT_COLOR[ThemeManager.THEME_CONTENT_COLOR.length - 1]),
                    ContextCompat.getColor(mContext, ThemeManager.THEME_TITLE_COLOR[ThemeManager.THEME_CONTENT_COLOR.length - 1]));
            ivBookMode.setImageResource(R.mipmap.ic_menu_mode_day_manual);
            tvBookMode.setText("日间");
        }

        ThemeManager.setReaderTheme(curTheme, mRlBookReadRoot);
    }

    @OnClick(R.id.item_book_setting)//设置
    public void setting() {
        if (isVisible(llBookReadBottom)) {
            if (isVisible(rlReadAaSet)) {
                gone(rlReadAaSet);
                visible(itemChapter);
            } else {
                visible(rlReadAaSet);
                gone(rlReadMark);
                gone(itemChapter);
            }
        }
    }

//    @OnClick(R.id.tvBookReadDownload)
//    public void downloadBook() {
//        gone(rlReadAaSet);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("缓存多少章？")
//                .setItems(new String[]{"后面五十章", "后面全部", "全部"}, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which) {
//                            case 0:
//                                DownloadBookService.post(new DownloadQueue(novel_id, mChapterList, currentChapter + 1, currentChapter + 50));
//                                break;
//                            case 1:
//                                DownloadBookService.post(new DownloadQueue(novel_id, mChapterList, currentChapter + 1, mChapterList.size()));
//                                break;
//                            case 2:
//                                DownloadBookService.post(new DownloadQueue(novel_id, mChapterList, 1, mChapterList.size()));
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                });
//        builder.show();
//    }

    @OnClick(R.id.tv_book_share)//分享
    public void onClickMark() {
        gone(rlReadAaSet, rlReadMark);
        shareBookDialog = new ShareBookDialog(this, novel_id);
        shareBookDialog.show();
    }

    @OnClick(R.id.item_book_menu)//目录
    public void onClickToc() {
        gone(rlReadAaSet, rlReadMark);
        visible(itemChapter);
        visible(mTvBookReadTocTitle);
        MenuActivity.startActivityFor(this, novel_id, currentChapter, true);
    }

    /***************Setting Menu*****************/

    //亮度
    @OnClick(R.id.ivBrightnessMinus)
    public void brightnessMinus() {
        int curBrightness = SettingManager.getInstance().getReadBrightness();
        if (curBrightness > 5 && !SettingManager.getInstance().isAutoBrightness()) {
            seekbarLightness.setProgress((curBrightness = curBrightness - 2));
            ScreenUtils.saveScreenBrightnessInt255(curBrightness, ReadActivity.this);
        }
    }

    @OnClick(R.id.ivBrightnessPlus)
    public void brightnessPlus() {
        int curBrightness = SettingManager.getInstance().getReadBrightness();
        if (!SettingManager.getInstance().isAutoBrightness()) {
            seekbarLightness.setProgress((curBrightness = curBrightness + 2));
            ScreenUtils.saveScreenBrightnessInt255(curBrightness, ReadActivity.this);
        }
    }

    //字体大小
    @OnClick(R.id.tvFontsizeMinus)
    public void fontsizeMinus() {
        calcFontSize(false);
    }

    @OnClick(R.id.tvFontsizePlus)
    public void fontsizePlus() {
        calcFontSize(true);
    }

    @OnClick(R.id.tvClear)
    public void clearBookMark() {
        SettingManager.getInstance().clearBookMarks(novel_id + "");
    }


    /***************Chapter*****************/
    @OnClick(R.id.pre_chapter)//查看上一章
    public void preChapter() {
        if (currentChapter > 1) {
            currentChapter = currentChapter - 1;
            startRead = false;
            readCurrentChapter(currentChapter);
        } else {
            ToastUtils.showToast("已经是第一章了");
        }
    }

    @OnClick(R.id.next_chapter)//查看下一章
    public void nextChapter() {
//        if (currentChapter > 1) {
        currentChapter = currentChapter + 1;
        startRead = false;
        readCurrentChapter(currentChapter);
//        } else {
//            ToastUtils.showToast("已经是第一章了");
//        }
    }

    /***************Book Mark*****************/
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void showDownProgress(DownloadProgress progress) {
//        if (novel_id.equals(progress.novel_id)) {
//            if (isVisible(llBookReadBottom)) { // 如果工具栏显示，则进度条也显示
//                visible(mTvDownloadProgress);
//                // 如果之前缓存过，就给提示
//                mTvDownloadProgress.setText(progress.message);
//            } else {
//                gone(mTvDownloadProgress);
//            }
//        }
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void downloadMessage(final DownloadMessage msg) {
//        if (isVisible(llBookReadBottom)) { // 如果工具栏显示，则进度条也显示
//            if (novel_id.equals(msg.novel_id)) {
//                visible(mTvDownloadProgress);
//                mTvDownloadProgress.setText(msg.message);
//                if (msg.isComplete) {
//                    mTvDownloadProgress.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            gone(mTvDownloadProgress);
//                        }
//                    }, 2500);
//                }
//            }
//        }
//    }

    /**
     * 显示加入书架对话框
     */
    private void showJoinBookShelfDialog() {
        ConfirmDialog.showNotice(mContext, "提示", "确认将此书加入书架？", "确定", "取消", new ConfirmDialog.SureCallback() {
            @Override
            public void exec() throws Exception {
                mPresenter.addBookShelf(novel_id);
            }
        }, new ConfirmDialog.CancleCallback() {
            @Override
            public void exec() throws Exception {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
//                    BookSource bookSource = (BookSource) data.getSerializableExtra("source");
//                    novel_id = bookSource._id;
                    //mPresenter.getBookMixAToc(novel_id, "chapters");
                    break;
                case 100:
                    currentChapter = data.getIntExtra("chapter", 1);
                    startRead = false;
                    readCurrentChapter(currentChapter);
                    hideReadBar();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (isVisible(rlReadAaSet)) {
                    gone(rlReadAaSet);
                    visible(itemChapter);
                    return true;
                } else if (isVisible(llBookReadBottom)) {
                    hideReadBar();
                    return true;
                } else if (!isOnShelf) {//若未包含在书架中
                    showJoinBookShelfDialog();
                    return true;
                }
                break;
            case KeyEvent.KEYCODE_MENU:
                toggleReadBar();
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (SettingManager.getInstance().isVolumeFlipEnable()) {
                    return true;
                }
                break;
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (SettingManager.getInstance().isVolumeFlipEnable()) {
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            if (SettingManager.getInstance().isVolumeFlipEnable()) {
                mPageWidget.nextPage();
                return true;// 防止翻页有声音
            }
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            if (SettingManager.getInstance().isVolumeFlipEnable()) {
                mPageWidget.prePage();
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventManager.refreshCollectionIcon();
        EventManager.refreshCollectionList();
        EventBus.getDefault().unregister(this);

        try {
            unregisterReceiver(receiver);
        } catch (Exception e) {
            LogUtils.e("Receiver not registered");
        }

        if (isAutoLightness) {
            ScreenUtils.startAutoBrightness(ReadActivity.this);
        } else {
            ScreenUtils.stopAutoBrightness(ReadActivity.this);
        }

        if (mPresenter != null) {
            mPresenter.detachView();
        }

        if (shareBookDialog != null) {
            shareBookDialog.dismiss();
        }
        // 观察内存泄漏情况
//        MyApplication.getRefWatcher(this).watch(this);
    }

    private class ReadListener implements OnReadStateChangeListener {
        @Override
        public void onChapterChanged(int chapter) {
            LogUtils.i("onChapterChanged:" + chapter);

            currentChapter = chapter;
            setChapterProgress(currentChapter);
//            mTocListAdapter.setCurrentChapter(currentChapter);

            // 预加载
            // 加载前一节 与 后三节
            for (int i = chapter - 1; i <= chapter + 3 && i <= mChapterList.size(); i++) {
                if (i > 0 && i != chapter
                        && CacheManager.getInstance().getChapterFile(novel_id + "", i) == null) {
                    mPresenter.getChapterRead(novel_id, i, 0);
                }
            }
        }

        @Override
        public void onPageChanged(int chapter, int page) {
            LogUtils.i("onPageChanged:" + chapter + "-" + page);
        }

        @Override
        public void onLoadChapterFailure(int chapter) {
            LogUtils.i("onLoadChapterFailure:" + chapter);
            startRead = false;
            if (CacheManager.getInstance().getChapterFile(novel_id + "", chapter) == null) {
                mPresenter.getChapterRead(novel_id, chapter, 0);
            }
        }

        @Override
        public void onCenterClick() {
            LogUtils.i("onCenterClick");
            toggleReadBar();
        }

        @Override
        public void onPageFinish() {
            showDialog();
            mPresenter.getChapterRead(novel_id, currentChapter + 1, 0);
        }

        @Override
        public void onFlip() {
            hideReadBar();
        }
    }

    private class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//            if (seekBar.getId() == seekbarFontSize.getId() && fromUser) {
//                calcFontSize(progress);
//            } else
            if (seekBar.getId() == seekbarLightness.getId() && fromUser
                    && !SettingManager.getInstance().isAutoBrightness()) { // 非自动调节模式下 才可调整屏幕亮度
                ScreenUtils.saveScreenBrightnessInt100(progress, ReadActivity.this);
                //SettingManager.getInstance().saveReadBrightness(progress);
            } else if (seekBar.getId() == seekbarChapter.getId() && fromUser) {
                readCurrentChapter(progress);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    private class ChechBoxChangeListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView.getId() == cbVolume.getId()) {
                SettingManager.getInstance().saveVolumeFlipEnable(isChecked);
            } else if (buttonView.getId() == cbAutoBrightness.getId()) {
                if (isChecked) {
                    startAutoLightness();
                } else {
                    stopAutoLightness();
                    ScreenUtils.saveScreenBrightnessInt255(ScreenUtils.getScreenBrightnessInt255(), AppUtils.getAppContext());
                }
            }
        }
    }

    /**
     * 实时更新电池余量和系统时间
     */
    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (mPageWidget != null) {
                if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                    int level = intent.getIntExtra("level", 0);
                    mPageWidget.setBattery(100 - level);
                } else if (Intent.ACTION_TIME_TICK.equals(intent.getAction())) {
                    mPageWidget.setTime(sdf.format(new Date()));
                }
            }
        }

    }

    private void startAutoLightness() {
        SettingManager.getInstance().saveAutoBrightness(true);
        ScreenUtils.startAutoBrightness(ReadActivity.this);
        seekbarLightness.setEnabled(false);
    }

    private void stopAutoLightness() {
        SettingManager.getInstance().saveAutoBrightness(false);
        ScreenUtils.stopAutoBrightness(ReadActivity.this);
        seekbarLightness.setProgress((int) (ScreenUtils.getScreenBrightnessInt255() / 255.0F * 100));
        seekbarLightness.setEnabled(true);
    }

    private void calcFontSize(boolean isAdd) {
        int font = Integer.parseInt(tvFontSize.getText().toString());
        int fontSize = isAdd == true ? (font += fontChangeSize) : (font -= fontChangeSize);
        // progress range 1 - 10
//        int progress = 1;
        if (fontSize >= 10 && fontSize <= 100) {
//            seekbarFontSize.setProgress(progress);
//            mPageWidget.setFontSize(ScreenUtils.dpToPxInt(12 + 1.7f * progress));
            mPageWidget.setFontSize(fontSize);
            tvFontSize.setText(fontSize + "");
        }
    }
}
