package com.wxb.wanshu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wxb.wanshu.R;

import java.io.IOException;

/**
 * Created by qiming on 2017/12/11.
 */

public class EmptyView extends RelativeLayout {

    private TextView toastText;
    private TextView enterText;
    private ImageView wnImage;

    public EmptyView(Context context) {
        this(context, null);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.wn_toast, this);

        toastText = (TextView) view.findViewById(R.id.tv_toast);
        enterText = (TextView) view.findViewById(R.id.tv_take);
        wnImage = (ImageView) view.findViewById(R.id.iv_wn);
    }

    public void setWnImage(int resId) {
        wnImage.setImageResource(resId);
    }

    public void setWnToast(int resId) {
        toastText.setText(resId);
    }

    public void setEnterText(int resId) {
        enterText.setText(resId);
    }

    public void setWnToast(String toast) {
        toastText.setText(toast);
    }

    public void setEnterText(String enter) {
        enterText.setText(enter);
    }

    public void setEnterListener(Callback callback) {
        enterText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.exec();
            }
        });
    }

    public static interface Callback {
        void exec();
    }
}
