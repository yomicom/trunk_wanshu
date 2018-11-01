package com.wxb.wanshu.ui.presenter;

import com.wxb.wanshu.api.BookApi;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.BookList;
import com.wxb.wanshu.bean.NovelCategory;
import com.wxb.wanshu.manager.SettingManager;
import com.wxb.wanshu.ui.contract.SelectBooksContract;
import com.wxb.wanshu.utils.LogUtils;
import com.wxb.wanshu.utils.RxUtil;
import com.wxb.wanshu.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiming on 2017/11/30.
 */

public class SelectBookPresenter extends RxPresenter<SelectBooksContract.View> implements SelectBooksContract.Presenter<SelectBooksContract.View> {
    BookApi bookApi;

    @Inject
    public SelectBookPresenter(BookApi bookApi) {
        this.bookApi = bookApi;
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

    @Override
    public void getNovelCategory() {
        String key = StringUtils.creatAcacheKey("category-list", SettingManager.getInstance().getUserChooseSex());
        Observable<NovelCategory> fromNetWork = bookApi.getNovelCategory()
                .compose(RxUtil.<NovelCategory>rxCacheListHelper(key));

        //依次检查disk、network
        Subscription rxSubscription = Observable.concat(RxUtil.rxCreateDiskObservable(key, NovelCategory.class), fromNetWork)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NovelCategory>() {
                    @Override
                    public void onNext(NovelCategory recommend) {
                        if (recommend != null) {
                            List<NovelCategory.DataBean> list = recommend.getData();
                            if (list != null && !list.isEmpty() && mView != null) {
                                mView.showNovelCategory(list);
                            }
                        }
                    }

                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("getRecommendList", e.toString());
                        mView.showError();
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
