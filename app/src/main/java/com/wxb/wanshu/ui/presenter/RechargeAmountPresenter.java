package com.wxb.wanshu.ui.presenter;

import com.wxb.wanshu.api.Api;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.RechargeAmount;
import com.wxb.wanshu.ui.contract.RechargeAmountContract;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiming on 2017/11/30.
 */

public class RechargeAmountPresenter extends RxPresenter<RechargeAmountContract.View> implements RechargeAmountContract.Presenter<RechargeAmountContract.View> {
    Api api;

    @Inject
    public RechargeAmountPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getRechargeAmount() {
        Subscription rxSubscription = api.getRechargeAmount().subscribeOn(Schedulers.io())
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
