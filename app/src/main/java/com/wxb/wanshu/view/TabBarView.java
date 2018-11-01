package com.wxb.wanshu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wxb.wanshu.R;

/**
 * Created by qiming on 2017/12/12.
 */

public class TabBarView extends LinearLayout implements View.OnClickListener {

    private ImageView tab1, tab2, tab3, tab4;
    private ImageView lastView;

    private TabChangeListener listener;
//    private ImageView message_hint_dian;

    public TabBarView(Context context) {
        this(context, null);
    }

    public TabBarView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context);
        setOnClickListener();
        defaultOnClick();
    }

    private void initView(Context context) {
        View layout = LayoutInflater.from(context).inflate(R.layout.ctr_tabbar, this);
//        message_hint_dian = (ImageView) layout.findViewById(R.id.message_hint_dian);

        tab1 = (ImageView) layout.findViewById(R.id.tab1);
        tab2 = (ImageView) layout.findViewById(R.id.tab2);
        tab3 = (ImageView) layout.findViewById(R.id.tab3);
        tab4 = (ImageView) layout.findViewById(R.id.tab4);
//        post = (ImageView) layout.findViewById(R.id.post);
    }

//    public void setMessageHint(boolean isVisibility) {
//        if (isVisibility) {
//            message_hint_dian.setVisibility(VISIBLE);
//        } else {
//            message_hint_dian.setVisibility(GONE);
//
//        }
//    }

    private void setOnClickListener() {
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        tab4.setOnClickListener(this);
    }

    private void defaultOnClick() {
        tab1.callOnClick();
    }

    private void handleOnClick(View view) {

//        if (view == post) {
//            if (null != listener) {
//                listener.onTabChange(0);
//            }
//            return;
//        }

        if (lastView == view) {
            return;
        }

        tab1.setImageResource(R.mipmap.ic_data);
        tab2.setImageResource(R.mipmap.ic_function);
        tab3.setImageResource(R.mipmap.ic_materical);
        tab4.setImageResource(R.mipmap.ic_my);

        int index = 0;
        if (view == tab1) {
            tab1.setImageResource(R.mipmap.ic_data_select);
            lastView = tab1;
            index = 1;
        } else if (view == tab2) {
            tab2.setImageResource(R.mipmap.ic_function_select);
            lastView = tab2;
            index = 2;
        } else if (view == tab3) {
            tab3.setImageResource(R.mipmap.ic_materical_select);
            lastView = tab3;
            index = 3;
        } else if (view == tab4) {
            tab4.setImageResource(R.mipmap.ic_my_select);
            lastView = tab4;
//            setMessageHint(false);
            index = 4;
        } else {
            return;
        }

        if (null != listener) {
            listener.onTabChange(index);
        }
    }

    public void setCurrentItem(int item) {
        View destView;
        switch (item) {
//            case 0:
//                destView = post;
//                break;
            case 1:
                destView = tab1;
                break;
            case 2:
                destView = tab2;
                break;
            case 3:
                destView = tab3;
                break;
            case 4:
                destView = tab4;
                break;
            default:
                return;
        }

        handleOnClick(destView);
    }


    @Override
    public void onClick(View view) {
        handleOnClick(view);
    }

    public void setOnTabChangeListener(TabChangeListener listener) {
        this.listener = listener;
    }

    public interface TabChangeListener {
        void onTabChange(int index);
    }
}
