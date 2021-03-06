package com.wxb.wanshu.ui.presenter;

import com.wxb.wanshu.api.Api;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.AppVersion;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.UserInfo;
import com.wxb.wanshu.ui.contract.MeContract;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiming on 2017/11/30.
 */

public class MePresenter extends RxPresenter<MeContract.View> implements MeContract.Presenter<MeContract.View> {
    Api api;

    @Inject
    public MePresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getUserData() {
        Subscription rxSubscription = api.getUserInfo().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInfo>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserInfo data) {
                        mView.showUserData(data);
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getLastVersion(boolean isClick) {
        Subscription rxSubscription = api.getVersion().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AppVersion>() {
                    @Override
                    public void onCompleted() {
                        if (mView != null) mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError();
                    }

                    @Override
                    public void onNext(AppVersion data) {
                        mView.showLastVersion(data, isClick);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
