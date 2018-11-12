package com.wxb.wanshu.ui.contract;

import com.wxb.wanshu.base.BaseContract;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.BookMenu;
import com.wxb.wanshu.bean.UserInfo;

/**
 * Created by qiming on 2017/11/30.
 */

public interface MeContract {

    interface View extends BaseContract.BaseView {
        void showUserData(UserInfo data);

        void showLastVersion(Base data);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getUserData();

        void getLastVersion();
    }
}
