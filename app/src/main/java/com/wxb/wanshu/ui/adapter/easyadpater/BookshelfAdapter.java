package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.ViewGroup;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.BookselfList;
import com.wxb.wanshu.bean.UserOrder;
import com.wxb.wanshu.view.recycleview.adapter.BaseViewHolder;
import com.wxb.wanshu.view.recycleview.adapter.RecyclerArrayAdapter;

/**
 * Created by qiming on 2017/11/30.
 */

public class BookshelfAdapter extends RecyclerArrayAdapter<BookselfList.DataBean> {

    public BookshelfAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<BookselfList.DataBean>(parent, R.layout.item_bookself_list) {
            @Override
            public void setData(BookselfList.DataBean item) {
                super.setData(item);
                holder.setText(R.id.tv_title, item.getName())
                        .setImageUrl(R.id.iv_book_cover, item.getCover(), R.mipmap.defalt_book_cover)
//                        .setVisible(R.id.iv_book_update, item.getIs_updated() == 1)
                        .setVisible(R.id.iv_book_choose, item.showCheckBox);

                if (item.isSeleted) {
                    holder.setImageDrawableRes(R.id.iv_book_choose, R.mipmap.ic_choose_book);
                } else {
                    holder.setImageDrawableRes(R.id.iv_book_choose, R.mipmap.ic_nochoose_book);
                }
            }
        };
    }
}
