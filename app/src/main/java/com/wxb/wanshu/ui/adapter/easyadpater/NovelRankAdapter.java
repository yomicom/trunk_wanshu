package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.ViewGroup;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.NovelRank;
import com.wxb.wanshu.view.recycleview.adapter.BaseViewHolder;
import com.wxb.wanshu.view.recycleview.adapter.RecyclerArrayAdapter;

/**
 * Created by qiming on 2017/11/30.
 */

public class NovelRankAdapter extends RecyclerArrayAdapter<NovelRank.DataBean> {

    public NovelRankAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<NovelRank.DataBean>(parent, R.layout.include_item_big_book) {
            @Override
            public void setData(NovelRank.DataBean item) {

                holder.setImageUrl(R.id.iv_article_pic, item.getCover(), R.mipmap.defalt_book_cover)
                        .setText(R.id.article_title, item.getTitle())
                        .setText(R.id.tv_article_intro, item.getSummary())
//                        .setText(R.id.read_times, item.getRead() + "人在看")
//                        .setText(R.id.tv_status, item.getComplete_status() == 0 ? "未完结" : "已完结")
                        .setText(R.id.tv_category, item.getCategory());

            }
        };
    }

    @Override
    public void OnBindViewHolder(BaseViewHolder holder, int position) {
        super.OnBindViewHolder(holder, position);

        holder.setVisible(R.id.iv_sort, true);
        switch (position + 1) {
            case 1:
                holder.setImageResource(R.id.iv_sort, R.mipmap.top_1);
                break;
            case 2:
                holder.setImageResource(R.id.iv_sort, R.mipmap.top_2);
                break;
            case 3:
                holder.setImageResource(R.id.iv_sort, R.mipmap.top_3);
                break;
            case 4:
                holder.setImageResource(R.id.iv_sort, R.mipmap.top_4);
                break;
            case 5:
                holder.setImageResource(R.id.iv_sort, R.mipmap.top_5);
                break;
            case 6:
                holder.setImageResource(R.id.iv_sort, R.mipmap.top_6);
                break;
            default:
                holder.setVisible(R.id.iv_sort, false);
                break;
        }
    }
}
