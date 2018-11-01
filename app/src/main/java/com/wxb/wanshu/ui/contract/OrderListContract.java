package com.wxb.wanshu.ui.contract;

import com.wxb.wanshu.base.BaseContract;
import com.wxb.wanshu.bean.BookList;
import com.wxb.wanshu.bean.UserOrder;

/**
 * Created by qiming on 2017/11/30.
 */

public interface OrderListContract {

    interface View extends BaseContract.BaseView {
        void showOrderList(UserOrder data);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getOrderList(int page);
    }
}
