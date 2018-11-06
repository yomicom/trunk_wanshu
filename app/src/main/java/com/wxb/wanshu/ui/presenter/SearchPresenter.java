package com.wxb.wanshu.ui.presenter;

import com.wxb.wanshu.api.Api;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.BookList;
import com.wxb.wanshu.bean.HotNovelList;
import com.wxb.wanshu.ui.contract.SearchContract;
import com.wxb.wanshu.utils.LogUtils;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiming on 2017/12/13.
 */

public class SearchPresenter extends RxPresenter<SearchContract.View> implements SearchContract.Presenter<SearchContract.View> {
    Api api;

    @Inject
    public SearchPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getHotNovelList() {
        Subscription rxSubscription = api.getHotNovelList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotNovelList>() {
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
                    public void onNext(HotNovelList data) {
                        mView.showHotNovelList(data);
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getBookList(String kw,int page) {
        Subscription rxSubscription = api.getSearchResult(kw,page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookList>() {
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
                    public void onNext(BookList data) {
                        mView.showBookList(data);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
