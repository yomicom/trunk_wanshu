package com.wxb.wanshu.ui.presenter;

import com.wxb.wanshu.R;
import com.wxb.wanshu.api.Api;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.ReadHistoryList;
import com.wxb.wanshu.ui.contract.ReadHistoryContract;
import com.wxb.wanshu.utils.LogUtils;
import com.wxb.wanshu.utils.ToastUtils;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiming on 2017/11/30.
 */

public class ReadHistoryPresenter extends RxPresenter<ReadHistoryContract.View> implements ReadHistoryContract.Presenter<ReadHistoryContract.View> {
    Api api;

    @Inject
    public ReadHistoryPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getReadHistoryList(int page) {
        Subscription rxSubscription = api.getReadHistoryList(page).subscribeOn(Schedulers.io())
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

    @Override
    public void addBookShelf(String novel_id) {

        Subscription subscribe = api.addBookshelfList(novel_id).subscribeOn(Schedulers.io())
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
                    public void onNext(Base result) {
                        if (result.errcode == 0) {
                            mView.addBookResult(novel_id);
                        } else {
                            ToastUtils.showToast(R.string.add_shlef_fail + result.message);
                        }
                    }
                });
        addSubscrebe(subscribe);
    }

    @Override
    public void delHistory(String novel_ids) {

        Subscription subscribe = api.delReadHistoryList(novel_ids).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Base>() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.getMessage();
                    }

                    @Override
                    public void onNext(Base result) {
                        if (result.errcode == 0) {
                            mView.delHistoryBookResult(novel_ids);
                        } else {
                            ToastUtils.showToast(result.message);
                        }
                    }
                });
        addSubscrebe(subscribe);
    }
}
