package com.wxb.wanshu.ui.activity.ListActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.bean.BookMenu;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerBookComponent;
import com.wxb.wanshu.ui.activity.ReadActivity;
import com.wxb.wanshu.ui.adapter.easyadpater.BookMenuAdapter;
import com.wxb.wanshu.ui.contract.MenuContract;
import com.wxb.wanshu.ui.presenter.BookMenuPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.wxb.wanshu.ui.activity.BookDetailsActivity.INTENT_BOOK_ID;

public class MenuActivity extends BaseActivity implements MenuContract.View {

    String novel_id;
    int curChapter;
    public static String INTENT_CHAPTER = "chapter";
    public static String INTENT_ON_SHELF = "on_shelf";//是否在书架
    public static String INTENT_IS_READING = "isReading";

    @BindView(R.id.listview)
    ListView listView;
    List<BookMenu.DataBean.ChaptersBean> list = new ArrayList<>();

    @Inject
    BookMenuPresenter mPresenter;
    private BookMenuAdapter adapter;
    private boolean isReading = false;
    private BookMenu menu;

    public static void startActivity(Context context, String novel_id, int curChapter, boolean isReading) {
        context.startActivity(new Intent(context, MenuActivity.class)
                .putExtra(INTENT_BOOK_ID, novel_id)
                .putExtra(INTENT_CHAPTER, curChapter)
                .putExtra(INTENT_IS_READING, isReading));
    }

    public static void startActivityFor(Activity context, String novel_id, int curChapter, boolean isReading) {
        context.startActivityForResult(new Intent(context, MenuActivity.class)
                .putExtra(INTENT_BOOK_ID, novel_id)
                .putExtra(INTENT_CHAPTER, curChapter)
                .putExtra(INTENT_IS_READING, isReading), 100);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_menu;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerBookComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("目录");
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        novel_id = getIntent().getStringExtra(INTENT_BOOK_ID);
        curChapter = getIntent().getIntExtra(INTENT_CHAPTER, 0);
        isReading = getIntent().getBooleanExtra(INTENT_IS_READING, false);
        mPresenter.attachView(this);
        showDialog();
        mPresenter.getBookMenu(novel_id);
    }

    @Override
    public void configViews() {
        adapter = new BookMenuAdapter(mContext, list, novel_id, curChapter - 1);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                BookMenu.DataBean.ChaptersBean data = adapter.getData(position);
                if (isReading) {
                    Intent intent = getIntent();
                    intent.putExtra("chapter", data.sort);

                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    ReadActivity.startActivity(mContext, novel_id, data.sort, true, menu.data.novel.on_self);
                }
            }
        });
    }

    @Override
    public void showBookMenu(BookMenu data) {
        hideDialog();
        adapter.addAll(data.getData().getChapters());

        this.menu = data;
        listView.setSelection(curChapter - 1);//位置从0开始 对应第一章
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
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 10, 0, "").setIcon(R.mipmap.menu_down_sort).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 10) {
            int pos = curChapter - 1;
            Collections.reverse(list);
            adapter.setSelectPos(list.size() - pos - 1);
            adapter.notifyDataSetChanged();
            return true;
        } else {
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
