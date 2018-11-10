package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.ViewGroup;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.AddShlef;
import com.wxb.wanshu.bean.ReadHistoryList;
import com.wxb.wanshu.ui.activity.ReadActivity;
import com.wxb.wanshu.view.recycleview.adapter.BaseViewHolder;
import com.wxb.wanshu.view.recycleview.adapter.RecyclerArrayAdapter;

import org.simple.eventbus.EventBus;

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

                holder.setImageUrl(R.id.iv_book, item.novel.cover)
                        .setText(R.id.tv_title, item.novel.name)
                        .setText(R.id.tv_time, "上次阅读：" + item.last_read_time)
                        .setText(R.id.tv_introduce, "已读：" + item.chapter.name)
                        .setVisible(R.id.add_shelf, !item.showCheckBox)
                        .setVisible(R.id.select, item.showCheckBox);
                if (item.isOn_shelf()) {
                    holder.setImageResource(R.id.add_shelf, R.mipmap.has_add_shlef);
                } else {
                    holder.setImageResource(R.id.add_shelf, R.mipmap.add_shlef);
                }

                if (item.isSeleted) {
                    holder.setImageDrawableRes(R.id.select, R.mipmap.select_history);
                } else {
                    holder.setImageDrawableRes(R.id.select, R.mipmap.no_select_history);
                }
//                int complete_status = item.getComplete_status();
//                if (complete_status == 1) {
//                    holder.setText(R.id.tv_introduce, "上次看到第" + item.getChapter_name() + "章 | 已完结共" + item.getLatest_chapter() + "章");
//                } else {
//                    holder.setText(R.id.tv_introduce, "上次看到第" + item.getChapter_name() + "章 | 更新到" + item.getLatest_chapter() + "章");
//                }
            }
        };
    }

    @Override
    public void OnBindViewHolder(BaseViewHolder holder, int position) {
        super.OnBindViewHolder(holder, position);
        ReadHistoryList.DataBean item = getItem(position);

        holder.setOnClickListener(R.id.add_shelf, v -> {
            if (item.isOn_shelf()) {
            } else {
                EventBus.getDefault().post(new AddShlef(item));
            }
        });
    }
}
