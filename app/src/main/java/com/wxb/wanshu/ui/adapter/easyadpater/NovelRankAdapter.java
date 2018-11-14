package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.ViewGroup;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.BookList;
import com.wxb.wanshu.utils.FormatUtils;
import com.wxb.wanshu.view.recycleview.adapter.BaseViewHolder;
import com.wxb.wanshu.view.recycleview.adapter.RecyclerArrayAdapter;

/**
 * Created by qiming on 2017/11/30.
 */

public class NovelRankAdapter extends RecyclerArrayAdapter<BookList.DataBean> {

    public NovelRankAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<BookList.DataBean>(parent, R.layout.item_novel_rank) {
            @Override
            public void setData(BookList.DataBean item) {
                holder.setImageUrl(R.id.iv_article_pic, item.getCover(), R.drawable.defalt_book_cover)
                        .setText(R.id.article_title, item.getName())
                        .setText(R.id.tv_article_intro, item.getDescription())
                        .setText(R.id.word_num, FormatUtils.formatWordCount(item.word_num) )
                        .setText(R.id.author, item.getAuthor());

            }
        };
    }

    @Override
    public void OnBindViewHolder(BaseViewHolder holder, int position) {
        super.OnBindViewHolder(holder, position);

        holder.setVisible(R.id.rank, true);
        switch (position + 1) {
            case 1:
                holder.setBackgroundColorRes(R.id.rank, R.mipmap.top_1);
                break;
            case 2:
                holder.setBackgroundColorRes(R.id.rank, R.mipmap.top_2);
                break;
            case 3:
                holder.setBackgroundColorRes(R.id.rank, R.mipmap.top_3);
                break;
            default:
                holder.setBackgroundColorRes(R.id.rank, R.mipmap.top_4);
                break;
        }
        holder.setText(R.id.rank, (position + 1) + "");

    }
}
