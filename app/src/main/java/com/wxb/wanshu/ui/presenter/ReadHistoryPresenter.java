package com.wxb.wanshu.ui.presenter;

import com.wxb.wanshu.api.BookApi;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.ReadHistoryList;
import com.wxb.wanshu.bean.UserOrder;
import com.wxb.wanshu.ui.activity.ListActivity.ReadHistoryActivity;
import com.wxb.wanshu.ui.contract.OrderListContract;
import com.wxb.wanshu.ui.contract.ReadHistoryContract;
import com.wxb.wanshu.utils.LogUtils;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiming on 2017/11/30.
 */

public class ReadHistoryPresenter extends RxPresenter<ReadHistoryContract.View> implements ReadHistoryContract.Presenter<ReadHistoryContract.View> {
    BookApi bookApi;

    @Inject
    public ReadHistoryPresenter(BookApi bookApi) {
        this.bookApi = bookApi;
    }

    @Override
    public void getReadHistoryList(int page) {
        Subscription rxSubscription = bookApi.getReadHistoryList(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReadHistoryList>() {
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
                    public void onNext(ReadHistoryList data) {
                        mView.showReadHistoryList(data);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
