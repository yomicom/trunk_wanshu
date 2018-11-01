package com.wxb.wanshu.common;

import android.view.View;

/**
 * Created by qiming on 2017/11/30.
 */

public interface OnRvItemClickListener<T> {

    void onItemClick(View view, int position, T data);

}