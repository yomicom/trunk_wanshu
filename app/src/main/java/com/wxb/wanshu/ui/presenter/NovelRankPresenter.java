package com.wxb.wanshu.ui.presenter;

import com.wxb.wanshu.api.BookApi;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.bean.NovelRank;
import com.wxb.wanshu.bean.UserOrder;
import com.wxb.wanshu.ui.contract.NovelRankContract;
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

public class NovelRankPresenter extends RxPresenter<NovelRankContract.View> implements NovelRankContract.Presenter<NovelRankContract.View> {
    BookApi bookApi;

    @Inject
    public NovelRankPresenter(BookApi bookApi) {
        this.bookApi = bookApi;
    }

    @Override
    public void getNovelRank(int type, int sex_type, int page) {
        Subscription rxSubscription = bookApi.getRankBookList(type, sex_type, page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NovelRank>() {
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
                    public void onNext(NovelRank data) {
                        mView.showNovelRank(data);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
