package com.wxb.wanshu.ui.contract;

import com.wxb.wanshu.base.BaseContract;
import com.wxb.wanshu.bean.HomeBookData;
import com.wxb.wanshu.bean.HomeData;

/**
 * Created by qiming on 2017/11/29.
 */

public interface HomeContract {

    interface View extends BaseContract.BaseView {
        void showHome(HomeData data);

        void netError();//添加网络处理异常接口
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getHomeData(String key);
    }

}
