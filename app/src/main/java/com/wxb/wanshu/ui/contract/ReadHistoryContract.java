package com.wxb.wanshu.ui.contract;

import com.wxb.wanshu.base.BaseContract;
import com.wxb.wanshu.bean.NotificationList;
import com.wxb.wanshu.bean.ReadHistoryList;

/**
 * Created by qiming on 2017/11/30.
 */

public interface ReadHistoryContract {

    interface View extends BaseContract.BaseView {
        void showReadHistoryList(ReadHistoryList data);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getReadHistoryList(int page);
    }
}
