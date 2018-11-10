package com.wxb.wanshu.ui.contract;

import com.wxb.wanshu.base.BaseContract;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.BookselfList;
import com.wxb.wanshu.bean.RechargeAmount;

/**
 * Created by qiming on 2017/12/11.
 */

public interface BookselfContract {

    interface View extends BaseContract.BaseView {
        void showData(BookselfList data);//显示书架列表

        void showDelSuccess(String novel_ids);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getData();

        void delBooks(String novel_ids);
    }
}
