package com.wxb.wanshu.view.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.ReaderApplication;
import com.wxb.wanshu.api.BookApi;
import com.wxb.wanshu.bean.BookDetails;
import com.wxb.wanshu.bean.UserInfo;
import com.wxb.wanshu.ui.activity.RechargeAmountActivity;
import com.wxb.wanshu.utils.ImageUtils;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by qiming on 2017/12/21.
 */

public class BuyBookPopupWindow extends PopupWindow implements View.OnClickListener {

    private View mPopView;
    private OnItemClickListener mListener;
    private TextView tvTitle;
    private ImageView ivClose;
    private TextView tvBookCoin;
    private TextView tvNeedPay;
    private LinearLayout llNextChapter;
    private ImageView ivSelect;
    private Button btAddMoney;

    private BookApi bookApi;
    protected CompositeSubscription mCompositeSubscription;
    Activity mContext;

    int novel_id;
    int chapter;
    int pay;//需花费金额
    private int amount;//账户余额

    public BuyBookPopupWindow(Activity context, int novel_id, int chapter) {
        super(context);
        bookApi = ReaderApplication.getsInstance().getAppComponent().getReaderApi();
        mContext = context;

        init(context);
        setPopupWindow();

        this.novel_id = novel_id;
        this.chapter = chapter;
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //绑定布局
        mPopView = inflater.inflate(R.layout.pop_buy_book, null);

        initView();

        setView();
    }

    private void setView() {

        Subscription subscribe = bookApi.getUserInfo().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInfo>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserInfo data) {
                        amount = data.getData().getAmount();
                        tvBookCoin.setText("余额：" + amount + "书币");

                        if (amount < pay) {
                            btAddMoney.setText("余额不足，去充值");
                        } else {
                            btAddMoney.setText("确定购买");
                        }
                    }
                });
        addSubscrebe(subscribe);
    }

    /**
     * 设置窗口的相关属性
     */
    @SuppressLint("InlinedApi")
    private void setPopupWindow() {
        this.setContentView(mPopView);// 设置View
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);// 设置弹出窗口的宽
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);// 设置弹出窗口的高
        this.setFocusable(true);// 设置弹出窗口可
        this.setAnimationStyle(R.style.popwindow_anim_style);// 设置动画
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));// 设置背景透明
        mPopView.setOnTouchListener(new View.OnTouchListener() {// 如果触摸位置在窗口外面则销毁

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = mPopView.findViewById(R.id.id_pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

        //设置背景变暗
        final WindowManager.LayoutParams params = mContext.getWindow().getAttributes();

        //popWindow消失监听方法
        setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mContext.getWindow().setAttributes(params);
                    }
                }, 200);
            }
        });
    }

    private void initView() {
        tvTitle = (TextView) mPopView.findViewById(R.id.tv_title);
        ivClose = (ImageView) mPopView.findViewById(R.id.iv_close);
        tvBookCoin = (TextView) mPopView.findViewById(R.id.tv_book_coin);
        tvNeedPay = (TextView) mPopView.findViewById(R.id.tv_need_pay);
        llNextChapter = (LinearLayout) mPopView.findViewById(R.id.ll_next_chapter);
        ivSelect = (ImageView) mPopView.findViewById(R.id.iv_select);
        btAddMoney = (Button) mPopView.findViewById(R.id.bt_add_money);

        btAddMoney.setOnClickListener(this);
        ivClose.setOnClickListener(this);
    }

    /**
     * 定义一个接口，公布出去 在Activity中操作按钮的单击事件
     */
    public interface OnItemClickListener {
        void setOnItemClick(View v);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_add_money) {

            /**
             * 购买书籍
             */
            if ("确定购买".equals(btAddMoney.getText().toString())) {
                Subscription subscribe = bookApi.getBookDetail(novel_id).subscribeOn(Schedulers.io())
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
                                dismiss();
                            }
                        });
                addSubscrebe(subscribe);
            } else {
                RechargeAmountActivity.startActivity(mContext);
                this.dismiss();
            }
        } else if (v.getId() == R.id.iv_close) {
            this.dismiss();
        }
    }

    public void setPay(int pay) {
        final WindowManager.LayoutParams params = mContext.getWindow().getAttributes();
        params.alpha = 0.8f;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mContext.getWindow().setAttributes(params);
            }
        }, 500);
        this.pay = pay;
        tvNeedPay.setText(pay + "书币");
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
