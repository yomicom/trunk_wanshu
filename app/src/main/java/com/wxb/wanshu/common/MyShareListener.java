package com.wxb.wanshu.common;

import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.wxb.wanshu.utils.ToastUtils;

/**
 * Created by qiming on 2017/12/6.
 */

public class MyShareListener implements UMShareListener {
    @Override
    public void onStart(SHARE_MEDIA share_media) {
    }


    @Override
    public void onResult(SHARE_MEDIA share_media) {
        ToastUtils.showLongToast("分享成功");
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        ToastUtils.showLongToast("分享失败");
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {

    }
}
