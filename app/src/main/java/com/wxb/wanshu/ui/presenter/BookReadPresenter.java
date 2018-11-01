/**
 * Copyright 2016 JustWayward Team
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wxb.wanshu.ui.presenter;

import android.content.Context;

import com.wxb.wanshu.api.BookApi;
import com.wxb.wanshu.base.ChapterRead;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.BookMenu;
import com.wxb.wanshu.bean.RewardType;
import com.wxb.wanshu.ui.contract.BookReadContract;
import com.wxb.wanshu.utils.LogUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author lfh.
 * @date 2016/8/7.
 */
public class BookReadPresenter extends RxPresenter<BookReadContract.View>
        implements BookReadContract.Presenter<BookReadContract.View> {

    private Context mContext;
    private BookApi bookApi;

    @Inject
    public BookReadPresenter(Context mContext, BookApi bookApi) {
        this.mContext = mContext;
        this.bookApi = bookApi;
    }

    @Override
    public void getBookMixAToc(final int novel_id) {
//        String key = StringUtils.creatAcacheKey("book-toc", novel_id);
//        Observable<BookMenu.DataBean> fromNetWork = bookApi.getBookMixAToc(novel_id)
//                .map(new Func1<BookMenu, BookMenu.DataBean>() {
//                    @Override
//                    public BookMenu.DataBean call(BookMenu data) {
//                        return data.data;
//                    }
//                })
//                .compose(RxUtil.<BookMenu.DataBean>rxCacheListHelper(key));
//
//        //依次检查disk、network
//        Subscription rxSubscription = Observable
//                .concat(RxUtil.rxCreateDiskObservable(key, BookMenu.DataBean.class), fromNetWork)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<BookMenu.DataBean>() {
//                    @Override
//                    public void onNext(BookMenu.DataBean data) {
//                        List<BookMenu.DataBean.ChaptersBean> list = data.getChapters();
//                        if (list != null && !list.isEmpty() && mView != null) {
//                            mView.showBookToc(list);
//                        }
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        LogUtils.e("onError: " + e);
//                        mView.netError(0);
//                    }
//                });
//        addSubscrebe(rxSubscription);

        Subscription rxSubscription = bookApi.getBookMixAToc(novel_id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookMenu>() {
                    @Override
                    public void onNext(BookMenu data) {
                        List<BookMenu.DataBean.ChaptersBean> list = data.getData().chapters;
                        if (list != null && !list.isEmpty() && mView != null) {
                            mView.showBookToc(list);
                        }
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("onError: " + e);
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getChapterRead(int novel_id, final int chapter) {
        Subscription rxSubscription = bookApi.getChapterRead(novel_id, chapter).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChapterRead>() {
                    @Override
                    public void onNext(ChapterRead data) {
                        if (data.data != null && mView != null) {
                            mView.showChapterRead(data.data);
                        } else {
                            mView.netError(chapter);
                        }
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("onError: " + e);
                        mView.netError(chapter);
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getRewardType() {
        bookApi.getRewardType().subscribeOn(Schedulers.io())
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
    }

    @Override
    public void addBookShelf(int novel_id) {

        bookApi.addBookshelfList(novel_id).subscribeOn(Schedulers.io())
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
    }
}