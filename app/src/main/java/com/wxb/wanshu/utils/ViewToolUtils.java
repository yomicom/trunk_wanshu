package com.wxb.wanshu.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.renderscript.Type;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;

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

    //将EditText的光标置于最后
    public static void setTextLast(EditText editText) {
        editText.postInvalidate();
        CharSequence charSequence = editText.getText();
        if (charSequence instanceof Spannable) {
            Spannable spannable = (Spannable) charSequence;
            Selection.setSelection(spannable, charSequence.length());
        }
    }

    private void dealNumber(String str, TextView textView) {
        if (str.equals("-")) {
            textView.setText("" + str);
        } else if (str.contains("+") || str.equals("100000")) {
            textView.setText("" + "10万+");
        } else {
            int num = Integer.parseInt(str);
            if (num > 9999) {
                double count = (double) num / 10000;
                BigDecimal b = new BigDecimal(count);
                double f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                textView.setText("" + f1 + "万");
            } else {
                textView.setText("" + str);
            }
        }
    }

    //获取系统状态栏高度
    public static int getStatusBarHeight() {
        int height = 0;
        int resourceId = AppUtils.getAppContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
            height = AppUtils.getAppContext().getResources().getDimensionPixelSize(resourceId);
        if (height > 0)
            return height;
        else return 25;
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

    //弹出键盘
    public static void showSoftInput(Context context) {
//        etSearch.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.RESULT_SHOWN);
    }

    //打开页面弹出键盘
    public static void showSoftInputDelay(final Context context) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, 500); // 秒后自动弹出
    }

    public static void KeyBoardCancle(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE); //得到InputMethodManager的实例
        if (imm.isActive()) {//如果开启
            //关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /**
     * 隐藏软键盘
     *
     * @param view
     */
    public static void hideSoftKeyboard(View view) {
        Context context = view.getContext();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 高斯模糊
     * @param context
     * @param source
     * @param radius
     * @param scale
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static Bitmap rsBlur(Context context, Bitmap source, int radius, float scale){

//        Log.i(TAG,"origin size:"+source.getWidth()+"*"+source.getHeight());
        int width = Math.round(source.getWidth() * scale);
        int height = Math.round(source.getHeight() * scale);

        Bitmap inputBmp = Bitmap.createScaledBitmap(source,width,height,false);

        RenderScript renderScript =  RenderScript.create(context);

//        Log.i(TAG,"scale size:"+inputBmp.getWidth()+"*"+inputBmp.getHeight());

        // Allocate memory for Renderscript to work with

        final Allocation input = Allocation.createFromBitmap(renderScript,inputBmp);
        final Allocation output = Allocation.createTyped(renderScript,input.getType());

        // Load up an instance of the specific script that we want to use.
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        scriptIntrinsicBlur.setInput(input);

        // Set the blur radius
        scriptIntrinsicBlur.setRadius(radius);

        // Start the ScriptIntrinisicBlur
        scriptIntrinsicBlur.forEach(output);

        // Copy the output to the blurred bitmap
        output.copyTo(inputBmp);


        renderScript.destroy();
        return inputBmp;
    }
    /**
     * 模糊图片
     * @param bitmap 原图片
     * @param radius 模糊度  0~25
     * @param context
     * @return 模糊后的图片
     */
    public static Bitmap blurBitmap(Bitmap bitmap, float radius, Context context) {
        //Create renderscript
        RenderScript rs = RenderScript.create(context);

        //Create allocation from Bitmap
        Allocation allocation = Allocation.createFromBitmap(rs, bitmap);

        Type t = allocation.getType();

        //Create allocation with the same type
        Allocation blurredAllocation = Allocation.createTyped(rs, t);

        //Create script
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        //Set blur radius (maximum 25.0)
        blurScript.setRadius(radius);
        //Set input for script
        blurScript.setInput(allocation);
        //Call script for output allocation
        blurScript.forEach(blurredAllocation);

        //Copy script result into bitmap
        blurredAllocation.copyTo(bitmap);

        //Destroy everything to free memory
        allocation.destroy();
        blurredAllocation.destroy();
        blurScript.destroy();
        t.destroy();
        rs.destroy();
        return bitmap;
    }
}
