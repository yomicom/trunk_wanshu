package com.wxb.wanshu.ui.contract;

import com.wxb.wanshu.base.BaseContract;
import com.wxb.wanshu.bean.RechargeAmount;

/**
 * Created by qiming on 2017/11/23.
 */

public interface RechargeAmountContract {

    interface View extends BaseContract.BaseView {
        void showRechargeAmount(RechargeAmount data);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getRechargeAmount();
    }

}
