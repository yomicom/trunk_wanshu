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

public class HomeHotAdapter extends EasyRVAdapter<HomeData.DataBeanX.DataBean> {
    private OnRvItemClickListener itemClickListener;

    public HomeHotAdapter(Context context, List<HomeData.DataBeanX.DataBean> list, OnRvItemClickListener listener) {
        super(context, list, R.layout.item_home_hot);
        this.itemClickListener = listener;
    }

    @Override
    protected void onBindData(EasyRVHolder viewHolder, int position, HomeData.DataBeanX.DataBean item) {
        viewHolder.setImageUrl(R.id.cover, item.getCover())
                .setText(R.id.title, item.getName())
                .setText(R.id.word_num, FormatUtils.formatWordCount(item.word_num));

        viewHolder.setOnItemViewClickListener(view -> itemClickListener.onItemClick(view, position, item));
    }
}
