package com.wxb.wanshu.ui.contract;

import com.wxb.wanshu.base.BaseContract;
import com.wxb.wanshu.bean.BookList;

/**
 * Created by qiming on 2017/11/30.
 */

public interface NovelRankContract {

    interface View extends BaseContract.BaseView {
        void showNovelRank(BookList data);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getNovelRank(String type,int page);
    }
}
