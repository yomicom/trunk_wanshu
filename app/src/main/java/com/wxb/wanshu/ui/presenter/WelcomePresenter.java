package com.wxb.wanshu.ui.presenter;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Handler;

import com.wxb.wanshu.base.BaseContract;
import com.wxb.wanshu.ui.contract.IWelcomeView;

public class WelcomePresenter implements BaseContract.BasePresenter<IWelcomeView> {
    @Override
    public void attachView(IWelcomeView view) {

    }

    @Override
    public void detachView() {

    }

    private Handler mhandler;
    private Activity mContext;
    private closeRunnable runnable;
    private boolean isShowAd;
    public WelcomePresenter(Activity context, IWelcomeView view) {
        mhandler = new Handler(context.getMainLooper());
        mContext = context;
        runnable = new closeRunnable();
        isShowAd = false;
    }

    public void CheckPermission(){
        //检查读写权限
        if(PermissionUtils.CheckPermission(PermissionUtils.READ_EXTERNAL_STORAGE,mContext)&&PermissionUtils.CheckPermission(PermissionUtils.WRITE_EXTERNAL_STORAGE,mContext)){
            init();
        }else{
            PermissionUtils.verifyStoragePermissions(mContext);
        }
    }

    /**
     * 初始化
     */
    public void init(){
        mhandler.postDelayed(runnable,2000);
//        FileUtil.init();
//        long lasttime = Hawk.get(Constants.LAST_START_TIME,0L);
//        if(lasttime!=0&&getCurrentTime()-lasttime>=24*60*60*1000*Constants.CACHE_DAYS){
//            //mView.ShowToast("距离上次大于一天");
//            GlideCacheUtil.getInstance().clearImageAllCache(mContext);
//            LogUtil.d("距离上次启动大于一天，清除缓存");
//        }else{
//            LogUtil.d("距离上次启动小于一天，不清除缓存");
//        }
//        Hawk.put(Constants.LAST_START_TIME,getCurrentTime());
    }

    /**
     * 根据请求权限的结果
     * @param grantResult
     */
    public void requestPermission(int grantResult) {
        switch (grantResult){
            case PackageManager.PERMISSION_GRANTED:
                init();
                break;
            case PackageManager.PERMISSION_DENIED:
//                mView.ShowToast("请开启SD卡权限");
                mContext.finish();
                break;
            default:
                break;
        }
    }


    class closeRunnable implements Runnable{

        @Override
        public void run() {
            mContext.finish();
//            IntentUtil.ToMainActivity(mContext);
        }
    }

    public void onDestory(){
        if(!isShowAd){
            mhandler.removeCallbacks(runnable);
        }
    }
}
