package com.wxb.wanshu.ui.contract;

import com.wxb.wanshu.base.BaseContract;
import com.wxb.wanshu.bean.AmountDetailList;
import com.wxb.wanshu.bean.AmountRecordList;
import com.wxb.wanshu.bean.NotificationList;

/**
 * Created by qiming on 2017/11/30.
 */

public interface AmountRecordContract {

    interface View extends BaseContract.BaseView {
        void showAmountRecordList(AmountRecordList data);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getAmountRecordList(int page);
    }
}
