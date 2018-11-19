package com.wxb.wanshu.ui.contract;

import com.wxb.wanshu.base.BaseContract;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.NotificationList;
import com.wxb.wanshu.bean.ReadHistoryList;

/**
 * Created by qiming on 2017/11/30.
 */

public interface ReadHistoryContract {

    interface View extends BaseContract.BaseView {
        void showReadHistoryList(ReadHistoryList data);

        void addBookResult(String novel_ids);

        void delHistoryBookResult(String novel_ids);


    }
    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getReadHistoryList(int page);

        void addBookShelf(String novel_id);

        void delHistory(String novel_ids,int delete_all);
    }
}
