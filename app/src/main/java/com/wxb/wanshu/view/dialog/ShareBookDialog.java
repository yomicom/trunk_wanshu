package com.wxb.wanshu.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.wxb.wanshu.MyApplication;
import com.wxb.wanshu.R;
import com.wxb.wanshu.api.Api;
import com.wxb.wanshu.bean.BookDetails;
import com.wxb.wanshu.common.MyShareListener;
import com.wxb.wanshu.utils.ImageUtils;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by qiming on 2017/12/5.
 */

public class ShareBookDialog extends Dialog implements View.OnClickListener {


    Context context;
    View registerView;

    BookDetails.DataBean bookDetails;
    private ImageView ivBook;
    private TextView tvTitle;
    private TextView tvQq;
    private TextView tvWeixin;
    private TextView tvFriend;
    private Api api;
    protected CompositeSubscription mCompositeSubscription;
    String novel_id;

    public ShareBookDialog(Context context, String novel_id) {
        super(context);
        this.context = context;
        this.novel_id = novel_id;
        api = MyApplication.getsInstance().getAppComponent().getReaderApi();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 这句代码换掉dialog默认背景，否则dialog的边缘发虚透明而且很宽
        // 总之达不到想要的效果
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        registerView = View.inflate(this.context, R.layout.layout_share_dialog, null);
        setContentView(registerView);
        // 这句话起全屏的作用
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        initView();
        initData();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.dismiss();
        return super.onTouchEvent(event);
    }

    private void initData() {

        Subscription subscribe = api.getBookDetail(novel_id,0,0).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookDetails>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BookDetails data) {
                        bookDetails = data.getData();
                        ImageUtils.displayImage(context, ivBook, bookDetails.getCover(), R.drawable.defalt_book_cover, R.drawable.defalt_book_cover);
                        tvTitle.setText("把《" + bookDetails.getName() + "》");
                    }
                });
        addSubscrebe(subscribe);
    }


    private void initView() {
        ivBook = (ImageView) findViewById(R.id.iv_book);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvQq = (TextView) findViewById(R.id.tv_qq);
        tvWeixin = (TextView) findViewById(R.id.tv_weixin);
        tvFriend = (TextView) findViewById(R.id.tv_friend);

        tvQq.setOnClickListener(this);
        tvWeixin.setOnClickListener(this);
        tvFriend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        UMImage image = new UMImage(context, "https://avatar.csdn.net/F/1/D/3_huanglin_developer.jpg");
//        UMImage image = new UMImage(context, bookDetails.getCover());
        UMWeb web = new UMWeb("url");
        web.setTitle("");//标题
        web.setThumb(image);  //缩略图
        web.setDescription(bookDetails.getDescription());//描述

        switch (view.getId()) {
            case R.id.tv_qq:
                new ShareAction((Activity) context)
                        .setPlatform(SHARE_MEDIA.QQ)
                        .withMedia(web)
                        .setCallback(new MyShareListener())
                        .share();
                break;
            case R.id.tv_weixin:
                new ShareAction((Activity) context)
                        .setPlatform(SHARE_MEDIA.WEIXIN)
                        .withMedia(web)
                        .setCallback(new MyShareListener())
                        .share();
                break;
            case R.id.tv_friend:
                new ShareAction((Activity) context)
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                        .withMedia(web)
                        .setCallback(new MyShareListener())
                        .share();
                break;
            case R.id.iv_close:
                unSubscribe();
                dismiss();
                break;
        }
    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

}
