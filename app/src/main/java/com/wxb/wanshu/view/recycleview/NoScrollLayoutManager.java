package com.wxb.wanshu.view.recycleview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by qiming on 2017/11/30.
 */

public class NoScrollLayoutManager extends LinearLayoutManager {

    private boolean isScrollEnabled = true;

    public NoScrollLayoutManager(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}
