package com.wxb.wanshu.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.base.Constant;
import com.wxb.wanshu.component.AppComponent;

import org.apache.http.util.EntityUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.data;

public class WanshuLoginActivity extends BaseActivity {


    @BindView(R.id.qq_login)
    TextView qqLogin;
    @BindView(R.id.weixin_login)
    TextView weixinLogin;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    private UMShareAPI mShareAPI = null;

    @Override
    public int getLayoutId() {
        return R.layout.wanshu_login;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("登录");
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        //首先获取UMShareAPI
        mShareAPI = UMShareAPI.get(this);
    }

    @Override
    public void configViews() {
        tvTip.setText(Html.fromHtml("登录表示您已经阅读并同意<font color=#E8554D>《用户使用协议》</font>"));
    }

    public static String INTENT_COME_ID = "come";

    public static void startActivity(Context context, int bookId) {
        context.startActivity(new Intent(context, WanshuLoginActivity.class)
                .putExtra(INTENT_COME_ID, bookId));
    }

    public static void startActivityFor(Activity context, int requestId) {
        context.startActivityForResult(new Intent(context, WanshuLoginActivity.class), requestId);
    }


    @OnClick({R.id.weixin_login, R.id.qq_login, R.id.tv_tip})
    public void onViewClicked(View view) {
        SHARE_MEDIA platform = null;

        switch (view.getId()) {
            case R.id.weixin_login:
                platform = SHARE_MEDIA.WEIXIN;

//                String WECHAT_APP_ID = Constant.WEXIN_APPID;
//                IWXAPI api;
//                api = WXAPIFactory.createWXAPI(this,WECHAT_APP_ID,true);
//                api.registerApp(WECHAT_APP_ID);
//
//                SendAuth.Req req = new SendAuth.Req();
//                req.scope = "snsapi_userinfo";
//                req.state = "wechat_sdk_demo_test";
//                api.sendReq(req);
                break;
            case R.id.qq_login:
                platform = SHARE_MEDIA.QQ;
                break;
            case R.id.tv_tip:
                break;
        }
        mShareAPI.getPlatformInfo(this, platform, authListener);
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(mContext, "成功了", Toast.LENGTH_LONG).show();

            Intent intent = new Intent();
            intent.putExtra("name", data.get("name"));
            intent.putExtra("head", data.get("iconurl"));
            setResult(RESULT_OK, intent);
            finish();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(mContext, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
