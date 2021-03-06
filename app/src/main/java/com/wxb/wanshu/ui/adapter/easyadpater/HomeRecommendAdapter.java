package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.View;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.Book;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.ui.adapter.base.EasyRVAdapter;
import com.wxb.wanshu.ui.adapter.base.EasyRVHolder;

import java.util.List;

/**
 * Created by qiming on 2017/11/30.
 */

public class HomeRecommendAdapter extends EasyRVAdapter<Book> {
    private OnRvItemClickListener itemClickListener;

    public HomeRecommendAdapter(Context context, List<Book> list, OnRvItemClickListener listener) {
        super(context, list, R.layout.include_item_easy_book);
        this.itemClickListener = listener;
    }

    @Override
    protected void onBindData(EasyRVHolder viewHolder, int position, Book item) {
        viewHolder.setImageUrl(R.id.iv, item.getCover(),R.drawable.defalt_book_cover)
                .setText(R.id.tv_title, item.getName());
        viewHolder.setOnItemViewClickListener(view -> itemClickListener.onItemClick(view, position, item));
    }
}
