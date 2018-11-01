package com.wxb.wanshu.ui.presenter;

import com.wxb.wanshu.api.BookApi;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.BookList;
import com.wxb.wanshu.bean.UserOrder;
import com.wxb.wanshu.ui.contract.OrderListContract;
import com.wxb.wanshu.ui.contract.SelectBooksContract;
import com.wxb.wanshu.utils.LogUtils;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiming on 2017/11/30.
 */

public class OrderListPresenter extends RxPresenter<OrderListContract.View> implements OrderListContract.Presenter<OrderListContract.View> {
    BookApi bookApi;

    @Inject
    public OrderListPresenter(BookApi bookApi) {
        this.bookApi = bookApi;
    }

    @Override
    public void getOrderList(int page) {
        Subscription rxSubscription = bookApi.getOrderList(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserOrder>() {
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
                    public void onNext(UserOrder data) {
                        mView.showOrderList(data);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
