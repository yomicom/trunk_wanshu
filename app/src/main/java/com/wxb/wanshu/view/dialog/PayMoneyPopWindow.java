package com.wxb.wanshu.view.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.wxb.wanshu.R;
import com.wxb.wanshu.ReaderApplication;
import com.wxb.wanshu.api.BookApi;
import com.wxb.wanshu.base.Constant;
import com.wxb.wanshu.bean.BookDetails;
import com.wxb.wanshu.bean.UserInfo;
import com.wxb.wanshu.ui.activity.RechargeAmountActivity;
import com.wxb.wanshu.utils.FormatUtils;
import com.wxb.wanshu.utils.OrderInfoUtil;
import com.wxb.wanshu.utils.PayResult;

import java.util.Map;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.wxb.wanshu.R.id.tv;

/**
 * Created by qiming on 2017/12/21.
 */

public class PayMoneyPopWindow extends PopupWindow implements View.OnClickListener {

    private View mPopView;
    private OnItemClickListener mListener;

    private BookApi bookApi;
    protected CompositeSubscription mCompositeSubscription;
    Activity mContext;

    int pay;//需花费金额
    private LinearLayout idPopLayout;
    private TextView tvTitle;
    private ImageView ivClose;
    private RelativeLayout chooseAlipay;
    private ImageView ivSelectAlipay;
    private RelativeLayout chooseWeixinPay;
    private ImageView ivSelectWeixin;
    private Button btAddMoney;

    private static final int SDK_PAY_FLAG = 1;
    int payWay = 1;
    int WEIXIN_PAY = 2;
    int ALI_PAY = 1;

    public PayMoneyPopWindow(Activity context) {
        super(context);
        bookApi = ReaderApplication.getsInstance().getAppComponent().getReaderApi();
        mContext = context;

        init(context);
        setPopupWindow();

    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //绑定布局
        mPopView = inflater.inflate(R.layout.pop_pay_way, null);

        initView(mPopView);

        setView();
    }

    private void setView() {
        btAddMoney.setText("确认支付 ¥" + pay);

        chooseAlipay.setOnClickListener(this);
        chooseWeixinPay.setOnClickListener(this);
        btAddMoney.setOnClickListener(this);
        ivClose.setOnClickListener(this);
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
                // TODO Auto-generated method stub
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

    private void initView(View view) {
        idPopLayout = (LinearLayout) view.findViewById(R.id.id_pop_layout);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        ivClose = (ImageView) view.findViewById(R.id.iv_close);
        chooseAlipay = (RelativeLayout) view.findViewById(R.id.choose_alipay);
        ivSelectAlipay = (ImageView) view.findViewById(R.id.iv_select_alipay);
        chooseWeixinPay = (RelativeLayout) view.findViewById(R.id.choose_weixin_pay);
        ivSelectWeixin = (ImageView) view.findViewById(R.id.iv_select_weixin);
        btAddMoney = (Button) view.findViewById(R.id.bt_add_money);
    }

    /**
     * 定义一个接口，公布出去 在Activity中操作按钮的单击事件
     */
    public interface OnItemClickListener {
        void setOnItemClick(View v);
    }

    public void setPay(int pay) {
        final WindowManager.LayoutParams params = mContext.getWindow().getAttributes();
        params.alpha = 0.8f;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mContext.getWindow().setAttributes(params);
            }
        }, 00);
        this.pay = pay;
        btAddMoney.setText("确认支付" + pay + "元");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_alipay:
                if (payWay != ALI_PAY) {
                    payWay = ALI_PAY;
                    ivSelectAlipay.setImageResource(R.mipmap.ic_choose_book);
                    ivSelectWeixin.setImageResource(R.mipmap.ic_no_pay);
                }
                break;
            case R.id.choose_weixin_pay:
                if (payWay != WEIXIN_PAY) {
                    payWay = WEIXIN_PAY;
                    ivSelectAlipay.setImageResource(R.mipmap.ic_no_pay);
                    ivSelectWeixin.setImageResource(R.mipmap.ic_choose_book);
                }
                break;
            case R.id.bt_add_money:
                if (payWay == ALI_PAY) {
                    AliPay();
                } else {
                    WeinxinPay();
                }
                break;
            case R.id.iv_close:
                dismiss();
                break;
        }
//            Subscription subscribe = bookApi.getBookDetail(0).subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer<BookDetails>() {
//                        @Override
//                        public void onCompleted() {
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//
//                        }
//
//                        @Override
//                        public void onNext(BookDetails data) {
//                            dismiss();
//                        }
//                    });
//            addSubscrebe(subscribe);
    }

    /**
     * 支付宝支付
     */
    private void AliPay() {
        String orderInfo = OrderInfoUtil.buildOrderParamMap((Activity) mContext, Constant.ALIPAY_APPID, pay);

        String sign = OrderInfoUtil.getSign(mContext, orderInfo);

        final String payInfo = orderInfo + "&" + sign;
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask((Activity) mContext);
                Map<String, String> result = alipay.payV2(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    dismiss();
                    break;
                }
                default:
                    break;
            }
        }

    };

    /**
     * 微信支付
     */
    private void WeinxinPay() {
        IWXAPI api = new IWXAPI() {
            @Override
            public boolean registerApp(String s) {
                return false;
            }

            @Override
            public boolean registerApp(String s, long l) {
                return false;
            }

            @Override
            public void unregisterApp() {

            }

            @Override
            public boolean handleIntent(Intent intent, IWXAPIEventHandler iwxapiEventHandler) {
                return false;
            }

            @Override
            public boolean isWXAppInstalled() {
                return false;
            }

            @Override
            public boolean isWXAppSupportAPI() {
                return false;
            }

            @Override
            public int getWXAppSupportAPI() {
                return 0;
            }

            @Override
            public boolean openWXApp() {
                return false;
            }

            @Override
            public boolean sendReq(BaseReq baseReq) {
                return false;
            }

            @Override
            public boolean sendResp(BaseResp baseResp) {
                return false;
            }

            @Override
            public void detach() {

            }
        };

        PayReq request = new PayReq();

        request.appId = Constant.WEXIN_APPID;

        request.partnerId = "1900000109";

        request.prepayId = "1101000000140415649af9fc314aa427";

        request.packageValue = "Sign=WXPay";

        request.nonceStr = "1101000000140429eb40476f8896f4c9";

        request.timeStamp = (System.currentTimeMillis() + "").substring(0, 10);

        request.sign = "7FFECB600D7157C5AA49810D2D8F28BC2811827B";

        api.sendReq(request);
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
