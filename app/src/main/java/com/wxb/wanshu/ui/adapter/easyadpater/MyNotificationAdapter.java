package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.ViewGroup;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.NotificationList;
import com.wxb.wanshu.bean.UserOrder;
import com.wxb.wanshu.utils.FormatUtils;
import com.wxb.wanshu.view.recycleview.adapter.BaseViewHolder;
import com.wxb.wanshu.view.recycleview.adapter.RecyclerArrayAdapter;

/**
 * Created by qiming on 2017/11/30.
 */

public class MyNotificationAdapter extends RecyclerArrayAdapter<NotificationList.DataBean> {

    public MyNotificationAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<NotificationList.DataBean>(parent, R.layout.item_notification) {
            @Override
            public void setData(NotificationList.DataBean item) {

                holder.setText(R.id.title, item.getTitle())
                        .setText(R.id.content, item.getContent())
                        .setText(R.id.time, FormatUtils.formatDataTime(Long.parseLong(item.getCtime()), "yyyy-MM-dd HH:mm"));

            }
        };
    }
}
