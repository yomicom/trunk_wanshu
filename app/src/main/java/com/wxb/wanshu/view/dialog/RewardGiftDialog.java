package com.wxb.wanshu.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.ReaderApplication;
import com.wxb.wanshu.api.Api;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.RewardType;
import com.wxb.wanshu.bean.SimpleEventBus;
import com.wxb.wanshu.bean.UserInfo;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.ui.activity.RechargeAmountActivity;
import com.wxb.wanshu.ui.adapter.easyadpater.GiftTypeAdapter;
import com.wxb.wanshu.utils.ToastUtils;
import com.wxb.wanshu.view.recycleview.decoration.SupportGridItemDecoration;

import org.simple.eventbus.EventBus;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by qiming on 2017/12/6.
 */

public class RewardGiftDialog implements View.OnClickListener {

    private static int amount;

    public static PopupWindow shareView(Activity context, int baseId,
                                        RewardType rewardType, String title, int novel_id) {
        CompositeSubscription mCompositeSubscription = null;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_send_gift, null);

        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        ImageView ivClose = (ImageView) view.findViewById(R.id.iv_close);
        RecyclerView ricycleview = (RecyclerView) view.findViewById(R.id.ricycleview);
        TextView tvReduceCount = (TextView) view.findViewById(R.id.tv_reduce_count);
        TextView tvGiftCount = (TextView) view.findViewById(R.id.tv_gift_count);
        TextView tvAddCount = (TextView) view.findViewById(R.id.tv_add_count);
        TextView tvBookCoin = (TextView) view.findViewById(R.id.tv_book_coin);
        TextView tvNeedPay = (TextView) view.findViewById(R.id.tv_need_pay);
        Button btAddMoney = (Button) view.findViewById(R.id.bt_add_money);

        tvTitle.setText(title);
        RewardType.DataBean type0 = rewardType.data.get(0);
        tvNeedPay.setText(type0.getAmount() + "书币");

        Api api = ReaderApplication.getsInstance().getAppComponent().getReaderApi();
        Subscription subscribe = api.getUserInfo().subscribeOn(Schedulers.io())
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
                    }
                });
        addSubscrebe(mCompositeSubscription, subscribe);

        GridLayoutManager layoutManager = new GridLayoutManager(context, 4, LinearLayoutManager.VERTICAL, false);
        ricycleview.setLayoutManager(layoutManager);
        ricycleview.addItemDecoration(new SupportGridItemDecoration(context));

        GiftTypeAdapter adapter = new GiftTypeAdapter(context, rewardType.getData(), new OnRvItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Object data) {
                tvGiftCount.setText("1");
                RewardType.DataBean type = (RewardType.DataBean) data;
                tvNeedPay.setText(type.getAmount() + "书币");
            }
        });
        ricycleview.setAdapter(adapter);


        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        tvAddCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPayCoin(1, tvGiftCount, rewardType, adapter, tvNeedPay, btAddMoney);
            }
        });
        tvReduceCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPayCoin(-1, tvGiftCount, rewardType, adapter, tvNeedPay, btAddMoney);

            }
        });
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unSubscribe(mCompositeSubscription);
                window.dismiss();
            }
        });

        btAddMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("打赏".equals(btAddMoney.getText().toString())) {//书币充足时，去打赏
                    int giftCount = Integer.parseInt((String) tvGiftCount.getText());
                    String type = adapter.getData(adapter.getSelectedPos()).getType();

                    Subscription subscribe = api.rewardGift(type, giftCount, novel_id, "").subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<Base>() {
                                @Override
                                public void onCompleted() {
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(Base data) {
                                    if (data.getErrcode() == 0) {
                                        ToastUtils.showLongToast("打赏成功");
                                        EventBus.getDefault().post(new SimpleEventBus(true));
                                    }
                                    window.dismiss();
                                }
                            });
                    addSubscrebe(mCompositeSubscription, subscribe);
                } else {//书币不足时，去充值
                    RechargeAmountActivity.startActivity(context);
                    window.dismiss();
                }
            }
        });
        setPopuWindow(context, baseId, window);

        return window;
    }

    private static void showPayCoin(int add, TextView tvGiftCount, RewardType rewardType, GiftTypeAdapter adapter, TextView tvNeedPay, Button btAddMoney) {
        int giftCount = Integer.parseInt(tvGiftCount.getText().toString());
        if (giftCount > 0) {
            giftCount += add;

            tvGiftCount.setText(giftCount + "");
            RewardType.DataBean type = rewardType.getData().get(adapter.getSelectedPos());
            tvNeedPay.setText((type.getAmount() * giftCount) + "书币");

            int coast = adapter.getData(adapter.getSelectedPos()).getAmount() * giftCount;
            if (amount >= coast) {
                btAddMoney.setText("打赏");
            } else {
                btAddMoney.setText("余额不足，去充值");
            }
        }
    }

    private static void setPopuWindow(final Activity context, int baseId, PopupWindow window) {
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);
        // 设置popWindow的背景色
        window.setBackgroundDrawable(new ColorDrawable());
        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.popwindow_anim_style);
        // 在底部显示
        window.showAtLocation(context.findViewById(baseId),
                Gravity.BOTTOM, 0, 0);

        //设置背景变暗
        final WindowManager.LayoutParams params = context.getWindow().getAttributes();
        params.alpha = 0.8f;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                context.getWindow().setAttributes(params);
            }
        }, 500);

        //popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        context.getWindow().setAttributes(params);
                    }
                }, 200);
            }
        });
    }


    @Override
    public void onClick(View view) {

    }

    protected static void unSubscribe(CompositeSubscription mCompositeSubscription) {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    protected static void addSubscrebe(CompositeSubscription mCompositeSubscription, Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

}
