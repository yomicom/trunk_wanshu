package com.wxb.wanshu.ui.presenter;

import com.wxb.wanshu.api.BookApi;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.BookMenu;
import com.wxb.wanshu.bean.UserOrder;
import com.wxb.wanshu.ui.contract.MenuContract;
import com.wxb.wanshu.ui.contract.OrderListContract;
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
    BookApi bookApi;

    @Inject
    public BookMenuPresenter(BookApi bookApi) {
        this.bookApi = bookApi;
    }

    @Override
    public void getBookMenu(int novel_id) {
        Subscription rxSubscription = bookApi.getBookMixAToc(novel_id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookMenu>() {
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
                    public void onNext(BookMenu data) {
                        mView.showBookMenu(data);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
