package com.wxb.wanshu.ui.contract;

import com.wxb.wanshu.base.BaseContract;
import com.wxb.wanshu.bean.NotificationList;
import com.wxb.wanshu.bean.UserOrder;

/**
 * Created by qiming on 2017/11/30.
 */

public interface MyNotificationContract {

    interface View extends BaseContract.BaseView {
        void showNotificationList(NotificationList data);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getNotificationList(int page);
    }
}
