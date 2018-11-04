package com.wxb.wanshu.ui.presenter;

import com.wxb.wanshu.api.BookApi;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.HomeBookData;
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

    private BookApi bookApi;

    @Inject
    public HomeBookPresenter(BookApi bookApi) {
        this.bookApi = bookApi;
    }
    @Override
    public void getHomeData(String key) {
        Subscription rxSubscription = bookApi.getHomeData(key).subscribeOn(Schedulers.io())
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
