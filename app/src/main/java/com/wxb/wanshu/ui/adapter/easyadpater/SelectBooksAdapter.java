package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.BookList;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.ui.adapter.base.EasyRVAdapter;
import com.wxb.wanshu.ui.adapter.base.EasyRVHolder;
import com.wxb.wanshu.view.recycleview.adapter.BaseViewHolder;
import com.wxb.wanshu.view.recycleview.adapter.RecyclerArrayAdapter;

import java.util.List;

import static android.R.id.list;

/**
 * Created by qiming on 2017/11/30.
 */

public class SelectBooksAdapter extends RecyclerArrayAdapter<BookList.DataBean> {

    public SelectBooksAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<BookList.DataBean>(parent,R.layout.item_select_book) {
            @Override
            public void setData(BookList.DataBean item) {

                holder.setImageUrl(R.id.iv_article_pic, item.getCover(),R.mipmap.defalt_book_cover)
                        .setText(R.id.article_title, item.getTitle())
                        .setText(R.id.tv_article_intro, item.getSummary())
                        .setText(R.id.read_times, item.getRead() + "人在看")
                        .setText(R.id.tv_status, item.getComplete_status() == 0 ? "未完结" : "已完结")
                        .setText(R.id.tv_category, item.getCategory());

            }
        };
    }
}
