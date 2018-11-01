package com.wxb.wanshu.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wxb.wanshu.R;

/**
 * Created by qiming on 2017/12/11.
 */

public class SignInDialog extends Dialog {
    Context context;
    View registerView;
    private RelativeLayout rlContent;
    private ImageView iv_close;
    private TextView tvTitle;
    private Button btnSure;

    public SignInDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 这句代码换掉dialog默认背景，否则dialog的边缘发虚透明而且很宽
        // 总之达不到想要的效果
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        registerView = View.inflate(this.context, R.layout.day_enter_dialog, null);
        setContentView(registerView);
        // 这句话起全屏的作用
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        initView();
        initData();
    }

    private void initData() {
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.dismiss();
        return super.onTouchEvent(event);
    }

    private void initView() {
        rlContent = (RelativeLayout) findViewById(R.id.rl_content);
        iv_close = (ImageView) findViewById(R.id.iv_close);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        btnSure = (Button) findViewById(R.id.btn_sure);
    }
}
