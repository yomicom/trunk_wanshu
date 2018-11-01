package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.View;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.BookDetails;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.ui.adapter.base.EasyRVAdapter;
import com.wxb.wanshu.ui.adapter.base.EasyRVHolder;

import java.util.List;

/**
 * Created by qiming on 2017/11/30.
 */

public class RVRecommandAdapter extends EasyRVAdapter<BookDetails.DataBean.RecommendBean> {
    private OnRvItemClickListener itemClickListener;

    public RVRecommandAdapter(Context context, List<BookDetails.DataBean.RecommendBean> list, OnRvItemClickListener listener) {
        super(context, list, R.layout.include_item_easy_book);
        this.itemClickListener = listener;
    }

    @Override
    protected void onBindData(EasyRVHolder viewHolder, int position, BookDetails.DataBean.RecommendBean item) {
        viewHolder.setImageUrl(R.id.iv, item.getCover())
                .setText(R.id.tv_title, item.getTitle());
        viewHolder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(view, position, item);
            }
        });
    }
}
