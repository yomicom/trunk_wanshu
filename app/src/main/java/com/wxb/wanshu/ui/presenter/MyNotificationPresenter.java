package com.wxb.wanshu.ui.presenter;

import com.wxb.wanshu.api.Api;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.NotificationList;
import com.wxb.wanshu.ui.contract.MyNotificationContract;
import com.wxb.wanshu.utils.LogUtils;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiming on 2017/11/30.
 */

public class MyNotificationPresenter extends RxPresenter<MyNotificationContract.View> implements MyNotificationContract.Presenter<MyNotificationContract.View> {
    Api api;

    @Inject
    public MyNotificationPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getNotificationList(int page) {
        Subscription rxSubscription = api.getNotificationList(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NotificationList>() {
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
                    public void onNext(NotificationList data) {
                        mView.showNotificationList(data);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
