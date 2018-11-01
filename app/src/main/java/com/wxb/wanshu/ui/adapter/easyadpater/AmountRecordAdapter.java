package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.ViewGroup;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.AmountRecordList;
import com.wxb.wanshu.bean.UserOrder;
import com.wxb.wanshu.utils.FormatUtils;
import com.wxb.wanshu.view.recycleview.adapter.BaseViewHolder;
import com.wxb.wanshu.view.recycleview.adapter.RecyclerArrayAdapter;

/**
 * Created by qiming on 2017/11/30.
 */

public class AmountRecordAdapter extends RecyclerArrayAdapter<AmountRecordList.DataBean> {

    public AmountRecordAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<AmountRecordList.DataBean>(parent, R.layout.item_order_list) {
            @Override
            public void setData(AmountRecordList.DataBean item) {

                holder.setText(R.id.tv_title, item.getType_name())
                        .setText(R.id.tv_money, "+" + item.getAmount())
                        .setText(R.id.tv_time, FormatUtils.formatDataTime(item.getCtime(), "yyyy-MM-dd HH:mm"));

            }
        };
    }
}
