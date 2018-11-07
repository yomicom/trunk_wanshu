package com.wxb.wanshu.ui.contract;

import com.wxb.wanshu.base.BaseContract;
import com.wxb.wanshu.bean.BookMenu;
import com.wxb.wanshu.bean.UserOrder;

/**
 * Created by qiming on 2017/11/30.
 */

public interface MenuContract {

    interface View extends BaseContract.BaseView {
        void showBookMenu(BookMenu data);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getBookMenu(String novel_id);
    }
}
