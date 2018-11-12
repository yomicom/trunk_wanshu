package com.wxb.wanshu.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.wxb.wanshu.MainActivity;
import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.utils.LogUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.welcome)
    RelativeLayout welcome;

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                    10);
        } else {

        }
//        if (PermissionUtils.CheckPermission(PermissionUtils.READ_EXTERNAL_STORAGE, (Activity) mContext)
//                && PermissionUtils.CheckPermission(PermissionUtils.WRITE_EXTERNAL_STORAGE, (Activity) mContext)) {
//        } else {
//            PermissionUtils.verifyStoragePermissions((Activity) mContext);
//        }

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3F, 1.0F);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jumpToMain();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        welcome.startAnimation(alphaAnimation);
    }

    public void jumpToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /**
     * 打开安装包
     */
    private void openAPKFile() {
        String mimeDefault = "application/vnd.android.package-archive";

        File apkFile = null;
//        if (!TextUtils.isEmpty(mApkUri)) {
//            //mApkUri是apk下载完成后在本地的存储路径
//            apkFile = new File(Uri.parse(mApkUri).getPath());
//        }
        if (apkFile == null) {
            return;
        }

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //兼容7.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                //这里牵涉到7.0系统中URI读取的变更
                Uri contentUri = FileProvider.getUriForFile(mContext, getPackageName() + ".fileprovider", apkFile);
                intent.setDataAndType(contentUri, mimeDefault);
                //兼容8.0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
                    if (!hasInstallPermission) {
                        startInstallPermissionSettingActivity();
                        return;
                    }
                }
            } else {
                intent.setDataAndType(Uri.fromFile(apkFile), mimeDefault);
            }
            if (getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
                //如果APK安装界面存在，携带请求码跳转。使用forResult是为了处理用户 取消 安装的事件。外面这层判断理论上来说可以不要，但是由于国内的定制，这个加上还是比较保险的
                startActivityForResult(intent, 2);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转到设置-允许安装未知来源-页面
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity() {
        //后面跟上包名，可以直接跳转到对应APP的未知来源权限设置界面。使用startActivityForResult 是为了在关闭设置界面之后，获取用户的操作结果，然后根据结果做其他处理
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                openAPKFile();
            }
        } else {
            if (requestCode == 1) {
                //CnPeng 2018/8/2 下午4:31 8.0手机位置来源安装权限
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
                    if (!hasInstallPermission) {
                        LogUtils.e("", "没有赋予 未知来源安装权限");
//                        showUnKnowResourceDialog();
                    }
                }
            } else if (requestCode == 2) {
                // CnPeng 2018/8/2 下午4:31 在安装页面中退出安装了
                LogUtils.e("", "从安装页面回到欢迎页面--拒绝安装");

//                showApkInstallDialog();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

//    /**
//     * 作者：CnPeng
//     * 时间：2018/8/2 下午6:06
//     * 功用：弹窗请安装APP的弹窗
//     * 说明：8.0手机升级APK时获取了未知来源权限，并跳转到APK界面后，用户可能会选择取消安装，所以，再给一个弹窗
//     */
//    private void showApkInstallDialog() {
//        final CustomAlertDialog installDialog = new CustomAlertDialog(mActivity);
//        installDialog.setCancelable(false);
//        DialogInstallApkBinding binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog_install_apk, null, false);
//        installDialog.setView(binding.getRoot());
//        installDialog.show();
//
//        binding.ivIKnowBt2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //再次回到安装界面
//                openAPKFile();
//            }
//        });
//
//        binding.tvInstallNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                installDialog.dismiss();
//
//                //CnPeng 2018/8/2 下午5:28  使用自定义方法关闭全部activity
//                ActivitiesCollector.finishAll();
//            }
//        });
//    }
//
//    /**
//     * 作者：CnPeng
//     * 时间：2018/8/2 下午5:50
//     * 功用：未知来源权限弹窗
//     * 说明：8.0系统中升级APK时，如果跳转到了 未知来源权限设置界面，并且用户没用允许该权限，会弹出此窗口
//     */
//    private void showUnKnowResourceDialog() {
//        final CustomAlertDialog alertDialog = new CustomAlertDialog(mActivity);
//        alertDialog.setCancelable(false);
//
//        DialogUnknowResourceBinding binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog_unknow_resource, null, false);
//        alertDialog.setView(binding.getRoot());
//        alertDialog.show();
//
//        binding.ivIKnowBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //兼容8.0
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
//                    if (!hasInstallPermission) {
//                        startInstallPermissionSettingActivity();
//                    }
//                }
//
//                alertDialog.dismiss();
//            }
//        });
//    }
}
