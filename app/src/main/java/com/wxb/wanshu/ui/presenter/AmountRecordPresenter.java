package com.wxb.wanshu.ui.presenter;

import com.wxb.wanshu.api.Api;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.AmountRecordList;
import com.wxb.wanshu.ui.contract.AmountRecordContract;
import com.wxb.wanshu.utils.LogUtils;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiming on 2017/11/30.
 */

public class AmountRecordPresenter extends RxPresenter<AmountRecordContract.View> implements AmountRecordContract.Presenter<AmountRecordContract.View> {
    Api api;

    @Inject
    public AmountRecordPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getAmountRecordList(int page) {
        Subscription rxSubscription = api.getAmountRecordList(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AmountRecordList>() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("getBookListDetail:" + e.toString());
                        mView.complete();
                    }

                    @Override
                    public void onNext(AmountRecordList data) {
                        mView.showAmountRecordList(data);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
