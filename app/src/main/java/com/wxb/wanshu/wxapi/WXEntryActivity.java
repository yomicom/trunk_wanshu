package com.wxb.wanshu.wxapi;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.weixin.view.WXCallbackActivity;
import com.wxb.wanshu.base.Constant;
import com.wxb.wanshu.utils.ToastUtils;

import org.apache.http.util.EntityUtils;

public class WXEntryActivity extends WXCallbackActivity{
//
//    private IWXAPI api;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        api = WXAPIFactory.createWXAPI(this, Constant.WEXIN_APPID, false);
//        try {
//            api.handleIntent(getIntent(), this);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onReq(BaseReq req) {
//        super.onReq(req);
//        if (req.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//        }
//    }
//
//    @Override
//    public void onResp(BaseResp resp) {
//        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            int errCode = resp.errCode;
//            if (errCode == 0) {
//                ToastUtils.showToast("支付成功");
//            } else {
//                ToastUtils.showToast("支付失败");
//            }
//        }
//    }


}
