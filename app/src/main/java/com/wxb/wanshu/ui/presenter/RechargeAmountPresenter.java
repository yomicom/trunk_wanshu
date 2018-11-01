package com.wxb.wanshu.ui.presenter;

import com.wxb.wanshu.api.BookApi;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.RechargeAmount;
import com.wxb.wanshu.bean.UserOrder;
import com.wxb.wanshu.ui.contract.OrderListContract;
import com.wxb.wanshu.ui.contract.RechargeAmountContract;
import com.wxb.wanshu.utils.LogUtils;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiming on 2017/11/30.
 */

public class RechargeAmountPresenter extends RxPresenter<RechargeAmountContract.View> implements RechargeAmountContract.Presenter<RechargeAmountContract.View> {
    BookApi bookApi;

    @Inject
    public RechargeAmountPresenter(BookApi bookApi) {
        this.bookApi = bookApi;
    }

    @Override
    public void getRechargeAmount() {
        Subscription rxSubscription = bookApi.getRechargeAmount().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RechargeAmount>() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(RechargeAmount data) {
                        mView.showRechargeAmount(data);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
