package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.View;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.ui.adapter.base.EasyRVAdapter;
import com.wxb.wanshu.ui.adapter.base.EasyRVHolder;
import com.wxb.wanshu.utils.FormatUtils;

import java.util.List;

/**
 * Created by qiming on 2017/11/30.
 */

public class RVVertical1Adapter extends EasyRVAdapter<HomeData.DataBeanX.DataBean> {
    private OnRvItemClickListener itemClickListener;

    public RVVertical1Adapter(Context context, List<HomeData.DataBeanX.DataBean> list, OnRvItemClickListener listener) {
        super(context, list, R.layout.include_item_big_book);
        this.itemClickListener = listener;
    }

    @Override
    protected void onBindData(EasyRVHolder viewHolder, int position, HomeData.DataBeanX.DataBean item) {
        viewHolder.setImageUrl(R.id.iv_article_pic, item.getCover(), R.mipmap.defalt_book_cover)
                .setText(R.id.article_title, item.getName())
                .setText(R.id.tv_article_intro, item.getDescription())
                .setText(R.id.author, item.getAuthor())
                .setText(R.id.tv_word_nums, FormatUtils.formatWordCount(item.word_num) )
                .setText(R.id.tv_category, item.getCategory_name());

        viewHolder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(view, position, item);
            }
        });
    }
}
