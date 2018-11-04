package com.wxb.wanshu.ui.presenter;

import com.wxb.wanshu.api.Api;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.NovelRank;
import com.wxb.wanshu.ui.contract.NovelRankContract;
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
    Api api;

    @Inject
    public NovelRankPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getNovelRank(int type, int sex_type, int page) {
        Subscription rxSubscription = api.getRankBookList(type, sex_type, page).subscribeOn(Schedulers.io())
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
