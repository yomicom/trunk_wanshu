package com.wxb.wanshu.ui.presenter;

import com.wxb.wanshu.api.Api;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.BookMenu;
import com.wxb.wanshu.ui.contract.MenuContract;
import com.wxb.wanshu.utils.LogUtils;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiming on 2017/11/30.
 */

public class BookMenuPresenter extends RxPresenter<MenuContract.View> implements MenuContract.Presenter<MenuContract.View> {
    Api api;

    @Inject
    public BookMenuPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getBookMenu(String novel_id) {
        Subscription rxSubscription = api.getBookMixAToc(novel_id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookMenu>() {
                    @Override
                    public void onCompleted() {
                        if (mView!=null)
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("getBookListDetail:" + e.toString());
                        if (mView!=null)
                        mView.complete();
                    }

                    @Override
                    public void onNext(BookMenu data) {
                        mView.showBookMenu(data);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
