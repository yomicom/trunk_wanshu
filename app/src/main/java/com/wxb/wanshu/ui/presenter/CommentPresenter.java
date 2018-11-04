package com.wxb.wanshu.ui.presenter;

import com.wxb.wanshu.api.Api;
import com.wxb.wanshu.base.RxPresenter;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.UploadPictureBean;
import com.wxb.wanshu.ui.contract.CommentContract;
import com.wxb.wanshu.utils.LogUtils;

import java.io.File;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiming on 2017/11/30.
 */

public class CommentPresenter extends RxPresenter<CommentContract.View> implements CommentContract.Presenter<CommentContract.View> {
    Api api;

    @Inject
    public CommentPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getUploadSinglePicture(File file) {
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("files", file.getName(), requestFile);

        Subscription rxSubscription = api.uploadSinglePicture(part).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UploadPictureBean>() {
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
                    public void onNext(UploadPictureBean data) {
                        mView.showUploadSinglePicture(data);
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void sendComment(String data) {
        Subscription rxSubscription = api.sendComment(data).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Base>() {
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
                    public void onNext(Base data) {
                        mView.getCommentResult(data);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
