package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.View;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.bean.RewardType;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.ui.adapter.base.EasyRVAdapter;
import com.wxb.wanshu.ui.adapter.base.EasyRVHolder;

import java.util.List;

/**
 * Created by qiming on 2017/11/30.
 */

public class GiftTypeAdapter extends EasyRVAdapter<RewardType.DataBean> {
    private OnRvItemClickListener itemClickListener;

    public int getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(int selectedPos) {
        this.selectedPos = selectedPos;
    }

    int selectedPos = 0;

    public GiftTypeAdapter(Context context, List<RewardType.DataBean> list, OnRvItemClickListener listener) {
        super(context, list, R.layout.item_send_left);
        this.itemClickListener = listener;
    }

    @Override
    protected void onBindData(EasyRVHolder viewHolder, int position, RewardType.DataBean item) {
        viewHolder.setImageUrl(R.id.iv_gift, item.getIcon())
                .setText(R.id.tv_gift_1, item.getAmount() + "书币");

        if(position == selectedPos){
            viewHolder.setBackgroundColorRes(R.id.ll_content,R.color.common_bg);
        }else {
            viewHolder.setBackgroundColorRes(R.id.ll_content,R.color.white);
        }

        viewHolder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPos = position;
                notifyDataSetChanged();
                itemClickListener.onItemClick(view, position, item);
            }
        });
    }
}
