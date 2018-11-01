package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.AmountRecordList;
import com.wxb.wanshu.bean.NotificationList;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.ui.adapter.base.EasyRVAdapter;
import com.wxb.wanshu.ui.adapter.base.EasyRVHolder;
import com.wxb.wanshu.utils.FormatUtils;
import com.wxb.wanshu.view.recycleview.adapter.BaseViewHolder;
import com.wxb.wanshu.view.recycleview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by qiming on 2017/11/30.
 */

public class AmountTypeAdapter extends EasyRVAdapter<AmountRecordList.TypeNameBean> {

    private OnRvItemClickListener itemClickListener;
    public AmountTypeAdapter(Context context, List<AmountRecordList.TypeNameBean> list, OnRvItemClickListener listener) {
        super(context, list, R.layout.item_amount_type);
        itemClickListener = listener;
    }

    @Override
    protected void onBindData(EasyRVHolder holder, int position, AmountRecordList.TypeNameBean item) {
        holder.setText(R.id.tv, item.getValue()).setVisible(R.id.select, item.isSelected);
        if(item.isSelected){
            holder.setTextColorRes(R.id.tv, R.color.gobal_color);
        }else {
            holder.setTextColorRes(R.id.tv, R.color.text_color_1);
        }
        holder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(view,position,item);
            }
        });
    }
}
