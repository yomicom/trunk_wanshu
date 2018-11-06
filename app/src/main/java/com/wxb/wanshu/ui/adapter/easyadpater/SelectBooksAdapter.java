package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.BookList;
import com.wxb.wanshu.view.recycleview.adapter.BaseViewHolder;
import com.wxb.wanshu.view.recycleview.adapter.RecyclerArrayAdapter;

/**
 * Created by qiming on 2017/11/30.
 */

public class SelectBooksAdapter extends RecyclerArrayAdapter<BookList.DataBean> {

    public SelectBooksAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<BookList.DataBean>(parent,R.layout.include_item_big_book) {
            @Override
            public void setData(BookList.DataBean item) {
                holder.setImageUrl(R.id.iv_article_pic, item.getCover(), R.mipmap.defalt_book_cover)
                        .setText(R.id.article_title, item.getName())
                        .setText(R.id.tv_article_intro, item.getDescription())
                        .setText(R.id.author, item.getAuthor())
                        .setText(R.id.tv_word_nums, item.getWord_num() + "字")
                        .setText(R.id.tv_category, item.getCategory_name());

                holder.setOnItemViewClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        itemClickListener.onItemClick(view, position, item);
                    }
                });
            }
        };
    }
}
