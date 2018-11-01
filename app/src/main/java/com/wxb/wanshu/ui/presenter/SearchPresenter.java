package com.wxb.wanshu.ui.presenter;

import com.wxb.wanshu.api.BookApi;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.BookList;
import com.wxb.wanshu.bean.HotNovelList;
import com.wxb.wanshu.bean.UserOrder;
import com.wxb.wanshu.ui.activity.SearchActivity;
import com.wxb.wanshu.ui.contract.OrderListContract;
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
    BookApi bookApi;

    @Inject
    public SearchPresenter(BookApi bookApi) {
        this.bookApi = bookApi;
    }

    @Override
    public void getHotNovelList() {
        Subscription rxSubscription = bookApi.getHotNovelList().subscribeOn(Schedulers.io())
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
    public void getBookList(int sex_type,String category_id, String complete_status,int page,String kw) {
        Subscription rxSubscription = bookApi.getSelectBookList(sex_type,category_id,complete_status,page,kw).subscribeOn(Schedulers.io())
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
