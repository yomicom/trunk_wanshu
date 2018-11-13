package com.wxb.wanshu.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wxb.wanshu.MainActivity;
import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.ui.presenter.PermissionUtils;
import com.wxb.wanshu.utils.LogUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.welcome)
    RelativeLayout welcome;
    private AlphaAnimation alphaAnimation;
    private static final String TAG = "SplashActivity";

    final private static int PERMISSIONS_CODE = 29; // 请求码

    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_NETWORK_STATE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_SETTINGS,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    private PermissionsChecker permissionsChecker;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        permissionsChecker = new PermissionsChecker(this);
    }

    @Override
    protected void onResume() {
//        if (permissionsChecker.lacksPermissions(PERMISSIONS)) {
//            startPermissionsActivity();
//        } else {
//            showMainActivity();
//            finish();
//        }
        super.onResume();
    }

    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, PERMISSIONS_CODE, PERMISSIONS);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
    // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
//        if (requestCode == PERMISSIONS_CODE &&
//                resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
//        } else {
//            showMainActivity();
//        }
//        finish();
//    }

    private void showMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        alphaAnimation = new AlphaAnimation(0.3F, 1.0F);
        alphaAnimation.setDuration(1000);
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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                    10);
        } else {
            writeSettingsPermission();
        }
//        if (PermissionUtils.CheckPermission(PermissionUtils.READ_EXTERNAL_STORAGE, (Activity) mContext)
//                && PermissionUtils.CheckPermission(PermissionUtils.WRITE_EXTERNAL_STORAGE, (Activity) mContext)) {
//        } else {
//            PermissionUtils.verifyStoragePermissions((Activity) mContext);
//        }

    }

    public void jumpToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    int REQUEST_CODE_WRITE_SETTINGS = 10;

    private void writeSettingsPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("WRITE_SETTINGS权限申请")
                        .setMessage("点击OK进入设置界面授予权限")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //核心代码
                                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
                                        Uri.parse("package:" + getPackageName()));
                                startActivityForResult(intent, REQUEST_CODE_WRITE_SETTINGS);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {writeSettingsPermission();
                            }
                        })
                        .create();
                dialog.show();
            } else {
                welcome.startAnimation(alphaAnimation);
            }
        }else {
            welcome.startAnimation(alphaAnimation);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Settings.System.canWrite(this) && requestCode == REQUEST_CODE_WRITE_SETTINGS) {//屏幕亮度权限
            welcome.startAnimation(alphaAnimation);
//            Toast.makeText(this, "WRITE_SETTINGS permission granted", Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(this, "WRITE_SETINGS permission denied", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){ //同意权限申请
                    writeSettingsPermission();
                }else { //拒绝权限申请
//                    Toast.makeText(this,"权限被拒绝了",Toast.LENGTH_SHORT).show();
                    writeSettingsPermission();}
                break;
            default:
                break;
        }
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
    //        }
    //            }
    ////                showApkInstallDialog();
    //
    //                LogUtils.e("", "从安装页面回到欢迎页面--拒绝安装");
    //                // CnPeng 2018/8/2 下午4:31 在安装页面中退出安装了
    //            } else if (requestCode == 2) {
    //                }
    //                    }
    ////                        showUnKnowResourceDialog();
    //                        LogUtils.e("", "没有赋予 未知来源安装权限");
    //                    if (!hasInstallPermission) {
    //                    boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
    //                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    //                //CnPeng 2018/8/2 下午4:31 8.0手机位置来源安装权限
    //            if (requestCode == 1) {
    //        } else {
    //            }
    //                openAPKFile();
    //            if (requestCode == 1) {
    //        if (resultCode == RESULT_OK) {
    //        super.onActivityResult(requestCode, resultCode, data);
    //    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//    @Override

//    }

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
