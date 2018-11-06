package com.wxb.wanshu.ui.contract;

import com.wxb.wanshu.base.BaseContract;
import com.wxb.wanshu.bean.BookList;
import com.wxb.wanshu.bean.HotNovelList;

/**
 * Created by qiming on 2017/11/23.
 */

public interface SearchContract {

    interface View extends BaseContract.BaseView {
        void showHotNovelList(HotNovelList data);
        void showBookList(BookList data);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getHotNovelList();
        void getBookList(String kw,int page);
    }

}
