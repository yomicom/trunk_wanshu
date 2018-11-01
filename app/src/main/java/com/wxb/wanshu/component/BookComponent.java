package com.wxb.wanshu.component;

import com.wxb.wanshu.ui.activity.BookDetailsActivity;
import com.wxb.wanshu.ui.activity.BookshelfActivity;
import com.wxb.wanshu.ui.activity.ClassifyActivity;
import com.wxb.wanshu.ui.activity.HomeBookActivity;
import com.wxb.wanshu.ui.activity.ListActivity.MenuActivity;
import com.wxb.wanshu.ui.activity.ListActivity.OrderListActivity;
import com.wxb.wanshu.ui.activity.ListActivity.SelectBooksActivity;
import com.wxb.wanshu.ui.activity.NovelRankActivity;
import com.wxb.wanshu.ui.activity.ReadActivity;
import com.wxb.wanshu.ui.activity.SearchActivity;

import dagger.Component;

/**
 * Created by qiming on 2017/11/24.
 */

@Component(dependencies = AppComponent.class)
public interface BookComponent {

    HomeBookActivity inject(HomeBookActivity activity);

    BookshelfActivity inject(BookshelfActivity activity);

    SelectBooksActivity inject(SelectBooksActivity activity);

    BookDetailsActivity inject(BookDetailsActivity activity);

    ReadActivity inject(ReadActivity activity);

    SearchActivity inject(SearchActivity activity);

    ClassifyActivity inject(ClassifyActivity activity);

    NovelRankActivity inject(NovelRankActivity activity);

    MenuActivity inject(MenuActivity activity);

}