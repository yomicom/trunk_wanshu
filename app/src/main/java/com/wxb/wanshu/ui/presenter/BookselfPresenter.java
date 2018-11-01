package com.wxb.wanshu.ui.presenter;

import com.wxb.wanshu.api.BookApi;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.BookselfList;
import com.wxb.wanshu.bean.RechargeAmount;
import com.wxb.wanshu.ui.contract.BookselfContract;
import com.wxb.wanshu.ui.contract.RechargeAmountContract;
import com.wxb.wanshu.utils.LogUtils;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiming on 2017/12/11.
 */

public class BookselfPresenter  extends RxPresenter<BookselfContract.View> implements BookselfContract.Presenter<BookselfContract.View> {
    BookApi bookApi;

    @Inject
    public BookselfPresenter(BookApi bookApi) {
        this.bookApi = bookApi;
    }

    @Override
    public void getData(int page,int pageSize) {
        Subscription rxSubscription = bookApi.getBookshelfList(page,pageSize).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookselfList>() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError();
                    }

                    @Override
                    public void onNext(BookselfList data) {
                        mView.showData(data);
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void delBooks(String novel_ids) {

        Subscription rxSubscription = bookApi.delBookshelfList(novel_ids).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Base>() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError();
                    }

                    @Override
                    public void onNext(Base data) {
                        mView.showDelSuccess(data);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
