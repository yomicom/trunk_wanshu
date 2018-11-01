package com.wxb.wanshu.ui.presenter;

import com.wxb.wanshu.api.BookApi;
import com.wxb.wanshu.base.BaseContract;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.BookDetails;
import com.wxb.wanshu.bean.BookRewardData;
import com.wxb.wanshu.bean.RewardType;
import com.wxb.wanshu.ui.contract.BookDetailsContract;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by qiming on 2017/12/1.
 */

public class BookDetailsPresenter extends RxPresenter<BookDetailsContract.View> implements BookDetailsContract.Presenter<BookDetailsContract.View> {
    BookApi bookApi;

    @Inject
    public BookDetailsPresenter(BookApi bookApi) {
        this.bookApi = bookApi;
    }


    @Override
    public void getBookDetails(int novel_id) {
        Subscription subscribe = bookApi.getBookDetail(novel_id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookDetails>() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BookDetails bookDetails) {
                        mView.showBookDetails(bookDetails.getData());
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getBookReward(int novel_id,int page) {
        Subscription subscribe = bookApi.getBookReward(novel_id,page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookRewardData>() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BookRewardData bookDetails) {
                        mView.showBookReward(bookDetails);
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void getRewardType() {
        Subscription subscribe = bookApi.getRewardType().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RewardType>() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RewardType bookDetails) {
                        mView.showRewardType(bookDetails);
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void addBookShelf(int novel_id) {

        Subscription subscribe = bookApi.addBookshelfList(novel_id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Base>() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Base bookDetails) {
                        mView.addBookResult(bookDetails);
                    }
                });
        addSubscrebe(subscribe);
    }
}
