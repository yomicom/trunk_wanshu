package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.ViewGroup;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.ReadHistoryList;
import com.wxb.wanshu.ui.activity.ReadActivity;
import com.wxb.wanshu.view.recycleview.adapter.BaseViewHolder;
import com.wxb.wanshu.view.recycleview.adapter.RecyclerArrayAdapter;

/**
 * Created by qiming on 2017/11/30.
 */

public class ReadHistoryAdapter extends RecyclerArrayAdapter<ReadHistoryList.DataBean> {

    public ReadHistoryAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<ReadHistoryList.DataBean>(parent, R.layout.include_item_read_history) {
            @Override
            public void setData(ReadHistoryList.DataBean item) {

                holder.setImageUrl(R.id.iv_book, item.getCover())
                        .setText(R.id.tv_title, item.getName())
                        .setText(R.id.tv_time, item.getCategory_name());

//                int complete_status = item.getComplete_status();
//                if (complete_status == 1) {
//                    holder.setText(R.id.tv_introduce, "上次看到第" + item.getChapter_name() + "章 | 已完结共" + item.getLatest_chapter() + "章");
//                } else {
//                    holder.setText(R.id.tv_introduce, "上次看到第" + item.getChapter_name() + "章 | 更新到" + item.getLatest_chapter() + "章");
//                }

                holder.setOnClickListener(R.id.add_shelf,v -> {
                    if (item.isIs_complete()){

                    }else {
                    }
                });
            }
        };
    }
}
