package com.wxb.wanshu.view;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wxb.wanshu.R;

import cn.bingoogolapple.bgabanner.BGABanner;

public class AlphaTitleScrollView extends ScrollView {
    public static final String TAG = "AlphaTitleScrollView";
    private int mSlop;
    private RelativeLayout mytitleview;
    LinearLayout bg;
    TextView text;
    ImageView imageView;
    private BGABanner banner;

    public AlphaTitleScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public AlphaTitleScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public AlphaTitleScrollView(Context context) {
        this(context, null);
    }

    private void init(Context context) {
        // mSlop = ViewConfiguration.get(context).getScaledDoubleTapSlop();
        mSlop = 10;
    }

    //Activity中调用,获取了本自定义View的控件id,就可调用.
    public void setTitleAndHead(RelativeLayout mytitleview, LinearLayout bg, TextView text, ImageView search, BGABanner banner) {
        this.mytitleview = mytitleview;  //搜索栏,头布局
        this.bg = bg;  //搜索栏背景
        this.text = text;  //搜索栏提示字
        this.banner = banner;   //需要和搜索栏对比滑动高度的bannerbuju,这里最上边的布局是banner,别的也行
        this.imageView = search;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        float headHeight = banner.getMeasuredHeight()
                - mytitleview.getMeasuredHeight();
        int alpha = (int) (((float) t / headHeight) * 255);
        if (alpha >= 255)
            alpha = 255;
        if (alpha <= mSlop)
            alpha = 0;

        if (t < headHeight) {//状态1
            text.setHintTextColor(getResources().getColor(R.color.white));
            bg.setBackgroundResource(R.drawable.bg_search_home);
            imageView.setImageResource(R.mipmap.ic_search_white);
        } else {
            text.setHintTextColor(getResources().getColor(R.color.text_hint_color));
            bg.setBackgroundResource(R.drawable.bg_search_home2);
            imageView.setImageResource(R.mipmap.ic_search);
        }
        mytitleview.setBackgroundColor(Color.argb(alpha, 255, 255, 255));

        super.onScrollChanged(l, t, oldl, oldt);
    }
}