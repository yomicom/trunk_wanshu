package com.wxb.wanshu.ui.adapter.easyadpater;

import android.content.Context;
import android.view.View;
import android.widget.ListAdapter;

import com.wxb.wanshu.R;
import com.wxb.wanshu.bean.NovelCategory;
import com.wxb.wanshu.common.OnRvItemClickListener;
import com.wxb.wanshu.ui.adapter.base.EasyLVAdapter;
import com.wxb.wanshu.ui.adapter.base.EasyLVHolder;
import com.wxb.wanshu.ui.adapter.base.EasyRVAdapter;
import com.wxb.wanshu.ui.adapter.base.EasyRVHolder;

import java.util.List;

import static android.R.id.list;

/**
 * Created by qiming on 2017/12/14.
 */

public class NovelCategoryAdapter extends EasyRVAdapter<NovelCategory.DataBean> {
    private OnRvItemClickListener itemClickListener;

    public NovelCategoryAdapter(Context context, List<NovelCategory.DataBean> list, OnRvItemClickListener itemClickListener) {
        super(context, list, R.layout.item_book_classify);
        this.itemClickListener = itemClickListener;
    }
    public void selectNoAll() {
//        for (NovelCategory.DataBean dataBean : mList) {
//            dataBean.isSelected = false;
//        }
//        notifyDataSetChanged();
    }

    @Override
    protected void onBindData(EasyRVHolder viewHolder, int position, NovelCategory.DataBean item) {

        viewHolder.setImageUrl(R.id.cover,item.getCover()).setText(R.id.classify, item.getName());
        viewHolder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(view, position, item);
            }
        });
    }
}
