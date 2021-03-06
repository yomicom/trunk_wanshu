package com.wxb.wanshu.ui.contract;

import com.wxb.wanshu.base.BaseContract;
import com.wxb.wanshu.bean.BookList;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.bean.NovelCategory;

import java.util.List;

/**
 * Created by qiming on 2017/11/30.
 */

public interface SelectBooksContract {

    interface View extends BaseContract.BaseView {
        void showBookList(BookList data);

        void showNovelCategory(NovelCategory category);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getBookList(String category_id, int page);

        void getLikeBooks(String category_id, int page);

        void getBoutiqueList(int type, int page);

        void getShortStoryList(int category_id, int page);

        void getFinishList(String sort, int status, int page);

        void getNovelCategory();
    }
}
