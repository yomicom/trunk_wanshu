package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.BookList;
import com.wxb.wanshu.bean.NovelCategory;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.ui.adapter.base.EasyLVAdapter;
import com.wxb.wanshu.ui.adapter.base.EasyLVHolder;
import com.wxb.wanshu.ui.adapter.base.EasyRVAdapter;
import com.wxb.wanshu.ui.adapter.base.EasyRVHolder;
import com.wxb.wanshu.view.recycleview.adapter.BaseViewHolder;
import com.wxb.wanshu.view.recycleview.adapter.RecyclerArrayAdapter;

import java.util.List;

import static android.R.id.list;

/**
 * Created by qiming on 2017/12/14.
 */

public class BookClassifyAdapter extends RecyclerArrayAdapter<NovelCategory.DataBean> {
    public BookClassifyAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<NovelCategory.DataBean>(parent, R.layout.item_book_classify) {
            @Override
            public void setData(NovelCategory.DataBean item) {
                holder.setImageUrl(R.id.cover, item.getCover(), R.drawable.defalt_book_cover).setText(R.id.classify, item.getName());
            }
        };
    }
}
