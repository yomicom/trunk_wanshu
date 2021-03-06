package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.BookList;
import com.wxb.wanshu.utils.FormatUtils;
import com.wxb.wanshu.view.recycleview.adapter.BaseViewHolder;
import com.wxb.wanshu.view.recycleview.adapter.RecyclerArrayAdapter;

/**
 * Created by qiming on 2017/11/30.
 */

public class SelectBooksAdapter extends RecyclerArrayAdapter<BookList.DataBean> {

    String selected = "";

    public SelectBooksAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<BookList.DataBean>(parent, R.layout.include_item_big_book) {
            @Override
            public void setData(BookList.DataBean item) {
                holder.setImageUrl(R.id.iv_article_pic, item.getCover(), R.drawable.defalt_book_cover)
                        .setText(R.id.article_title, item.getName())
                        .setText(R.id.tv_article_intro, item.getDescription())
                        .setText(R.id.author, item.getAuthor())
                        .setText(R.id.tv_word_nums, FormatUtils.formatWordCount(item.word_num))
                        .setText(R.id.tv_category, item.is_complete ? "已完结" : "连载中");
                if (!"".equals(selected)) {
                    String name = item.getName();
                    name = name.replace(selected, "<font color=\"#276EFF\">" + selected + "</font>");
                    holder.setText(R.id.article_title, name, true);
                }
            }
        };
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
