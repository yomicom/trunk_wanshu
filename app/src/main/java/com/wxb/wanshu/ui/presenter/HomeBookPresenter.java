package com.wxb.wanshu.ui.presenter;

import com.wxb.wanshu.api.Api;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.ui.contract.HomeContract;
import com.wxb.wanshu.utils.LogUtils;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiming on 2017/11/29.
 */

public class HomeBookPresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter<HomeContract.View> {

    private Api api;

    @Inject
    public HomeBookPresenter(Api api) {
        this.api = api;
    }
    @Override
    public void getHomeData(String key) {
        Subscription rxSubscription = api.getHomeData(key).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeData>() {
                    @Override
                    public void onNext(HomeData data) {
                        mView.showHome(data);
                    }

                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("onError: " + e);
                        mView.netError();
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
