package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.View;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.BookRewardData;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.ui.adapter.base.EasyRVAdapter;
import com.wxb.wanshu.ui.adapter.base.EasyRVHolder;
import com.wxb.wanshu.utils.FormatUtils;

import java.util.List;

/**
 * Created by qiming on 2017/11/30.
 */

public class BookRewardAdapter extends EasyRVAdapter<BookRewardData.DataBean> {
    private OnRvItemClickListener itemClickListener;

    public BookRewardAdapter(Context context, List<BookRewardData.DataBean> list, OnRvItemClickListener listener) {
        super(context, list, R.layout.item_reward);
        this.itemClickListener = listener;
    }

    @Override
    protected void onBindData(EasyRVHolder viewHolder, int position, BookRewardData.DataBean item) {
        viewHolder.setCircleImageUrl(R.id.iv_user, item.getAvatar(), 0)
                .setText(R.id.tv_user, item.getNick_name())
                .setText(R.id.tv_gift, "×" + item.getGift_num() + "个")
                .setImageUrl(R.id.iv_gift,item.getIcon(),R.mipmap.defalt_book_cover)
                .setText(R.id.tv_time, FormatUtils.formatDataTime(item.getCtime(), "yyyy-MM-dd HH:mm"));


        viewHolder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(view, position, item);
            }
        });
    }
}
