package com.wxb.wanshu.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by qiming on 2017/11/29.
 */

public class ViewToolUtils {
    public static void getResourceColor(Context context, TextView view, int color) {
        view.setTextColor(ContextCompat.getColor(context, color));
    }
    public static void setBackgroundResourceColor(Context context, TextView view, int color) {
        view.setBackgroundColor(ContextCompat.getColor(context, color));
    }


    //EditText设置清除图标是否可见
    public static void showEditTextClean(EditText editText, ImageView imageView) {
        if ("".equals(editText.getText().toString())) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
        }
    }

    //EditText设置清除图标是否可见 并设置Image清除文字
    public static void showEditTextCleanAction(final EditText editText, final ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if ("".equals(editText.getText().toString())) {
                    imageView.setVisibility(View.GONE);
                } else {
                    if (editText.hasFocus())
                        imageView.setVisibility(View.VISIBLE);
                }
            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && !"".equals(((EditText) v).getText().toString())) {
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.GONE);
                }
            }
        });
    }

    //动态设置TextView左右Drawable
    public static void setTextViewLRDrawable(Context context, TextView editText, int imageLeft, int imageRight) {
        Drawable drawableLeft = null;
        if (0 != imageLeft) {/// 这一步必须要做,否则不会显示.
            drawableLeft = ViewToolUtils.getResourcesDrawable(context, imageLeft);
            drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
        }
        Drawable drawableRight = null;
        if (0 != imageRight) {
            drawableRight = ViewToolUtils.getResourcesDrawable(context, imageRight);
            drawableRight.setBounds(0, 0, drawableRight.getMinimumWidth(), drawableRight.getMinimumHeight());
        }
        editText.setCompoundDrawables(drawableLeft, null, drawableRight, null);
    }

    //动态设置TextView上下Drawable
    public static void setTextViewTBDrawable(Context context, TextView editText, int imageTop, int imageBottom) {
        Drawable drawableLeft = null;
        if (0 != imageTop) {/// 这一步必须要做,否则不会显示.
            drawableLeft = ViewToolUtils.getResourcesDrawable(context, imageTop);
            drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
        }
        Drawable drawableRight = null;
        if (0 != imageBottom) {
            drawableRight = ViewToolUtils.getResourcesDrawable(context, imageBottom);
            drawableRight.setBounds(0, 0, drawableRight.getMinimumWidth(), drawableRight.getMinimumHeight());
        }
        editText.setCompoundDrawables(null, drawableLeft, null, drawableRight);
    }

    public static Drawable getResourcesDrawable(Context context, int resId) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(resId, null);
        } else {
            return context.getResources().getDrawable(resId);
        }
    }


    /**
     * 设置可更多显示的介绍文本
     *
     * @param maxDescripLine
     * @param content
     * @param descriptionView
     * @param expandView
     * @param layoutView
     */
    public static void setShowMoreContent(int maxDescripLine, String content, TextView descriptionView, ImageView expandView, View layoutView) {
        descriptionView.setText(content);
        //descriptionView设置默认显示高度
        descriptionView.setHeight(descriptionView.getLineHeight() * maxDescripLine);
        //根据高度来判断是否需要再点击展开
        descriptionView.post(new Runnable() {

            @Override
            public void run() {
                expandView.setVisibility(descriptionView.getLineCount() > maxDescripLine ? View.VISIBLE : View.GONE);
            }
        });

        layoutView.setOnClickListener(new View.OnClickListener() {
            boolean isExpand;//是否已展开的状态

            @Override
            public void onClick(View v) {
                isExpand = !isExpand;
                descriptionView.clearAnimation();//清楚动画效果
                final int deltaValue;//默认高度，即前边由maxLine确定的高度
                final int startValue = descriptionView.getHeight();//起始高度
                int durationMillis = 200;//动画持续时间
                if (isExpand) {
                    /**
                     * 折叠动画
                     * 从实际高度缩回起始高度
                     */
                    deltaValue = descriptionView.getLineHeight() * descriptionView.getLineCount() - startValue;
                    RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    expandView.startAnimation(animation);
                } else {
                    /**
                     * 展开动画
                     * 从起始高度增长至实际高度
                     */
                    deltaValue = descriptionView.getLineHeight() * maxDescripLine - startValue;
                    RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    expandView.startAnimation(animation);
                }
                Animation animation = new Animation() {
                    protected void applyTransformation(float interpolatedTime, Transformation t) { //根据ImageView旋转动画的百分比来显示textview高度，达到动画效果
                        descriptionView.setHeight((int) (startValue + deltaValue * interpolatedTime));
                    }
                };
                animation.setDuration(durationMillis);
                descriptionView.startAnimation(animation);
            }
        });
    }

}
