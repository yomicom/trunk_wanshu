package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.ViewGroup;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.BookList;
import com.wxb.wanshu.bean.UserOrder;
import com.wxb.wanshu.view.recycleview.adapter.BaseViewHolder;
import com.wxb.wanshu.view.recycleview.adapter.RecyclerArrayAdapter;

/**
 * Created by qiming on 2017/11/30.
 */

public class OrderListAdapter extends RecyclerArrayAdapter<UserOrder.DataBean> {

    public OrderListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<UserOrder.DataBean>(parent, R.layout.item_order_list) {
            @Override
            public void setData(UserOrder.DataBean item) {

                holder.setText(R.id.tv_title, "充值成功")
                        .setText(R.id.tv_money, "+" + item.getMoney())
                        .setText(R.id.tv_coin, item.getAmount() + "书币")
                        .setText(R.id.tv_time, item.getPay_time());

            }
        };
    }
}
