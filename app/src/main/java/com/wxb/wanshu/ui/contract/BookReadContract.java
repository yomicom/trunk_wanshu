package com.wxb.wanshu.ui.contract;

import com.wxb.wanshu.base.BaseContract;
import com.wxb.wanshu.base.ChapterRead;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.BookMenu;
import com.wxb.wanshu.bean.RewardType;

import java.util.List;

/**
 * Created by qiming on 2017/11/23.
 */

public interface BookReadContract {

    interface View extends BaseContract.BaseView {
        void showBookToc(List<BookMenu.DataBean.ChaptersBean> list);

        void showChapterRead(ChapterRead.DataBean data);

        void netError(int chapter);//添加网络处理异常接口

        void showRewardType(RewardType data);

        void addBookResult(Base result);

//        void showShareBook(BookDetails.DataBean data);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getBookMixAToc(int bookId);

        void getChapterRead(int novel_id, int chapter);

        void getRewardType();

        void addBookShelf(int novel_id);

//        void getShareBook(int novel_id);
    }

}
