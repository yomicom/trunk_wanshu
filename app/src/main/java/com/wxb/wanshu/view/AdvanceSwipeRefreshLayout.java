package com.wxb.wanshu.view;


import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

public class AdvanceSwipeRefreshLayout extends SwipeRefreshLayout {

    private OnPreInterceptTouchEventDelegate mOnPreInterceptTouchEventDelegate;

    public AdvanceSwipeRefreshLayout(Context context) {
        super(context);
    }

    public AdvanceSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        ViewConfiguration mConfiguration = ViewConfiguration.get(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean disallowIntercept = false;
        if (mOnPreInterceptTouchEventDelegate != null)
            disallowIntercept = mOnPreInterceptTouchEventDelegate.shouldDisallowInterceptTouchEvent(ev);

        if (disallowIntercept) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public void setOnPreInterceptTouchEventDelegate(OnPreInterceptTouchEventDelegate listener) {
        mOnPreInterceptTouchEventDelegate = listener;
    }

    public interface OnPreInterceptTouchEventDelegate {
        boolean shouldDisallowInterceptTouchEvent(MotionEvent ev);
    }
}
