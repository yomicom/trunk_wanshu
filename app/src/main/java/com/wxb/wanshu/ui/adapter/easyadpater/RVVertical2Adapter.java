package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.View;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.HomeData;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.ui.adapter.base.EasyRVAdapter;
import com.wxb.wanshu.ui.adapter.base.EasyRVHolder;

import java.util.List;

/**
 * Created by qiming on 2017/11/30.
 */

public class RVVertical2Adapter extends EasyRVAdapter<HomeData.DataBeanX.DataBean> {
    private OnRvItemClickListener itemClickListener;

    public RVVertical2Adapter(Context context, List<HomeData.DataBeanX.DataBean> list, OnRvItemClickListener listener) {
        super(context, list, R.layout.item_vertical_type);
        this.itemClickListener = listener;
    }

    @Override
    protected void onBindData(EasyRVHolder viewHolder, int position, HomeData.DataBeanX.DataBean item) {
        viewHolder.setImageUrl(R.id.iv_article_pic, item.getCover())
                .setText(R.id.article_title, item.getTitle())
                .setText(R.id.tv_article_intro, item.getSummary())
                .setText(R.id.read_times, item.getRead() + "人在看")
                .setText(R.id.tv_status, item.getComplete_status() == 0 ? "未完结" : "已完结")
                .setText(R.id.tv_category, item.getCategory())
                .setVisible(R.id.iv_free, true);

        viewHolder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(view, position, item);
            }
        });
    }
}
