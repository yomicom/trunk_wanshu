package com.wxb.wanshu;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.wxb.wanshu.utils.ImageUtils;

public class ImageActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Window window = getWindow();
//默认API 最低19
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup contentView = window.getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
            contentView.getChildAt(0).setFitsSystemWindows(false);
        }
//       ImageView imageView = findViewById(R.id.imageview);
//        ImageUtils.displayImage(this,imageView,"https://www.google.com.hk/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwi-_7OB-M3eAhUTPXAKHQUlCe8QjRx6BAgBEAU&url=https%3A%2F%2Fstars.udn.com%2Fstar%2Fstory%2F10091%2F3026669&psig=AOvVaw2WJmdLBr_dRnwHWR2shMbX&ust=1542080362790879");
    }

}
